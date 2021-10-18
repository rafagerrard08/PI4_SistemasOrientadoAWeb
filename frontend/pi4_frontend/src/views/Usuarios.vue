<template>
  <div class="container">
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Usuarios</h4>
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
                  <th scope="col">Cpf</th>
                  <th scope="col">Tipo</th>
                  <th scope="col">Estado</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for=" usuario in usuarios" :key=usuario.id>
                  <th scope="row">{{ usuario.id }}</th>
                  <td>{{ usuario.nome }}</td>
                  <td>{{ usuario.email }}</td>
                  <td>{{ usuario.cpf }}</td>
                  <td>{{ usuario.tipoUsuario }}</td>
                  <td>{{ usuario.estadoUsuario }}</td>
                  <td><button class="editarBtn">Editar</button></td>
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
import UsuariosService from "@/services/usuarios.js";
import alertUtils from "@/utils/alertUtils";

export default {
  name: "Usuarios",

  data() {
    return {
      usuarios: [],
    };
  },

  mounted() {
    this.ListaUsuarios();
  },

  methods: {
    ListaUsuarios() {
      axios.get("http://localhost:8080/usuarios").then((res) => {
        this.usuarios = res.data.content;
      });
    },

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
    },
  },
};
</script>

<style></style>
