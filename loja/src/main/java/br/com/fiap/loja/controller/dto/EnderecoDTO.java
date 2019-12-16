package br.com.fiap.loja.controller.dto;

public class EnderecoDTO {
	private String rua;
	private String numero;
	private String estado;
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "EnderecoDTO [rua=" + rua + ", numero=" + numero + ", estado=" + estado + "]";
	}
	
	

}
