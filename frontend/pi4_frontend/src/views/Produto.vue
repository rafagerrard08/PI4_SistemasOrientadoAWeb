<template>
  <div class="container">
    <router-link to="/home">Listagem</router-link>
  
    
    <!-- Left Column / Headphones Image -->
    <div class="left-column">
      <img data-image="black" src="images/black.png" alt="" />
      <img data-image="blue" src="images/blue.png" alt="" />
      <img data-image="red" class="active" src="images/red.png" alt="" />
    </div>

    <!-- Right Column -->
    <div class="right-column">
      <!-- Product Description -->
      <div class="product-description">
        <div>
          <span>{{produto.categoria.nome}}</span>
          <button class="btn btn-primary" @click="Editar()">Editar</button>
        </div>
        <h1>{{produto.nome}}</h1>
        <h3>Marca: {{produto.marca}}</h3>
        <h4>Código do produto: {{produto.id}}</h4>
        <br>
        <p>
          {{produto.descricao}}
        </p>
      </div>

      <!-- Product Configuration -->
      <div class="product-configuration">
        <!-- Product Color -->
        <div class="row">
          <i class="fas fa-star"></i>
          <h1>{{produto.avaliacao}}</h1>
        </div>
        <!-- Cable Configuration -->
        
      </div>

      <!-- Product Pricing -->
      <div class="product-price">
        <span>R${{produto.preco}}</span>
        <a href="#" class="cart-btn">Comprar</a>
      </div>
    </div>
    </div>
</template>

<script>
// import DownloadService from '@/services/downloadService';
// import alertUtils from '@/utils/AlertUtils';
import axios from "axios";
import router from '../router.js';

export default {
  name: "Produto",
  components: {},

  data() {
    return {
      id: this.$route.params.Id,
      produto: [],
    };
  },

  mounted() {
    this.buscarDadosProduto();
  },

  methods: {
    buscarDadosProduto() {
      // alert(this.id)
      axios.get("http://localhost:8080/produtos/" + this.id).then((res) => {
/*         alert(res.data);
 */        this.produto = res.data;
      });
    },
    Editar(){
      router.push({ name: 'cadastro', params: { Id: this.id } })
    }
  },
};
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 15px;
  display: flex;
}
.left-column {
  width: 65%;
  position: relative;
}
 
.right-column {
  width: 35%;
  margin-top: 60px;
}
.left-column img {
  width: 100%;
  position: absolute;
  left: 0;
  top: 0;
  opacity: 0;
  transition: all 0.3s ease;
}
 
.left-column img.active {
  opacity: 1;
}
.product-description {
  border-bottom: 1px solid #E1E8EE;
  margin-bottom: 20px;
}
.product-description span {
  font-size: 12px;
  color: #358ED7;
  letter-spacing: 1px;
  text-transform: uppercase;
  text-decoration: none;
}
.product-description h1 {
  font-weight: 300;
  font-size: 52px;
  color: #43484D;
  letter-spacing: -2px;
}
.product-description p {
  font-size: 16px;
  font-weight: 300;
  color: #86939E;
  line-height: 24px;
}
.product-price {
  display: flex;
  align-items: center;
}
 
.product-price span {
  font-size: 26px;
  font-weight: 300;
  color: #43474D;
  margin-right: 20px;
}
 
.cart-btn {
  display: inline-block;
  background-color: #7DC855;
  border-radius: 6px;
  font-size: 16px;
  color: #FFFFFF;
  text-decoration: none;
  padding: 12px 30px;
  transition: all .5s;
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

