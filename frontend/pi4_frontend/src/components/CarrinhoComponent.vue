<template>
  <div class="container">
    <h2>Carrinho de Compras</h2>

    <div v-if="produtosNoCarrinho.length > 0">
      <div class="row">
        <div>
          <label>CEP</label>
          <input
            v-model="cepFrete"
            type="text"
            class="form-control form-control-sm"
          />
        </div>
        <button class="btn btn-primary btn-sm" @click.prevent="calcularFrete()">
          <i class="fa fa-search fa-fw"></i>{{ textoFrete }} Frete
        </button>
      </div>

      <div v-if="exibirValoresFrete">
        <b-form-group
          label="Escolha o tipo de frete"
          v-slot="{ ariaDescribedby }"
        >
          <b-form-radio
            v-model="valorFreteSelecionado"
            :aria-describedby="ariaDescribedby"
            name="some-radios"
            :value="valorFast"
            @change="atualizarTotalzao()"
          >
            Sedex Fast Motion R$ {{ valorFast }}</b-form-radio
          >
          <b-form-radio
            v-model="valorFreteSelecionado"
            :aria-describedby="ariaDescribedby"
            name="some-radios"
            :value="valorMedium"
            @change="atualizarTotalzao()"
          >
            Sedex Medium Motion R$ {{ valorMedium }}</b-form-radio
          >
          <b-form-radio
            v-model="valorFreteSelecionado"
            :aria-describedby="ariaDescribedby"
            name="some-radios"
            :value="valorSlow"
            @change="atualizarTotalzao()"
          >
            Sedex Slow Motion R$ {{ valorSlow }}</b-form-radio
          >
        </b-form-group>
      </div>
    </div>

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
            <button
              v-if="item.quantidade <= 1"
              class="btn btn-info btn-sm"
              @click="removerProduto(item.produto.id)"
              disabled
            >
              <b-icon icon="dash"></b-icon>
            </button>
            <button
              v-else
              class="btn btn-info btn-sm"
              @click="removerProduto(item.produto.id)"
            >
              <b-icon icon="dash"></b-icon>
            </button>
            <input
              type="text"
              min="1"
              name="quantidade"
              size="1"
              :value="item.quantidade"
              disabled
            />
            <button
              class="btn btn-danger btn-sm"
              @click="adicionarProduto(item.produto.id)"
            >
              <b-icon icon="plus"></b-icon>
            </button>
          </td>
          <td data-th="Subtotal" class="text-center">
            R$ {{ getValorProdutos(item.produto.id) }}
          </td>
          <td class="actions" data-th="">
            <button
              class="btn btn-danger btn-sm"
              @click="deletarDoCarrinho(item.produto.id)"
            >
              <b-icon icon="trash-fill"></b-icon>
            </button>
          </td>
        </tr>
      </tbody>
      <tfoot>
        <tr class="visible-xs">
          <td class="text-center">
            <strong>Total R${{ totalzao }}</strong>
          </td>
        </tr>
        <tr>
          <td>
            <router-link to="/home">
              <a href="#" class="btn btn-warning"
                ><i class="fa fa-angle-left"></i> Continue Comprando</a
              >
            </router-link>
          </td>
          <td colspan="2" class="hidden-xs"></td>
          <td class=" text-center">
            <strong>Total: R$ {{ totalzao }}</strong>
          </td>
          <td>
            <button :disabled="valorFreteSelecionado < 1" @click="finalizarPedido()" class="btn btn-success btn-block"
              >Finalizar Pedido <i class="fa fa-angle-right"></i
            ></button>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
</template>

<script>
import router from "../router.js";
import axios from "axios";
import alertUtils from "@/utils/alertUtils";

export default {
  watch: {},
  name: "CarrinhoComponent",

  data() {
    return {
      produtosNoCarrinho: vm.cart,
      preco: null,
      valorFreteSelecionado: 0,
      idCliente: null,
      exibirValoresFrete: false,
      cepFrete: null,
      uf: null,
      valorFast: null,
      valorMedium: null,
      valorSlow: null,
      totalzao: 0,
      totalItens: 0,
    };
  },

  computed: {
    textoFrete() {
      return this.idCliente == null ? "Calcular" : "Recalcular";
    },
  },

  created() {
    this.idCliente = sessionStorage.getItem("idUsuario");
    if (this.idCliente != null) {
      this.init(this.idCliente);
    }
    this.getTotal();
  },

  methods: {
    init() {
      axios
        .get(`http://localhost:8080/clientes/${this.idCliente}`)
        .then((res) => {
          
         var enderecoPadrao =  res.data.enderecos.find(e => e.padrao == true);
         this.uf = enderecoPadrao.cidade.uf.nome;
         this.montarRadioComValores(this.uf);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    async calcularFrete() {
      if (this.cepFrete == null) {
        alertUtils.alertFinalTop("Digite um CEP valido!", "error");
      }

      this.cepFrete = this.cepFrete.replace("-", "");

      await axios
        .get(`https://viacep.com.br/ws/${this.cepFrete}/json/`)
        .then((res) => {
          if (res.data.erro) {
            alertUtils.alertFinalTop("ERRO ao buscar o CEP", "error");
            return;
          }

          this.valorFreteSelecionado = 0;
          this.uf = res.data.uf;

          this.montarRadioComValores(this.uf);
        })
        .catch(() => alertUtils.alertFinalTop("ERRO ao buscar o CEP", "error"));
    },

    montarRadioComValores(uf) {
      this.exibirValoresFrete = true;
      if (uf == "SP") {
        this.valorFast = 15;
        this.valorMedium = 10;
        this.valorSlow = 5;
      } else {
        this.valorFast = 20;
        this.valorMedium = 15;
        this.valorSlow = 10;
      }
    },

    atualizarTotalzao() {
      // debugger
      this.totalzao = this.totalItens + this.valorFreteSelecionado;
    },

    getTotal() {
      
      this.totalItens = 0;

      for (var i = 0; i < vm.cart.length; i++) {
        this.totalItens += vm.cart[i].produto.preco * vm.cart[i].quantidade;
      }

      this.atualizarTotalzao();
    },

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

    adicionarProduto(idProduto) {
      for (var i = 0; i < vm.cart.length; i++) {
        if (vm.cart[i].produto.id == idProduto) {
          vm.cart[i].quantidade++;
          break;
        }
      }
      this.getTotal()
      router.push({ name: "carrinho" });
    },

    deletarDoCarrinho(idProduto) {
      for (var i = 0; i < vm.cart.length; i++) {
        if (vm.cart[i].produto.id == idProduto) {
          vm.cart.splice(i, 1);
          this.getTotal()
          router.push({ name: "carrinho" });
        }
      }
    },

    removerProduto(idProduto) {
      for (var i = 0; i < vm.cart.length; i++) {
        if (vm.cart[i].produto.id == idProduto) {
          if (vm.cart[i].quantidade <= 1) {
            this.deletarDoCarrinho(idProduto);
          } else {
            vm.cart[i].quantidade--;
          }
          break;
        }
      }
      this.getTotal()
      router.push({ name: "carrinho" });
    },

    finalizarPedido(){
      if(this.valorFreteSelecionado < 1 ){
      alertUtils.alertFinalTop("Tipo de frete obrigatorio!","error");
      return;
      }

      vm.valorFrete = this.valorFreteSelecionado;
      if(this.idCliente == null){
        router.push({ name: "loginCliente" });
      } else {
        router.push({ name: "checkout" });
      }

      
    }
  },
};
</script>

<style>
.btn {
  margin: 5px;
}
</style>
