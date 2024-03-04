package tec.br.lucromais.models;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	/**
	 * Retorna o nome da autorização de usuários
	 * @return o nome da autorização
	**/
	@Override
	public String getAuthority() {
		return authority;
	}
}