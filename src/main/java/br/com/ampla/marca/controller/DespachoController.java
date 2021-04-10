package br.com.ampla.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.DespachoDTO;
import br.com.ampla.marca.service.DespachoService;

@RestController
@RequestMapping("/despachos")
public class DespachoController {
	
	@Autowired
	private DespachoService despachoService;
	
	@GetMapping("/{numeroProcessoMarca}")
	public ResponseEntity<List<DespachoDTO>> pesquisarPorMarca(@PathVariable Long numeroProcessoMarca) {
		List<DespachoDTO> despachosDTO = this.despachoService.pesquisarPorMarca(numeroProcessoMarca);

		return despachosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(despachosDTO);
	}
}
