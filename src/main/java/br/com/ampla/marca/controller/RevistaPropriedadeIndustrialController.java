package br.com.ampla.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.NiceDTO;
import br.com.ampla.marca.dto.RevistaPropriedadeIndustrialDTO;
import br.com.ampla.marca.service.NiceService;
import br.com.ampla.marca.service.RevistaPropriedadeIndustrialService;

@RestController
@RequestMapping("/rpis")
public class RevistaPropriedadeIndustrialController {
	
	@Autowired
	private RevistaPropriedadeIndustrialService revistaPropriedadeIndustrialService;
	
	@GetMapping("/{numeroProcessoMarca}")
	public ResponseEntity<List<RevistaPropriedadeIndustrialDTO>> pesquisarPorMarca(@PathVariable Long numeroProcessoMarca) {
		List<RevistaPropriedadeIndustrialDTO> rpisDTO = this.revistaPropriedadeIndustrialService.pesquisarPorMarca(numeroProcessoMarca);

		return rpisDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(rpisDTO);
	}
}