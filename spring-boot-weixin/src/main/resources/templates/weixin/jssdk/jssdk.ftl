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
  <button value="调用选择图片接口"  onclick="chooseImage()">调用选择图片接口</button>
</body>

<script>
    var chooseImage;
    wx.ready(function(){
        chooseImage=function () {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    alert(localIds)
                }
            });
        }
    });
</script>
</html>