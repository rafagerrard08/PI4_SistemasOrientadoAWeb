<template>
  <div class="container">
    <NavbarComponent />
    <div class="row principal">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <LoginComponent />
      </div>
            <div class="col-md-3"></div>

    </div>
  </div>
</template>

<script>
import NavbarComponent from "../components/NavbarComponent.vue";
import LoginComponent from "../components/LoginComponent.vue";

export default {
  components: { NavbarComponent, LoginComponent },
};
</script>

<style>
.principal {
  padding-top: 100px;
}
</style>
