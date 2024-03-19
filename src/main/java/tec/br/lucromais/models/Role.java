package tec.br.lucromais.models;

import lombok.Getter;

/**
 * Enumeração model de perfil
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
public enum Role {
	SYS("SYS"),
	ADM("ADM"),
	CLI("CLI");
	
	/**
	 * Nome (autorização) do perfil
	**/
	@Getter
	private String authority;
	
	/**
	 * Construtor da enumeração de perfil
	**/
	private Role(String authority) {
		this.authority = authority;
	}
}