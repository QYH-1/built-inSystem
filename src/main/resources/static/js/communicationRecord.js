var Birthday;
//获取当前用户的用户名
var name = document.getElementById("userName").innerText;
$(function () {
    layui.use(['table', 'form', 'layer'], function () {
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        form.render();

        tableIn = table.render({
            elem: '#layListId',
            id: 'layTableId',
            url: '/UserList',
            method: 'post', //默认：get请求
            page: true,
            toolbar: '#toolbarDemo',//开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print'],
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'data' //数据列表的字段名称，默认：data
            },
            height: 520,
            cols: [[
                {checkbox: true, fixed: true},
                {field: 'uid', width: 120, align: 'center', title: '编号'},
                {field: 'username', width: 120, align: 'center', title: '姓名'},
                {field: 'phone', width: 180, align: 'center', title: '电话'},
                {field: 'gender', width: 120, align: 'center', title: '性别'},
                {field: 'birthday', width: 180, align: 'center', title: '生日'},
                {field: 'email', width: 200, align: 'center', title: '邮箱'},
                {field: 'recentlyLanded', width: 180, align: 'center', title: '最近登录时间'},

                {
                    field: 'department', width: 150, align: 'center', title: '所在部门',
                    templet: function (res) {
                        if (res.department == "研发部") {
                            return "研发部";
                        } else if (res.department == "销售部") {
                            return "销售部";
                        } else if (res.department == "综合部") {
                            return "综合部";
                        } else {
                            return "还未分配部门"
                        }
                    }
                },
                {field: 'position', width: 120, align: 'center', title: '职位'},
                {width: 180, title: '操作', align: 'center', toolbar: '#barDemo'}
            ]],
            done: function (res, curr, count) {
                $("[data-field='username']").children().each(function () {
                    if ($(this).text() == '') {
                        $(this).text("根目录");
                    } else {
                        $(this).text($(this).text());
                    }
                });
                if (name != "admin") {
                    // 隐藏列
                    $("#set").attr("style", "display:none;");
                    $("#edit").attr("disabled", true).css("pointer-events", "none").css("opacity", "0.2");
                    $("#del").attr("disabled", true).css("pointer-events", "none").css("opacity", "0.2");
                }
                pageCurr = curr;

            }
        });

        //监听工具条
        table.on('tool(layList)', function (obj) {
            var Data = obj.data;
            if (obj.event === 'del') {
                //删除
                del(Data, Data.uid);
            } else if (obj.event === 'edit') {
                //编辑
                edit(Data);
                console.log(Data);
            }
        });

        //监听提交
        form.on('submit(userSubmit)', function (data) {
            console.log(data);
            console.log(Birthday);
            $.ajax({
                type: "post",
                data: {
                    "uid": data.field.uid,
                    "username": data.field.username,
                    "phone": data.field.phone,
                    "email": data.field.email,
                    "gender": data.field.sex,
                    "birthday": Birthday,
                    "department": data.field.department1,
                    "position": data.field.position
                },
                url: "/updateUser",
                success: function (data) {
                    if (data.code == 1) {
                        layer.alert(data.msg, function () {
                            layer.closeAll();
                            load(data);
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
            return false;
        });

        //----搜索开始-----
        var $ = layui.$,
            active = {
                getCheckLength: function () { //获取选中数目
                    var checkStatus = table.checkStatus('layTableId'),
                        data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                },

                isAll: function () { //验证是否全选
                    var checkStatus = table.checkStatus('layTableId');
                    layer.msg(checkStatus.isAll ? '全选' : '未全选')
                },

                reload: function () {
                    var deviceNumber = $("#deviceNumber"); //获取输入框的值
                    var department = $("#department option:selected");
                    var index = layer.msg('查询中，请稍后...', {icon: 16, time: false, shade: 0});
                    //执行重载
                    table.reload('layTableId', {
                        url: '/effectiveUser',
                        page: {
                            curr: 1 //重新从第 1 页开始
                        },
                        where: {
                            username: deviceNumber.val(),//这里传参  向后台
                            department: department.val()
                        }
                    });
                    layer.close(index);
                }
            };
        //点击事件
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //  ----搜索结束-----


        function maintainRecord() {
            layer.msg("");
        };

        function repairRecord() {
            layer.msg("");

        };

    });

});

//新增
function add() {
    edit(null, "新增");
}

//打开编辑框
function edit(data, title) {
    //获取div
    var div = document.getElementById("DvelopmentTarget");
    var span = document.createElement("span");
    var div1 = document.createElement("div");
    span.id = "testView";
    div1.id = "test2";
    div.appendChild(span);
    div.appendChild(div1);
    var date;
    var parentId = null;
    if (data == null) {
        $("#id").val("");
    } else {
        //回显数据
        $("#uid").val(data.uid);
        $("#email").val(data.email);
        $("#username").val(data.username);
        $("#phone").val(data.phone);
        $("#department1").val(data.department);
        $("#position").val(data.position);
        var sex = data.gender;

        console.log(sex);
        parentId = data.uid;
        birthday = data.birthday;
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            date = laydate.render({
                id: 'laydate',
                elem: '#test2',
                position: 'static',
                value: birthday,
                showBottom: false,
                change: function (value, date) { //监听日期被切换
                    lay('#testView').html(value);
                },
                done: function (value, date, endDate) {
                    Birthday = value;
                    console.log(Birthday);
                }
            });

        });
    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);
    layui.use('form', function () {
        var form = layui.form;
        $.ajax({
            success: function () {
                $("input[name=sex][value='男']").attr("checked", sex == '男' ? true : false);
                $("input[name=sex][value='女']").attr("checked", sex == '女' ? true : false);
                form.render();
            }
        });
    });
    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        area: ['550px'],
        content: $('#setUser'),
        end: function () {
            cleanUser();
            $("#testView").remove();
            $("#test2").remove();
        }
    });
}

function cleanUser() {
    $("#email").val("");
    $("#username").val("");
    $("#phone").val("");
    $("#department").val("");
    $("#position").val("");

}


//删除
function del(obj, uid) {
    console.log(obj.field);
    if (null != uid) {
        layer.confirm('您确定要删除吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/del", {"uid": uid}, function (data) {
                if (data.code == 1) {
                    layer.alert(data.msg, function () {
                        layer.closeAll();
                        load(obj);

                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function () {
            layer.closeAll();
        });
    }
}

//重新加载table
function load(obj) {
    tableIn.reload('layTableId', {});
}