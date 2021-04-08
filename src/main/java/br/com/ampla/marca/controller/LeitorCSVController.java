package br.com.ampla.marca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ampla.marca.service.LeitorCSVService;

@RestController
@RequestMapping("/leitorCSV")
public class LeitorCSVController {

	@Autowired
	private LeitorCSVService leitorCSVService;

	@PostMapping("/upload")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = this.leitorCSVService.save(file);

		return ResponseEntity.ok(message);
	}

}