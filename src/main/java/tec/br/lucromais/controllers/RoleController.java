package tec.br.lucromais.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import tec.br.lucromais.models.Role;
import tec.br.lucromais.repositories.RoleRepository;
import tec.br.lucromais.services.RoleService;

/**
 * Classe controller de actions de autorizações de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Controller
@RequestMapping("/grupos")
public class RoleController {
	
	/**
	 * Injeção de dependência com a interface de repositório de autorizações de usuários
	**/
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * Action get da página de pesquisa de autorizações de usuários
	**/
	@GetMapping
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("roles/search");
		mv.addObject("roles", roleRepository.findAll());
		mv.addObject("menu", "system");
		return mv;
	}
	
	/**
	 * Action get da página de formulário de cadastro de autorizações de usuários
	**/
	@GetMapping("/novo")
	public ModelAndView create(Role role) {
		ModelAndView mv = new ModelAndView("roles/create");
		mv.addObject("menu", "system");
		return mv;
	}
	
	/**
	 * Action post de gravação de autorizações de usuários
	**/
	@PostMapping("/novo")
	public ModelAndView create(@Valid Role role, BindingResult result) {
		if(result.hasErrors())
			return create(role);
		roleService.persist(role);
		return new ModelAndView("redirect:/grupos");
	}
	
	/**
	 * Action get da página de formulário de edição de autorizações de usuários
	**/
	@GetMapping("/{id}/editar")
	public ModelAndView update(@PathVariable("id") Long id, Role role, boolean isInvalid) {
		ModelAndView mv = new ModelAndView("roles/update");
		if(!isInvalid)
			role = roleService.getById(id);
		mv.addObject("role", role);
		mv.addObject("menu", "system");
		return mv;
	}
	
	/**
	 * Action post de alteração de autorizações de usuários
	**/
	@PostMapping("/editar")
	public ModelAndView update(@Valid Role role, BindingResult result) {
		if(result.hasErrors())
			return update(role.getId(), role, true);
		Role persistedRole = roleService.getById(role.getId());
		BeanUtils.copyProperties(role, persistedRole, "id", "createdAt");
		roleService.persist(persistedRole);
		return new ModelAndView("redirect:/grupos");
	}
	
	/**
	 * Action get de exclusão de autorizações de usuários
	**/
	@GetMapping("/{id}/excluir")
	public ModelAndView delete(@PathVariable("id") Long id) {
		Role role = roleService.getById(id);
		roleService.remove(role);
		return new ModelAndView("redirect:/grupos");
	}
}