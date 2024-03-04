package tec.br.lucromais.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para redirecionar exceções para páginas de erro 
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	/**
	 * handler para captura de EntityNotPersistedException
	 * @return caminho da página de erro 400
	**/
	@ExceptionHandler(EntityNotPersistedException.class)
	public String handleEntityNotPersistedException(EntityNotPersistedException e) {
		return "errors/400";
	}
	
	/**
	 * handler para captura de EntityNotFoundException
	 * @return caminho da página de erro 404
	**/
	@ExceptionHandler(EntityNotFoundException.class)
	public String handleEntityNotFoundException(EntityNotFoundException e) {
		return "error/404";
	}
	
	/**
	 * handler para captura de IllegalArgumentException
	 * @return caminho da página de erro 400
	**/
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException e) {
		return "error/400";
	}
}