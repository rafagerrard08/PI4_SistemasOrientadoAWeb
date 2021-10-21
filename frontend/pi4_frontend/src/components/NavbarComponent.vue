<template>
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button
          type="button"
          class="navbar-toggle"
          data-toggle="collapse"
          data-target="#mynavbar"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a href="index.html" class="navbar-brand">eLoja</a>
      </div>
      <div class="collapse navbar-collapse" id="mynavbar">
        <ul class="nav navbar-nav navbar-right">
          <li>
            <a href=""
              ><span class="glyphicon glyphicon-shopping-cart">
                Carrinho</span
              ></a
            >
          </li>
          <li>
            <router-link to="/login">
              <span class="glyphicon glyphicon-user"> Configuracoes</span>
            </router-link>  
          </li>
          <li>
            <router-link to="/logout">
              <span class="glyphicon glyphicon-log-out"> Sair</span>
            </router-link>  
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>

export default {
    name: "NavbarComponent"
}
</script>

<style scoped>
nav {
  display: flex;
}
</style>
