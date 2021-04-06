package br.com.ampla.marca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Despacho {

	@Id
	@Column(name = "codigo_despacho")
	private Long codigoDespacho;
	
	@Column(name = "descricao_despacho", length = 1000)
	private String descricaoDespacho;
	
	@Column(name = "complemento_despacho",length = 2000)
	private String complementoDespacho;
	
	@ManyToOne
	@JoinColumn(name = "marca_numero_processo", nullable = false)
	@NotNull(message = "{despacho.campo.marca.obrigatorio}")
	private Marca marca;
}
