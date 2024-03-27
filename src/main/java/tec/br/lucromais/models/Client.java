package tec.br.lucromais.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tec.br.lucromais.groups.CnpjGroup;
import tec.br.lucromais.groups.CpfGroup;
import tec.br.lucromais.validations.ClientGroupSequenceProvider;

/**
 * Classe model de cliente
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "clients")
@GroupSequenceProvider(ClientGroupSequenceProvider.class)
public class Client implements Serializable {
	
	/**
	 * Identificador serial advindo da implementação Serializable
	**/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador de auto incremento do cliente
	**/
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Tipo do cliente
	**/
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo de cliente é obrigatório")
	@Column(columnDefinition = "varchar(15)", nullable = false)
	private ClientType type;
	
	/**
	 * CPF ou CNPJ do cliente
	**/
	@CNPJ(groups = CnpjGroup.class, message = "CNPJ inválido")
	@CPF(groups = CpfGroup.class, message = "CPF inválido")
	@NotBlank(message = "CNPJ ou CPF é obrigatório")
	@Column(name = "cpf_cnpj", length = 14, nullable = false)
	private String cpfOrCnpj;
	
	/**
	 * Razão social do cliente
	**/
	@Column(length = 80)
	@Length(max = 80, message = "Razão social deve conter no máximo 80 caracteres")
	private String corporateReason;
	
	/**
	 * Nome fantasia ou nome completo do cliente
	**/
	@NotBlank(message = "Nome fantasia ou nome completo é obrigatório")
	@Length(max = 80, message = "Nome fantasia ou nome completo deve conter no máximo 80 caracteres")
	@Column(length = 80, nullable = false)
	private String fantasyName;
	
	/**
	 * Email do cliente
	**/
	@NotBlank(message = "E-mail é obrigatório")
	@Length(max = 60, message = "E-mail deve conter no máximo 60 caracteres")
	@Email(message = "E-mail inválido")
	@Column(length = 50, nullable = false)
	private String email;
	
	/**
	 * Data de abertura ou data de nascimento do cliente
	**/
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data de abertura ou data de nascimento é obrigatória")
	@Column(columnDefinition = "datetime",  nullable = false)
	private Date birth;
	
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
	
	@PrePersist @PreUpdate
	private void prePersistPreUpdate() {
		cpfOrCnpj = cpfOrCnpj.replaceAll("\\.|-|/", "");
	}
}