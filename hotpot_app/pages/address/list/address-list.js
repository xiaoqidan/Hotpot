var app = getApp();
Page({
  data: {
    addressesList: [],
    defaultId: 0,
    tipsData: {
      title: ''
    }
  },
  setDefault(event) {
    const _this = this;
    const id = event.currentTarget.dataset.id
    console.log(id)
    wx.request({
      url: 'http://localhost:8080/hg/location/default',
      method: 'post',
      data: {
        userId:app.globalData.user.id,
        id:id
      },
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        var list = _this.data.addressesList
        for(var i = 0;i<list.length;i++){
          if(list[i].id == id){
            list[i].valid = 1
          }else{
            list[i].valid = 0;
          }
        }
        _this.setData({
          addressesList:list
        })
      }
    })
  },
  goEdit(event) {
    const id = event.currentTarget.dataset.id
    wx.navigateTo({
      url: `../edit/address-edit?id=${id}`
    });
  },

  onShow() {
    const _this = this;
    wx.request({
      url: 'http://localhost:8080/hg/location',
      method: 'GET',
      data: {userId:app.globalData.user.id},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        if (res.data.data) {
          _this.setData({
            addressesList: res.data.data,
          });
        } else {
          _this.setData({
            addressesList: [],
          });
        }
      }
    })
  },
  delete:function(e){
    var _this = this;
    var id = e.currentTarget.dataset.id
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
            url: 'http://localhost:8080/hg/location/'+id,
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
  }
});
