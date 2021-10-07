package com.senac.PI4_ecommerce.controller.exceptions;

import java.io.Serializable;

public class StdError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private Integer status;
	private String erro;

	public StdError() {
		super();
	}

	public StdError(long timestamp, Integer status, String erro) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.erro = erro;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
