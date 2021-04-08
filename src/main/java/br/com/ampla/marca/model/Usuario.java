package br.com.ampla.marca.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private UUID id;

	@Column(name = "nome", nullable = false)
	@NotEmpty(message = "{usuario.campo.nome.obrigatorio}")
	private String nome;

	@Column(name = "email", unique = true, nullable = false)
	@NotEmpty(message = "{usuario.campo.email.obrigatorio}")
	@Email(message = "{usuario.campo.email.invalido}")
	private String email;

	@Column(name = "senha", nullable = false)
	@NotEmpty(message = "{usuario.campo.senha.obrigatorio}")
	private String senha;
}
