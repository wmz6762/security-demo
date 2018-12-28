import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

const globalRoutes = [{
    path: '/login',
    component:  require('@/page/login.vue').default,
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
    component: require('@/page/403.vue').default,
    meta: {
      title: '403',
      global: true,
    }
  },
  {
    path: "/404",
    name: '404',
    global: true,
    component: require('@/page/404.vue').default,
    meta: {
      title: '404',
      global: true,
    }
  }
]



const router = new Router({
  mode: 'history',
  routes: globalRoutes
});

export default router;
