package br.com.fiap.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.fiap.loja.controller.dto.InfoFornecedorDTO;
import br.com.fiap.loja.controller.dto.InfoPedidoDTO;
import br.com.fiap.loja.controller.dto.ItemDaCompraDTO;

@FeignClient("fornecedor")
public interface FornecedorClient {

	@RequestMapping("/v1/info/{estado}")
	public InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado) ;

	@PostMapping("/v1/pedido")
	public InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
}
