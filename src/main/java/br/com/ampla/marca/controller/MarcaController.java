package br.com.ampla.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.MarcaElasticSearchDTO;
import br.com.ampla.marca.service.MarcaElasticSearchService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaElasticSearchService marcaElasticSearchService;

	@GetMapping("/{nomeMarca}")
	public ResponseEntity<List<MarcaElasticSearchDTO>> pesquisarPorDescricao(@PathVariable String nomeMarca) {
		List<MarcaElasticSearchDTO> marcasElasticSearch = this.marcaElasticSearchService.findByNomeMarca(nomeMarca);

		return marcasElasticSearch.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(marcasElasticSearch);
	}
}
