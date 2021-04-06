package br.com.ampla.marca.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import br.com.ampla.marca.model.Marca;
import br.com.ampla.marca.model.RevistaPropriedadeIndustrial;
import lombok.Data;

@Data
public class RevistaPropriedadeIndustrialDTO {

	private Long numeroRpi;
	private LocalDate dataRpi;
	private Marca marca;
	
	public static RevistaPropriedadeIndustrialDTO create(RevistaPropriedadeIndustrial rpi) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(rpi, RevistaPropriedadeIndustrialDTO.class);

	}
}