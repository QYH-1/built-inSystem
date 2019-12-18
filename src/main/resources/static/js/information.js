window.setBirthday = function (birthday, gender) {
    layui.use(['laydate', 'form','layer'], function () {
        var laydate = layui.laydate;
        var form = layui.form;
        var layer = layui.layer;
        laydate.render({
            id: 'birthday',
            elem: '#birthday',
            value: birthday
        });
        $.ajax({
            success: function () {
                $("input[name=sex][value='男']").attr("checked", gender == '男' ? true : false);
                $("input[name=sex][value='女']").attr("checked", gender == '女' ? true : false);
                form.render();
            }
        });
        form.render();

        form.on('submit(userSubmit)', function (data) {
            // console.log(data.field.sex);
            $.ajax({
                type: "post",
                async: false,//指定为同步请求
                cache: false,
                data: {
                    "username": data.field.username,
                    "phone": data.field.phone,
                    "email": data.field.email,
                    "gender": data.field.sex,
                    "birthday": data.field.birthday
                },
                url: "/personalModification",
                success: function (data) {
                    var announcements = JSON.stringify(data);// //jsonData是该下路下的所有区间（json格式）
                    var jsonData = JSON.parse(announcements);
                    console.log(data.msg);
                    if (data.code == 1) {
                        layer.alert(data.msg, function () {
                            layer.closeAll();
                            //load(jsonData);
                        });
                    } else {
                        layer.alert(data.msg);
                    }
                },
                error: function () {
                    layer.alert("操作请求错误，请您稍后再试", function () {
                        layer.closeAll();
                        load(data);
                    });
                }
            });
        })
    });
};