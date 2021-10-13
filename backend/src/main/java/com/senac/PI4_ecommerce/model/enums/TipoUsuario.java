package com.senac.PI4_ecommerce.model.enums;

public enum TipoUsuario {
	ADMINISTRADOR(1, "Administrador"),
	ESTOQUISTA(2, "Estoquista");

	private Integer id;
	private String descricao;
	
	private TipoUsuario(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoUsuario toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(TipoUsuario tipo : TipoUsuario.values()) {
			if(id.equals(tipo.getId())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Id invalido: "+ id);
	}
}
