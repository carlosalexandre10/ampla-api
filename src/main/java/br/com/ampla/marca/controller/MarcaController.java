package br.com.ampla.marca.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ampla.marca.dto.MarcaDTO;
import br.com.ampla.marca.model.MarcaElasticSearch;
import br.com.ampla.marca.repository.MarcaRepository;
import br.com.ampla.marca.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import br.com.ampla.marca.dto.MarcaElasticSearchDTO;
import br.com.ampla.marca.service.MarcaElasticSearchService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private MarcaService marcaService;

	@GetMapping("/{nomeMarca}")
	public ResponseEntity<List<MarcaDTO>> pesquisarPorDescricao(@PathVariable String nomeMarca) {
		List<MarcaDTO> MarcasDTO = this.marcaService.pesquisarPorNome(nomeMarca);

		return MarcasDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(MarcasDTO);
	}
	@GetMapping("/radical/{nomeMarca}")
	public ResponseEntity<List<MarcaDTO>> pesquisarPorDescricaoRadical(@PathVariable String nomeMarca) {
		List<MarcaDTO> MarcasDTO = this.marcaService.pesquisarPorDescricaoRadical(nomeMarca);

		return MarcasDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(MarcasDTO);
	}
	@GetMapping("/processo/{numeroProcesso}")
	public ResponseEntity<MarcaDTO> pesquisarPorNumeroProcesso(@PathVariable Long numeroProcesso) {
		MarcaDTO MarcasDTO = this.marcaService.pesquisaNumeroProcesso(numeroProcesso);

		return MarcasDTO == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(MarcasDTO);
	}
}
