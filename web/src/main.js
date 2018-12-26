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

router.beforeEach((to, from, next) => {
  NProgress.start();
  if (to.meta.global) {
    next()
  } else {
    if (_.isEmpty(store.state.user.id)) {
      http( {
         url: http.adornUrl('/account'),
         method: 'get',
      }).then(( { data } ) => {
        if (data) {
          store.commit('user/updateUser', data)
          next({ ...to, replace: true })
        } else {
          next()
        }
      })
    } else {
      var flag = false;
      store.state.user.menus.forEach(item=>{
        if( item.path === to.path ) {
          flag = true
        }
      })
      flag ? next() : next( { path:'/403' } )
    }
  }
  NProgress.done();
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
