/* eslint-disable no-undef */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const login = r => require.ensure([], () => r(require('@/page/login')), 'login');
const manage = r => require.ensure([], () => r(require('@/page/manage')), 'manage');
const commentList = r => require.ensure([], () => r(require('@/page/list/commentList')), 'commentList');
const categoryList = r => require.ensure([], () => r(require('@/page/list/categoryList')), 'categoryList');
const foodList = r => require.ensure([], () => r(require('@/page/list/foodList')), 'foodList');
const orderList = r => require.ensure([], () => r(require('@/page/list/orderList')), 'orderList');
const pictureList = r => require.ensure([], () => r(require('@/page/list/pictureList')), 'pictureList');
const wmOrder = r => require.ensure([], () => r(require('@/page/list/wmOrder')), 'wmOrder');
const tjFood = r => require.ensure([], () => r(require('@/page/list/tjFood')), 'tjFood');
const tableList = r => require.ensure([], () => r(require('@/page/list/tableList')), 'tableList');
const pdList = r => require.ensure([], () => r(require('@/page/list/pdList')), 'pdList');
const userList = r => require.ensure([], () => r(require('@/page/list/userList')), 'userList');
const yearList = r => require.ensure([], () => r(require('@/page/list/year')), 'yearList');
const monthList = r => require.ensure([], () => r(require('@/page/list/month')), 'monthList');
const wmFood = r => require.ensure([], () => r(require('@/page/list/wmFood')), 'wmFood');

const routes = [
	{
		path: '/',
		component: login
	},
	{
		path: '/manage',
		component: manage,
		name: '',
		children: [{
			path: '',
			component: categoryList,
			meta: [],
		},{
			path: '/commentList',
			component: commentList,
			meta: ['评论列表'],
		},{
			path: '/categoryList',
			component: categoryList,
			meta: [ '分类管理'],
		},{
            path: '/pictureList',
            component: pictureList,
            meta: [ '首页图片'],
        },{
			path: '/foodList',
			component: foodList,
			meta: ['菜品列表'],
		},{
            path: '/tjFood',
            component: tjFood,
            meta: ['推荐菜品'],
        },{
			path: '/orderList',
			component: orderList,
			meta: [ '订单列表'],
		},{
			path: '/wmOrder',
			component: wmOrder,
			meta: [ '外卖订单'],
		},{
            path: '/tableList',
            component: tableList,
            meta: [ '桌号情况'],
        },{
            path: '/pdList',
            component: pdList,
            meta: [ '排队情况'],
        },{
            path: '/userList',
            component: userList,
            meta: [ '员工情况'],
        },{
            path: '/monthList',
            component: monthList,
            meta: [ '月销量'],
        },{
            path: '/yearList',
            component: yearList,
            meta: [ '年销量'],
        },{
            path: '/wmFood',
            component: wmFood,
            meta: [ '外卖菜品'],
        }]
	}
]

export default new Router({
	routes,
	strict: process.env.NODE_ENV !== 'production',
})
