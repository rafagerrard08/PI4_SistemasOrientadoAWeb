package com.senac.PI4_ecommerce.model.enums;

public enum EstadoPedido {

	AGUARDANDO_PAGAMENTO(1, "Aguardando Pagamento"), 
	PAGAMENTO_REJEITADO(2, "Pagamento Rejeitado"),
	PAGAMENTO_APROVADO(3, "Pagamento Aprovado"), 
	AGUARDANDO_RETIRADA(4, "Aguardando Retirada"),
	EM_TRANSITO(5, "Em Transito"), 
	ENTREGUE(6, "Entregue");

	private int cod;
	private String descricao;

	private EstadoPedido(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
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
