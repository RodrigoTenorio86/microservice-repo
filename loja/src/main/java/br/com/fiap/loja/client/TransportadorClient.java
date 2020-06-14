package br.com.fiap.loja.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.loja.controller.dto.InfoEntregaDTO;
import br.com.fiap.loja.controller.dto.VoucherDTO;


@FeignClient("transportador")
public interface TransportadorClient {
	
	@PostMapping("/v1/entrega")
	public VoucherDTO reservaEntrega( InfoEntregaDTO pedidoDTO) ;
}
