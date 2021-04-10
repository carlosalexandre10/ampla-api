package br.com.ampla.marca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ampla.marca.model.Despacho;

@Repository
public interface DespachoRepository extends JpaRepository<Despacho, Long> {

	List<Despacho> findByMarcaNumeroProcesso(Long numeroProcesso);
}