<template>
  <div class="container">
    <NavbarComponent />
    <div v-if="isMobile() == false" class="principal" >
      <div  class="left-column">
        <div id="carrossel">
          <img
            id="imgProduto"
            data-image="red"
            class="active"
            :src="imagemPrincipalProduto"
            alt=""
          />
          <VueSlickCarousel v-bind="settings">
            <div v-for="(imagem, idx) in imagensCarrossel" :key="idx + imagem">
              <div>
                <img
                  class="active imagem-carrossel"
                  :src="imagem"
                  @click="setImagem(imagem)"
                  style="width: "
                />
              </div>
            </div>
          </VueSlickCarousel>
        </div>
      </div>
      <div class="row right-column">
        <!-- Product Description -->
        <div class="product-description">
          <div>
            <span>{{ produto.categoria.nome }} </span>
            <button class="btn btn-primary p-100" v-if="tipoUsuario == 'ADMINISTRADOR'" @click="Editar()">
              Editar
            </button>
          </div>
          <h1>{{ produto.nome }}</h1>
          <h3>Marca: {{ produto.marca }}</h3>
          <h4>Código do produto: {{ produto.id }}</h4>
          <br />
          <p>
            {{ produto.descricao }}
          </p>
        </div>

        <!-- Product Configuration -->
        <div class="product-configuration">
          <!-- Product Color -->
          <div class="row">
            <i class="fas fa-star"></i>
            <h3>Avaliação dos Usuarios: {{ produto.avaliacao }}</h3>
          </div>
          <!-- Cable Configuration -->
          <div class="product-price">
          <span>R${{ produto.preco }}</span>
          <button class="cart-btn" @click.prevent="comprar()">Comprar</button>
        </div>
        </div>
      </div>
    </div>
    <div v-else>
    <div  class="row col-12 justify-content-center">
        <div id="carrossel">
          <img
            id="imgProduto"
            data-image="red"
            class="active"
            :src="imagemPrincipalProduto"
            style="width: 245px; height: 200px"
            alt=""
          />
          <VueSlickCarousel v-bind="settings">
            <div v-for="(imagem, idx) in imagensCarrossel" :key="idx + imagem">
              <div>
                <img
                  class="active imagem-carrossel"
                  :src="imagem"
                  @click="setImagem(imagem)"
                />
              </div>
            </div>
          </VueSlickCarousel>
        </div>
      </div>
      <!-- Right Column -->
      <div class="row">
        <!-- Product Description -->
        <div class="product-description">
          <div>
            <span>{{ produto.categoria.nome }} </span>
            <button class="btn btn-primary p-100" v-if="tipoUsuario == 'ADMINISTRADOR'" @click="Editar()">
              Editar
            </button>
          </div>
          <h1>{{ produto.nome }}</h1>
          <h3>Marca: {{ produto.marca }}</h3>
          <h4>Código do produto: {{ produto.id }}</h4>
          <br />
          <p>
            {{ produto.descricao }}
          </p>
        </div>

        <!-- Product Configuration -->
        <div class="product-configuration">
          <!-- Product Color -->
          <div class="row">
            <i class="fas fa-star"></i>
            <h3>Avaliação dos Usuarios: {{ produto.avaliacao }}</h3>
          </div>
          <!-- Cable Configuration -->
        </div>

        <!-- Product Pricing -->
        <div class="product-price">
          <span>R${{ produto.preco }}</span>
          <button class="cart-btn" @click.prevent="comprar()">Comprar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import DownloadService from '@/services/downloadService';
// import alertUtils from '@/utils/AlertUtils';
import axios from "axios";
import router from "../router.js";
import VueSlickCarousel from "vue-slick-carousel";
import "vue-slick-carousel/dist/vue-slick-carousel.css";
import "vue-slick-carousel/dist/vue-slick-carousel-theme.css";
import NavbarComponent from "../components/NavbarComponent.vue";
import alertUtils from "@/utils/alertUtils";
import carrinhoUtils from "@/utils/carrinhoUtils";

