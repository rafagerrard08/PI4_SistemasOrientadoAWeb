<template>
  <div class="container">
    <NavbarComponent />
    <div class="row principal">
      <CarrinhoComponent />
    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";
import CarrinhoComponent from "../components/CarrinhoComponent.vue";

export default {
  components: { NavbarComponent, CarrinhoComponent },
};
</script>

<style>
.principal {
  padding-top: 100px;
}

#titulo {
  padding-bottom: 5px;
}
</style>
