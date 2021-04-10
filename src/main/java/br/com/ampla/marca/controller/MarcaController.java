package br.com.ampla.marca.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.MarcaDTO;
import br.com.ampla.marca.model.MarcaElasticSearch;
import br.com.ampla.marca.repository.MarcaRepository;
import br.com.ampla.marca.service.MarcaElasticSearchService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaElasticSearchService marcaElasticSearchService;

	@Autowired
	private MarcaRepository marcaRepository;

	@GetMapping("/{nomeMarca}")
	public ResponseEntity<List<MarcaElasticSearch>> pesquisarPorDescricao(@PathVariable String nomeMarca) {
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
			marcaElasticSearch.setDataDeposito(marca.getDataDeposito());
			marcaElasticSearch.setDataConcessao(marca.getDataConcessao());
			marcaElasticSearch.setDataVigencia(marca.getDataVigencia());
			marcaElasticSearch.setNomeTitular(marca.getNomeTitular());

			marcasElasticSearchParaSalvar.add(marcaElasticSearch);
		});

		this.marcaElasticSearchService.incluir(marcasElasticSearchParaSalvar);

		List<MarcaElasticSearch> marcasElasticSearch = this.marcaElasticSearchService.findByNomeMarca(nomeMarca);

		return marcasElasticSearch.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(marcasElasticSearch);
	}
}
