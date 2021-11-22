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
                <th style="width: 25%">Data</th>
                <th style="width: 25%">Subtotal</th>
                <th style="width: 25%"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pedido of listaDePedidos" :key="pedido.id">
                <td data-th="Product">
                  <h5>Numero do Pedido: {{ pedido.id }}</h5>
                </td>
                <td data-th="Date">{{ pedido.data }}</td>
                <td data-th="Price">R$ {{ pedido.total }}</td>

                <td data-th="Quantity">
                  <button
                    class="btn btn-info btn-sm"
                    @click="detalhePedido(pedido.id)"
                  >
                    <b-icon icon="eye-fill"></b-icon>
                  </button>
                </td>
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
import router from "../router.js";
import alertUtils from "@/utils/alertUtils";
import axios from "axios";
export default {
  name: "Resumo",

  components: { NavbarComponent },

  data() {
    return {
      listaDePedidos: [],
      idCliente: null,
    };
  },

  computed: {},

  created() {
    this.idCliente = sessionStorage.getItem("idUsuario");

    axios.get("http://localhost:8080/pedidos/" + this.idCliente).then((res) => {
      this.listaDePedidos = res.data;

      alert(this.listaDePedidos);
    });
  },
  mounted() {
    //alert(JSON.stringify(vm))
    this.getTotal();
  },
  methods: {
    detalhePedido(idProduto) {},
    voltarPagina() {},
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
</style>
