const app = getApp();
Page({
  data: {
    array: [],
    listData:[],
    food:{},
    index:0,
    imgUrl:'',
    staticImg: app.globalData.staticImg,
    current:0,
    attitude:true,
    time: true,
    efficiency: true,
    environment:true,
    professional:true,
    wjxScore:5,
    // textarea
    min: 5,//最少字数
    max: 300, //最多字数 (根据自己需求改变)
    pics:[],
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
    // 图片
    choose: function (e) {//这里是选取图片的方法
    var that = this;
    var pics = that.data.pics;
    wx.chooseImage({
      count: 1 - pics.length, // 最多可以选择的图片张数，默认9
      sizeType: ['original', 'compressed'], // original 原图，compressed 压缩图，默认二者都有
      sourceType: ['album', 'camera'], // album 从相册选图，camera 使用相机，默认二者都有
      success: function (res) {

        var imgsrc = res.tempFilePaths;
        pics = pics.concat(imgsrc);
        that.setData({
          pics: pics,
        });
        that.uploadimg();
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })

  },
  uploadimg: function () {//这里触发图片上传的方法
    const _this = this;
    var pics = this.data.pics[0];
    console.log("图片上传{}",pics);
    wx.uploadFile({
      url: 'http://localhost:8080/hg/image/upload',//这里是你图片上传的接口
      filePath: pics,//这里是选取的图片的地址数组
      name:'file',
      success:function(res){
        console.log(res.data)
        _this.setData({
          imgUrl:res.data.data
        })
      }, 
      fail: function (err) { 
        console.log(err) 
      } 
    });
  },
  bindPickerChange: function(e) {
    this.setData({
      index: e.detail.value
    })
  },
  onShow: function () {
    var _this = this
    const pages = getCurrentPages()
    var options = pages[pages.length - 1].options
    if(options.id){
      wx.request({
        url: 'http://localhost:8080/hg/food/get/'+options.id,
        method: 'GET',
        data: {},
        header: {
          'Accept': 'application/json'
        },
        success: function (res) {
          console.log(res.data.data)
            _this.setData({
              food: res.data.data,
              id:options.id,
              pics:[res.data.data.imgUrl]
            });
        }
      })
  }
    wx.request({
      url: 'http://localhost:8080/hg/category',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        var arraryList = [];
        var index = 0;
        for(var i =0;i<res.data.length;i++){
            arraryList.push(res.data[i].name)
            if(_this.data.food.categoryId == res.data[i].id){
              index = i;
            }
        }
        _this.setData({
          listData: res.data,
          array:arraryList,
          index:index
        })
      }
    })
  },
  bindSubmitForm:function(e){
    const value = e.detail.value;
    var _this = this;
    wx.request({
      url: 'http://localhost:8080/hg/food',
      method: 'POST',
      data: {
        id:_this.data.id,
        name:value.name,
        size:value.size,
        price:value.price,
        categoryId:_this.data.listData[_this.data.index].id,
        valid:1,
        imgUrl:_this.data.imgUrl
    },
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        _this.setData({
          edit:!_this.data.edit,
          category:{}
        })
        wx.navigateBack()
      }
    })
  },
  // 删除图片
  deleteImg: function (e) {
    var pics = this.data.pics;
    var index = e.currentTarget.dataset.index;
    pics.splice(index, 1);
    this.setData({
      pics: pics
    });
  },
  // 预览图片
  previewImg: function (e) {
    //获取当前图片的下标
    var index = e.currentTarget.dataset.index;
    //所有图片
    var pics = this.data.pics;
    wx.previewImage({
      //当前显示图片
      current: pics[index],
      //所有图片
      urls: pics
    })
  },
})