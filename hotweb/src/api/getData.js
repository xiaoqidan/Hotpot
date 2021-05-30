import fetch from '@/config/fetch'

const front = '/hg'
// 登录
export const login = data => fetch(front + '/admin/login', data, 'GET');

// 退出
export const signout = () => fetch('/admin/signout');

// 获取用户信息
export const getAdminInfo = () => fetch('/admin/info');

