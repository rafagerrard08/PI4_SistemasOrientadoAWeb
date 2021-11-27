package com.senac.PI4_ecommerce.model;


import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;
	private String numeroCartao;
	private String codVerificador;
	private Date dataVencimento;
	
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, Pedido pedido, Integer numeroDeParcelas, String numeroCartao, String codVerificador, Date dataVencimento) {
		super(id, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
		this.numeroCartao = numeroCartao;
		this.codVerificador = codVerificador;
		this.dataVencimento = dataVencimento;
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public String getCodVerificador() {
		return codVerificador;
	}

	public void setCodVerificador(String codVerificador) {
		this.codVerificador = codVerificador;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	
		
}
