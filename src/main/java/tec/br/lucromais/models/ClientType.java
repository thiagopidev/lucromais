package tec.br.lucromais.models;

import lombok.Getter;
import tec.br.lucromais.groups.CnpjGroup;
import tec.br.lucromais.groups.CpfGroup;

/**
 * Enumeração model de tipo de cliente
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Getter
public enum ClientType {
	
	FISICA("Física", "CPF", "999.999.999-99", CpfGroup.class),
	JURIDICA("Jurídica", "CNPJ", "99.999.999/9999-99", CnpjGroup.class);
	
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
	
	/**
	 * Grupo de validação do tipo de cliente
	**/
	private Class<?> group;
	
	private ClientType(String description, String document, String mask, Class<?> group) {
		this.description = description;
		this.document = document;
		this.mask = mask;
		this.group = group;
	}
}