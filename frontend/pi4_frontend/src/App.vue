<template>
  <div id="app">
    <BlockUI :message="msg" :html="html" v-if="this.$parent.loading"></BlockUI>
    <div class="container mt-3">
      <div class="content">
        <div id="nav" class="text-center"></div>
        <transition name="router-anim" mode="out-in">
          <router-view />
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        msg: 'Carregando...',
        html: '<i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i>',  //this line demostrate how to use fontawesome animation icon 
      }
    },
  }
</script>

<style scoped>
  .router-anim-enter-active {
    animation: coming .1s;
    opacity: 0;
  }

  .router-anim-leave-active {
    animation: going .1s;
  }

  @keyframes going {
    from {
      transform: translateX(0);
    }
    to {
      transform: translateX(-50px);
      opacity: 0;
    }
  }
  @keyframes coming {
    from {
      transform: translateX(50px);
    }
    to {
      transform: translateX(0px);
      opacity: 1;
    }
  }
</style>