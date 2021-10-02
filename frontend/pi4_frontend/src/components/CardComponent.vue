<template>
  <mdb-card id="card">
    <mdb-view hover>
      <a href="#!">
        <mdb-card-image
          id="imagemPrincipal"
          :src="require('/Users/victor/tmp/pi4_ecommerce/imagens/3/IMG1.jpg')"
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
      <b-button
        variant="success"
        id="botao"
        @click="redirecionarProduto(produto.id)"
      >
        Comprar
      </b-button>
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
import router from "../router.js";

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
    return {};
  },

  props: ["produto"],

  methods: {
    redirecionarProduto(id) {
      router.push({ name: "produto", params: { Id: id } });
    },
    getUrl(){
      return this.produto.imagens[0];
    }
  },
};
</script>

<style>
#imagemPrincipal img {
  max-width: 100%;
  min-height: 100%;
  object-fit: cover;
  margin: 0;

  display: flex;
  align-content: center;
}

#card {
  margin: 15px;
  width: 100%;
  height: 42vh;

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
</style>
