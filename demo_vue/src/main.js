import Vue from "vue";
import App from "./App.vue";
import axios from 'axios';
import echarts from "echarts";

Vue.config.productionTip = false;
Vue.use(echarts);
Vue.prototype.$axios = axios

new Vue({
  render: h => h(App)
}).$mount("#app");
