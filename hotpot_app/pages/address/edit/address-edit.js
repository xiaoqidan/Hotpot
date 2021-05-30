var app = getApp();
Page({
  data:{
    id:null,
    address:{
      name:'',
      telephone:'',
      content:''
    }
  },
  onLoad(option){
    var _this = this
    console.log(option)
    if(option.id){
      wx.request({
        url: 'http://localhost:8080/hg/location/'+option.id,
        method: 'GET',
        data: {},
        header: {
          'Accept': 'application/json'
        },
        success: function (res) {
            _this.setData({
              address: res.data.data,
              id:option.id
            });
        }
      })
  }
  },
  onGoWeizhi(e){
    let that = this
    wx.chooseLocation({
        success(res){
          console.log('店铺位置信息:',res)
          let value = 'address.content'
            that.setData({
                [value]: res.address
            })
        }
    })
},
  bindSubmitForm(e){
    const value = e.detail.value;
    console.log("地址信息提交：{}",value)
    if (value.name && value.telephone && value.content){
      wx.request({
        url: 'http://localhost:8080/hg/location',
        method: 'POST',
        data: {
          id:this.data.id,
          name:value.name,
          telephone:value.telephone,
          content:value.content,
          userId:app.globalData.user.id
      },
        header: {
          'Accept': 'application/json'
        },
        success: function (res) {
          wx.navigateBack();
        }
      })
    }else{
      wx.showModal({
        title:'提示',
        content:'请填写完整资料',
        showCancel:false
      })
    }
  }
})