package br.com.ampla.marca.dto;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ampla.marca.model.Usuario;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

	private UUID id;
	private String nome;
	private String email;

	// token jwt
	private String token;

	public static UsuarioDTO create(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	public static UsuarioDTO create(Usuario usuario, String token) {
		UsuarioDTO usuarioDTO = create(usuario);
		usuarioDTO.token = token;
		return usuarioDTO;
	}

	public String toJson() throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		return m.writeValueAsString(this);
	}

}
