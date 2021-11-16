package com.senac.PI4_ecommerce.model.enums;

public enum EstadoPedido {
	
	AGUARDANDO_PAGAMENTO(1, "Aguardando Pagamento"),
	EM_SEPARACAO(2, "Em Separacao"),
	AGUARDANDO_ENVIO(3, "Aguardando Envio"),
	ENVIADO(3, "Enviado"),
	ENTREGUE(3, "Entregue");


	
	private int cod;
	private String descricao;
	
	private EstadoPedido(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static EstadoPedido toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (EstadoPedido x : EstadoPedido.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}

