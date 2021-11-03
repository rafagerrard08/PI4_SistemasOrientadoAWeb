<template>
  <div></div>
</template>

<script>
import axios from "axios";
import swal from "sweetalert2";
import router from "../router";


export default {
  name: "Logout",

  created() {
    swal
      .fire({
        title: "Voce gostaria mesmo de sair?",
        showCancelButton: true,
        confirmButtonText: "Sim",
        cancelButtonText: "Não",
      })
      .then((result) => {
        if (result.value == true) {
          axios
            .get("http://localhost:8080/logout")
            .then((res) => {
              sessionStorage.clear();
              this.$router.push("/home");
            })
            .catch(() => alert("erro ao fazer logout"));
        } else if (result.dismiss == "cancel") {
          router.push({ name: "home"});
        }
      });
  },
};
</script>

<style></style>
