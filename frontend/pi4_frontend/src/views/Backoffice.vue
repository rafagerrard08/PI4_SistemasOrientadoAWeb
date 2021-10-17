<template>
  <div class="container">
    <div class="content">
      <div class="card">
        <div class="card-body">
          <h4 class="card-title">Backoffice</h4>
          <router-link to="/home">Lista de Produtos</router-link>
          <br />
          <router-link to="/usuarios">Lista de Usuarios</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Backoffice",
};
</script>

<style>
</style>