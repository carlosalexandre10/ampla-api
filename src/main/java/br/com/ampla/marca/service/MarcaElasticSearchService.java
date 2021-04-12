package br.com.ampla.marca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.dto.MarcaElasticSearchDTO;
import br.com.ampla.marca.model.MarcaElasticSearch;
import br.com.ampla.marca.repository.MarcaElasticSearchRepository;

@Service
public class MarcaElasticSearchService {

	private static final String MARCA_INDEX = "marcaindex";

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private MarcaElasticSearchRepository marcaElasticSearchRepository;

	public void incluir(final List<MarcaElasticSearch> marcas) {
		marcaElasticSearchRepository.saveAll(marcas);
	}

	public List<MarcaElasticSearchDTO> findByNomeMarca(final String nomeMarca) {
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.matchQuery("nomeMarca", nomeMarca).fuzziness(Fuzziness.AUTO)).build();

		return elasticsearchOperations.search(searchQuery, MarcaElasticSearch.class, IndexCoordinates.of(MARCA_INDEX))
				.stream().map(marca -> MarcaElasticSearchDTO.create(marca.getContent())).collect(Collectors.toList());
	}

	public void migrarElasticsearch(final List<MarcaElasticSearch> marcas) {
		this.removerDadosElasticsearch();
		marcaElasticSearchRepository.saveAll(marcas);

	}
	public void removerDadosElasticsearch() {
		marcaElasticSearchRepository.deleteAll();
	}
}