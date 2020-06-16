import Vue from "vue";
import MainPage from "./views/MainPage.vue";
import router from "./router";
import store from "./store";

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(MainPage)
}).$mount("#app");
