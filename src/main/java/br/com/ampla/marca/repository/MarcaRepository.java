package br.com.ampla.marca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ampla.marca.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByNomeMarca(String name);
    List<Marca> findByNomeMarcaContainingIgnoreCase(String name);
    Marca findByNumeroProcesso(Long numeroProcesso);
	
}