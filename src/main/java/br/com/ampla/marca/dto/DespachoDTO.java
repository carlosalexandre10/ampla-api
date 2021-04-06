package br.com.ampla.marca.dto;

import org.modelmapper.ModelMapper;

import br.com.ampla.marca.model.Despacho;
import br.com.ampla.marca.model.Marca;
import lombok.Data;

@Data
public class DespachoDTO {
	private Long codigoDespacho;
	private String descricaoDespacho;
	private String complementoDespacho;
	private Marca marca;
	
	public static DespachoDTO create(Despacho despacho) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(despacho, DespachoDTO.class);

	}
}
