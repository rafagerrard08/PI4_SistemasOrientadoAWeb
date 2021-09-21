<template>
  <div class="container">
    <router-link to="/home">Listagem</router-link>
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Inclusão Produtos</h4>

          <div class="row">
            <div class="col form-group">
              <label>Nome do Produto</label>
              <input
                v-model="nomeProduto"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Marca</label>
              <input
                v-model="marca"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Descrição detalhada</label>
              <textarea
                v-model="detalhes"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Descrição detalhada</label>
              <textarea
                v-model="detalhes"
                type="text"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Preço produto</label>
              <input
                v-model="preco"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Quantidade estoque</label>
              <input
                v-model="quantidade"
                type="number"
                class="form-control form-control-sm"
              />
            </div>
          </div>
          <div class="row">
            <div class="col form-group">
              <label>Categoria</label><br>
              <select
                class="form-select form-select-sm"
                aria-label=".form-select-sm example"
               v-model="categoria" 
              >
                <option selected>Selecionle uma categoria</option>
                <option v-for="categoria of categorias" :key="categoria.id" :value="categoria.id">{{
                  categoria.nome
                }}</option>
              </select>
            </div>
          </div>

          <div class="row">
            <div class="col form-group">
              <label>Local Imagem</label>
              <input
                v-model="locationImagem"
                type="text"
                class="form-control form-control-sm"
              />
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
import axios from "axios";
import alertUtils from "@/utils/alertUtils";

export default {
  name: "Cadastro",
  components: {},

  data() {
    return {
      nomeProduto: null,
      detalhes: null,
      preco: null,
      quantidade: null,
      marca: null,
      categoria: null,
      produto: [],
      categorias: [],
    };
  },

  mounted() {
    this.ListaCategorias();
  },

  methods: {
    salvar() {
      this.produto.nome = this.nomeProduto;
      this.produto.marca = this.marca;
      this.produto.descricao = this.detalhes;
      this.produto.preco = this.preco;
      this.produto.quantidade = this.quantidade;
      this.produto.categoria = this.categoria;
      console.log(this.produto);
      //debugger;
      axios.post("http://localhost:8080/produtos",{
        
          nome: this.produto.nome,
          marca: this.produto.marca,
          descricao: this.produto.descricao,
          preco:  this.produto.preco,
          quantidade: this.produto.quantidade,
          categoriaId: this.produto.categoria
      })
    },
    ListaCategorias() {
      axios.get("http://localhost:8080/categorias").then((res) => {
        this.categorias = res.data;
      });
    },
    handleErrors(erro) {
      alertUtils.alertFinalMid(erro, "Ok", "error");
      console.log("erro: ", erro);
    },
  },
};
</script>

<style scoped></style>
