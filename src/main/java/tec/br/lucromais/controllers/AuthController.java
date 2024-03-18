package tec.br.lucromais.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tec.br.lucromais.exceptions.EntityWithInvalidFieldException;
import tec.br.lucromais.models.Role;
import tec.br.lucromais.models.User;
import tec.br.lucromais.security.Account;
import tec.br.lucromais.services.ResetPasswordService;
import tec.br.lucromais.services.UserService;

/**
 * Classe controller de actions de authenticação
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResetPasswordService resetPasswordService;
	
	/**
	 * Action get do formulário de login
	 * @return página de login
	**/
	@RequestMapping("/login")
	public String login() {
		return "auths/login";
	}
	
	/**
	 * Action get do formulário de redefinição de senha do usuário logado
	**/
	@RequestMapping("/senhas")
	public ModelAndView changePassword(User user) {
		ModelAndView mv = new ModelAndView("auths/password");
		mv.addObject("menu", "user");
		return mv;
	}
	
	/**
	 * Action post de redefinição de senha do usuário logado
	**/
	@PostMapping("/senhas")
	public ModelAndView changePassword(User user, BindingResult result, RedirectAttributes attributes) {
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = account.getUser().getId();
		User persistedUser = userService.findOrFail(userId);
		user.setId(persistedUser.getId());
		user.setUsername(persistedUser.getUsername());	
		@SuppressWarnings("unchecked")
		List<Role> authorities = (List<Role>) persistedUser.getAuthorities();
		user.setAuthorities(authorities);
		user.setEnabled(persistedUser.isEnabled());
		user.setCreatedAt(persistedUser.getCreatedAt());
		user.setUsualname(persistedUser.getUsualname());
		user.setEmail(persistedUser.getEmail());
		try {
			resetPasswordService.save(user);
			attributes.addFlashAttribute("success", "Senha resetada com sucesso");
		} catch (EntityWithInvalidFieldException e) {
			result.rejectValue(e.getField(), e.getMessage(), e.getMessage());
			return changePassword(user);
		}
		return new ModelAndView("redirect:/senhas");
	}
}