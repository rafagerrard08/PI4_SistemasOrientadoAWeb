<template>
  <div class="container">
    <NavbarComponent />

    <div class="content">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title">Checkout</h2>

          <div>
            <div class="row" style="margin-top: 30px">
              <div class="">
                <label>CEP</label>
                <input
                  v-model="cepEntrega"
                  type="text"
                  class="form-control form-control-sm"
                />
              </div>
              <button
                class="btn btn-primary btn-sm"
                @click.prevent="pesquisarCepEntrega()"
              >
                <i class="fa fa-search fa-fw"></i>Adicionar
              </button>
            </div>

            <div class="row" v-if="enderecosEntregas.length > 0">
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col"></th>
                    <th scope="col">CEP</th>
                    <th scope="col">Logradouro</th>
                    <th scope="col">Número</th>
                    <th scope="col">Bairro</th>
                    <th scope="col">Cidade</th>
                    <th scope="col">UF</th>
                    <th scope="col">Padrão</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(endereco, index) in enderecosEntregas"
                    :key="endereco.cep + index"
                  >
                    <td>
                      <b-form-radio
                        v-model="enderecoSelecionado"
                        name="endereco_entrega"
                        :value="index"
                        @change="setarEnderecoSelecionado()"
                      >
                      </b-form-radio>
                    </td>
                    <td>{{ endereco.cep }}</td>
                    <td>{{ endereco.logradouro }}</td>
                    <td>{{ endereco.numero }}</td>
                    <td>{{ endereco.bairro }}</td>
                    <td>{{ endereco.cidade }}</td>
                    <td>{{ endereco.uf }}</td>
                    <td>
                      <button @click="removerEnderecoEntrega(endereco.cep)">
                        Remover
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <div v-if="exibirPagamento">
            <h2 class="card-title">Forma de Pagamento</h2>
            <div class="row">
              <b-form-radio
                v-model="pagamentoSelecionado"
                name="forma_pagamento"
                value="boleto"
                @change="setarPagamentoSelecionado()"
              >
                Boleto</b-form-radio
              >

              <b-form-radio
                v-model="pagamentoSelecionado"
                name="forma_pagamento"
                value="credito"
                @change="setarPagamentoSelecionado()"
              >
                Cartão crédito</b-form-radio
              >
            </div>

            <div v-if="exibirCamposCartaoCredito">
              <!-- número, código verificador, data de vencimento e quantidade de parcelas -->
              <div class="row">
                <label>Número</label>
                <input
                  v-model="numeroCartao"
                  type="text"
                  class="form-control form-control-sm"
                />
              </div>
              <div class="row">
                <label>Código verificador</label>
                <input
                  v-model="codVerificador"
                  type="text"
                  class="form-control form-control-sm"
                />
              </div>
              <div class="row">
                <label>Data vencimento</label>
                <input
                  v-model="dataVencimento"
                  type="date"
                  class="form-control form-control-sm"
                />
              </div>
              <div class="row">
                <!-- <label>Parcelas</label>
                <b-form-select
                  v-model="qtdParcelas"
                  :options="vezes"
                ></b-form-select> -->

                <div class="col form-group">
                  <label>Parcelas</label><br />
                  <select
                    class="form-select"
                    aria-label=".form-select"
                    v-model="qtdParcelas"
                  >
                    <option
                      v-for="(vez, index) of vezes"
                      :key="vez + index"
                      :value="vez"
                    >
                      {{ vez }}
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <button
              @click="finalizarPedido()"
              class="btn btn-success btn-block"
            >
              Finalizar Pedido <i class="fa fa-angle-right"></i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";
import router from "../router.js";
import alertUtils from "@/utils/alertUtils";
import axios from "axios";
import swal from "sweetalert2";

export default {
  name: "Checkout",

  components: { NavbarComponent },

  data() {
    return {
      
      cepEntrega: null,
      enderecosEntregas: [],
      idCliente: null,
      enderecoSelecionado: vm.dadosPedido.enderecoEntrega,
      exibirPagamento: false,
      pagamentoSelecionado: vm.dadosPedido.pagamentoSelecionado,
      exibirCamposCartaoCredito: false,
      numeroCartao: vm.dadosPedido.numeroCartao,
      codVerificador: vm.dadosPedido.codVerificador,
      dataVencimento: vm.dadosPedido.dataVencimento,
      qtdParcelas: vm.dadosPedido.qtdParcelas,
      vezes: [1, 2, 3, 4, 5, 6],
    };
  },

  computed: {},

  created() {
    this.idCliente = sessionStorage.getItem("idUsuario");
    if (this.idCliente == null) {
      alertUtils.alertFinalTop("Cliente não logado", "error");
      return router.push({ name: "loginCliente" });
    }

    this.getUsuarioById();
  },

  methods: {
    getUsuarioById() {
      axios
        .get("http://localhost:8080/clientes/" + this.idCliente)
        .then((res) => {
          const retorno = res.data;
          this.email = retorno.email;
          this.cpf = retorno.cpf;
          this.nomeCliente = retorno.nomeCompleto;
          this.data = retorno.dataNascimento.split("T")[0];
          this.genero = retorno.genero;

          const endsEntregs = retorno.enderecos.filter(
            (e) => e.tipo === "ENTREGA"
          );

          for (const e of endsEntregs) {
            const cidade = e.cidade.nome;
            const uf = e.cidade.uf.nome;

            e.cidade = cidade;
            e.uf = uf;
          }

          this.enderecosEntregas.push(...endsEntregs);
          for(var i = 0; i < this.enderecosEntregas.length; i++){
            if (this.enderecosEntregas[i].cep == vm.dadosPedido.enderecoEntrega.cep) {
              alert(this.enderecosEntregas.indexOf(this.enderecoEntrega))
              this.enderecoSelecionado = this.enderecosEntregas[i]
              break;
            }
          }
        })
        .catch((err) => alertUtils.alertFinalTop(err, "error"));
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
      cep = cep.replace("-", "");
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
          this.addNaLista(endereco);
        });
    },

    async addNaLista(endereco) {
      endereco.tipo = "ENTREGA";
      this.enderecosEntregas.push(endereco);
    },

    removerEnderecoEntrega(cepParaRemover) {
      this.enderecosEntregas = this.enderecosEntregas.filter(
        (e) => e.cep != cepParaRemover
      );
    },

    setarEnderecoSelecionado() {
      this.exibirCamposCartaoCredito = false;
      this.exibirPagamento = true;
    },

    setarPagamentoSelecionado() {
      if (this.pagamentoSelecionado == "boleto") {
        this.exibirCamposCartaoCredito = false;
      } else {
        this.exibirCamposCartaoCredito = true;
      }
    },

    finalizarPedido() {
      alert(this.enderecoSelecionado)
     vm.dadosPedido.pagamentoSelecionado = this.pagamentoSelecionado;
      vm.dadosPedido.numeroCartao = this.numeroCartao;
      vm.dadosPedido.codVerificador = this.codVerificador;
      vm.dadosPedido.dataVencimento = this.dataVencimento;
      vm.dadosPedido.qtdParcelas = this.qtdParcelas;
      vm.dadosPedido.enderecoEntrega =
        this.enderecosEntregas[this.enderecoSelecionado];

      //vm.dadosPedido = dadosCheckout;

      return router.push({ name: "resumo" });
    },
  },
};
</script>

<style scoped>
.content {
  padding: 75px;
}
</style>
