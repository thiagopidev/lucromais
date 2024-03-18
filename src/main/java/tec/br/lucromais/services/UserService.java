package tec.br.lucromais.services;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tec.br.lucromais.exceptions.EntityInUseException;
import tec.br.lucromais.exceptions.EntityNotFoundException;
import tec.br.lucromais.exceptions.EntityNotPersistedException;
import tec.br.lucromais.exceptions.EntityWithInvalidFieldException;
import tec.br.lucromais.models.User;
import tec.br.lucromais.repositories.UserRepository;

/**
 * Classe de serviço de definição de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Service
public class UserService {
	
	/**
	 * Injeção de dependência com a interface de repositório de usuários
	**/
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Persistência de usuários
	**/
	@Transactional
	public void persist(User user) throws EntityWithInvalidFieldException {
		if(isDuplicateUserCpf(user))
			throw new EntityWithInvalidFieldException("CPF cadastrado para outro usuário", "username");
		if(isDuplicateUserEmail(user))
			throw new EntityWithInvalidFieldException("E-mail cadastrado para outro usuário", "email");
		if(user.getId() == null) {
			user.setPassword(generateRandomPassword());
			user.setEnabled(true);
		}
		if(userRepository.save(user) == null)
			throw new EntityNotPersistedException("Erro ao salvar usuário");
	}
	
	/**
	 * Captura um usuário pelo identificador desse usuário
	 * @return usuário encontrado
	**/
	public User findOrFail(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
	}
	
	/**
	 * Remoção de um usuário persistido, desde que não haja violação de integridade
	**/
	@Transactional
	public void remove(User user) {
		try {
			userRepository.deleteById(user.getId());
			userRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException("Usuário em uso");
		}
	}
	
	/**
	 * Verifica se existe outro usuário cadastrado com o mesmo cpf
	 * @return true caso exista outro usuário com o mesmo cpf ou false caso não exista outro usuário com o mesmo cpf
	**/
	private boolean isDuplicateUserCpf(User user) {
		Optional<User> persistedUser = userRepository.findByUsername(user.getUsername().replaceAll("\\.|-", ""));
		if(persistedUser.isPresent() && user.getId() != persistedUser.get().getId())
			return true;
		return false;
	}
	
	/**
	 * Verifica se existe outro usuário cadastrado com o mesmo e-mail
	 * @return true caso exista outro usuário com o mesmo e-mail ou false caso não exista outro usuário com o mesmo e-mail
	**/
	private boolean isDuplicateUserEmail(User user) {
		Optional<User> persistedUser = userRepository.findByEmail(user.getEmail().toLowerCase());
		if(persistedUser.isPresent() && user.getId() != persistedUser.get().getId())
			return true;
		return false;
	}
	
	/**
	 * Gera uma senha aleatória
	 * @return senha aleatória
	**/
	private String generateRandomPassword() {
		SecureRandom random = new SecureRandom();
		random.setSeed(100000);
		return String.valueOf(random.nextInt(999999));		
	}
}