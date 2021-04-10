package br.com.ampla.marca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.model.MarcaElasticSearch;
import br.com.ampla.marca.repository.MarcaElasticSearchRepository;

@Service
public class MarcaElasticSearchService {

	private static final String MARCA_INDEX = "marcaindex";

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private MarcaElasticSearchRepository marcaElasticSearchRepository;

//	public List<MarcaElasticSearch> findByNomeMarca(final String nomeMarca) {
//		return marcaElasticSearchRepository.findByNomeMarca(nomeMarca);
//	}
//	
//	public void createProductIndexBulk(final List<MarcaElasticSearch> marcas) {
//		marcaElasticSearchRepository.saveAll(marcas);
//	}
//
//	public void createProductIndex(final MarcaElasticSearch marcas) {
//		marcaElasticSearchRepository.save(marcas);
//	}
//	
	public List<IndexedObjectInformation> createProductIndexBulk(final List<MarcaElasticSearch> marcas) {

		List<IndexQuery> queries = marcas.stream()
				.map(marca -> new IndexQueryBuilder().withId(marca.getId().toString()).withObject(marca).build())
				.collect(Collectors.toList());
		;

		return elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(MARCA_INDEX));

	}

	public List<MarcaElasticSearch> findByNomeMarca(final String nomeMarca) {
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(
				QueryBuilders.matchQuery("nomeMarca", nomeMarca).fuzziness(Fuzziness.AUTO))
				.build();

		return elasticsearchOperations.search(searchQuery, MarcaElasticSearch.class, IndexCoordinates.of(MARCA_INDEX))
				.stream().map(marca -> marca.getContent()).collect(Collectors.toList());
	}
}