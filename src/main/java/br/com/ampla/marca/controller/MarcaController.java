package br.com.ampla.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping()
	public ResponseEntity<List<MarcaDTO>> listar(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<MarcaDTO> MarcasDTO = this.marcaService.listar(PageRequest.of(page, size));

		return MarcasDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(MarcasDTO);
	}
}
