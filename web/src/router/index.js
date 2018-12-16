import Vue from "vue";
import Router from "vue-router";
import index from "@/page/index";
import layout from "@/page/layout";
import login from "@/page/login";
import unauthorize from "@/page/403";
Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      name: "layout",
      component: layout,
      path: "/",
      children: [
        {
          path: "/index",
          name: "index",
          component: index
        }
      ]
    },
    {
      path: "/login",
      name: "login",
      component: login
    },
    {
      path: "/403",
      name: "unauthorize",
      component: unauthorize
    }
  ]
});
