package tec.br.lucromais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tec.br.lucromais.exceptions.EntityNotPersistedException;
import tec.br.lucromais.exceptions.EntityInUseException;
import tec.br.lucromais.exceptions.EntityNotFoundException;
import tec.br.lucromais.models.Role;
import tec.br.lucromais.repositories.RoleRepository;

/**
 * Classe de serviço de definição de autorizações de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Service
public class RoleService {
	
	/**
	 * Injeção de dependência com a interface de repositório de autorizações de usuários
	**/
	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * Persistência de autorizações de usuários
	**/
	@Transactional
	public void persist(Role role) {
		if(roleRepository.save(role) == null)
			throw new EntityNotPersistedException("Erro ao salvar autorização de usuário");
	}
	
	/**
	 * Captura uma autorização de usuário pelo identificador dessa autorização.
	 * @return autorização encontrada
	**/
	public Role findOrFail(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Grupo de usuário não encontrado"));
	}
	
	/**
	 * Remoção de uma autorização de usuário persistida, desde que não haja violação de integridade
	**/
	@Transactional
	public void remove(Role role) {
		try {
			roleRepository.deleteById(role.getId());
			roleRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Autorização de usuário em uso");
		}
	}
}