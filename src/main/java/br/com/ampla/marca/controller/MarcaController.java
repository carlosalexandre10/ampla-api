package br.com.ampla.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.MarcaDTO;
import br.com.ampla.marca.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;

	@GetMapping()
	public ResponseEntity<List<MarcaDTO>> listar() {
		List<MarcaDTO> MarcasDTO = this.marcaService.listar();

		return MarcasDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(MarcasDTO);
	}
}