export default {
  name: "Produto",
  components: { VueSlickCarousel, NavbarComponent },

  data() {
    return {
      id: this.$route.params.Id,
      produto: null,
      settings: {
        arrows: false,
        dots: true,
        focusOnSelect: true,
        infinite: true,
        speed: 300,
        slidesToShow: 3,
        slidesToScroll: 1,
        touchThreshold: 5,
        swipe: true,
      },
      imagensCarrossel: [],
      imagemPrincipalProduto: null,
      imagensProduto: [],
      imagem: null,
      item: {
        produto: null,
        quantidade: null,
      },
      tipoUsuario: "",
    };
  },

  mounted() {
    this.buscarDadosProduto();
  },

  methods: {
    isMobile() {
      if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
        return true
      } else {
        return false
      }
    },
    buscarDadosProduto() {
      // alert(this.id)
      axios.get("http://localhost:8080/produtos/" + this.id).then((res) => {
        this.produto = res.data;

        this.imagemPrincipalProduto =
          "http://localhost:8080" + this.produto.imagemPrincipal;

        this.imagensCarrossel.push(this.imagemPrincipalProduto);
        for (var i in this.produto.imagens) {
          this.imagensCarrossel.push(
            "http://localhost:8080" + this.produto.imagens[i]
          );
        }
        this.tipoUsuario = sessionStorage.getItem("tipoUsuario")
      });
    },
    Editar() {
      router.push({ name: "cadastro", params: { Id: this.id } });
    },
    setImagem(imagem) {
      document.getElementById("imgProduto").src = imagem;
    },

    async comprar() {
      carrinhoUtils.adicionaAoCarrinho(this.produto);

      const continuarComprando = await alertUtils.alertConfirmacaoTop(
        "Deseja continuar comprando?"
      );
      if(continuarComprando == true){
         router.push({ name: "home" });
      } else {
         router.push({ name: "carrinho" });
      }
    },
  },
};
</script>

<style>
#imgProduto {
  max-width: 100%;
  max-height: 100%;
  min-width: 60%;
  min-height: 60%;

  margin: 0 auto;
  margin-top: 25 auto;
  padding: 20px;
  display: flex;
}

.slick-prev,
.slick-next {
  color: black;
  background: black !important;
}
</style>

<style scoped>
#carrossel {
  align-items: center;
}

.imagem-carrossel {
  display: block;
  margin-left: auto;
  margin-right: auto;

  max-width: 100%;
  max-height: 100%;
}

.principal {
  max-width: 1200px;
  margin: 100px auto ;
  padding: 20px;
  display: flex;
}
.left-column {
  width: 65%;
  position: relative;
  margin-top: 60px;
}

.left-column img {
  object-fit: cover;
  margin-top: 5px;

  align-content: center;

  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
  margin-left: 25%;
}

.right-column {
  width: 35%;
  margin-top: 60px;
}
.left-column img {
  width: 50%;

  left: 0;
  top: 0;
  opacity: 0;
  transition: all 0.3s ease;
}

.left-column img.active {
  opacity: 1;
}
.product-description {
  border-bottom: 1px solid #e1e8ee;
  margin-bottom: 20px;
}
.product-description span {
  font-size: 12px;
  color: #358ed7;
  letter-spacing: 1px;
  text-transform: uppercase;
  text-decoration: none;
}
.product-description h1 {
  font-weight: 300;
  font-size: 52px;
  color: #43484d;
  letter-spacing: -2px;
}
.product-description p {
  font-size: 16px;
  font-weight: 300;
  color: #86939e;
  line-height: 24px;
}
.product-price {
  display: flex;
  align-items: center;
}

.product-price span {
  font-size: 26px;
  font-weight: 300;
  color: #43474d;
  margin-right: 20px;
}

.cart-btn {
  display: inline-block;
  background-color: #7dc855;
  border-radius: 6px;
  font-size: 16px;
  color: #ffffff;
  text-decoration: none;
  padding: 12px 30px;
  transition: all 0.5s;
}
.cart-btn:hover {
  background-color: #64af3d;
}
@media (max-width: 940px) {
  .container {
    flex-direction: column;
    margin-top: 60px;
  }

  .left-column,
  .right-column {
    width: 100%;
  }

  .left-column img {
    width: 300px;
    right: 0;
    top: -65px;
    left: initial;
  }
}

@media (max-width: 535px) {
  .left-column img {
    width: 220px;
    top: -85px;
  }
}
</style>
