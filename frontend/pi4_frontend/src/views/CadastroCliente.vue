<template>
  <div class="container">
    <NavbarComponent />
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title">Cadastro de Cliente</h2>

          <div class="row">
            <div class="col form-group">
              <label>Email</label>
              <input
                v-model="email"
                type="email"
                class="form-control form-control-sm"
                :readonly="atualizacao"
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
                :readonly="atualizacao"
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
              <select
                name="genero"
                v-model="genero"
                class="form-control form-control-sm"
              >
                <option value="masculino">Masculino</option>
                <option value="feminino">Feminino</option>
              </select>
            </div>
          </div>

          <div class="row">
            <div class="">
              <label>CEP (Faturamento)</label>
              <input
                v-model="cepFaturamento"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
            <button
              type="submit"
              class="btn btn-primary btn-sm"
              @click.prevent="pesquisarCepFaturamento()"
            >
              <i class="fa fa-search fa-fw"></i>Pesquisar CEP
            </button>
          </div>

          <div class="row" v-if="enderecosFaturamento.length > 0">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">CEP</th>
                  <th scope="col">Logradouro</th>
                  <th scope="col">Número</th>
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
                  <td>{{ endereco.numero }}</td>
                  <td>{{ endereco.bairro }}</td>
                  <td>{{ endereco.cidade }}</td>
                  <td>{{ endereco.uf }}</td>
                  <td>{{ endereco.tipo }}</td>
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
            <div class="">
              <label>CEPs (Entregas)</label>
              <input
                v-model="cepEntrega"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
            <button
              type="submit"
              class="btn btn-primary btn-sm"
              @click.prevent="pesquisarCepEntrega()"
            >
              <i class="fa fa-search fa-fw"></i>Pesquisar CEP
            </button>
          </div>

          <div class="row" v-if="enderecosEntregas.length > 0">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">CEP</th>
                  <th scope="col">Logradouro</th>
                  <th scope="col">Número</th>
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
                  <td>{{ endereco.numero }}</td>
                  <td>{{ endereco.bairro }}</td>
                  <td>{{ endereco.cidade }}</td>
                  <td>{{ endereco.uf }}</td>
                  <td>{{ endereco.tipo }}</td>
                  <td>
                    <input
                      type="radio"
                      name="endereco_padrao"
                      :checked="endereco.padrao"
                      @change="mudarEnderecoPadrao(endereco.cep)"
                    />
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
            <i class="fa fa-search fa-fw"></i
            >{{ atualizacao ? "Atualizar" : "Incluir" }}
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
      atualizacao: false,
      id: null
    };
  },

  created() {
    if (this.$route.params.id != null) {
      this.atualizacao = true;
      this.id = this.$route.params.id;
      this.getUsuarioSessao();
    } else {
      this.atualizacao = false;
    }
  },

  methods: {
    getUsuarioSessao() {
      axios
        .get("http://localhost:8080/clientes/"+this.id)
        .then((res) => {
          const retorno = res.data;
          console.log(retorno);
          this.email = retorno.email;
          this.cpf = retorno.cpf;
          this.nomeCliente = retorno.nomeCompleto;
          this.data = retorno.dataNascimento.split("T")[0];
          this.genero = retorno.genero;

          const endFatur = retorno.enderecos.find(
            (e) => e.tipo === "FATURAMENTO"
          );

          const cidade = endFatur.cidade.nome;
          const uf = endFatur.cidade.uf.nome;

          endFatur.cidade = cidade;
          endFatur.uf = uf;

          this.enderecosFaturamento.push(endFatur);

          const endsEntregs = retorno.enderecos.filter(
            (e) => e.tipo === "ENTREGA"
          );

          debugger
          for (const e of endsEntregs) {
            const cidade = e.cidade.nome;
            const uf = e.cidade.uf.nome;

            e.cidade = cidade;
            e.uf = uf;
          }

          this.enderecosEntregas.push(...endsEntregs);
        })
        .catch((err) => alertUtils.alertFinalTop(err, "error"));
    },

    salvar() {
      if (this.enderecosEntregas.length > 1) {
        const lista = this.enderecosEntregas.filter((e) => e.padrao);

        if (lista == null || lista == undefined || lista.length == 0) {
          alertUtils.alertFinalTop(
            "Selecione um endereço de entrega padrão",
            "warning"
          );
          return;
        }
      }

      const cliente = {};
      (cliente.nomeCompleto = this.nomeCliente), (cliente.senha = this.senha);
      cliente.email = this.email;
      cliente.cpf = this.cpf;
      cliente.dataNascimento = this.data;
      cliente.genero = this.genero;

      const enderecos = [
        ...this.enderecosFaturamento,
        ...this.enderecosEntregas,
      ];

      cliente.enderecos = enderecos;

      if (this.atualizacao) {
        axios
          .put("http://localhost:8080/clientes/"+this.id, cliente)
          .then((res) => {
            console.log(res);
            this.$router.push("/home");
          })
          .catch((err) => alertUtils.alertFinalTop(err, "error"));
      } else {
        axios
          .post("http://localhost:8080/clientes", cliente)
          .then((res) => {
            console.log(res);
            this.$router.push("/home");
          })
          .catch((err) => alertUtils.alertFinalTop(err, "error"));
      }
    },

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

          const endereco = {};
          endereco.cep = res.data.cep;
          endereco.logradouro = res.data.logradouro;
          endereco.bairro = res.data.bairro;
          endereco.cidade = res.data.localidade;
          endereco.uf = res.data.uf;
          endereco.padrao = false;

          this.solicitarNumeroLogradouro(endereco, lista);
        })
        .catch(() => alert("ERRO ao buscar o CEP"));
    },

    async solicitarNumeroLogradouro(endereco, lista) {
      await swal
        .fire({
          title: "Número do Logradouro",
          html: `<input type="text" id="numero" class="swal2-input" placeholder="Digite o numero do logradouro">`,
          confirmButtonText: "OK",
          focusConfirm: false,
          preConfirm: () => {
            const numero = swal.getPopup().querySelector("#numero").value;
            if (!numero) {
              swal.showValidationMessage(`Número inválido`);
            }
            return numero;
          },
        })
        .then((result) => {
          endereco.numero = result.value;
          this.addNaLista(endereco, lista);
        });
    },

    async addNaLista(endereco, lista) {
      if (lista === "entrega") {
        if (
          this.enderecosEntregas == null ||
          this.enderecosEntregas == undefined ||
          this.enderecosEntregas.length == 0
        ) {
          endereco.padrao = true;
        }
        endereco.tipo = "ENTREGA";
        this.enderecosEntregas.push(endereco);
      } else {
        endereco.tipo = "FATURAMENTO";
        this.enderecosFaturamento.push(endereco);

        const mesmoEndereco = await alertUtils.alertConfirmacaoTop(
          "Deseja usar o mesmo endereço para entregas?"
        );
        if (mesmoEndereco) {
          const entrgEnder = { ...endereco };
          entrgEnder.tipo = "ENTREGA";
          if (
            this.enderecosEntregas == null ||
            this.enderecosEntregas == undefined ||
            this.enderecosEntregas.length == 0
          ) {
            entrgEnder.padrao = true;
          }
          this.enderecosEntregas.push(entrgEnder);
        }
      }
    },

    removerEnderecoFaturamento() {
      this.enderecosFaturamento = [];
    },

    removerEnderecoEntrega(cepParaRemover) {
      this.enderecosEntregas = this.enderecosEntregas.filter(
        (e) => e.cep != cepParaRemover
      );
    },

    mudarEnderecoPadrao(cep) {
      for (const end of this.enderecosEntregas) {
        end.padrao = false;
      }

      const endEncont = this.enderecosEntregas.find((e) => e.cep === cep);

      endEncont.padrao = true;
    },
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
</style>
