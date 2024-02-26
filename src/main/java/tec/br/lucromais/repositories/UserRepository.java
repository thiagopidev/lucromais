package tec.br.lucromais.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tec.br.lucromais.models.User;

/**
 * Interface de repositório de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Retorna, caso exista, um usuário a partir do nome usado para autenticação
	 * @return o usuário, caso este exista
	**/
	public Optional<User> findByUsername(String username);
}