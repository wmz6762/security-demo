import Vue from "vue";
import Router from "vue-router";
import index from "@/page/index";
import layout from "@/page/layout";
import unauthorize from "@/page/403";
Vue.use(Router);

export default new Router({
  // mode: 'history',
  routes: [{
      name: "layout",
      component: layout,
      path:"/",
      redirect:"/home",
      children: [{
        path: "/home",
        name: "home",
        component: index
      }]
    },
    {
      path: "/403",
      name:'403',
      component: unauthorize
    },
   
    
  ]
});
