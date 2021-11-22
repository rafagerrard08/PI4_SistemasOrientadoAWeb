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
              <router-link :to="pedidos">
                  <span><b-dropdown-item>Meus Pedidos</b-dropdown-item></span>
              </router-link>
              <router-link :to="telaClientes">
                  <span><b-dropdown-item>Atualizar Dados</b-dropdown-item></span>
              </router-link>
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

    telaClientes() {
      return "/clientes/" + sessionStorage.getItem("idUsuario");
    },

    pedidos() {
      return "/pedidos";
    },

    qtdItens() {
      return vm.cartTotal;
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
