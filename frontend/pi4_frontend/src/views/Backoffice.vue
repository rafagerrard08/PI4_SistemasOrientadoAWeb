<template>
  <div class="container">
    <NavbarComponent />
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Backoffice</h4>
          <div>
            <router-link to="/home">Lista de Produtos</router-link>
          </div>
          <div>
            <router-link to="/usuarios">Lista de Usuarios</router-link>
          </div>
          <div v-if="sessao == 'ESTOQUISTA'">
            <router-link to="/gerenciamentopedidos"
              >Gerenciamento de Pedidos</router-link
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";
import alertUtils from "@/utils/alertUtils";

export default {
  name: "Backoffice",

  components: { NavbarComponent },

  data() {
    return {
    };
  },

  computed: {
    sessao() {
      return sessionStorage.getItem("tipoUsuario");
    },
  },

  created() {

    if (this.sessao == "CLIENTE" || this.sessao == null) {
      alertUtils.alertFinalMid(
        "Clientes não podem acessar o Backoffice!!",
        "Ok",
        "error"
      );
      return this.$router.push("/home");
    }

  },
};
</script>

<style>
.content {
  padding-top: 5%;
}
</style>