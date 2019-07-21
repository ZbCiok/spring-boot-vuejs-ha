import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import VueSession from "vue-session";
import VueSweetalert2 from "vue-sweetalert2";
import Vuelidate from "vuelidate";

Vue.config.productionTip = false;

// ----------- vue-session
var options = {
  persist: true
};
Vue.use(VueSession, options);

// ----------- modal
Vue.use(VueSweetalert2);

// ----------- Vuelidate
Vue.use(Vuelidate);

new Vue({
  beforeCreate: function() {
    this.$session.start();
  },
  router,
  render: h => h(App)
}).$mount(`#app`);
