package br.com.ampla.marca.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity()
@NoArgsConstructor
@AllArgsConstructor
public class RevistaPropriedadeIndustrial {
	
	@Id
	@Column(name = "numero_rpi")
	private Long numeroRpi;
	
	@Column(name = "data_Rpi", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "{rpi.campo.dataRPI.obrigatorio}")
	private LocalDate dataRpi;
	
	@ManyToOne
	@JoinColumn(name = "marca_numero_processo", nullable = false)
	@NotNull(message = "{rpi.campo.marca.obrigatorio}")
	private Marca marca;
}
