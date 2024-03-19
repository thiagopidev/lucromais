package tec.br.lucromais.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe model de usuário
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	/**
	 * Identificador serial advindo da implementação Serializable
	**/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador de auto incremento do usuário
	**/
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * CPF do usuário
	**/
	@NotBlank(message = "CPF é obrigatório")
	@CPF(message = "CPF inválido")
	@Column(columnDefinition = "char(11)", nullable = false)
	private String cpf;
	
	/**
	 * Nome do usuário
	**/
	@NotBlank(message = "Nome é obrigatório")
	@Length(min = 5, max = 30, message = "Nome deve conter entre 5 e 30 caracteres")
	@Column(length = 30, nullable = false)
	private String name;
	
	/**
	 * E-mail do usuário
	**/
	@NotBlank(message = "E-mail é obrigatório")
	@Length(min = 10, max = 60, message = "E-mail deve contee entre 10 e 60 caracteres")
	@Email(message = "E-mail inválido")
	@Column(length = 60, nullable = false)
	private String email;
	
	/**
	 * Senha do usuário
	**/
	@Column(length = 60, nullable = false)
	private String password;
	
	/**
	 * Confirmação da senha do usuário
	**/
	@Transient
	private String confirmPassword;
	
	/**
	 * Grupo de autorização do usuário
	**/
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Grupo é obrigatório")
	@Column(columnDefinition = "char(3)", nullable = false)
	private Role role;
	
	/**
	 * Indica se o usuário está ativo
	**/
	@Column(columnDefinition = "tinyint(1) default 1", nullable = false)
	private boolean enabled;
	
	/**
	 * Data e hora em que o usuário foi criado
	**/
	@CreationTimestamp
	@Column(columnDefinition = "datetime", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	/**
	 * Data e hora em que o usuário foi alteração pela última vez
	**/
	@UpdateTimestamp
	@Column(columnDefinition = "datetime", nullable = false)
	private LocalDateTime updatedAt;
	
	/**
	 * Retorna o perfil do usuário
	 * @return o perfil do usuário
	**/
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.getAuthority()));
	}
	
	/**
	 * Método executado após o carregar usuário
	 * Esse método formata o valor do atributo username após o carregamento de usuário
	**/
	@PostLoad
	private void postLoad() {
		cpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
	}
	
	/**
	 * Método executado antes de persistir e de alterar usuário
	 * Esse método formata os valores dos atributos username e email antes da persistência e da alteração de dados
	**/
	@PrePersist @PreUpdate
	private void prePersistPreUpdate() {
		cpf = cpf.replaceAll("\\.|-", "");
		email = email.toLowerCase();
	}
}