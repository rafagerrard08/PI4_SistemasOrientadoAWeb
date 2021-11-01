<template>
  <!-- Material form login -->
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <form>
          <p class="h4 text-center mb-4">ENTRE COM USUARIO E SENHA <br>(Essa pagina é para login dos CLIENTES)</p>
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
import alertUtils from "@/utils/alertUtils";

export default {
  name: "LoginClienteComponent",

  data() {
    return {
      email: null,
      senha: null, 
      valido: true,
      usuarioLogado: null,
    };
  },

  components: {},

  methods: {
    validarLogin(){
      sessionStorage.clear();
      axios.get("http://localhost:8080/clientes/validarLogin?email=" + this.email + "&senha=" + this.senha)
      .then((res) => {
        
        const tipoUser = res.data.tipoUsuario;
        sessionStorage.setItem("tipoUsuario", "CLIENTE");
        sessionStorage.setItem("idUsuario", res.data.id);


        this.$router.push('/home') 
      })
      .catch((err) => {
        console.log(err)
        alertUtils.alertFinalTop("Dados inválidos", "error");
      });
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
