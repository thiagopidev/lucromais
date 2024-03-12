package tec.br.lucromais.exceptions;

import lombok.Getter;

/**
 * Classe de exceção de entidade com valor de atributo (campo) inválido
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
public class EntityWithInvalidFieldException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Nome do atributo (campo)
	**/
	@Getter
	private String field;
	
	/**
	 * Construtor da classe de exceção de entidade com valor de atributo (campo) inválido
	**/
	public EntityWithInvalidFieldException(String message, String field) {
		super(message);
		this.field = field;
	}
}