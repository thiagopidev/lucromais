package tec.br.lucromais.models;

import lombok.Getter;

/**
 * Enumeração model de tipo de cliente
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Getter
public enum ClientType {
	
	FISICA("Física", "CPF", "999.999.999-99"),
	JURIDICA("Jurídica", "CNPJ", "99.999.999/9999-99");
	
	/**
	 * Descrição do tipo de cliente
	**/
	private String description;
	
	/**
	 * Documento do tipo de cliente
	**/
	private String document;
	
	/**
	 * Máscara atrelada ao tipo de cliente
	**/
	private String mask;
	
	private ClientType(String description, String document, String mask) {
		this.description = description;
		this.document = document;
		this.mask = mask;
	}
}