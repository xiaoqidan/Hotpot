// pages/order/balance/balance.js
var app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    addressesList: [],
    locationList:[],
    index:0,
    order:{},
    cartList: [],
    type:1,
    sumMonney: 0,
    cutMonney: 0,
    cupNumber:0,
    tableNumber:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const _this = this
    wx.setNavigationBarTitle({
      title: '订单支付'
    })
    wx.request({
      url: 'http://localhost:8080/hg/location',
      method: 'GET',
      data: {userId:app.globalData.user.id},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        if (res.data.data) {
          var locationList = [];
          for(var i =0;i<res.data.data.length;i++){
            var t = res.data.data[i];
            locationList.push(t.telephone + ' ' + t.content);
          }
          console.log(locationList)
          _this.setData({
            addressesList: res.data.data,
            locationList:locationList
          });
        } else {
          _this.setData({
            addressesList: [],
          });
        }
      }
    })
    this.setData({
      cartList: wx.getStorageSync('cartList'),
      sumMonney: wx.getStorageSync('sumMonney'),
      cupNumber: wx.getStorageSync('cupNumber'),
      type:wx.getStorageSync('type'),
    })
  },
  bindPickerChange: function(e) {
      this.setData({
        index: e.detail.value
      })
    },
    bindinput:function(e){
      let value = this.validateNumber(e.detail.value);
      this.setData({
        tableNumber:value
      })
    },
    //数字限制
    validateNumber(val) {
      return val.replace(/\D/g, '')
    },
  selectRadio:function(e){
    console.log(e);
    this.setData({
      type:e.detail.value
    })
    console.log(this.data.type)
  },
  goPay:function(){
    var _this = this;
    console.log("订单创建中......")
    if(this.data.type == 1 && this.data.tableNumber == ''){
        wx.showModal({
          title: '提示',
          content: '请输入桌号',
          showCancel:false
      })
      return 
    }
    var locationId = this.data.type == 2?_this.data.addressesList[this.data.index].id:null
    var number =   this.data.type == 1?_this.data.tableNumber:null
    wx.request({
      url: 'http://localhost:8080/hg/order/pay',
      method: 'POST',
      data: {
        foods:_this.data.cartList,
        money:_this.data.sumMonney,
        userId:app.globalData.user.id,
        type:_this.data.type,
        bz:_this.data.bz,
        locationId:locationId,
        tableNumber:number
      },
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
       
      }
    })
    wx.navigateTo({
      url: '../detail/detail'
    })
  }
})