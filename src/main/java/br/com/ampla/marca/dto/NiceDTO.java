package br.com.ampla.marca.dto;

import org.modelmapper.ModelMapper;

import br.com.ampla.marca.model.Marca;
import br.com.ampla.marca.model.Nice;
import lombok.Data;

@Data
public class NiceDTO {
	
	private Integer codigoClassificacaoNice;
	private Integer numeroRevisaoClassificacaoNice;
	private String especificacaoClassificacaoNice;
	private Marca marca;

	public static NiceDTO create(Nice nice) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(nice, NiceDTO.class);

	}
}
