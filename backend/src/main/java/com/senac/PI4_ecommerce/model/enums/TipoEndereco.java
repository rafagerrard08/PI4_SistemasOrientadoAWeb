package com.senac.PI4_ecommerce.model.enums;

public enum TipoEndereco {
	COBRANCA(1, "Cobrança"),
	ENTREGA(2, "Entrega");

	private Integer id;
	private String descricao;
	
	private TipoEndereco(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoEndereco toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(TipoEndereco tipo : TipoEndereco.values()) {
			if(id.equals(tipo.getId())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Id invalido: "+ id);
	}
}
