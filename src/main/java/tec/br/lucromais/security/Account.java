package tec.br.lucromais.security;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;

/**
 * Classe usada como estratégia para mostrar informações do usuário logado alheias à classe UserDetails
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
public class Account extends User {
	
	/**
	 * Identificador serial advindo da herança de User
	**/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Usuário utilizado para autenticação e autorização
	**/
	@Getter
	private tec.br.lucromais.models.User user;
	
	/**
	 * Construtor da classe usada como estratégia para mostrar informações do usuário logado alheias à classe UserDetails
	**/
	public Account(tec.br.lucromais.models.User user) {
		super(user.getCpf(), user.getPassword(), user.isEnabled(), user.isEnabled(), user.isEnabled(), user.isEnabled(), user.getAuthorities());
		this.user = user;
	}
}