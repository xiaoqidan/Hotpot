const serviceRootPath = '/hg';
// 服务数据
const serverUrl = {
    // 测试模块
	feature:{
        // 菜单
		food: `${serviceRootPath}/food/list`,
		foodEdit: `${serviceRootPath}/food`,
        // 订单
		order: `${serviceRootPath}/order/list`,
        orderupdate: `${serviceRootPath}/order/update`,
        orderYear: `${serviceRootPath}/order/year`,
        orderMonth: `${serviceRootPath}/order/month`,
        // 用户
		comment: `${serviceRootPath}/comment/list`,
        commentBack: `${serviceRootPath}/comment`,
        // 管理员
		admin: `${serviceRootPath}/admin/list`,
        // 分类
        category:`${serviceRootPath}/category`,
        // 首页图片推荐
        picture:`${serviceRootPath}/picture`,
        // 首页图片推荐
        table:`${serviceRootPath}/table`,
	},
};
export default serverUrl;
