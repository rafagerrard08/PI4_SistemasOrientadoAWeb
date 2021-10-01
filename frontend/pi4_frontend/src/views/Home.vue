<template>
  <div class="container">
    <div class="content">
      <div class="card">
        <div class="card-body">
          <NavbarComponent />
          <div class="row" id="first">
            <div class="col form-group">
              <nav class="navbar navbar-light bg-light">
                <form class="form-inline">
                  <input
                    class="form-control mr-sm-2"
                    type="text"
                    placeholder="Busque por um produto"
                    v-model="campoBuscar"
                    @keyup="pesquisarProduto(campoBuscar)"
                    aria-label="Buscar"
                  />
                </form>
                <router-link id="linkCadastrar" to="/cadastro/0"
                  ><img
                    src="https://img.icons8.com/ios-filled/50/000000/plus.png"
                /></router-link>
              </nav>
            </div>
            <div class="padding-20"></div>
            <div class="row text-center">
              <div v-for="produto of produtos" :key="produto.id">
                <div class="col-md-4 col-sm-4">
                  <CardComponent :produto="produto"/>
                  <!-- <div class="thumbnail card">
                    <p>{{ produto.imagens[0] }}</p>
                    <img v-bind:src="produto.imagens[0]" alt="foto" />
                    <div class="caption">
                      <h5>{{ produto.marca }}</h5>
                      <h3>{{ produto.nome }}</h3>
                      <p>R${{ produto.preco }}</p>
                      <button
                        class="add-to-cart"
                        @click="redirecionar(produto.id)"
                      >
                        Comprar
                      </button>
                    </div> 
                  </div>-->
                </div>
              </div>
            </div>
            <nav aria-label="Page navigation example">
              <ul class="pagination">
                <li class="page-item">
                  <a
                    class="page-link"
                    href="#"
                    v-for="pagina of paginas"
                    :key="pagina"
                    @click="ListaProdutos(pagina - 1, '')"
                    >{{ pagina }}</a
                  >
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
import router from "../router";
// import alertUtils from '@/utils/AlertUtils';
import NavbarComponent from "../components/NavbarComponent.vue";
import CardComponent from "../components/CardComponent.vue";

export default {
  name: "Home",
  components: { NavbarComponent, CardComponent },

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
        .get(
          "http://localhost:8080/produtos?pagina=" + pagina + "&nome=" + nome
        )
        .then((res) => {
          this.produtos = res.data.content;
          this.paginas = [];
          for (let i = 0; i < res.data.totalPages; i++) {
            this.paginas.push(i + 1);
          }
        });
    },
    pesquisarProduto(campoBuscar) {
      this.ListaProdutos(0, campoBuscar);
    },
    redirecionar(id) {
      router.push({ name: "produto", params: { Id: id } });
    },
  },
};
</script>

<style scoped>
nav {
  display: flex;
}
#linkCadastrar {
  position: absolute;
  left: 96%;
}

#first {
  padding: 75px;
}
</style>
