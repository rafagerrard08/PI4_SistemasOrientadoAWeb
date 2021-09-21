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

              <input
                type="text"
                v-model="campoBuscar"
                class="form-control form-control-sm"
                @keyup="pesquisarProduto(campoBuscar)"
              />

              <button class="btn btn-primary btn-sm">
                <i class="fa fa-search fa-fw"></i>Adicionar
              </button>
            </div>
            <div class="padding-20"></div>
            <div class="row text-center">
              <div v-for="produto of produtos" :key="produto.id">
                <div class="col-md-6 col-sm-6">
                  <div class="thumbnail card">
                    <img src="logo.png" alt="foto" />
                    <div class="caption">
                      <h3>{{ produto.nome }}</h3>
                      <p>R${{ produto.preco }}</p>
                      <button class="add-to-cart">Comprar</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <nav aria-label="Page navigation example">
              <ul class="pagination">
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">«</span>
                    <span class="sr-only">Previous</span>
                  </a>
                </li>
                <li class="page-item"><a class="page-link" href="#" v-for="pagina of paginas" :key="pagina" @click="ListaProdutos(pagina - 1, '')">{{ pagina }}</a></li>
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">»</span>
                    <span class="sr-only">Next</span>
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import alertUtils from '@/utils/AlertUtils';

export default {
  name: "Home",
  components: {},

  data() {
    return {
      campoBuscar: null,
      produtos: [],
      paginas: [],
    };
  },

  mounted() {
    this.ListaProdutos(0, "");
  },

  methods: {
    ListaProdutos(pagina, nome) {
      axios
        .get("http://localhost:8080/produtos?itensPorPagina=1&pagina=" + pagina + "&nome=" + nome)
        .then((res) => {
          this.produtos = res.data.content;
          this.paginas = []
          for (let i = 0; i < res.data.totalPages; i++) {
            this.paginas.push(i+1)
          }
          alert(this.paginas)
        });
    },
    pesquisarProduto(campoBuscar) {
      this.ListaProdutos(0, campoBuscar);
    },
  },
};
</script>

<style scoped></style>
