package br.com.fiap.loja.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.fiap.loja.client.FornecedorClient;
import br.com.fiap.loja.client.TransportadorClient;
import br.com.fiap.loja.controller.dto.CompraDTO;
import br.com.fiap.loja.controller.dto.InfoEntregaDTO;
import br.com.fiap.loja.controller.dto.InfoFornecedorDTO;
import br.com.fiap.loja.controller.dto.InfoPedidoDTO;
import br.com.fiap.loja.controller.dto.VoucherDTO;
import br.com.fiap.loja.model.Compra;
import br.com.fiap.loja.model.CompraState;
import br.com.fiap.loja.repository.CompraRepository;

@Service
public class CompraService {
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

//	@Autowired
//	private RestTemplate client;	
//	@Autowired
//	private DiscoveryClient eurekaCliente;

	@Autowired
	private FornecedorClient fornecedorClient;
	@Autowired
	private CompraRepository comprarepository;
	@Autowired
	private TransportadorClient transportadorClient;

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizarCompraThreadPool")
	public Compra realizaCompra(CompraDTO compra) {
		
		Compra compraSalva = new Compra();
		compraSalva.setState(CompraState.RECEBIDO);
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		comprarepository.save(compraSalva);
		compra.setCompraId(compraSalva.getId());
		
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		compraSalva.setState(CompraState.PEDIDO_REALIZADO);
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		comprarepository.save(compraSalva);
		
		//Integracao Transporte
		InfoEntregaDTO entregaDto = new InfoEntregaDTO();
		entregaDto.setPedidoId(pedido.getId());
		entregaDto.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
		entregaDto.setEnderecoOrigem(info.getEndereco());
		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);
		compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
		compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraSalva.setVoucher(voucher.getNumero());
		comprarepository.save(compraSalva);
		// System.out.println(info.getEndereco());
		
		/**
		 * Teste para HystrixCommand
		
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		 */
		
		
		

		return compraSalva;

		// RestTemplate client = new RestTemplate();
//		ResponseEntity<InfoFornecedorDTO> exchange =
//		client.exchange("http://fornecedor/info/"+compra.getEndereco().getEstado(), HttpMethod.GET,null,InfoFornecedorDTO.class);
//		System.out.println(exchange.getBody().getEndereco());

//		eurekaCliente.getInstances("fornecedor").stream().forEach(fornecedor-> System.out.println("localhost: "+fornecedor.getPort()));

	}

	public Compra realizaCompraFallback(CompraDTO compra) {
		if(compra.getCompraId() != null) {
			return comprarepository.findById(compra.getCompraId()).get();
		}
		
		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compra.getEndereco().toString());
		return compraFallback;
	}

	@HystrixCommand(threadPoolKey = "getByThreadPool")
	public Compra getById(Long id) {

		return comprarepository.findById(id).orElse(new Compra());
	}

}
