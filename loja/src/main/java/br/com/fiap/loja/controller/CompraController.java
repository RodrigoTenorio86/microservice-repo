package br.com.fiap.loja.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.loja.controller.dto.CompraDTO;
import br.com.fiap.loja.model.Compra;
import br.com.fiap.loja.service.CompraService;

@RequestMapping("/compra")
@RestController
public class CompraController {
	
	private final static Logger LOG = LoggerFactory.getLogger(CompraController.class);
	
	@Autowired
	private CompraService compraService;

	@PostMapping
	public Compra realizaCompra(@RequestBody CompraDTO compra) {
		LOG.info("Criando compra {}",compra.getClass());
		return  compraService.realizaCompra(compra);  
	}
}
