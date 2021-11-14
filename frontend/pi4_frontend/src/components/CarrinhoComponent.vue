<template>
  <div class="container">
    <h2>Carrinho de Compras</h2>

    <table id="cart" class="table table-hover table-condensed">
      <thead>
        <tr>
          <th style="width:40%">Produto</th>
          <th style="width:10%">Preco</th>
          <th style="width:18%">Quantidade</th>
          <th style="width:22%" class="text-center">Subtotal</th>
          <th style="width:10%"></th>
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
            <strong>Total R${{ getTotal() }}</strong>
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
          <td class="hidden-xs text-center">
            <strong>Total: R$ {{ getTotal() }}</strong>
          </td>
          <td>
            <a href="#" class="btn btn-success btn-block"
              >Finalizar Pedido <i class="fa fa-angle-right"></i
            ></a>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
</template>

<script>
import router from "../router.js";
import swal from "sweetalert2";

export default {
  watch: {},
  name: "CarrinhoComponent",

  data() {
    return {
      produtosNoCarrinho: vm.cart,
      preco: null,
    };
  },

  mounted() {},

  components: {},

  methods: {
    getTotal() {
      var total = 0;

      for (var i = 0; i < vm.cart.length; i++) {
        total += this.getValorProdutos(vm.cart[i].produto.id);
      }
      return total;
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
      router.push({ name: "carrinho" });
    },

    deletarDoCarrinho(idProduto) {
      for (var i = 0; i < vm.cart.length; i++) {
        if (vm.cart[i].produto.id == idProduto) {
          vm.cart.splice(i, 1);
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
      router.push({ name: "carrinho" });
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
  },
};
</script>

<style>
.btn {
  margin: 5px;
}
</style>
