package br.com.ampla.marca.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marca {

	@Id
	@Column(name = "numero_processo")
	private Long numeroProcesso;
	
	@Column(name = "nome_marca")
	private String nomeMarca;
	
	@Column(name = "descricao_situacao", nullable = false)
	@NotEmpty(message = "{marca.campo.descricaoSituacao.obrigatorio}")
	private String descricaoSituacao;
	
	@Column(name = "descricao_apresentacao", nullable = false)
	@NotEmpty(message = "{marca.campo.descricaoApresentacao.obrigatorio}")
	private String descricaoApresentacao;
	
	@Column(name = "descricao_natureza", nullable = false)
	@NotEmpty(message = "{marca.campo.descricaoNatureza.obrigatorio}")
	private String descricaoNatureza;
	
	@Column(name = "classificacao_viena", length = 2000)
	private String classificacaoViena;
	
	@Column(name = "nome_representante_legal")
	private String nomeRepresentanteLegal;
	
	@Column(name = "data_deposito", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "{marca.campo.dataDeposito.obrigatorio}")
	private LocalDate dataDeposito;
	
	@Column(name = "data_concessao")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataConcessao;
	
	@Column(name = "data_vigencia")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVigencia;
	
	@Column(name = "nome_titular", nullable = false)
	@NotNull(message = "{marca.campo.nomeTitular.obrigatorio}")
	private String nomeTitular;
}
