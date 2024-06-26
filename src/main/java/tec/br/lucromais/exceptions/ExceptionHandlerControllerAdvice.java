package tec.br.lucromais.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe para redirecionar exceções para páginas de erro 
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Slf4j
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
		return "errors/404";
	}
	
	/**
	 * handler para captura de EntityInUseException
	 * @return caminho da página de erro 404
	**/
	@ExceptionHandler(EntityInUseException.class)
	public String handleEntityInUse(EntityInUseException e) {
		return "errors/409";
	}
	
	/**
	 * handler para captura de IllegalArgumentException
	 * @return caminho da página de erro 400
	**/
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(IllegalArgumentException e) {
		return "errors/400";
	}
	
	/**
	 * handler para captura de NoResourceFoundException
	 * @return caminho da página de erro 404
	**/
	@ExceptionHandler(NoResourceFoundException.class)
	public String handleNoResourceFoundException(NoResourceFoundException e) {
		return "errors/404";
	}
	
	/**
	 * handler para captura de NoHandlerFoundException
	 * @return caminho da página de erro 404
	**/
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleNoHandleFoundException(NoHandlerFoundException e) {
		return "errors/404";
	}
	
	/**
	 * handler para captura de exceções não tratadas
	 * @return caminho da página de erro 500
	**/
	@ExceptionHandler(Exception.class)
	public String handleUncaught(Exception e) {
		log.error(e.getMessage(), e);
		return "errors/500";
	}
}