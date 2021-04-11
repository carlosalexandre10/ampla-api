package br.com.ampla.marca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.dto.NiceDTO;
import br.com.ampla.marca.model.Nice;
import br.com.ampla.marca.repository.NiceRepository;

@Service
public class NiceService {
	
	@Autowired
	private NiceRepository niceRepository;
	
	public NiceDTO incluir(Nice nice) {
		return NiceDTO.create(this.niceRepository.save(nice));
	}
	
	public List<NiceDTO> pesquisarPorMarca(Long numeroProcessoMarca) {
		return this.niceRepository.findByMarcaNumeroProcesso(numeroProcessoMarca).stream().map(NiceDTO::create)
				.collect(Collectors.toList());
	}
}
