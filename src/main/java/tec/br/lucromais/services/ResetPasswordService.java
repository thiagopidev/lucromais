package tec.br.lucromais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tec.br.lucromais.exceptions.EntityNotPersistedException;
import tec.br.lucromais.exceptions.EntityWithInvalidFieldException;
import tec.br.lucromais.models.User;
import tec.br.lucromais.repositories.UserRepository;

/**
 * Classe de serviço de redefinição de senha do usuário logado
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Service
public class ResetPasswordService {
	
	/**
	 * Injeção de dependência com a interface de repositório de usuários
	**/
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Gravação da nova senha do usuário logado
	**/
	@Transactional
	public void save(User user) throws EntityWithInvalidFieldException {
		if(user.getPassword() == "")
			throw new EntityWithInvalidFieldException("Nova senha deve ser preenchida", "password");
		if(user.getConfirmPassword() == "")
			throw new EntityWithInvalidFieldException("Confirmação da nova senha deve ser preenchida", "confirmPassword");
		if(!user.getPassword().equals(user.getConfirmPassword())) 
			throw new EntityWithInvalidFieldException("Nova senha e confirmação da nova senha não conferem", "confirmPassword");
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		if(userRepository.saveAndFlush(user) == null)
			throw new EntityNotPersistedException("Erro ao atualizar a senha do usuário");
	}
}