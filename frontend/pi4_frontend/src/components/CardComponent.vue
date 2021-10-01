<template>
  <mdb-card id="card">
    <mdb-view hover>
      <a href="#!">
        <mdb-card-image
          id="imagemPrincipal"
          src="''"
          alt="Card image cap"
        />
      </a>
    </mdb-view>
    <mdb-card-body id="corpo">
      <mdb-card-title>{{ produto.nome }}</mdb-card-title>
      <mdb-card-text>{{ produto.marca }}</mdb-card-text>
      <mdb-card-text id="preco">R$ {{ produto.preco }} <small>á vista</small></mdb-card-text>
      <mdb-btn color="primary" id="botao">Comprar</mdb-btn>
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

export default {
  name: "CardPage",
  components: {
    mdbCard,
    mdbCardImage,
    mdbCardBody,
    mdbCardTitle,
    mdbCardText,
    mdbBtn,
    mdbView,
  },
  data() {
    return {
      urlImagem: this.produto.imagens[0],

    };
  },
  props: ["produto"],
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

#preco small{
  font-size: 50%;
}
</style>
