package tec.br.lucromais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tec.br.lucromais.models.Role;

/**
 * Interface de repositório de autorizações de usuários
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}