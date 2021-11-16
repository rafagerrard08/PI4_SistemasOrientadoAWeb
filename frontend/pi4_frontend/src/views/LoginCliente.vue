<template>
  <div class="container">
    <NavbarComponent />
    <div class="row principal">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <LoginClienteComponent />
      </div>
            <div class="col-md-3"></div>

    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";
import LoginClienteComponent from "../components/LoginClienteComponent.vue";
export default {
  components: { NavbarComponent, LoginClienteComponent },
};
</script>

<style>
.principal {
  padding-top: 100px;
}
</style>