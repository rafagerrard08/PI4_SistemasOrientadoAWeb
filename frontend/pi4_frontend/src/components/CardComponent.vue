<template>
  <mdb-card id="card">
    <mdb-view hover>
      <a href="#!">
        <mdb-card-image
          id="imagemPrincipal"
          :src="imagemPrincipalProduto"
          alt="Card image cap"
        />
      </a>
    </mdb-view>
    <mdb-card-body id="corpo">
      <mdb-card-title>{{ produto.nome }} </mdb-card-title>
      <mdb-card-text>{{ produto.marca }}</mdb-card-text>
      <mdb-card-text id="preco"
        >R$ {{ produto.preco }} <small>á vista</small></mdb-card-text
      >
      <button type="button" class="btn btn-primary"
        id="botao"
        @click="redirecionarProduto(produto.id)"
      >
        + Detalhes
      </button>
      <br/>
      <button type="button" class="btn btn-success"
        variant="success"
        id="botao"
        @click="adicionaAoCarrinho()"
      >
        Adicionar ao Carrinho
      </button>
    </mdb-card-body>
  </mdb-card>
</template>

<script>
import {
  mdbCard,
  mdbCardImage,
  mdbCardBody,
  mdbCardTitle,
  mdbCardText,
  mdbBtn,
  mdbView,
} from "mdbvue";

import router from "../router";

export default {
  name: "CardComponent",
  components: {
    mdbCard,
    mdbCardImage,
    mdbCardBody,
    mdbCardTitle,
    mdbCardText,
    mdbView,
  },

  data() {
    return {
      imagemPrincipalProduto: null,
      item: {
        produto: null,
        quantidade: null,
      },
      produtoNoCarrinho: false,
    };
  },

  props: ["produto"],

  mounted() {
    this.getImagemPrincipal();
  },

  methods: {
    redirecionarProduto(id) {
      router.push({ name: "produto", params: { Id: id } });
    },

    getImagemPrincipal() {
      this.imagemPrincipalProduto =
        "http://localhost:8080" + this.produto.imagemPrincipal;
    },

    adicionaAoCarrinho() {
      alert(vm.cart.length)
      for(var i = 0; i < vm.cart.length; i++){
        if(vm.cart[i].produto.id == this.produto.id){
          vm.cart[i].quantidade += 1
          return
        }
      }

      this.item.produto = this.produto;
      this.item.quantidade = 1;
      vm.cart.push(this.item);
      
    },



  },
};
</script>

<style>
#imagemPrincipal img {
  object-fit: cover;
  margin-top: 5px;

  align-content: center;

  max-width: 250px;
  max-height: 200px;
  width: auto;
  height: auto;
}

#card {
  max-width: 400px;
  min-height: 400px;

  margin: 15px;
  width: 100%;
  height: 100%;

  border: 2px solid gray;
  border-radius: 5px;

  justify-content: center;
  align-items: center;
  align-content: center;
}

#corpo {
  margin-bottom: 15px;
  margin-top: 15px;
}

#preco {
  font-size: 150%;
  font-style: inherit;
}

#preco small {
  font-size: 50%;
}

#botao {
  margin: 10px auto;

}
</style>
