<template>
  <div class="container">
    <NavbarComponent />
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Backoffice</h4>
          <router-link to="/home">Lista de Produtos</router-link>
          <br />
          <router-link to="/usuarios">Lista de Usuarios</router-link> <br/>
          <div v-if="sessao == 'ESTOQUISTA'">
          <router-link to="/gerenciamentopedidos">Gereniamento de Pedidos</router-link> <br/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";


export default {
  name: "Backoffice",
  
  components: { NavbarComponent },

    computed: {
    sessao() {
      return sessionStorage.getItem("tipoUsuario");
    },
  },


  beforeCreate: function () {
    if (!this.$session.exists()) {
      this.$router.Push('/')
      alert();
    }
  },

  data() {
    return {
      
    };
  },

};
</script>

<style>

.content{
  padding-top: 5%;
}

</style>