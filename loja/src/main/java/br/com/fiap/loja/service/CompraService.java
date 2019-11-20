package br.com.fiap.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.loja.client.FornecedorClient;
import br.com.fiap.loja.controller.dto.CompraDTO;
import br.com.fiap.loja.controller.dto.InfoFornecedorDTO;

@Service
public class CompraService {
	
//	@Autowired
//	private RestTemplate client;
	
//	@Autowired
//	private DiscoveryClient eurekaCliente;
	
	@Autowired
	private FornecedorClient fornecedorClient;

	public void realizaCompra(CompraDTO compra) {
		InfoFornecedorDTO info= fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		System.out.println(info.getEndereco());
		
		//RestTemplate client = new RestTemplate();
//		ResponseEntity<InfoFornecedorDTO> exchange =
//		client.exchange("http://fornecedor/info/"+compra.getEndereco().getEstado(), HttpMethod.GET,null,InfoFornecedorDTO.class);
//		System.out.println(exchange.getBody().getEndereco());
	
//		eurekaCliente.getInstances("fornecedor").stream().forEach(fornecedor-> System.out.println("localhost: "+fornecedor.getPort()));
	
	
	}

}
