package br.com.alura.microservice.fornecedor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.service.InfoService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1/info")
@Api(value = "Endpoint to manage InfoController")
public class InfoController {
	private static final Logger LOG = LoggerFactory.getLogger(InfoController.class);
	@Autowired
	private InfoService infoService;
	
	@RequestMapping("/{estado}")
	public InfoFornecedor getInfoPorEstado(@PathVariable String estado) {
		LOG.info(" recebido pedido de informacao do fornecedor de {}", estado);
		return infoService.getInfoPorEstado(estado);
	}
}
