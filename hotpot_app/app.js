// app.js
App({
  globalData: {
    userInfo: null,
    auth:false,
    user:null
  },
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    let _this = this;
    // 登录
    wx.login({
      success: res => {
        if(res.code){
          wx.request({
            url: 'http://localhost:8080/hg/user',
            method: 'GET',
            data: { code:res.code},
            header: {'Accept': 'application/json'},
            success: function (res) {
              _this.globalData.auth = res.data.authority,
              _this.globalData.user = res.data
              wx.getUserInfo({
                success: res => {
                  // 可以将 res 发送给后台解码出 unionId
                  _this.globalData.userInfo = res.userInfo
                  console.log(res.userInfo)
                  // wx.request({
                  //   url: 'http://localhost:8080/hg/user',
                  //   method: 'POST',
                  //   data: { name:res.userInfo.nickName,
                  //           url:res.userInfo.avatarUrl,
                  //           id: _this.globalData.user.id
                  //   },
                  //   header: {'Accept': 'application/json'},
                  //   success: function (res) {
                  //     console.log("用户数据保存成功！")
                  //   }
                  // })
                }
              })
            }
          })
        }else{
          console.log("获取用户登录态失败！"+res.errMsg)
        }
      }
    })
  }
})
