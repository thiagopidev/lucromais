package tec.br.lucromais.exceptions;

/**
 * Classe de exceção de entidade não persistida
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
public class EntityNotPersistedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor da classe de exceção de entidade não persistida
	**/
	public EntityNotPersistedException(String message) {
		super(message);
	}
}