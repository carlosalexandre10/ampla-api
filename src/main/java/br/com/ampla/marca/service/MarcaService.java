package br.com.ampla.marca.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ampla.marca.dto.NiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.dto.MarcaDTO;
import br.com.ampla.marca.model.Marca;
import br.com.ampla.marca.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	public MarcaDTO incluir(Marca marca) {
		return MarcaDTO.create(this.marcaRepository.save(marca));
	}
	
	public List<MarcaDTO> listar(Pageable pageable) {
		return this.marcaRepository.findAll(pageable).stream().map(MarcaDTO::create)
				.collect(Collectors.toList());
	}

	public List<MarcaDTO> pesquisarPorNome(String name) {
		return this.marcaRepository.findByNomeMarca(name).stream().map(MarcaDTO::create)
				.collect(Collectors.toList());
	}

	public List<MarcaDTO> pesquisarPorDescricaoRadical(String name) {
		return this.marcaRepository.findByNomeMarcaContainingIgnoreCase(name).stream().map(MarcaDTO::create)
				.collect(Collectors.toList());
	}

	public MarcaDTO pesquisaNumeroProcesso(Long numeroProcesso) {
		return MarcaDTO.create(this.marcaRepository.findByNumeroProcesso(numeroProcesso));
	}
}
