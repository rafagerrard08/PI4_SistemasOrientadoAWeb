import axios from "axios";
import alertUtils from "@/utils/alertUtils";

export default {

    async detalhesPedido(pedido) {

        const htmlItens = await this.montarHTMLItens(pedido.itens);
        const htmlPagamento = this.montarHTMLPagamento(pedido.pagamento);

        const htmlBody = `
        <div class="row" style="font-size: 20px;">
          <h2>Produtos: </h2>
            <span  >${htmlItens} </span>
        </div>
        <div class="row" style="font-size: 20px;">
          <h2>Frete: </h2>
          R$ ${pedido.valorFrete}.00
        </div>
        <div class="row" style="font-size: 20px;">
          <h2>Total pedido: </h2>
          R$ ${pedido.total} 
        </div>
  
        <div class="row" style="font-size: 20px;">
          <h2>Endereço entrega: </h2>
          <div class="row">
            Logradouro: ${pedido.enderecoDeEntrega.logradouro}, ${pedido.enderecoDeEntrega.numero}
          </div>
          <div class="row">
            Bairro: ${pedido.enderecoDeEntrega.bairro}
          </div>
          <div class="row">
            CEP: ${pedido.enderecoDeEntrega.cep}
          </div>
          <div class="row">
            Localidade: ${pedido.enderecoDeEntrega.cidade.nome} - ${pedido.enderecoDeEntrega.cidade.uf.nome}
          </div>
        </div>
  
        <div class="row" style="font-size: 20px;">
          <h2>Dados pagamento: </h2>
            ${htmlPagamento}
        </div>
        `;

        alertUtils.alertHTML("DETALHES", htmlBody);

    },

    async montarHTMLItens(itens) {
        const itensDetalhado = [];
        for (const item of itens) {
            await axios
                .get("http://localhost:8080/produtos/" + item.itemId)
                .then((res) => {
                    const it = {
                        nome: res.data.nome,
                        marca: res.data.marca,
                        qtd: item.quantidade,
                        valorTotal: item.subTotal,
                    };
                    itensDetalhado.push(it);
                });
        }

        let htmlItens = "";
        for (const item of itensDetalhado) {
            const linha = `
          <div class="row">
            Nome: ${item.nome}
            | Marca: ${item.marca}
            | Quantidade: ${item.qtd}
            | Valor total: ${item.valorTotal}
          </div>
          `;

            htmlItens = `${htmlItens} ${linha}`;
        }

        return htmlItens;
    },

    montarHTMLPagamento(pagamento) {
        if (pagamento.codVerificador != undefined) {
            return `
          <div class="row">
            Forma pagamento: Cartão crédito
          </div>
          <div class="row">
            Numero cartão: ${this.numeroCartao(pagamento.numeroCartao)}
          </div>
          <div class="row">
            Quantidade parcelas: ${pagamento.numeroDeParcelas}
          </div>
          `;
        }

        return `
          <div class="row">
            Forma pagamento: Boleto
          </div>
          <div class="row">
            Código de pagamento: ${pagamento.nuemro}
          </div>
          <div class="row">
            Data vencimento: ${pagamento.dataVencimento}
          </div>
        `;
    },

    numeroCartao(numero) {
        if (numero.length <= 4) {
            return "**** **** **** " + numero;
        }

        return "**** **** **** " + numero.substring(numero.length - 4);
    },


}