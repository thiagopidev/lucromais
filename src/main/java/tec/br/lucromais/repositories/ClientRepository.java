package tec.br.lucromais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tec.br.lucromais.models.Client;

/**
 * Interface repository de cliente
 * @author Thiago Pinheiro do Nascimento
 * @version 0.1
 * @since 0.1
**/
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}