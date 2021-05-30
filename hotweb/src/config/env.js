// 服务器配置
import serverUrl from './server-config.js';
/**
 * 配置编译环境和线上环境之间的切换
 * 
 * baseUrl: 域名地址
 * routerMode: 路由模式
 * baseImgPath: 图片存放地址
 * 
 */
let baseUrl = ''; 
let routerMode = 'hash';
let baseImgPath;

if (process.env.NODE_ENV == 'development') {
	baseUrl = '';
    baseImgPath = '/img/';
}else{
	baseUrl = '//elm.cangdu.org';
    baseImgPath = '//elm.cangdu.org/img/';
}

export {
	baseUrl,
	routerMode,
	baseImgPath
}

// 默认开发环境
const develop = false;
const config = {
    //  是否是调试模式，true 为json数据，false 为服务器数据
    isDebug: develop,
    // ajax查询方式   GET
    methodGet: 'GET',
    // ajax查询方式  POST
    methodPost: develop ? 'GET' : 'POST',
    // ajax查询方式  PUT
    methodPut: develop ? 'GET' : 'PUT',
    // ajax查询方式  DELETEsocketio
    methodDelete: develop ? 'GET' : 'DELETE',
    // ajax查询方式  PATCH
    methodPatch: develop ? 'GET' : 'PATCH',
    //  url链接,也就是数据的地址
    url: {},
    // 是否显示日志
    showLog: true,
};
// 本地数据
const localUrl = {
    // 测试模块
    test:{}
};
config.url = config.isDebug ? localUrl : serverUrl;
window.fdConfig = config;
