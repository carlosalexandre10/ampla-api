package br.com.ampla.marca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ampla.marca.model.RevistaPropriedadeIndustrial;

@Repository
public interface RevistaPropriedadeIndustrialRepository extends JpaRepository<RevistaPropriedadeIndustrial, Long> {

	List<RevistaPropriedadeIndustrial> findByMarcaNumeroProcesso(Long numeroProcesso);
}