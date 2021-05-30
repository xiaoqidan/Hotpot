const app = getApp();
Page({
  data: {
    staticImg: app.globalData.staticImg,
    current:0,
    attitude:true,
    time: true,
    efficiency: true,
    environment:true,
    professional:true,
    userStars: [
      "/images/201.png",
      "/images/201.png",
      "/images/201.png",
      "/images/201.png",
      "/images/201.png",
    ],
    wjxScore:5,
    // textarea
    min: 5,//最少字数
    max: 300, //最多字数 (根据自己需求改变)
    content:'',
    type:["1"],
    id:''
  },
  onLoad:function(e){
    console.log('订单id',e.id)
    this.setData({
      id:e.id
    })
  },
  // 星星点击事件
  starTap: function (e) {
    var that = this;
    var index = e.currentTarget.dataset.index; // 获取当前点击的是第几颗星星
    var tempUserStars = this.data.userStars; // 暂存星星数组
    var len = tempUserStars.length; // 获取星星数组的长度
    for (var i = 0; i < len; i++) {
      if (i <= index) { // 小于等于index的是满心
        tempUserStars[i] = "/images/201.png";
        that.setData({
          wjxScore:i+1,
        })
      } else { // 其他是空心
        tempUserStars[i] = "/images/101.png"
      }
    }
    // 重新赋值就可以显示了
    that.setData({
      userStars: tempUserStars
    })
  },
  // 标签
  label:function(e){
    console.log(e)
    var that = this;
    if(that.data.attitude){
      that.data.type.push(e.currentTarget.dataset.id);
    }else{
      for(var i =0;i<that.data.type.length;i++){
        if(that.data.type[i] == e.currentTarget.dataset.id){
           that.data.type.splice(i,1)
        }
      }
    }
    that.setData({
      attitude:!e.currentTarget.dataset.index,
    })
  },
   label1: function (e) {
    console.log(e)
    var that = this;
   that.data.type.push(e.currentTarget.dataset.id);
    that.setData({
      time: !e.currentTarget.dataset.index,
    })
  },
   label2: function (e) {
     console.log(e)
     var that = this;
     that.data.type.push(e.currentTarget.dataset.id);
     that.setData({
       efficiency: !e.currentTarget.dataset.index,
     })
   },
    label3: function (e) {
     console.log(e)
     var that = this;
     that.data.type.push(e.currentTarget.dataset.id);
     that.setData({
       environment: !e.currentTarget.dataset.index,
     })
   },
    label4: function (e) {
      console.log(e)
      var that = this;
      if(that.data.professional){
       that.data.type.push(e.currentTarget.dataset.id);
      }
      that.setData({
        professional: !e.currentTarget.dataset.index,
      })
    },
    // 留言
    //字数限制
  inputs: function (e) {
      // 获取输入框的内容
      var value = e.detail.value;
      // 获取输入框内容的长度
      var len = parseInt(value.length);
      //最多字数限制
      if (len > this.data.max) return;
      // 当输入框内容的长度大于最大长度限制（max)时，终止setData()的执行
      this.setData({
        currentWordNumber: len //当前字数
      });
    },
    bindSubmitForm:function(e){
      const value = e.detail.value;
      var _this = this;
      var types = ""
      for(var i=0;i<_this.data.type.length;i++){
          types+=_this.data.type[i]
          types+=";"
      }
      console.log("评论类型：{}",types)
      wx.request({
        url: 'http://localhost:8080/hg/comment',
        method: 'POST',
        data: {
          userId:app.globalData.user.id,
          star:_this.data.wjxScore,
          content:value.content,
           type:types,
           orderId:_this.data.id
      },
        header: {
          'Accept': 'application/json'
        },
        success: function (res) {
          wx.navigateBack()
        }
      })
    }
})