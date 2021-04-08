package br.com.ampla.marca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.model.Usuario;
import br.com.ampla.marca.repository.UsuarioRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(("Email não existe.")));

		return User.withUsername(usuario.getEmail()).password(usuario.getSenha()).roles("USER").build();
	}

}
