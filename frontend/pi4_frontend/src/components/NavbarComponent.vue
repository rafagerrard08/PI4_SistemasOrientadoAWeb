<template>
  <nav class="navbar custom navbar-inverse navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button
          type="button"
          class="navbar-toggle"
          data-toggle="collapse"
          data-target="#mynavbar"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>

        <router-link to="/home">
          <span class="navbar-brand">
            <img class="img img-logo" src="../assets/imagens/logo.png"
          /></span>
        </router-link>
      </div>
      <div class="collapse navbar-collapse" id="mynavbar">
        <ul class="nav navbar-nav navbar-right">
          <li>
            <router-link to="/carrinho">
              <span class="glyphicon glyphicon-shopping-cart">
                {{ qtdItens }} Carrinho</span
              >
            </router-link>
          </li>

          <li v-if="sessao == 'ADMINISTRADOR' || sessao == 'ESTOQUISTA'">
            <router-link to="/backoffice">
              <span class="glyphicon glyphicon-user"> Configuracoes</span>
            </router-link>
          </li>
          <li v-if="sessao == 'CLIENTE'">
            <b-dropdown id="dropdown-1" text="Area Do Cliente" class="m-md-2">
              <span
                ><b-dropdown-item @click="pedidos"
                  >Meus Pedidos</b-dropdown-item
                ></span
              >

              <span
                ><b-dropdown-item @click="telaClientes"
                  >Atualizar Dados</b-dropdown-item
                ></span
              >
            </b-dropdown>
          </li>
          <li v-else>
            <router-link to="/loginCliente">
              <span class="glyphicon glyphicon-user"> Login</span>
            </router-link>
          </li>
          <li>
            <router-link to="/logout">
              <span class="glyphicon glyphicon-log-out"> Sair</span>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavbarComponent",

  created() {},
  data() {
    return {};
  },

  computed: {
    sessao() {
      return sessionStorage.getItem("tipoUsuario");
    },

    qtdItens() {
      return vm.cartTotal;
    },
  },

  methods: {
    pedidos() {
      return this.$router.push("/pedidos/");
    },

    telaClientes() {
      const idCliente = sessionStorage.getItem("idUsuario");
      if (idCliente == null) {
        return this.$router.push("/loginCliente");
      }

      return this.$router.push("/clientes/" + idCliente);
    },
  },
};
</script>

<style scoped>
nav {
  display: flex;
  padding-bottom: 15px;
}

.img-logo {
  max-height: 100px;
  max-width: 100px;
}

.navbar.custom {
  height: 10%;
  margin: 0 auto;
}
</style>
