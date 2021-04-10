package br.com.ampla.marca.model;

import java.time.LocalDate;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "marcaindex")
public class MarcaElasticSearch {

	@Id
	private String id;

	@Field(type = FieldType.Float, name = "numeroProcesso")
	private Long numeroProcesso;

	@Field(type = FieldType.Text, name = "nomeMarca")
	private String nomeMarca;

	@Field(type = FieldType.Text, name = "descricaoSituacao")
	private String descricaoSituacao;

	@Field(type = FieldType.Text, name = "descricaoApresentacao")
	private String descricaoApresentacao;

	@Field(type = FieldType.Text, name = "descricaoNatureza")
	private String descricaoNatureza;

	@Field(type = FieldType.Text, name = "classificacaoViena")
	private String classificacaoViena;

	@Field(type = FieldType.Text, name = "nomeRepresentanteLegal")
	private String nomeRepresentanteLegal;

	@Field(type = FieldType.Date, name = "dataDeposito", format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
	private LocalDate dataDeposito;

	@Field(type = FieldType.Date, name = "dataConcessao", format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
	private LocalDate dataConcessao;

	@Field(type = FieldType.Date, name = "dataVigencia", format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
	private LocalDate dataVigencia;

	@Field(type = FieldType.Text, name = "nomeTitular")
	private String nomeTitular;
}
