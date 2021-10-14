<template>
  <!-- Material form login -->
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <form>
          <p class="h4 text-center mb-4">ENTRE COM USUARIO E SENHA</p>
          <label for="defaultFormLoginEmailEx" class="grey-text"
            >Seu E-Mail</label
          >
          <input
            type="email"
            id="defaultFormLoginEmailEx"
            class="form-control"
            required
            v-model="email"
          />
          <br />
          <label for="defaultFormLoginPasswordEx" class="grey-text"
            >Sua Senha</label
          >
          <input
            type="password"
            id="defaultFormLoginPasswordEx"
            class="form-control"
            required
            v-model="senha"
          />
          <p class="erro" v-if="!valido">Usuario ou senha invalido</p>
          <div class="text-center mt-4">
            <button class="btn btn-indigo" type="submit" @click="validarLogin()">Entrar</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <!-- Material form login -->
</template>

<script>
import axios from "axios";

export default {
  name: "LoginComponent",

  data() {
    return {
      email: null,
      senha: null, 
      valido: true,
    };
  },

  components: {},

  methods: {
    validarLogin(){
      axios.get("http://localhost:8080/usuarios/validarLogin?email=" + this.email + "&senha=" + this.senha).then((res) => {
        this.valido = res.data;
        this.$router.push('/Home') 
      })
      .catch(() => {
        this.valido = false;
      });
      alert(this.valido);
    },
  },
};
</script>

<style>
  .btn{
    margin-top: 15px;
  }

  .erro{
    color: red;
  }
</style>
