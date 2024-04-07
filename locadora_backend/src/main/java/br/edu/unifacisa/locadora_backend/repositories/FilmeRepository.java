package br.edu.unifacisa.locadora_backend.repositories;

import br.edu.unifacisa.locadora_backend.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
