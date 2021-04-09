package br.com.ampla.marca.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ampla.marca.model.Despacho;
import br.com.ampla.marca.model.Marca;
import br.com.ampla.marca.model.Nice;
import br.com.ampla.marca.model.RevistaPropriedadeIndustrial;
import br.com.ampla.marca.repository.DespachoRepository;
import br.com.ampla.marca.repository.MarcaRepository;
import br.com.ampla.marca.repository.NiceRepository;
import br.com.ampla.marca.repository.RevistaPropriedadeIndustrialRepository;

@Service
public class LeitorCSVService {

	@Autowired
	MarcaRepository marcaRepository;

	@Autowired
	RevistaPropriedadeIndustrialRepository rpiRepository;

	@Autowired
	DespachoRepository despachoRepository;

	@Autowired
	NiceRepository niceRepository;

	public String save(MultipartFile file) {
		try {
			if (!"text/csv".equals(file.getContentType())) {
				throw new IllegalArgumentException("Só é permitido upload de arquivos .csv");
			}

			BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "ISO-8859-1"));

			CSVParser csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withDelimiter('|').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			List<Marca> marcas = new ArrayList<>();
			List<RevistaPropriedadeIndustrial> rpis = new ArrayList<>();
			List<Despacho> despachos = new ArrayList<>();
			List<Nice> nices = new ArrayList<>();

			csvParser.getRecords().stream().forEach(csvRecord -> {
				Marca marca = new Marca(Long.parseLong(csvRecord.get("numero_processo")), csvRecord.get("nome_marca"),
						csvRecord.get("descricao_situacao"), csvRecord.get("descricao_apresentacao"),
						csvRecord.get("descricao_natureza"), csvRecord.get("classificacao_viena"),
						csvRecord.get("nome_representante_legal"), LocalDate.parse(csvRecord.get("data_deposito")),
						csvRecord.get("data_concessao").isEmpty() ? null
								: LocalDate.parse(csvRecord.get("data_concessao")),
						csvRecord.get("data_vigencia").isEmpty() ? null
								: LocalDate.parse(csvRecord.get("data_vigencia")),
						csvRecord.get("nome_titular"));

				RevistaPropriedadeIndustrial rpi = new RevistaPropriedadeIndustrial(
						Long.parseLong(csvRecord.get("numero_rpi")), LocalDate.parse(csvRecord.get("data_Rpi")), marca);

				Despacho despacho = new Despacho(Long.parseLong(csvRecord.get("codigo_despacho")),
						csvRecord.get("descricao_despacho"), csvRecord.get("complemento_despacho"), marca);

				Nice nice = new Nice(Integer.parseInt(csvRecord.get("codigo_classificacao_nice")),
						Integer.parseInt(csvRecord.get("numero_revisao_classificacao_nice")),
						csvRecord.get("especificacao_classificacao_nice"), marca);

				marcas.add(marca);
				rpis.add(rpi);
				despachos.add(despacho);
				nices.add(nice);
			});

			this.marcaRepository.saveAll(marcas);
			this.rpiRepository.saveAll(rpis);
			this.despachoRepository.saveAll(despachos);
			this.niceRepository.saveAll(nices);
			
			csvParser.close();

		} catch (IOException e) {
			throw new RuntimeException("Falha na inclusão dos dados: " + e.getMessage());
		}

		return "Dados salvos com sucesso";
	}
}
