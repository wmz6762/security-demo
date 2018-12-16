// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import http from './util/http'
import ElementUI from 'element-ui';
import Vue from 'vue'
import App from './App'
import router from './router'
import '@/assets/css/element-theme/blue/index.css'
import '@/assets/css/aui-blue.min.css'
import '@/assets/icons/iconfont'

Vue.config.productionTip = false
// 挂载全局
Vue.prototype.$http = http // ajax请求方法
Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
