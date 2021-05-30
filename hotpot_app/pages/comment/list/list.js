// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    starAll: 0,
    listData: [],
    typeData:[0,0,0,0,0,0],
    userStars: [
      "/images/201.png",
      "/images/201.png",
      "/images/201.png",
      "/images/201.png",
      "/images/201.png"
    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _this = this;
    wx.request({
      url: 'http://localhost:8080/hg/comment',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        var data = res.data.data
        console.log('评论列表信息：',data)
        var sum = 0;
        var array=[0,0,0,0,0,0];
        for(var i=0;i<data.length;i++){
          sum += data[i].star
          var types = data[i].type.split(";")
          console.log(types)
          for(var z=0;z<types.length-1;z++){
            array[Number(types[z])]++
          }
          var j =0;
          var userStar = []
          for(j=0;j<data[i].star;j++){
            userStar.push('/images/201.png')
          }
          data[i].userStars = userStar
        }
        console.log(data)
        _this.setData({
          listData: data,
          starAll:(sum/data.length).toFixed(1),
          typeData:array
        })
      }
    })
  }
})