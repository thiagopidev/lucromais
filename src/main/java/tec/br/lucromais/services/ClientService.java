package tec.br.lucromais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tec.br.lucromais.exceptions.EntityInUseException;
import tec.br.lucromais.exceptions.EntityNotFoundException;
import tec.br.lucromais.exceptions.EntityNotPersistedException;
import tec.br.lucromais.models.Client;
import tec.br.lucromais.repositories.ClientRepository;

/**
 * Classe service de cliente
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Service
public class ClientService {
	
	/**
	 * Injeção de dependência com a interface repository de cliente
	**/
	@Autowired
	private ClientRepository clientRepository;
	
	/**
	 * Persiste um cliente em banco de dados
	**/
	@Transactional
	public void persist(Client client) {
		if(clientRepository.save(client) == null)
			throw new EntityNotPersistedException("Erro ao salvar cliente");
	}
	
	/**
	 * Captura um cliente pelo seu id
	 * @return cliente encontrado
	**/
	public Client findOrFail(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
	}
	
	/**
	 * Remove um cliente persistido em banco de dados, desde que não haja violação de integridade
	**/
	@Transactional
	public void remove(Client client) {
		try {
			clientRepository.deleteById(client.getId());
			clientRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Cliente em uso");
		}
	}
}