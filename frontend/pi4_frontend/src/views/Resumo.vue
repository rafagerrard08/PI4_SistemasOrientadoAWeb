<template>
  <div class="container">
    <NavbarComponent />

    <div class="content">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title">Checkout</h2>
          "Carrinho: " {{carrinho}} <br/><br/>
          "Quantidade itens carrinho: " {{totalItensCarrinho}} <br/><br/>
          "Valor do Frete: " {{valorFrete}} <br/><br/>
          "Dados checkout: " {{dadosPedido}} <br/>
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
      carrinho: vm.cart,
      totalItensCarrinho: vm.cartTotal, 
      dadosPedido: vm.dadosPedido,
      valorFrete: vm.valorFrete,

    };
  },

  computed: {},

  created() {
    this.idCliente = sessionStorage.getItem("idUsuario");
    if (this.idCliente == null) {
      alertUtils.alertFinalTop("Cliente não logado", "error");
      return router.push({ name: "loginCliente" });
    }

  },

  methods: {
    
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
</style>
