package tec.br.lucromais.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Classe controller de redirecionamento para páginas de erro
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Controller
public class DeviationController implements ErrorController {
	
	/**
	 * Action de redirecionamento de páginas de erro baseada no status http
	 * @return caminho da página de erro
	**/
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.BAD_REQUEST.value())
				return "errors/400";
			if(statusCode == HttpStatus.FORBIDDEN.value())
				return "errors/403";
			if(statusCode == HttpStatus.NOT_FOUND.value())
				return "errors/404";
			if(statusCode == HttpStatus.CONFLICT.value())
				return "errors/409";
			if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
				return "errors/500";
		}
		return "errors/500";
	}
}