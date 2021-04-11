package br.com.ampla.marca.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import br.com.ampla.marca.model.MarcaElasticSearch;
import lombok.Data;

@Data
public class MarcaElasticSearchDTO {
	private Long numeroProcesso;
	private String nomeMarca;
	private String descricaoSituacao;
	private String descricaoApresentacao;
	private String descricaoNatureza;
	private String classificacaoViena;
	private String nomeRepresentanteLegal;
	private LocalDate dataDeposito;
	private LocalDate dataConcessao;
	private LocalDate dataVigencia;
	private String nomeTitular;

	public static MarcaElasticSearchDTO create(MarcaElasticSearch marca) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(marca, MarcaElasticSearchDTO.class);

	}
}