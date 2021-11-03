<template>
  <div></div>
</template>

<script>
import axios from "axios";

export default {
    name: "Logout",

    created() {
        axios
        .get("http://localhost:8080/logout")
        .then((res) => {
          sessionStorage.clear();
          this.$router.push('/home') 
        })
        .catch(() => alert("erro ao fazer logout"))
    }

}
</script>

<style>

</style>