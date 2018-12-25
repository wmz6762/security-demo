import Vue from "vue";
import Router from "vue-router";

import index from "@/page/index";
import layout from "@/page/layout";
import unauthorize from "@/page/403";
import login from "@/page/login";
import menu1 from "@/page/menu1"


Vue.use(Router);


const globalRoutes = [{
    path: '/login',
    component: login,
    name: 'login',
    global: true,
    meta: {
      title: '登录',
      global: true,
    }
  },
  {
    path: "/403",
    name: '403',
    global: true,
    component: unauthorize,
    meta: {
      title: '403',
      global: true,
    }
  }
]

const mainRoutes = {
  name: "layout",
  component: layout,
  redirect: "home",
  path: '/',
  children: [{
    path: "/",
    name: "home",
    component: index
  }, {
    path: '/menu1',
    name: 'menu1',
    component: menu1
  }]
}

const router = new Router({
  mode: 'history',
  routes: globalRoutes.concat(mainRoutes)
});

export default router;
