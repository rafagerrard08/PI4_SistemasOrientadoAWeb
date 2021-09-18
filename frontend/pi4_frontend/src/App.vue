<template>
<body>
    <header>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mynavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="index.html" class="navbar-brand">Lojae</a>
                </div>
                <div class="collapse navbar-collapse" id="mynavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="cart.html"><span class="glyphicon glyphicon-shopping-cart"> Carrinho</span></a></li>
                        <li><a href="settings.html"><span class="glyphicon glyphicon-user"> Configuracoes</span></a></li>
                        <li><a href="logout.html"><span class="glyphicon glyphicon-log-out"> Sair</span></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <main>
        <div class="container">
            <div class="jumbotron jumbotron_style">
                <h1>Bem-Vindo a Loja</h1>
                <p> Aqui podemos mostrar o produto mais recente adicionado na loja
                </p>
            </div>

            <div class="padding-20"></div>
                <div class="row text-center">
                    <div v-for="produto of produtos" :key="produto.id">
                        <div class="col-md-6 col-sm-6">
                            <div class="thumbnail card">
                                <img src="logo.png" alt="foto">
                                <div class="caption">
                                    <h3>{{produto.nome}}</h3>
                                    <p>R${{produto.preco}}</p>
                                    <button class="add-to-cart">Comprar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div class="padding-20"></div>
    </main>
    <footer>
        <p>Aqui vai as informacoes do rodape</p>
    </footer>

</body>

</template>

<script>

import Produto from './service/produtos'
export default {

  data(){
    return {
      produtos:[]
    }
  },

  mounted(){
    Produto.listar().then(resposta => {
      console.log(resposta.data.content)
      this.produtos = resposta.data.content
    })
  }
  
}

</script>

<style>

</style>
