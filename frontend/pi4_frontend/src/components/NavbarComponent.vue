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
        <a href="index.html" class="navbar-brand"><img class="img img-logo" src="../assets/imagens/logo.png"/></a>
      </div>
      <div class="collapse navbar-collapse" id="mynavbar">
        <ul class="nav navbar-nav navbar-right">
          <li>
            <a href=""
              ><span class="glyphicon glyphicon-shopping-cart">
                Carrinho</span
              ></a
            >
          </li>

          <li v-if="sessao == 'ADMINISTRADOR' || sessao == 'ESTOQUISTA'">
            <router-link to="/backoffice">
              <span class="glyphicon glyphicon-user"> Configuracoes</span>
            </router-link>  
          </li>
          <li v-if="sessao == 'CLIENTE'">
            <router-link :to="telaClientes">
              <span class="glyphicon glyphicon-user"> Atualizar-dados</span>
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

    created() {
    },

    computed: {
        sessao() {
            return sessionStorage.getItem("tipoUsuario");
        },

        telaClientes() {
          return "/clientes/"+sessionStorage.getItem("idUsuario");
        }
    },
}
</script>

<style scoped>
nav {
  display: flex;
  padding-bottom: 15px;
}

.img-logo{
  max-height: 100px;
  max-width: 100px;
}

.navbar.custom {
  height: 10%;  
  margin: 0 auto;
}
</style>
