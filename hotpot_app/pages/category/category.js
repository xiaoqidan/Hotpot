Page({
  data: {
    listData:[],
    edit:false,
    category:{}
  },
  onShow: function () {
    const _this = this;
    wx.request({
      url: 'http://localhost:8080/hg/category',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        _this.setData({
          listData: res.data,
        })
      }
    })
  },
  openEdit:function(event){
    const id = event.currentTarget.dataset.id
    if(id){
      for(var i=0;i<this.data.listData.length;i++){
        if(this.data.listData[i].id == id){
          this.setData({
            category:this.data.listData[i]
          })
        }
      }
    }else{
      this.setData({
        category:{}
      })
    }
    this.setData({
      edit:!this.data.edit,
      id:id
    })
  },
  deleteCategory:function(e){
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
            url: 'http://localhost:8080/hg/category/'+id,
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
  setValie:function(e){
    const _this = this;
    const id = e.currentTarget.dataset.id
    wx.request({
      url: 'http://localhost:8080/hg/category/valid/'+id,
      method: 'POST',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
      }
    })
  },
  bindSubmitForm:function(e){
    const value = e.detail.value;
    var _this = this;
    wx.request({
      url: 'http://localhost:8080/hg/category',
      method: 'POST',
      data: {
        id:_this.data.id,
        name:value.name,
        sort:value.sort,
        valid:1,
        storeId:1
    },
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        _this.setData({
          edit:!_this.data.edit,
          category:{}
        })
        _this.onShow()
      }
    })
  }
 
 })
 