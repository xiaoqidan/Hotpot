// page/component/new-pages/user/user.js
var app = getApp();
Page({
  data:{
    thumb:'',
    nickname:'',
    orders:[],
    hasAddress:false,
    address:{},
    auth:false
  },
  onLoad(){
  },
  onShow(){
    var self = this;
    this.setData({
      auth:app.globalData.auth
    });
    /**
     * 获取本地缓存 地址信息
     */
    wx.getStorage({
      key: 'address',
      success: function(res){
        self.setData({
          hasAddress: true,
          address: res.data
        })
      }
    })
  },
  goOrder: function () {
    wx.switchTab({
      url: '../order/list/list'
    })
  },
  goNotice: function () {
    wx.navigateTo({
      url: '../notice/notice'
    })
  },
  goCategoryEdit: function () {
    wx.navigateTo({
      url: '../category/category'
    })
  }
})