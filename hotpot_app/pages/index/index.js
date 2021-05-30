//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    //轮播图
    imgUrls: [],
    // 推荐
    business: [   ],
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    zj:''
  },
  onLoad: function () {
    const _this = this;
    wx.request({
      url: 'http://localhost:8080/hg/food/tj',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        _this.setData({
          business: res.data.data,
        })
      }
    })
    wx.request({
      url: 'http://localhost:8080/hg/picture/list',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        const data = res.data.data;
        console.log('首页图片',data)
        var array = [];
        let zj = '';
        for(var i = 0 ;i<data.length;i++){
          if(data[i].type == 0 ){
            array.push(data[i].location)
          }else if(data[i].type ==1){
            zj = data[i].location;
          }
        }
        _this.setData({
          imgUrls: array,
          zj:zj
        })
      }
    })
  },
  golist: function () {
    wx.navigateTo({
      url: '../food/list/list'
    })
  },
  gowm: function () {
    wx.navigateTo({
      url: '../food/wm/wm'
    })
  },
  goComment: function () {
    wx.navigateTo({
      url: '../comment/list/list'
    })
  },
})
