window.userInform = function () {
    var password, phone, gender, birthday, email, personalBrief, avatarImgUrl, department, position;
    var userName = document.getElementById("userName").innerText;
    var msg;

    layui.use(['layer', 'form', 'table', 'laydate'], function () {
        var $ = layui.jquery, layer = layui.layer, form = layui.form, table = layui.table, laydate = layui.laydate; //独立版的layer无需执行这一句

        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            url: '/UserInformation',
            data: {"userName": userName},
            dataType: "json",
            error: function () {
                alert('访问错误！');
            },
            success: function (data) {
                var user = JSON.stringify(data);// //jsonData是该下路下的所有区间（json格式）
                var jsonData = JSON.parse(user);

                //获取对应的内容
                password = jsonData[0]['password'];
                phone = jsonData[0]['phone'];
                gender = jsonData[0]['gender'];
                birthday = jsonData[0]['birthday'];
                email = jsonData[0]['email'];
                personalBrief = jsonData[0]['personalBrief'];
                avatarImgUrl = jsonData[0]['avatarImgUrl'];
                department = jsonData[0]['department'];
                position = jsonData[0]['position'];

                console.log(password);
            }
        });
        form.render();
        layer.open({
            type: 2,
            async: false,
            cache: false,
            title: ['个人信息', 'text-align:center;font-size:20px;'],
            content: ['/information', 'text-align:center;'],
            anim: 3, //弹出方式
            //maxmin: true, //开启最大化最小化按钮
            area: ['600px', '800px'], //设置宽高
            skin: 'layui-layer-rim',//加上边框
            // data: {'anName': anName},
            success: function (layero, index) {   //成功获得加载界面时，预先加载，将值从父窗口传到子窗口
                var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']];//得到iframe页的窗口对象，执行iframe页的方法：
                // var iframeWindow = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象
                //给弹窗界面填充内容
                // body.contents().find("#title").val(anName);
                body.contents().find("#username").val(userName);
                body.contents().find("#phone").val(phone);
                body.contents().find("#email").val(email);
                body.contents().find("#position").val(position);
                body.contents().find("#department").val(department);

                var value = iframeWin.setBirthday(birthday, gender);
            }
        });
    });

    function GetValue(obj) {
        console.log(obj);
    }
};

window.logOut = function () {
    var userName = document.getElementById("userName").innerText;
    console.log(userName);
    $.ajax({
        async: false,
        cache: false,
        url: '/logout',
        error: function () {
            alert('访问错误！');
        },
        success: function (data) {
        }
    });
};
window.imgUser = function () {
    console.log("上传头像");
    layui.use(['form', 'layer', 'upload'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var upload = layui.upload;
        //弹窗显示
        layer.open({
            type: 2,
            title: ['上传头像', 'text-align:center;font-size:20px;letter-spacing: 5px;'],
            anim: 2, //弹出方式
            offset: 'rt',
            area: ['400px', '300px'], //设置宽高
            content: ['/uploadUserImage', 'text-align:center;']

            // end: function () {
            //
            //     location.reload();
            // }
        });
    });
};

