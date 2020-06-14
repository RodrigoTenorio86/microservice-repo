package br.com.fiap.loja.controller.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class InfoEntregaDTO {
	private Long pedidoId;
	
	private LocalDate dataParaEntrega;
	
	private String enderecoOrigem;
	
	private String enderecoDestino;
}
