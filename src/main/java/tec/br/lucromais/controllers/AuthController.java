package tec.br.lucromais.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe controller de actions de authenticação
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Controller
public class AuthController {
	
	/**
	 * Action get do formulário de login
	 * @return página de login
	**/
	@RequestMapping("/login")
	public String login() {
		return "auths/login";
	}
}