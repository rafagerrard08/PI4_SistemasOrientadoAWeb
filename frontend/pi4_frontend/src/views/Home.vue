<template>
  <div class="container">
    <router-link to="/cadastro">Cadastro</router-link>
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Produtos</h4>
          <div class="row">
            <div class="col form-group">
              <label>Produto</label>

              <input type="text" class="form-control form-control-sm" />
              <button class="btn btn-primary btn-sm">
                <i class="fa fa-search fa-fw"></i>Buscar
              </button>
              <button class="btn btn-primary btn-sm">
                <i class="fa fa-search fa-fw"></i>Adicionar
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProdutoService from "@/services/produtos.js";
// import alertUtils from '@/utils/AlertUtils';

export default {
  name: "Home",
  components: {},

  data() {
    return {};
  },

  created() {
    ProdutoService.listar()
      .then(console.log)
      .catch(this.handleErrors);

  },

  methods: {
    handleErrors(erro) {
      console.log("erro: ", erro);
    },
  },
};
</script>

<style scoped>
</style>

