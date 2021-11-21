package com.senac.PI4_ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*{
    "itens": [
        {
            "idProduto": 16,
            "quantidade": 1
        },
        {
            "idProduto": 17,
            "quantidade": 1
        }
    ],
    "dadosPagamento": {
        "forma": "credito",
        "numeroCartao": "12345678",
        "codVerificador": "321",
        "dataVencimento": "2021-11-30",
        "qtdParcelas": 6
    },
    "enderecoEntrega": {
        "id": 26,
        "tipo": "ENTREGA",
        "logradouro": "Rua Geraldo Martins dos Santos",
        "numero": "30",
        "complemento": null,
        "bairro": "Parque Grajaú",
        "cep": "04843-620",
        "padrao": true,
        "cidade": "São Paulo",
        "uf": "SP"
    },
    "valorFrete": 15
}*/

//        # JSON TEST SALVAR PEDIDO /\ 

public class PedidoDTO {

	private List<ItensDTO> itens;
	private EnderecoDTO enderecoEntrega;
	private PagamentoDTO dadosPagamento;
	private Double valorFrete;

	public List<ItensDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItensDTO> itens) {
		this.itens = itens;
	}

	public EnderecoDTO getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoDTO enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public PagamentoDTO getDadosPagamento() {
		return dadosPagamento;
	}

	public void setDadosPagamento(PagamentoDTO dadosPagamento) {
		this.dadosPagamento = dadosPagamento;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public class PagamentoDTO {

		private String forma;
		private String numeroCartao;
		private String codVerificador;
		private LocalDate dataVencimento;
		private Integer qtdParcelas;

		public String getForma() {
			return forma;
		}

		public void setForma(String forma) {
			this.forma = forma;
		}

		public String getNumeroCartao() {
			return numeroCartao;
		}

		public void setNumeroCartao(String numeroCartao) {
			this.numeroCartao = numeroCartao;
		}

		public String getCodVerificador() {
			return codVerificador;
		}

		public void setCodVerificador(String codVerificador) {
			this.codVerificador = codVerificador;
		}

		public LocalDate getDataVencimento() {
			return dataVencimento;
		}

		public void setDataVencimento(LocalDate dataVencimento) {
			this.dataVencimento = dataVencimento;
		}

		public Integer getQtdParcelas() {
			return qtdParcelas;
		}

		public void setQtdParcelas(Integer qtdParcelas) {
			this.qtdParcelas = qtdParcelas;
		}

	}

}
