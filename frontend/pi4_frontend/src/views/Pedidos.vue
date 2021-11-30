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
                <th style="width: 25%">Status</th>
                <th style="width: 20%"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pedido of listaDePedidos" :key="pedido.id">
                <td data-th="Product">
                  <h5>Numero do Pedido: {{ pedido.id }}</h5>
                </td>
                <td data-th="Date">{{ pedido.data }}</td>
                <td data-th="Price">R$ {{ pedido.total }}</td>
                <td data-th="Status">{{ pedido.estadoPedido }}</td>

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
import modalUtils from "@/utils/modalUtils";
import axios from "axios";
export default {
  name: "Pedidos",

  components: { NavbarComponent },

  data() {
    return {
      listaDePedidos: [],
      idCliente: null,
      showModal: false,
    };
  },

  created() {
    this.idCliente = sessionStorage.getItem("idUsuario");

    if (this.idCliente == null) {
      alertUtils.alertFinalMid(
        "Não foi possivel obter o id do cliente da sessão",
        "Ok",
        "error"
      );

      return this.$router.push("/loginCliente");
    }

    this.buscarPedidos(this.idCliente);
  },

  methods: {
    buscarPedidos() {
      axios
        .get("http://localhost:8080/pedidos/" + this.idCliente)
        .then((res) => {
          this.listaDePedidos = res.data;
        });
    },

    detalhePedido(pedido) {
      modalUtils.detalhesPedido(pedido);
    },

    voltarPagina() {
      this.$router.push("/home");
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
