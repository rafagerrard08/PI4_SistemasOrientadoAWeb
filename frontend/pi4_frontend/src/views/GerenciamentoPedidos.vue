<template>
  <div class="container">
    <NavbarComponent />

    <div class="content">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title">Pedidos</h2>
          <table id="cart" class="table table-hover table-condensed">
            <thead>
              <tr>
                <th style="width: 25%">Numero</th>
                <th style="width: 16%">Data</th>
                <th style="width: 15%">Subtotal</th>
                <th style="width: 15%">Status</th>
                <th style="width: 15%"></th>
                <th style="width: 15%"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pedido of listaDePedidos" :key="pedido.id">
                <td data-th="Product">
                  <h5>Numero do Pedido: {{ pedido.id }}</h5>
                </td>
                <td data-th="Date">{{ pedido.data }}</td>
                <td data-th="Price">R$ {{ pedido.total }}</td>

                <td data-th="Status">
                  <b-form-select v-model="selected" class="mb-3">
                    <div v-for="opt of options" :key=opt.value>
                      <b-form-select-option :value=opt.value>{{opt.text}}</b-form-select-option>
                    </div>
                  </b-form-select>
                </td>
                <td data-th="Save" v-if="statusSelecionado != null">
                  <b-button variant="info" @click="salvarEstado"
                    >Salvar</b-button
                  >
                </td>

                <td data-th="Detail">
                  <button
                    class="btn btn-info btn-sm"
                    @click="detalhePedido(pedido)"
                  >
                    <b-icon icon="eye-fill"></b-icon>
                  </button>
                </td>
                <br />
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td>
                  <button
                    @click="voltarPagina()"
                    class="btn btn-danger btn-block"
                  >
                    Continue Comprando <i class="fa fa-angle-right"></i>
                  </button>
                </td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";
import alertUtils from "@/utils/alertUtils";
import axios from "axios";
export default {
  name: "GerenciamentosPedidos",

  components: { NavbarComponent },

  data() {
    return {
      listaDePedidos: [],
      idUsuario: null,
      showModal: false,
      statusSelecionado: null,
      options: [],
    };
  },

  created() {
    this.idUsuario = sessionStorage.getItem("idUsuario");

    if (this.idUsuario == null) {
      alertUtils.alertFinalMid(
        "Não foi possivel obter o id do usuario da sessão",
        "Ok",
        "error"
      );

      return this.$router.push("/loginCliente");
    }

    this.buscarPedidos(this.idUsuario);

    this.options = [
      { value: "AGUARDANDO_PAGAMENTO", text: "Aguardando Pagamento" },
      { value: "PAGAMENTO_REJEITADO", text: "Pagamento Rejeitado" },
      { value: "PAGAMENTO_APROVADO", text: "Pagamento Aprovado" },
      { value: "AGUARDANDO_RETIRADA", text: "Aguardando Retirada" },
      { value: "EM_TRANSITO", text: "Em Transito" },
      { value: "ENTREGUE", text: "Entregue" },
    ];
  },

  methods: {
    buscarPedidos() {
      axios.get("http://localhost:8080/pedidos").then((res) => {
        this.listaDePedidos = res.data;
      });
    },

    async detalhePedido(pedido) {
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

      // "dataVencimento":"16/11/2021",
      // "nuemro":"10499.71201 22517.701235 45678.901617 1 69800000012345"
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

    voltarPagina() {
      this.$router.push("/home");
    },

    salvarEstado() {
      alert("Salvar estado");
    },
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
.swal-wide {
  width: 850px !important;
}
</style>
