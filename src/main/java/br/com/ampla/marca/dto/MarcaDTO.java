package br.com.ampla.marca.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import br.com.ampla.marca.model.Marca;
import lombok.Data;

@Data
public class MarcaDTO {
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

	public static MarcaDTO create(Marca marca) {
		ModelMapper modelMapper = new ModelMapper();
		return marca != null? modelMapper.map(marca, MarcaDTO.class):null;
	}
}
