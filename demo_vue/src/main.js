import Vue from "vue";
import App from "./App.vue";
import echarts from "echarts";

Vue.config.productionTip = false;
Vue.use(echarts);

new Vue({
  render: h => h(App)
}).$mount("#app");
