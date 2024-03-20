package tec.br.lucromais.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tec.br.lucromais.exceptions.EntityWithInvalidFieldException;
import tec.br.lucromais.models.User;
import tec.br.lucromais.security.Account;
import tec.br.lucromais.services.ResetPasswordService;
import tec.br.lucromais.services.UserService;

/**
 * Classe controller de autenticação e autorização
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Controller
public class AuthController {
	
	/**
	 * Injeção de dependência com a classe service de usuário
	**/
	@Autowired
	private UserService userService;
	
	/**
	 * Injeção de dependência com a classe service de redefinição de senha do usuário logado
	**/
	@Autowired
	private ResetPasswordService resetPasswordService;
	
	/**
	 * Action get de login
	 * @return path da view de login
	**/
	@RequestMapping("/login")
	public String login() {
		return "auths/login";
	}
	
	/**
	 * Action get de redefinição de senha do usuário logado.
	 * @param objeto User que será preenchido na view de mudança de senha do usuário logado.
	 * @return objeto ModelAndView cuja viewName é a path da view de mudança de senha do usuário logado. Esse objeto
	 * também carrega o atributo menu, que serve para que o fragmento thymeleaf nav identifique que o menu user está ativo.
	**/
	@RequestMapping("/mypass")
	public ModelAndView changePassword(User user) {
		ModelAndView mv = new ModelAndView("auths/password");
		mv.addObject("menu", "user");
		return mv;
	}
	
	/**
	 * Action post de redefinição de senha do usuário logado.
	 * @param objeto User preenchido na view de mudança de senha do usuário logado.
	 * @param objeto BindingResult para captura de erros de validação que serão apresentados na view de mudança de senha do usuário logado.
	 * @param objeto RedirectAttributes para captura da mensagem de sucesso que será apresentada na view de mudança de senha do usuário logado.
	 * @return objeto ModelAndView de redirecionamento para a rota /mypass, em caso de sucesso ou falha na mudança de senha do usuário logado.
	**/
	@PostMapping("/mypass")
	public ModelAndView changePassword(User user, BindingResult result, RedirectAttributes attributes) {
		user = captureUserForChangePassword(user);
		try {
			resetPasswordService.save(user);
			attributes.addFlashAttribute("success", "Senha redefinida com sucesso");
		} catch (EntityWithInvalidFieldException e) {
			result.rejectValue(e.getField(), e.getMessage(), e.getMessage());
			return changePassword(user);
		}
		return new ModelAndView("redirect:/mypass");
	}
	
	/**
	 * Encapsulamento que garante que alguns valores de atributos do usuário logado não sofram alteração com a mudança de senha.
	 * Os atributos do usuário logado que não são alterados na mudança de senha são: id, cpf, name, email, role, enabled e createdAt.
	 * @param objeto User advindo da action post de redefinição de senha do usuário logado.
	 * @return objeto User atualizado, contendo valores de atributo imutáveis e advindo do banco de dados.
	**/
	private User captureUserForChangePassword(User user) {
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = account.getUser().getId();
		User persistedUser = userService.findOrFail(userId);
		user.setId(persistedUser.getId());
		user.setCpf(persistedUser.getCpf());
		user.setName(persistedUser.getName());
		user.setEmail(persistedUser.getEmail());
		user.setRole(persistedUser.getRole());
		user.setEnabled(persistedUser.isEnabled());
		user.setCreatedAt(persistedUser.getCreatedAt());
		return user;
	}
}