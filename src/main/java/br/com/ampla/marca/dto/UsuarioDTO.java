package br.com.ampla.marca.dto;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import br.com.ampla.marca.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {

	private UUID id;
	private String nome;
	private String email;

	public static UsuarioDTO create(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
}
