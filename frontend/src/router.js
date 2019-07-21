import Vue from "vue";
import Router from "vue-router";
const Home = () => import("./views/Home.vue");
const ProductList = () => import("./views/ProductList.vue");
const ProductCreate = () => import("./views/ProductCreate.vue");
const ProductShow = () => import("./views/ProductShow.vue");
const ProductEdit = () => import("./views/ProductEdit.vue");
const ProductHardDelete = () => import("./views/ProductHardDelete.vue");
const About = () => import("./views/About.vue");
const Login = () => import("./views/Login.vue");

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    { path: "/product-list", component: ProductList, name: "product-list" },
    {
      path: "/product/:id",
      component: ProductShow,
      name: "product-show",
      props: true
    },    
    {
      path: "/product/:index",
      component: ProductEdit,
      name: "product-edit",
      props: true
    },    
    {
      path: "/product/:index",
      component: ProductHardDelete,
      name: "product-hard-delete",
      props: true
    },    
    {
      path: "/product-create",
      component: ProductCreate,
      name: "product-create"
    },
    {
      path: "/about",
      name: "about",
      component: About
    },
    {
      path: "/login",
      name: "login",
      component: Login
    }
  ],
  mode: "history"
});
