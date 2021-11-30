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
                <th>Numero</th>
                <th>Data</th>
                <th>Subtotal</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pedido of listaDePedidos" :key="pedido.id">
                <td data-th="Product">
                  <h5>{{ pedido.id }}</h5>
                </td>
                <td data-th="Date">
                  {{ pedido.data.split("-").reverse().join("/") }}
                </td>
                <td data-th="Price">
                  R$ {{ pedido.total + pedido.valorFrete }}
                </td>
                <td data-th="Status">{{ pedido.estadoPedido }}</td>

                <td data-th="Edit">
                  <button
                    class="btn btn-info btn-sm"
                    @click="alterarStatus(pedido)"
                  >
                    <span class="glyphicon glyphicon-edit"></span>
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
import alertUtils from "@/utils/alertUtils";
import modalUtils from "@/utils/modalUtils";
import swal from "sweetalert2";
import axios from "axios";
export default {
  name: "GerenciamentosPedidos",

  components: { NavbarComponent },

  // { value: "AGUARDANDO_PAGAMENTO", text: "Aguardando Pagamento" },
  // { value: "PAGAMENTO_REJEITADO", text: "Pagamento Rejeitado" },
  // { value: "PAGAMENTO_APROVADO", text: "Pagamento Aprovado" },
  // { value: "AGUARDANDO_RETIRADA", text: "Aguardando Retirada" },
  // { value: "EM_TRANSITO", text: "Em Transito" },
  // { value: "ENTREGUE", text: "Entregue" },

  data() {
    return {
      listaDePedidos: [],
      idUsuario: null,
      showModal: false,
      statusSelecionado: null,
      selected: null,
      options: [
        "AGUARDANDO_PAGAMENTO",
        "PAGAMENTO_REJEITADO",
        "PAGAMENTO_APROVADO",
        "AGUARDANDO_RETIRADA",
        "EM_TRANSITO",
        "ENTREGUE",
      ],
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
  },

  methods: {
    buscarPedidos() {
      axios.get("http://localhost:8080/pedidos").then((res) => {
        this.listaDePedidos = res.data;
      });
    },

    async alterarStatus(pedido) {
      const statusPossiveis = this.options.filter(
        (opt) => opt != pedido.estadoPedido
      );

      let novoStatus = null;

      const htmlSelect = this.criarSelect(statusPossiveis);

      await swal
        .fire({
          title: "Escolha o novo status",
          html: htmlSelect,
          confirmButtonText: "OK",
          focusConfirm: false,
          preConfirm: () => {
            const status = swal.getPopup().querySelector("#status").value;
            if (!status) {
              swal.showValidationMessage(`Opção inválida`);
            }
            return status;
          },
        })
        .then((result) => {
          novoStatus = result.value;
          this.atualizarStatus(pedido.id, novoStatus);
        });
    },

    criarSelect(statuses) {
      let html = `
          <select id="status" style="font-size:20px;" class="form-select" aria-label="Default select example">
          <option selected>Selecione</option>
      `;

      for (const status of statuses) {
        html = html + `<option value="${status}">${status}</option>`;
      }

      html = html + "</select>";

      return html;
    },

    atualizarStatus(idPedido, novoStatus) {
      axios
        .put(`http://localhost:8080/pedidos/${idPedido}/status/${novoStatus}`)
        .then((res) => {
          this.buscarPedidos();
        });
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
