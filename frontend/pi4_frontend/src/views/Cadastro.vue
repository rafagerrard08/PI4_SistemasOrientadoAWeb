<template>
  <div class="container">
    <router-link to="/home">Listagem</router-link>
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Inclusão Produtos</h4>

          <div class="row">
            <div class="col form-group">
              <label>Nome Produto</label>
              <input v-model="nomeProduto" type="text" class="form-control form-control-sm" />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Avaliacão</label>
              <input v-model="avaliacao" type="number" class="form-control form-control-sm" />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Descrição detalhada</label>
              <textarea v-model="detalhes" type="text" class="form-control form-control-sm" />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Preço produto</label>
              <input v-model="preco" type="number" class="form-control form-control-sm" />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Quantidade estoque</label>
              <input v-model="quantidade" type="number" class="form-control form-control-sm" />
            </div>
          </div>

          <button class="btn btn-primary btn-sm" @click.prevent="salvar()">
            <i class="fa fa-search fa-fw"></i>Incluir
          </button>
          <button class="btn btn-primary btn-sm">
            <i class="fa fa-search fa-fw"></i>Cancelar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProdutoService from "@/services/produtos.js";
import alertUtils from '@/utils/alertUtils';

export default {
  name: "Cadastro",
  components: {},

  data() {
    return {
      nomeProduto: null,
      avaliacao: null,
      detalhes: null,
      preco: null,
      quantidade: null,
    };
  },

  created() {},

  methods: {
    salvar() {

      const produto = {};
      produto.nome = this.nomeProduto;
      produto.avaliacao = this.avaliacao;
      produto.descricao = this.detalhes;
      produto.preco = this.preco;
      produto.quantidade = this.quantidade;

      console.log(produto);
      //debugger; 
      ProdutoService.salvar(produto)
      .then(console.log)
      .catch(this.handleErrors);
    },

    handleErrors(erro) {
      alertUtils.alertFinalMid(erro, 'Ok', 'error');
      console.log("erro: ", erro);
    },
  },
};
</script>

<style scoped>
</style>

