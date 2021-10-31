<template>
  <div class="container">
    <NavbarComponent />
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Cadastro de Cliente</h4>

          <div class="row">
            <div class="col form-group">
              <label>Email</label>
              <input
                v-model="email"
                type="email"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Nome do Completo</label>
              <input
                v-model="nomeCliente"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>CPF</label>
              <input
                v-model="cpf"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Data de Nascimento</label>
              <input
                v-model="data"
                type="date"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Genero</label>
              <select name="genero" v-model="genero">
                <option value="masculino">Masculino</option>
                <option value="feminino">Feminino</option>
              </select>
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>CEP (Faturamento)</label>
              <input
                v-model="cepFaturamento"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <button
            type="submit"
            class="btn btn-primary btn-sm"
            @click.prevent="pesquisarCepFaturamento()"
          >
            <i class="fa fa-search fa-fw"></i>Pesquisar CEP
          </button>

          <div class="row" v-if="enderecosFaturamento.length > 0">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">CEP</th>
                  <th scope="col">Logradouro</th>
                  <th scope="col">Bairro</th>
                  <th scope="col">Cidade</th>
                  <th scope="col">UF</th>
                  <th scope="col">Tipo</th>
                  <th scope="col">Padrão</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="endereco in enderecosFaturamento"
                  :key="endereco.cep"
                >
                  <td>{{ endereco.cep }}</td>
                  <td>{{ endereco.logradouro }}</td>
                  <td>{{ endereco.bairro }}</td>
                  <td>{{ endereco.localidade }}</td>
                  <td>{{ endereco.uf }}</td>
                  <td>Faturamento</td>
                  <td>Não</td>
                  <td>
                    <button @click="removerEnderecoFaturamento()">
                      Remover
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          <div class="row" style="margin-top: 30px">
            <div class="col form-group">
              <label>CEPs (Entregas)</label>
              <input
                v-model="cepEntrega"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <button
            type="submit"
            class="btn btn-primary btn-sm"
            @click.prevent="pesquisarCepEntrega()"
          >
            <i class="fa fa-search fa-fw"></i>Pesquisar CEP
          </button>

          <div class="row" v-if="enderecosEntregas.length > 0">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">CEP</th>
                  <th scope="col">Logradouro</th>
                  <th scope="col">Bairro</th>
                  <th scope="col">Cidade</th>
                  <th scope="col">UF</th>
                  <th scope="col">Tipo</th>
                  <th scope="col">Padrão</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="endereco in enderecosEntregas" :key="endereco.cep">
                  <td>{{ endereco.cep }}</td>
                  <td>{{ endereco.logradouro }}</td>
                  <td>{{ endereco.bairro }}</td>
                  <td>{{ endereco.localidade }}</td>
                  <td>{{ endereco.uf }}</td>
                  <td>Faturamento</td>
                  <td>
                    <input type="radio" name="endereco_padrao" :checked="endereco.padrao"/>
                  </td>
                  <td>
                    <button @click="removerEnderecoEntrega(endereco.cep)">
                      Remover
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          <div class="row" style="margin-top: 30px">
            <div class="col form-group">
              <label>Senha</label>
              <input
                v-model="senha"
                type="password"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <button
            type="submit"
            class="btn btn-primary btn-sm"
            @click.prevent="salvar()"
          >
            <i class="fa fa-search fa-fw"></i>Incluir
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProdutoService from "@/services/produtos.js";
import axios from "axios";
import alertUtils from "@/utils/alertUtils";
import swal from "sweetalert2";
import NavbarComponent from "../components/NavbarComponent.vue";

export default {
  name: "Clientes",
  components: { NavbarComponent },

  data() {
    return {
      nomeCliente: null,
      senha: null,
      email: null,
      cpf: null,
      data: null,
      cepFaturamento: null,
      cepEntrega: null,
      genero: null,
      enderecosFaturamento: [],
      enderecosEntregas: [],
    };
  },

  methods: {
    salvar() {},

    async pesquisarCepFaturamento() {
      if (this.enderecosFaturamento.length > 0) {
        alertUtils.alertFinalTop(
          "É permitido apenas um endereço de faturamento",
          "warning"
        );
        return;
      }

      await this.buscarEndereco(this.cepFaturamento);
    },

    async pesquisarCepEntrega() {
      const cepDuplicado = this.enderecosEntregas.filter(
        (end) => end.cep.replace("-", "") == this.cepEntrega
      );

      if (cepDuplicado.length > 0) {
        alertUtils.alertFinalTop("Endereço já inserido", "error");
        return;
      }

      console.log(this.enderecosEntregas)
      await this.buscarEndereco(this.cepEntrega, "entrega");
    },

    async buscarEndereco(cep, lista) {
      await axios
        .get(`https://viacep.com.br/ws/${cep}/json/`)
        .then((res) => {
          if (res.data.erro) {
            alertUtils.alertFinalTop("ERRO ao buscar o CEP", "error");
            return;
          }

          if (lista === "entrega") this.enderecosEntregas.push(res.data);
          else this.enderecosFaturamento.push(res.data);
        })
        .catch(() => alert("ERRO ao buscar o CEP"));
    },

    removerEnderecoFaturamento() {
      this.enderecosFaturamento = [];
    },

    removerEnderecoEntrega(cepParaRemover) {
      this.enderecosEntregas = this.enderecosEntregas.filter(
        (e) => e.cep != cepParaRemover
      );
    },

    async alertNumero(endereco) {
      const { value: numero } = await swal.fire({
        title: "Input email address",
        input: "numero",
        inputLabel: "Your email address",
        inputPlaceholder: "Enter your email address",
      });

      if (numero === null || numero === "") {
        swal.fire("Numero inválido");
      }

      endereco.logradouro = endereco.logradouro + ", " + numero;

      console.log(endereco);
    },
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
</style>
