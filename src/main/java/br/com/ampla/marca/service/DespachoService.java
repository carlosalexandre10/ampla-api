package br.com.ampla.marca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ampla.marca.dto.DespachoDTO;
import br.com.ampla.marca.model.Despacho;
import br.com.ampla.marca.repository.DespachoRepository;

@Service
public class DespachoService {
	
	@Autowired
	private DespachoRepository despachoRepository;
	
	public DespachoDTO incluir(Despacho despacho) {
		return DespachoDTO.create(this.despachoRepository.save(despacho));
	}
}
