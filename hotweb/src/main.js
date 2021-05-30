import Vue from 'vue'
import App from './App'
import router from './router'
import VueResource from 'vue-resource'
import store from './store/'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'

Vue.config.productionTip = false;

Vue.use(ElementUI);
Vue.use(VueResource);

new Vue({
	el: '#app',
	router,
	store,
	template: '<App/>',
	components: { App }
})
