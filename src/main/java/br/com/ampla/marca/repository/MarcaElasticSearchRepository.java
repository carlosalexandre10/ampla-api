package br.com.ampla.marca.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.ampla.marca.model.MarcaElasticSearch;

public interface MarcaElasticSearchRepository extends ElasticsearchRepository<MarcaElasticSearch, String> {

	 List<MarcaElasticSearch> findByNomeMarca(String nomeMarca);
}