package tec.br.lucromais.exceptions;

/**
 * Classe de exceção de entidade não encontrada
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor da classe de exceção de entidade não encontrada
	**/
	public EntityNotFoundException(String message) {
		super(message);
	}
}