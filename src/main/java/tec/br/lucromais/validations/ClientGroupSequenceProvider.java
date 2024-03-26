package tec.br.lucromais.validations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import tec.br.lucromais.models.Client;

/**
 * Classe que provê a sequência de validações aplicada à classe model de cliente
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
public class ClientGroupSequenceProvider implements DefaultGroupSequenceProvider<Client> {

	/**
	 * Aplicação das validações à classe model de cliente
	 * @return lista de validações aplicadas à classe model de cliente, advindas de annotations sem grupo e com grupo 
	**/
	@Override
	public List<Class<?>> getValidationGroups(Client client) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Client.class);	
		if(isSelectedClientType(client)) {
			groups.add(client.getType().getGroup());
		}
		return groups;
	}
	
	/**
	 * Verifica se o tipo de cliente está selecionado
	 * @return true para tipo de cliente selecionado e false para tipo de cliente não selecionado
	**/
	private boolean isSelectedClientType(Client client) {
		return client != null && client.getType() != null;
	}
}