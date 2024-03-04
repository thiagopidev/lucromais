package tec.br.lucromais.exceptions;

/**
 * Classe de exceção de entidade em uso
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
public class EntityInUseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor da classe de exceção de entidade em uso
	**/
	public EntityInUseException(String message) {
		super(message);
	}
}