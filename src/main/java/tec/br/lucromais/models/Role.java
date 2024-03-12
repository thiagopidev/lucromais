package tec.br.lucromais.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Classe modelo para definição de autorizações de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "roles")
public class Role implements Serializable, GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador da autorização de usuários
	**/
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Nome da autorização de usuários
	**/
	@NotBlank(message = "Nome é obrigatório")
	@Length(min = 5, max = 20, message = "Nome deve conter entre 5 e 20 caracteres")
	@Getter(value = AccessLevel.NONE)
	@Column(length = 20, nullable = false)
	private String authority;
	
	/**
	 * Lista de usuários percententes a autorização de usuários
	**/
	@ManyToMany(mappedBy = "authorities")
	private List<User> users;
	
	/**
	 * Data e hora da criação da autorização de usuários
	**/
	@CreationTimestamp
	@Column(columnDefinition = "datetime", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	/**
	 * Data e hora da última alteração da autorização de usuários
	**/
	@UpdateTimestamp
	@Column(columnDefinition = "datetime", nullable = false)
	private LocalDateTime updatedAt;

	/**
	 * Retorna o nome da autorização de usuários
	 * @return o nome da autorização
	**/
	@Override
	public String getAuthority() {
		return authority;
	}
	
	/**
	 * Método executado antes de persistir e alterar uma autorização de usuário
	 * Esse método objetiva tornar maiúsculo o valor do atributo authority
	**/
	@PrePersist @PreUpdate
	public void prePersistPreUpdate() {
		authority = authority.toUpperCase();
	}
}