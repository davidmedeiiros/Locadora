package br.edu.unifacisa.locadora_backend.repositories;

import br.edu.unifacisa.locadora_backend.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
