package tec.br.lucromais.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

import tec.br.lucromais.exceptions.EntityWithInvalidFieldException;
import tec.br.lucromais.models.Role;
import tec.br.lucromais.models.User;
import tec.br.lucromais.repositories.UserRepository;
import tec.br.lucromais.services.UserService;

/**
 * Classe controller de usuário
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Controller
@RequestMapping("/users")
public class UserController {
	
	/**
	 * Injeção de dependência com a interface repository de usuário
	**/
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Injeção de dependência com a classe service de usuário
	**/
	@Autowired
	private UserService userService;
	
	/**
	 * Action get de listagem e pesquisa de usuários
	**/
	@GetMapping
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("users/search");
		mv.addObject("users", userRepository.findAll());
		mv.addObject("menu", "administration");
		return mv;
	}
	
	/**
	 * Action get de cadastro de um novo usuário
	**/
	@GetMapping("/create")
	public ModelAndView create(User user) {
		ModelAndView mv = new ModelAndView("users/create");
		mv.addObject("roles", Role.values());
		mv.addObject("menu", "administration");
		return mv;
	}
	
	/**
	 * Action post de gravação de um novo usuário
	**/
	@PostMapping("/create")
	public ModelAndView create(@Valid User user, BindingResult result) {
		if(result.hasErrors())
			return create(user);
		try {
			userService.persist(user);
		} catch (EntityWithInvalidFieldException e) {
			result.rejectValue(e.getField(), e.getMessage(), e.getMessage());
			return create(user);
		}
		return new ModelAndView("redirect:/users");
	}
	
	/**
	 * Action get de edição de um usuário existente
	**/
	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable("id") Long id, User user, boolean isInvalid) {
		ModelAndView mv = new ModelAndView("users/update");
		if(!isInvalid)
			user = userService.findOrFail(id);
		mv.addObject("user", user);
		mv.addObject("roles", Role.values());
		mv.addObject("menu", "administration");
		return mv;
	}
	
	/**
	 * Action post de edição de usuários existentes
	**/
	@PostMapping("/update")
	public ModelAndView update(@Valid User user, BindingResult result) {
		if(result.hasErrors())
			return update(user.getId(), user, true);
		User persistedUser = userService.findOrFail(user.getId());
		user.setId(persistedUser.getId());
		user.setCpf(persistedUser.getCpf());
		user.setCreatedAt(persistedUser.getCreatedAt());
		user.setPassword(persistedUser.getPassword());
		try {
			userService.persist(user);
		} catch (EntityWithInvalidFieldException e) {
			result.rejectValue(e.getField(), e.getMessage(), e.getMessage());
			return update(user.getId(), user, true);
		}
		return new ModelAndView("redirect:/users");
	}
}