package br.com.ampla.marca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Nice {

	@Id
	@Column(name = "codigo_classificacao_nice")
	private Integer codigoClassificacaoNice;
	
	@Column(name = "numero_revisao_classificacao_nice", nullable = false)
	@NotNull(message = "{nice.campo.numeroRevisaoClassificacaoNice.obrigatorio}")
	private Integer numeroRevisaoClassificacaoNice;

	@Column(name = "especificacao_classificacao_nice", nullable = false, length = 2000)
	@NotEmpty(message = "{nice.campo.especificacaoClassificacaoNice.obrigatorio}")
	private String especificacaoClassificacaoNice;
	
	@OneToOne
	@JoinColumn(name = "marca_numero_processo", nullable = false)
	@NotNull(message = "{nice.campo.marca.obrigatorio}")
	private Marca marca;
}
