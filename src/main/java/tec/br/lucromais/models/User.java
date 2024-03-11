package tec.br.lucromais.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Classe modelo para definição de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador do usuário
	**/
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Senha usada para autenticação do usuário
	**/
	@Getter(value = AccessLevel.NONE)
	@Column(length = 60, nullable = false)
	private String password;
	
	/**
	 * Nome usado para autenticação do usuário
	**/
	@Getter(value = AccessLevel.NONE)
	@Column(columnDefinition = "char(11)", nullable = false)
	private String username;
	
	/**
	 * Lista de autorizações do usuário
	**/
	@Getter(value = AccessLevel.NONE)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "authorities", joinColumns = @JoinColumn(
		name = "users_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(
		name = "roles_id", referencedColumnName = "id"
	))
	private List<Role> roles;
	
	/**
	 * Indica se a conta do usuário está vigente ou expirada
	**/
	@Getter(value = AccessLevel.NONE)
	@Column(columnDefinition = "tinyint(1) default 0", nullable = false)
	private boolean accountNonExpired;
	
	/**
	 * Indica se o usuário está desbloqueado ou bloqueado
	**/
	@Getter(value = AccessLevel.NONE)
	@Column(columnDefinition = "tinyint(1) default 0", nullable = false)
	private boolean accountNonLocked;
	
	/**
	 * Indica se as credenciais do usuário estão vigentes ou expiraram
	**/
	@Getter(value = AccessLevel.NONE)
	@Column(columnDefinition = "tinyint(1) default 0", nullable = false)
	private boolean credentialsNonExpired;
	
	/**
	 * Indica se o usuário está ativado ou desativado
	**/
	@Getter(value = AccessLevel.NONE)
	@Column(columnDefinition = "tinyint(1) default 0", nullable = false)
	private boolean enabled;
	
	/**
	 * Data e hora da criação do usuário
	**/
	@CreationTimestamp
	@Column(columnDefinition = "datetime", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	/**
	 * Data e hora da última alteração do usuário
	**/
	@UpdateTimestamp
	@Column(columnDefinition = "datetime", nullable = false)
	private LocalDateTime updatedAt;
	
	/**
	 * Retorna as autorizações concedidas ao usuário
	 * @return as autorizações do usuário
	**/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	/**
	 * Retorna a senha usada para autenticar o usuário
	 * @return a senha do usuário
	**/
	@Override
	public String getPassword() {
		return password;
	}
	
	/**
	 * Retorna o nome usado para autenticar o usuário
	 * @return o nome de autenticação do usuário
	**/
	@Override
	public String getUsername() {
		return username;
	}
	
	/**
	 * Retorna se a conta do usuário está vigente ou expirada
	 * @return true para conta vigente ou false para conta expirada
	**/
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * Retorna se o usuário está desbloqueado ou bloqueado
	 * @return true para usuário desbloqueado ou false para usuário bloqueado
	**/
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * Retorna se as credenciais (senha) do usuário estão vigentes ou expiraram
	 * @return true para credenciais vigentes ou false para credenciais expiradas
	**/
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * Retorna se o usuário está ativado ou desativado
	 * @return true para usuário ativado ou false para usuário desativado
	**/
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Método executado após o carregamento de usuários
	 * Esse método objetiva formatar o valor do atributo username
	**/
	@PostLoad
	private void postLoad() {
		this.username = this.username.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
	}
}