package br.com.fiap.loja.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.loja.controller.dto.CompraDTO;
import br.com.fiap.loja.model.Compra;
import br.com.fiap.loja.service.CompraService;

@RequestMapping("/v1/compra")
@RestController
public class CompraController {
	
	private final static Logger LOG = LoggerFactory.getLogger(CompraController.class);
	
	@Autowired
	private CompraService compraService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Compra compra =compraService.getById(id);
		return new ResponseEntity<>(compra, HttpStatus.OK) ;
	}

	@PostMapping
	public Compra realizaCompra(@RequestBody CompraDTO compra) {
		LOG.info("Criando compra {}",compra.getClass());
		return  compraService.realizaCompra(compra);  
	}
}
