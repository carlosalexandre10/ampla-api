package br.com.ampla.marca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampla.marca.dto.UsuarioDTO;
import br.com.ampla.marca.model.Usuario;
import br.com.ampla.marca.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping()
	public ResponseEntity<UsuarioDTO> incluir(@RequestBody @Valid Usuario usuario) {
		UsuarioDTO cursoDTO = this.usuarioService.incluir(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
	}
}
