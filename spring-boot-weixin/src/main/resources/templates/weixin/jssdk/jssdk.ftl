<!DOCTYPE html>

<html lang="en">
<head>
  <meta name="viewport" content="width=device-width,initial-scale=1.0">
  <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<script>
  wx.config({
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '${sign.appId}', // 必填，公众号的唯一标识
    timestamp:${sign.timestamp}, // 必填，生成签名的时间戳
    nonceStr: '${sign.nonceStr}', // 必填，生成签名的随机串
    signature: '${sign.signature}',// 必填，签名
    jsApiList: [
      'openEnterpriseChat',
      'openEnterpriseContact',
      'onMenuShareTimeline',
      'onMenuShareAppMessage',
      'onMenuShareQQ',
      'onMenuShareWeibo',
      'onMenuShareQZone',
      'startRecord',
      'stopRecord',
      'onVoiceRecordEnd',
      'playVoice',
      'pauseVoice',
      'stopVoice',
      'onVoicePlayEnd',
      'uploadVoice',
      'downloadVoice',
      'chooseImage',
      'previewImage',
      'uploadImage',
      'downloadImage',
      'translateVoice',
      'getNetworkType',
      'openLocation',
      'getLocation',
      'hideOptionMenu',
      'showOptionMenu',
      'hideMenuItems',
      'showMenuItems',
      'hideAllNonBaseMenuItem',
      'showAllNonBaseMenuItem',
      'closeWindow',
      'scanQRCode'
    ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
  });
</script>
<body>
<div class="wrapper wrapper-content animated fadeInRight row">
  <div class="col-sm-12">
    <div id="networkType"></div>
    <br> <br> <br>
    <button id="switchMenu">隐藏右上角菜单</button>
    <br> <br> <br>
    <button id="chooseImage">选择图片</button>
    <button id="uploadImage" style="margin-left: 20px">上传图片</button>
    <div>图片预览</div>
    <div id="prevImages"></div>
    <br> <br> <br>
    <button id="startRecord">录音</button>
    <button id="stopRecord" style="margin-left: 20px">停止</button>
    <button id="playVoice" style="margin-left: 20px">播放</button>
    <button id="stopVoice" style="margin-left: 20px">停播</button>
    <button id="uploadVoice" style="margin-left: 20px">上传录音</button>
    <br> <br> <br>
    <button id="ctlshow">显示资源</button>

    <button id="imgccc">点击查看大图</button>
  </div>
  <input type="file"/>
</div>
</body>

<script>
  var chooseImage;
  wx.ready(function () {
    wx.getNetworkType({
      success: function (res) {
        document.querySelector("#networkType").innerHTML = "网络状态：" + res.networkType;
      },
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });

    bindBtnEvent();
  });

  //图片接口
  var images = {
    localIds: [],
    serverIds: []
  };
  var imgHtml = '<img id="@idx" src="@src" style="width:60px;height:60px;margin:0 10px" />';

  function upload(idx, len) {
    wx.uploadImage({
      localId: images.localIds[idx],
      success: function (res) {
        idx++;
        images.serverIds.push(res.serverId);
        if (idx < len) {
          upload(idx, len);
        }
      },
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });
  }

  //语音接口
  var voice = {
    localId: '',
    serverId: ''
  };

  function bindBtnEvent() {
    document.querySelector('#chooseImage').onclick = function () {
      wx.chooseImage({
        "count": 6, // 默认9
        "sizeType": ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        "sourceType": ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        "success": function (res) {
          images.localIds = res.localIds;
          //alert('已选择 ' + res.localIds.length + ' 张图片');
          //alert(images.localIds[0]);
          var html = [];
          images.localIds.forEach(function (val, idx) {
            html.push(imgHtml.replace(/@idx/g, idx).replace(/@src/g, val));
          });
          html = html.join("");
          document.querySelector("#prevImages").innerHTML = html;
        }
      });
    };

    //上传图片
    document.querySelector('#uploadImage').onclick = function () {
      if (images.localIds.length == 0) {
        alert('请先使用 chooseImage 接口选择图片');
        return;
      }
      images.serverIds = [];
      var length = images.localIds.length;
      upload(0, length);
    };

    //开始录音
    document.querySelector('#startRecord').onclick = function () {
      wx.startRecord({
        cancel: function () {
          alert('用户拒绝授权录音');
        }
      });
    };

    //停止录音
    document.querySelector('#stopRecord').onclick = function () {
      wx.stopRecord({
        success: function (res) {
          voice.localId = res.localId;
          alert(voice.localId[0])
        },
        fail: function (res) {
          alert(JSON.stringify(res));
        }
      });
    };
    //监听录音自动停止
    wx.onVoiceRecordEnd({
      // 录音时间超过一分钟没有停止的时候会执行 complete 回调
      complete: function (res) {
        voice.localId = res.localId;
        alert('录音时间已超过一分钟');
      }
    });

    //播放音频
    document.querySelector('#playVoice').onclick = function () {
      if (voice.localId == '') {
        alert('请先录音');
        return;
      }
      alert(voice.localId);
      wx.playVoice({
        localId: voice.localId
      });
    };

    //停止播放音频
    document.querySelector('#stopVoice').onclick = function () {
      wx.stopVoice({
        localId: voice.localId
      });
    };

    //监听录音播放停止
    wx.onVoicePlayEnd({
      complete: function (res) {
        alert('录音（' + res.localId + '）播放结束');
      }
    });

    //上传语音
    document.querySelector('#uploadVoice').onclick = function () {
      if (voice.localId == '') {
        alert('请先使用 startRecord 接口录制一段声音');
        return;
      }
      wx.uploadVoice({
        localId: voice.localId,
        success: function (res) {
          alert('上传语音成功，serverId 为' + res.serverId);
          voice.serverId = res.serverId;
        }
      });
    };

    document.querySelector('#ctlshow').onclick = function () {
      var json_images = JSON.stringify(images);
      var json_voice = JSON.stringify(voice);
      alert("图片：" + json_images);
      alert("录音：" + json_voice);
    };

    document.querySelector('#switchMenu').onclick = function () {
      var self = document.querySelector('#switchMenu'), dts = self.getAttribute("data-dts");
      if (dts) {
        self.removeAttribute("data-dts");
        self.innerHTML = "隐藏右上角菜单";
        wx.showOptionMenu();
      } else {
        self.setAttribute("data-dts", "1");
        self.innerHTML = "显示右上角菜单";
        wx.hideOptionMenu();
      }
    };

    //图片预览
    document.querySelector('#imgccc').onclick = function () {
      var imgg = "http://ofqwny9tz.bkt.clouddn.com/2a4cf36caf0e49f38a21d42feeae0a49";
      var imgs = ["http://ofqwny9tz.bkt.clouddn.com/2a4cf36caf0e49f38a21d42feeae0a49", "http://ofqwny9tz.bkt.clouddn.com/2a4cf36caf0e49f38a21d42feeae0a49", "http://ofqwny9tz.bkt.clouddn.com/2a4cf36caf0e49f38a21d42feeae0a49"];
      wx.previewImage({
        current: imgg, // 当前显示图片的http链接
        urls: imgs // 需要预览的图片http链接列表
      });
    };

  }

</script>
</html>