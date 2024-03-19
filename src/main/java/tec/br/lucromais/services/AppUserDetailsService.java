package tec.br.lucromais.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tec.br.lucromais.models.User;
import tec.br.lucromais.repositories.UserRepository;
import tec.br.lucromais.security.Account;

/**
 * Classe de serviço de verificação e carregamento de usuário
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Service
public class AppUserDetailsService implements UserDetailsService {
	
	/**
	 * Injeção de dependência com a interface de repositório de usuário
	**/
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Retorna e carrega as informações do usuário a partir do nome de autenticação do usuário
	 * @return a conta de usuário, caso o usuário exista
	**/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByCpf(username.replaceAll("\\.|-", ""));
		if(user.isEmpty())
			throw new UsernameNotFoundException("Usuário não encontrado");
		return new Account(user.get());
	}
}