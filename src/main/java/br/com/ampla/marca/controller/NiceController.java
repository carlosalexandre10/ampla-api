package br.com.ampla.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.NiceDTO;
import br.com.ampla.marca.service.NiceService;

@RestController
@RequestMapping("/nices")
public class NiceController {
	
	@Autowired
	private NiceService niceService;
	
	@GetMapping("/{numeroProcessoMarca}")
	public ResponseEntity<List<NiceDTO>> pesquisarPorMarca(@PathVariable Long numeroProcessoMarca) {
		List<NiceDTO> nicesDTO = this.niceService.pesquisarPorMarca(numeroProcessoMarca);

		return nicesDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(nicesDTO);
	}
}