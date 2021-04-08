package br.com.ampla.marca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.dto.UsuarioDTO;
import br.com.ampla.marca.model.Usuario;
import br.com.ampla.marca.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioDTO incluir(Usuario usuario) {
		Optional<Usuario> checarSeEmailExiste = this.usuarioRepository.findByEmail(usuario.getEmail());
		
		if(checarSeEmailExiste.isPresent()) {
			throw new IllegalArgumentException("Email já foi cadastrado!");
		}
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		return UsuarioDTO.create(this.usuarioRepository.save(usuario));
	}
}
