// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    listData: [],
    activeIndex: 0,
    toView: 'a0',
    scrollTop: 100,
    screenWidth: 667,
    showModalStatus: false,
    currentType: 0,
    currentIndex: 0,
    cartList: [],
    sumMonney: 0,
    cupNumber:0,
    showCart: false,
    loading: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    var that = this;
    wx.showLoading({
      title: '努力加载中',
    })
    wx.request({
      url: 'http://localhost:8080/hg/category/food',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        wx.hideLoading();
        console.log(res)
        that.setData({
          listData: res.data,
          loading: true
        })
      }
    })
  },
  selectMenu: function (e) {
    var index = e.currentTarget.dataset.index
    this.setData({
      activeIndex: index,
      toView: 'a' + index,
      // scrollTop: 1186
    })
  },
  scroll: function (e) {
    var dis = e.detail.scrollTop
    console.log("执行scroll...")
    var j =0;
    for(var i =0 ;i<this.data.listData.length;i++){
      var t = this.data.listData[i].foods.length
      if( i==0){
        if(dis>0 && dis<j+(20+62*t)){
          this.setData({
            activeIndex: i,
          })
        }else{
          if(dis>j && dis<j+(20+62*t)){
            this.setData({
              activeIndex: i,
            })
        }
        j+=(20+62*t)
      }
    }
  }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  selectInfo: function (e) {
    var type = e.currentTarget.dataset.type;
    var index = e.currentTarget.dataset.index;
    this.setData({
      showModalStatus: !this.data.showModalStatus,
      currentType: type,
      currentIndex: index,
    });
  },
  OpenDeleteFood: function (e) {
    var type = e.currentTarget.dataset.type;
    var index = e.currentTarget.dataset.index;
    this.setData({
      showDeleteStatus: !this.data.showDeleteStatus,
      currentType: type,
      currentIndex: index,
    });
  },
  goEdit(event) {
    const id = event.currentTarget.dataset.id
    console.log("跳转到food编辑页面id{}",id)
    wx.navigateTo({
      url: `../add/add?id=${id}`
    });
  },
  deleteFood:function(e){
    var id = e.currentTarget.dataset.id
    var _this = this;
    wx.showModal({
      title:'提示',
      content:'确定要删除该分类吗？',
      showCancel:true,
      cancelText:"否",
      confirmText:"是",
      success:function(res){
        if(res.cancel){

        }else{
          wx.request({
            url: 'http://localhost:8080/hg/food/'+id,
            method: 'DELETE',
            data: {},
            header: {
              'Accept': 'application/json'
            },
            success: function (res) {
              _this.onShow()
            }
          })
        }
      }
    })
  },
 
  goAdd: function (event) {
    const id = event.currentTarget.dataset.id
    wx.navigateTo({
      url: '../add/add'
    })
  },

  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onLoad: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})