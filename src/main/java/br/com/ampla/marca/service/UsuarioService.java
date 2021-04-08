package br.com.ampla.marca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.dto.UsuarioDTO;
import br.com.ampla.marca.model.Role;
import br.com.ampla.marca.model.Usuario;
import br.com.ampla.marca.repository.UsuarioRepository;
import br.com.ampla.marca.utils.enums.RolesEnum;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioDTO incluir(Usuario usuario) {
		Optional<Usuario> checarSeEmailExiste = this.usuarioRepository.findByEmail(usuario.getEmail());

		if (checarSeEmailExiste.isPresent()) {
			throw new IllegalArgumentException("Email já foi cadastrado!");
		}

		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		if (usuario.getRoles() == null) {
			Role role = new Role();
			role.setId(Long.parseLong(RolesEnum.ROLE_USER.getValor()));
			List<Role> roles = new ArrayList<>();
			roles.add(role);
			usuario.setRoles(roles);
		}
		return UsuarioDTO.create(this.usuarioRepository.save(usuario));
	}
}
