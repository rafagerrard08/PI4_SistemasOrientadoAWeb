import Vue from 'vue'
import App from './App.vue'
import router from './router'

import BootstrapVue from 'bootstrap-vue'

import Datepicker2 from 'vue2-datepicker';
import BlockUI from 'vue-blockui'

import { BootstrapVueIcons } from 'bootstrap-vue'

Vue.use(BootstrapVueIcons)

Vue.use(BootstrapVue);

Vue.use(BlockUI);
Vue.component('datepicker2', Datepicker2);

Vue.config.productionTip = false

var vm = new Vue({
    router,
    data: {
        loading: false,
        cart: [],
        cartTotal: 0,
        dadosPedido: {},
        valorFrete: 0,
    },
    render: h => h(App)
}).$mount('#app');

global.vm = vm;

import VueSimpleAlert from "vue-simple-alert";

Vue.use(VueSimpleAlert);