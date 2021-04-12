package br.com.ampla.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.MarcaDTO;
import br.com.ampla.marca.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@GetMapping("/{nomeMarca}")
	public ResponseEntity<List<MarcaDTO>> pesquisarPorDescricao(@PathVariable String nomeMarca, @RequestParam(value = "radical", defaultValue = "false") Boolean radical) {
		List<MarcaDTO> MarcasDTO;

		if(radical)
			MarcasDTO = this.marcaService.pesquisarPorDescricaoRadical(nomeMarca);
		else
			MarcasDTO = this.marcaService.pesquisarPorNome(nomeMarca);

		return MarcasDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(MarcasDTO);
	}

	@GetMapping("/processo/{numeroProcesso}")
	public ResponseEntity<MarcaDTO> pesquisarPorNumeroProcesso(@PathVariable Long numeroProcesso) {
		MarcaDTO MarcasDTO = this.marcaService.pesquisaNumeroProcesso(numeroProcesso);

		return MarcasDTO == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(MarcasDTO);
	}
}
