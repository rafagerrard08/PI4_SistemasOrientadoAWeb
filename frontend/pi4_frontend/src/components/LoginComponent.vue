<template>
  <!-- Material form login -->
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <form>
          <p class="h4 text-center mb-4">ENTRE COM USUARIO E SENHA <br>(Essa pagina é para login dos usuarios de Backoffice)</p>
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
            <button class="btn btn-indigo" type="submit" @click.prevent="validarLogin()">Entrar</button>
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
  name: "LoginComponent",

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
      axios.get("http://localhost:8080/usuarios/validarLogin?email=" + this.email + "&senha=" + this.senha)
      .then((res) => {
        
        const tipoUser = res.data.tipoUsuario;
        sessionStorage.setItem("tipoUsuario", res.data.tipoUsuario);
        sessionStorage.setItem("idUsuario", res.data.id);


        this.$router.push('/backoffice') 
      })
      .catch(() => {
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
