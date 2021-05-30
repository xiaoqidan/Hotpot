// page/component/new-pages/user/user.js
var app = getApp();
Page({
  data:{
    currentTab:1,
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
  },
})