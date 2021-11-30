<template>
  <div class="container">
    <NavbarComponent />
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Usuarios</h4>
          <div v-if="sessao == 'ADMINISTRADOR'">
            <router-link id="linkCadastrar" to="cadastroUsuario"
              ><img
                id="iconeAdd"
                src="https://img.icons8.com/ios-filled/50/000000/plus.png"
              />
            </router-link>
          </div>

          <input
            class="form-control mr-sm-2"
            type="text"
            placeholder="Busque por um usuario"
            v-model="campoBuscar"
            @keyup="ListaUsuarios"
            aria-label="Buscar"
          />
          <!-- <div>
            <b-table
              striped
              hover
              :items="usuarios"
              selectable
              bordered
              select-mode="single"
              @row-selected="onRowSelected"
              small
            ></b-table>
          </div> -->
          <div>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">ID</th>
                  <th scope="col">Nome</th>
                  <th scope="col">Email</th>
                  <th scope="col">Grupo</th>
                  <th scope="col">Estado</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="usuario in usuarios" :key="usuario.id">
                  <th scope="row">{{ usuario.id }}</th>
                  <td>{{ usuario.nome }}</td>
                  <td>{{ usuario.email }}</td>
                  <td>{{ usuario.tipoUsuario }}</td>
                  <td>{{ usuario.estadoUsuario }}</td>
                  <td v-if="isAtivo(usuario.estadoUsuario)">
                    <button
                      class="btnDesativar"
                      @click="atualizaEstado(usuario.id, 'INATIVO')"
                    >
                      Desativar
                    </button>
                  </td>
                  <td v-else>
                    <button
                      class="btnAtivar"
                      @click="atualizaEstado(usuario.id, 'ATIVO')"
                    >
                      Ativar
                    </button>
                  </td>
                  <td>
                    <button
                      class="editarBtn"
                      @click="direcionarAtualizacao(usuario.id)"
                    >
                      Editar
                    </button>
                  </td>

                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import NavbarComponent from "../components/NavbarComponent.vue";
import router from "../router";
import UsuariosService from "@/services/usuarios.js";
import alertUtils from "@/utils/alertUtils";

export default {
  name: "Usuarios",
  components: { NavbarComponent },

  data() {
    return {
      usuarios: [],
      campoBuscar: null,
      idUsuario: "",
      uriBase: "",
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

    this.ListaUsuarios();
  },

  methods: {
    ListaUsuarios() {
      if (this.campoBuscar === null) {
        this.uriBase = "http://localhost:8080/usuarios";
      } else {
        this.uriBase =
          "http://localhost:8080/usuarios?nome=" + this.campoBuscar;
      }

      axios.get(this.uriBase).then((res) => {
        this.usuarios = res.data.content;
      });
    },

    pesquisarUsuaio() {
      this.ListaUsuarios();
    },

    isAtivo(estadoUsuario) {
      if (estadoUsuario == "ATIVO") {
        return true;
      } else {
        return false;
      }
    },

    direcionarAtualizacao(idUsuario) {
      router.push({ name: "atualizarUsuario", params: { Id: idUsuario } });
    },

    atualizaEstado(idUsuario, estado) {
      axios
        .put(
          "http://localhost:8080/usuarios/updateEstado/" +
            idUsuario +
            "/" +
            estado
        )
        .then((res) => {
          location.reload();
        });
    },

    /*
    async onRowSelected(usuario) {
      let statusAtual = "ativar";
      if (usuario[0].estadoUsuario == "ATIVO") {
        statusAtual = "inativar";
      }

      const resposta = await alertUtils.alertConfirmacaoTop(
        `Deseja ${statusAtual} o usuario ${usuario[0].nome}?`
      );

      if (resposta) {
        let novoEstado = "ATIVO";
        if (usuario[0].estadoUsuario == "ATIVO") {
          novoEstado = "INATIVO";
        }

        await UsuariosService.atualizarEstado(usuario[0].id, novoEstado).then(
          (res) => {
            this.ListaUsuarios();
          }
        );
      }
    },*/
  },
};
</script>

<style>
.content {
  padding-top: 50px;
}

#iconeAdd {
  padding: 10px;
}
</style>
