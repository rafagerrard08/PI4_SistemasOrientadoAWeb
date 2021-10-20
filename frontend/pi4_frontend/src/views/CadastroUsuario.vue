<template>
  <div class="container">
    <NavbarComponent />
    <div class="row principal">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <form v-if="id > 0">
          <h4>Atualizacao de Usuario</h4>

          <div class="form-group">
            <label for="nome">Nome Completo:</label>
            <input
              type="text"
              class="form-control"
              id="nome"
              v-model="usuario.nome"
              placeholder="Informe seu nome."
              required
            />
          </div>
          <div class="form-group">
            <label for="cpf">CPF:</label>
            <input
              type="text"
              class="form-control"
              id="cpf"
              v-model="usuario.cpf"
              placeholder="Informe seu cpf."
              required
            />
          </div>
          <div class="form-group">
            <label for="senhaPrin">Informe sua senha</label>
            <input
              type="password"
              class="form-control"
              id="senha"
              placeholder="Senha"
              v-model="senhaPrin"
              required
            />
          </div>
          <div class="form-group">
            <label for="senhaSec">Confirme sua senha</label>
            <input
              type="password"
              class="form-control"
              id="senha"
              placeholder="Confirme a Senha"
              v-model="senhaSec"
              @keyup="testarSenhasIguais()"
              required
            />
          </div>
          <p class="erro" v-if="!valido">As senhas nao sao iguais!</p>
         <div class="form-group">
            <label for="Grupo">Informe o Grupo</label>
            <select
              class="form-control"
              id="grupo"
              v-model="grupoSelecionado"
              required
            >
              <option v-for="tipo of tiposUsuario" :value="tipo" :key="tipo">{{
                tipo
              }}</option>
            </select>
          </div>
          <button
            @click="atualizarUsuario"
          >
            Atualizar
          </button>

          <p class="erro" v-if="msgObrigatorio">
            Todos os campos da pagina sao de preenchimento obrigatorio.
          </p>
        </form>

        <form v-else>
          <h4>Cadastro de Usuario</h4>

          <div class="form-group">
            <label for="nome">Nome Completo:</label>
            <input
              type="text"
              class="form-control"
              id="nome"
              v-model="nome"
              placeholder="Informe seu nome."
              required
            />
          </div>
          <div class="form-group">
            <label for="cpf">CPF:</label>
            <input
              type="text"
              class="form-control"
              id="cpf"
              v-model="cpf"
              placeholder="Informe seu cpf."
              required
            />
          </div>
          <div class="form-group">
            <label for="email">Endereço de e-mail</label>
            <input
              type="email"
              class="form-control"
              id="email"
              v-model="email"
              placeholder="nome@eloja.com"
              required
            />
          </div>
          <div class="form-group">
            <label for="senhaPrin">Informe sua senha</label>
            <input
              type="password"
              class="form-control"
              id="senha"
              placeholder="Senha"
              v-model="senhaPrin"
              required
            />
          </div>
          <div class="form-group">
            <label for="senhaSec">Confirme sua senha</label>
            <input
              type="password"
              class="form-control"
              id="senha"
              placeholder="Confirme a Senha"
              v-model="senhaSec"
              @keyup="testarSenhasIguais()"
              required
            />
          </div>
          <p class="erro" v-if="!valido">As senhas nao sao iguais!</p>
          <div class="form-group">
            <label for="Grupo">Informe o Grupo</label>
            <select class="form-control" id="grupo" v-model="grupo" required>
              <option v-for="tipo of tiposUsuario" :key="tipo" :value="tipo">{{
                tipo
              }}</option>
            </select>
          </div>
          <button
            type="submit"
            class="btn btn-primary"
            @click="cadastrarUsuario"
          >
            Cadastrar
          </button>
          <p class="erro" v-if="msgObrigatorio">
            Todos os campos da pagina sao de preenchimento obrigatorio.
          </p>
        </form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";
import axios from "axios";

export default {
  components: { NavbarComponent },

  data() {
    return {
      id: this.$route.params.Id,

      nome: "",
      cpf: "",
      email: "",
      senha: "",
      valido: true,
      senhaPrin: null,
      senhaSec: null,
      tiposUsuario: ["ADMINISTRADOR", "ESTOQUISTA"],
      grupo: "",
      msgObrigatorio: false,
      usuario: [],
    };
  },

  mounted() {
    this.getUsuario();
  },

  methods: {
    testarSenhasIguais() {
      this.valido = false;
      if (this.senhaPrin === this.senhaSec) {
        this.valido = true;
        this.senha = this.senhaPrin;
        this.usuario.senha = this.senha;
      }
    },

    getUsuario() {
      axios.get("http://localhost:8080/usuarios/id/" + this.id).then((res) => {
        this.usuario = res.data;
        this.grupoSelecionado = this.usuario.tipoUsuario;
      });
    },

    cadastrarUsuario() {
      if (
        (this.nome === "" ||
          this.cpf === "" ||
          this.email === "" ||
          this.senha === "" ||
          this.grupo === "") &&
        this.email.indexOf("@") != -1
      ) {
        this.msgObrigatorio = true;
      } else {
        axios
          .post("http://localhost:8080/usuarios", {
            nome: this.nome,
            cpf: this.cpf,
            email: this.email,
            senha: this.senha,
            tipoUsuario: this.grupo,
          })
          .then((res) => {
            this.usuario = res.data;
          })
          .catch((errors) => {
            alert(errors);
          });
      }
    },
    testeAt(){
      alert("teste");
    },

    atualizarUsuario() {
      alert(JSON.stringify(this.usuario));

      if (
        (this.usuario.nome === "" ||
          this.usuario.cpf === "" ||
          this.usuario.senha === "" ||
          this.usuario.tipoUsuario === "")
      ) {
        this.msgObrigatorio = true;
      } else {
        axios
          .put("http://localhost:8080/usuarios/" + this.id, {
            nome: this.usuario.nome,
            cpf: this.usuario.cpf,
            email: this.usuario.email,
            senha: this.usuario.senha,
            tipoUsuario: this.usuario.tipoUsuario, 
            estadoUsuario: this.usuario.estadoUsuario
          })
          .then((res) => {
            this.usuario = res.data;
            alert(res.data);
          })
          .catch((errors) => {
            alert(errors);
          });
      }
    },
  },

  

    
};
</script>

<style>
.principal {
  padding-top: 100px;
}
.erro {
  color: red;
}
</style>
