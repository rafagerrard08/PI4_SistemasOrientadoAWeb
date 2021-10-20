<template>
  <div></div>
</template>

<script>
import axios from "axios";

export default {
    name: "Logout",

    created() {
        debugger
        axios
        .get("http://localhost:8080/logout")
        .then((res) => {
          debugger
          sessionStorage.clear();
          this.$router.push('/home') 
        })
        .catch(() => alert("erro ao fazer logout"))
    }

}
</script>

<style>

</style>