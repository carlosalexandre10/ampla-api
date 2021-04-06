package br.com.ampla.marca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.dto.RevistaPropriedadeIndustrialDTO;
import br.com.ampla.marca.model.RevistaPropriedadeIndustrial;
import br.com.ampla.marca.repository.RevistaPropriedadeIndustrialRepository;

@Service
public class RevistaPropriedadeIndustrialService {
	
	@Autowired
	private RevistaPropriedadeIndustrialRepository rpiRepository;
	
	public RevistaPropriedadeIndustrialDTO incluir(RevistaPropriedadeIndustrial rpi) {
		return RevistaPropriedadeIndustrialDTO.create(this.rpiRepository.save(rpi));
	}
}
