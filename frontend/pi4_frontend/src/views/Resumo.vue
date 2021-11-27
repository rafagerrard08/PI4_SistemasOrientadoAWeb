<template>
  <div class="container">
    <NavbarComponent />

    <div class="content">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title">Resumo do Pedido</h2>
          <table id="cart" class="table table-hover table-condensed">
            <thead>
              <tr>
                <th style="width: 40%">Produto</th>
                <th style="width: 10%">Preco</th>
                <th style="width: 18%">Quantidade</th>
                <th style="width: 22%" class="text-center">Subtotal</th>
                <th style="width: 10%"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item of produtosNoCarrinho" :key="item.produto.id">
                <td data-th="Product">
                  <div class="row">
                    <div class="col-sm-2 hidden-xs">
                      <img
                        :src="getImagemPrincipal(item.produto.imagemPrincipal)"
                        alt="..."
                        class="img-responsive"
                      />
                    </div>
                    <div class="col-sm-10">
                      <h6 class="nomargin">{{ item.produto.marca }}</h6>
                      <h5 class="nomargin">{{ item.produto.nome }}</h5>
                    </div>
                  </div>
                </td>
                <td data-th="Price">R$ {{ item.produto.preco }}</td>
                <td data-th="Quantity">
                 
                  <input
                    type="text"
                    min="1"
                    name="quantidade"
                    size="1"
                    :value="item.quantidade"
                    disabled
                  />
                  
                </td>
                <td data-th="Subtotal" class="text-center">
                  R$ {{ getValorProdutos(item.produto.id) }}
                </td>
                
              </tr>
              <tr>
                <td><span>Endereço de entrega: {{dadosPedido.enderecoEntrega.logradouro}}, {{dadosPedido.enderecoEntrega.numero}} - {{dadosPedido.enderecoEntrega.bairro}} - {{dadosPedido.enderecoEntrega.cidade}}</span></td>
              </tr>
              <tr>  
                <td>Forma de pagamento: {{dadosPedido.pagamentoSelecionado}}</td>
              </tr>
              <tr>
                <td class="col-4" v-if="dadosPedido.pagamentoSelecionado == 'credito'"> Número do cartão: {{numeroCartao}}</td>
                <td class="col-4" v-if="dadosPedido.pagamentoSelecionado == 'credito'">
                  validade: {{dadosPedido.dataVencimento.split("-").reverse().join("/")}}
                </td>
                <td class="col-4" v-if="dadosPedido.pagamentoSelecionado == 'credito'">
                  parcelas: {{dadosPedido.qtdParcelas}}x
                </td>
              </tr>
            </tbody>
            <tfoot>
              
              <tr>
                <td>
                  <button @click="voltarPagina()" class="btn btn-danger btn-block"
                    >Voltar <i class="fa fa-angle-right"></i
                  ></button>
                </td>
                <td  class="visible-xs"></td>
                <td class=" text-center">
                  <span>frete: R$ {{ valorFrete }}</span>
                </td>
                <td class="text-center">
                  <strong>Total R${{ totalzao }}</strong>
                </td>
                <td>
                  <button @click="finalizarPedido()" class="btn btn-success btn-block"
                    >Finalizar Pedido <i class="fa fa-angle-right"></i
                  ></button>
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
import router from "../router.js";
import alertUtils from "@/utils/alertUtils";
import axios from "axios";


export default {
  name: "Resumo",

  components: { NavbarComponent },

  data() {
    return {
      produtosNoCarrinho: vm.cart,
      totalItensCarrinho: vm.cartTotal, 
      dadosPedido: vm.dadosPedido,
      valorFrete: vm.valorFrete,
      totalzao: 0,
      totalItens: 0,

    };
  },

  computed: {

    numeroCartao() {

      if(this.dadosPedido.numeroCartao.length <= 4) {
        return "**** **** **** "+this.dadosPedido.numeroCartao;
      }

      let nrCard = this.dadosPedido.numeroCartao;
      return "**** **** **** "+ nrCard.substring(nrCard.length-4)
    }
  },

  created() {
    this.idCliente = sessionStorage.getItem("idUsuario");
    if (this.idCliente == null) {
      alertUtils.alertFinalTop("Cliente não logado", "error");
      return router.push({ name: "loginCliente" });
    }

  },
  mounted(){
    this.getTotal()
  },
  methods: {
    getValorProdutos(idProduto) {
      for (var i = 0; i < vm.cart.length; i++) {
        if (vm.cart[i].produto.id == idProduto) {
          this.preco = vm.cart[i].produto.preco * vm.cart[i].quantidade;
          break;
        }
      }
      return this.preco;
    },
    getImagemPrincipal(str) {
      return "http://localhost:8080/" + str;
    },
    atualizarTotalzao() {
      this.totalzao = this.totalItens + vm.valorFrete;

    },

    getTotal() {
      this.totalItens = 0;

      for (var i = 0; i < vm.cart.length; i++) {
        this.totalItens += this.getValorProdutos(vm.cart[i].produto.id);
      }
      //alert(JSON.stringify(vm.dadosPedido))
      this.atualizarTotalzao();
    },
    voltarPagina(){
      this.$router.push('/checkout')
    },

    finalizarPedido() {

      const pedido = {};

      const itens = [];
      for (const item of vm.cart) {
        itens.push({idProduto: item.produto.id, quantidade: item.quantidade})
      }
      pedido.itens = itens;

      const pagamento = {}
      pagamento.forma = vm.dadosPedido.pagamentoSelecionado;
      pagamento.numeroCartao = vm.dadosPedido.numeroCartao;
      pagamento.codVerificador = vm.dadosPedido.codVerificador;
      pagamento.dataVencimento = vm.dadosPedido.dataVencimento;
      pagamento.qtdParcelas = vm.dadosPedido.qtdParcelas;
      
      pedido.dadosPagamento = pagamento;
      
      pedido.enderecoEntrega = vm.dadosPedido.enderecoEntrega;

      pedido.valorFrete = vm.valorFrete;

      axios
        .post(`http://localhost:8080/pedidos`, pedido)
        .then((res) => {
          alertUtils.alertFinalTop("PEDIDO CONFIRMADO!! O numero do seu pedido é " + res.data ,  "success");
          vm.cart = []
          vm.cartTotal = 0
          router.push({ name: "home" });
        })
        .catch((err) => {
          alertUtils.alertFinalTop(err, "error")
        });
    },
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
</style>
