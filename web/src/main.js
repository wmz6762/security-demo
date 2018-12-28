// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import http from './util/http'
import ElementUI from 'element-ui';
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index'
import VueCookie from 'vue-cookie'
import _ from 'lodash'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

import '@/assets/css/element-theme/orange/index.css'
import '@/assets/css/aui-orange.min.css'
import '@/assets/icons/iconfont'

Vue.use(VueCookie)
Vue.config.productionTip = false
// 挂载全局
Vue.prototype.$http = http // ajax请求方法
Vue.prototype._ = _

Vue.use(ElementUI);

var mainRoutes = [{
  name: "layout",
  component: require('@/page/layout.vue').default,
  redirect: "home",
  path: '/',
  children: []
}]



router.beforeEach((to, from, next) => {
  NProgress.start();
  if (to.meta.global) {
    next()
    NProgress.done();
  } else {
    if (_.isEmpty(store.state.user.id)) {
      http({
        url: http.adornUrl('/account'),
        method: 'get',
      }).then(({
        data
      }) => {
        if (data) {
          store.commit('user/updateUser', data);
          let mainContainer = mainRoutes.find(v => v.path === '/')
          debugger
          data.menus.forEach(item => {
            mainContainer.children.push({
              path: item.path,
              name: item.name,
              component: require('@/page/' + item.name + '.vue').default,
            })
          })
          router.addRoutes(mainRoutes)
          next({
            path: to.path
          })
          // next();
          NProgress.done();
        } else {
          next()
          NProgress.done();
        }
      })
    } else {
      debugger
      if (to.matched.length === 0) {
        next({
          path: '/403'
        })
        NProgress.done();
      } else {
        next()
        NProgress.done();
      }
    }
  }

})


new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})
