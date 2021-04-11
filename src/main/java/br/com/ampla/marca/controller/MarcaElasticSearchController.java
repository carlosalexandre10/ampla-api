package br.com.ampla.marca.controller;

import br.com.ampla.marca.dto.MarcaDTO;
import br.com.ampla.marca.dto.MarcaElasticSearchDTO;
import br.com.ampla.marca.model.MarcaElasticSearch;
import br.com.ampla.marca.repository.MarcaRepository;
import br.com.ampla.marca.service.MarcaElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marcas-elasticearch")
public class MarcaElasticSearchController {

	@Autowired
	private MarcaElasticSearchService marcaElasticSearchService;


	@Autowired
	private MarcaRepository marcaRepository;

	@GetMapping("/{nomeMarca}")
	public ResponseEntity<List<MarcaElasticSearchDTO>> pesquisarPorDescricao(@PathVariable String nomeMarca) {
		List<MarcaElasticSearchDTO> marcasElasticSearch = this.marcaElasticSearchService.findByNomeMarca(nomeMarca);

		return marcasElasticSearch.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(marcasElasticSearch);
	}


	@GetMapping("/migrarDadosElasticsearch")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String > migrarElasticsearch() {

		List<MarcaDTO> marcas = marcaRepository.findAll(PageRequest.of(0, 10)).stream().map(MarcaDTO::create)
				.collect(Collectors.toList());

		List<MarcaElasticSearch> marcasElasticSearchParaSalvar = new ArrayList<>();
		marcas.stream().forEach(marca -> {
			MarcaElasticSearch marcaElasticSearch = new MarcaElasticSearch();

			marcaElasticSearch.setNumeroProcesso(marca.getNumeroProcesso());
			marcaElasticSearch.setNomeMarca(marca.getNomeMarca());
			marcaElasticSearch.setDescricaoSituacao(marca.getDescricaoSituacao());
			marcaElasticSearch.setDescricaoApresentacao(marca.getDescricaoApresentacao());
			marcaElasticSearch.setDescricaoNatureza(marca.getDescricaoNatureza());
			marcaElasticSearch.setClassificacaoViena(marca.getClassificacaoViena());
			marcaElasticSearch.setNomeRepresentanteLegal(marca.getNomeRepresentanteLegal());
			marcaElasticSearch.setNomeTitular(marca.getNomeTitular());

			marcasElasticSearchParaSalvar.add(marcaElasticSearch);
		});

		this.marcaElasticSearchService.migrarElasticsearch(marcasElasticSearchParaSalvar);


		return ResponseEntity.status(HttpStatus.CREATED).body("ElasticSearch migrado com sucesso");
	}

	@GetMapping("/removerDadosElasticsearch")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String > removerDadosElasticsearch() {
		this.marcaElasticSearchService.removerDadosElasticsearch();
		return ResponseEntity.status(HttpStatus.CREATED).body("ElasticSearch removido com sucesso");
	}
}
