package br.com.fiap.loja.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.loja.client.FornecedorClient;
import br.com.fiap.loja.controller.dto.CompraDTO;
import br.com.fiap.loja.controller.dto.InfoFornecedorDTO;
import br.com.fiap.loja.controller.dto.InfoPedidoDTO;
import br.com.fiap.loja.model.Compra;

@Service
public class CompraService {
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
//	@Autowired
//	private RestTemplate client;	
//	@Autowired
//	private DiscoveryClient eurekaCliente;
	
	@Autowired
	private FornecedorClient fornecedorClient;

	public Compra realizaCompra(CompraDTO compra) {
		
		final String estado = compra.getEndereco().getEstado();
		LOG.info("Buscando informa;oes do Fornecedor de {} ", estado);		
		InfoFornecedorDTO info= fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
	    LOG.info("realizando um Pedido");
		InfoPedidoDTO pedido=	fornecedorClient.realizaPedido(compra.getItens());
		
	      
	   
		
		Compra compra2 =new  Compra();
		compra2.setPedidoId(pedido.getId());
		compra2.setTempoDePreparo(pedido.getTempoDePreparo());
		compra2.setEnderecoDestino(compra.getEndereco().toString());
		

		System.out.println(info.getEndereco());
		
		return compra2;
		
		//RestTemplate client = new RestTemplate();
//		ResponseEntity<InfoFornecedorDTO> exchange =
//		client.exchange("http://fornecedor/info/"+compra.getEndereco().getEstado(), HttpMethod.GET,null,InfoFornecedorDTO.class);
//		System.out.println(exchange.getBody().getEndereco());
	
//		eurekaCliente.getInstances("fornecedor").stream().forEach(fornecedor-> System.out.println("localhost: "+fornecedor.getPort()));
	
	
	}

}
