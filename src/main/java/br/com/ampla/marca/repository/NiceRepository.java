package br.com.ampla.marca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ampla.marca.model.Nice;

@Repository
public interface NiceRepository extends JpaRepository<Nice, Integer> {

	List<Nice> findByMarcaNumeroProcesso(Long numeroProcesso);
}