package br.com.ampla.marca.repository;

import br.com.ampla.marca.dto.MarcaDTO;
import br.com.ampla.marca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ampla.marca.model.Marca;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByNomeMarca(String name);
    List<Marca> findByNomeMarcaContainingIgnoreCase(String name);
    Marca findByNumeroProcesso(Long numeroProcesso);
	
}