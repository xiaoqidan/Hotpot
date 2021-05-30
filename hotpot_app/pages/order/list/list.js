var app = getApp();
Page({
  data: {
    tabIndex:0,
    orders:[
      {"number":"123123123","content":"肥牛；青菜；各种菜；土豆；藕片；鸭肠；","count":123123,"money":1111,"status":"1"},
      {"number":"123123123","content":"肥牛；青菜；各种菜；土豆；藕片；鸭肠；肥牛；青菜；各种菜；土豆；藕片；鸭肠；","count":123123,"money":1111,"status":"2"}
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    const _this = this;
    wx.request({
      url: 'http://localhost:8080/hg/order',
      method: 'GET',
      data: {userId:app.globalData.user.id},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        var data = res.data.data
        console.log(data)
        for(var i =0 ;i<data.length;i++){
          data[i].typeValue = data[i].type == 1?'实体店消费':'外卖订单'
          if(data[i].status == 0 ){
            data[i].statusFy = '已取消'
          }else if(data[i].status == 1 && data[i].type == 1){
            data[i].statusFy = '等待出菜'
          }else if(data[i].status == 1 && data[i].type == 2){
            data[i].statusFy = '等待配送'
          }else if(data[i].status == 2 && data[i].type == 2){
            data[i].statusFy = '配送中'
          }else if(data[i].status == 2 && data[i].type == 1){
            data[i].statusFy = '已出菜'
          }else if(data[i].status == 3){
            data[i].statusFy = '待评价'
          }else{
            data[i].statusFy = '已完成'
          }
        }
        _this.setData({
          orders:data
        })
      }
    })
  },

  changeTab:function(e){
    var index = e.currentTarget.dataset.index
    this.setData({
      tabIndex: index,
    })
  },
  golist: function () {
    wx.navigateTo({
      url: '../../list/list'
    })
  },
  goComment: function (event) {
    const id = event.currentTarget.dataset.id
    wx.navigateTo({
      url: '../../comment/edit/comment?id='+id
    })
  },
  pey:function(event){
    const id = event.currentTarget.dataset.id
    wx.showModal({
      title:'提示',
      content:'确认支付吗',
      showCancel:true,
      cancelText:"否",
      confirmText:"是",
      success:function(res){
        if(res.cancel){
        }else{
          wx.request({
            url: 'http://localhost:8080/hg/order/update',
            method: 'POST',
            data: {
              id:id,
              status:3
            },
            header: {
              'Accept': 'application/json'
            },
            success: function (res) {
              wx.showToast({
                title: '付款成功',
                icon: 'success',     //默认值是success,就算没有icon这个值，就算有其他值最终也显示success
                duration: 1000,      //停留时间
              })
              this.oonShow()
            }
          })
        }
      }
    })
  },
  cancel:function(event){
    const id = event.currentTarget.dataset.id
    wx.showModal({
      title:'提示',
      content:'确认取消吗',
      showCancel:true,
      cancelText:"否",
      confirmText:"是",
      success:function(res){
        if(res.cancel){
        }else{
          wx.request({
            url: 'http://localhost:8080/hg/order/update',
            method: 'POST',
            data: {
              id:id,
              status:0
            },
            header: {
              'Accept': 'application/json'
            },
            success: function (res) {
              wx.showToast({
                title: '取消成功',
                icon: 'success',     //默认值是success,就算没有icon这个值，就算有其他值最终也显示success
                duration: 1000,      //停留时间
              })
              this.oonShow()
            }
          })
        }
      }
    })
  },
  receive:function(event){
    const id = event.currentTarget.dataset.id
    const _this = this
    wx.showModal({
      title:'提示',
      content:'确认到达吗',
      showCancel:true,
      cancelText:"否",
      confirmText:"是",
      success:function(res){
        if(res.cancel){
        }else{
          wx.request({
            url: 'http://localhost:8080/hg/order/update',
            method: 'POST',
            data: {
              id:id,
              status:3
            },
            header: {
              'Accept': 'application/json'
            },
            success: function (res) {
              _this.oonShow()
            }
          })
        }
      }
    })
  }
})