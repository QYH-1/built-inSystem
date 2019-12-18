$(function () {
    layui.use(['table', 'form', 'layer'], function () {
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        form.render();

        table.render({
            elem: '#layListId',
            id: 'layTableId',
            url: '/InstitutionList',
            method: 'post', //默认：get请求
            page: true,
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
            height: 470,
            cols: [[
                {checkbox: true, fixed: true},
                {field: 'iName', width: 120, align: 'center',title:'制度名'},
                // {field: 'iPublisher', width: 180, align: 'center'},
                // {field: 'iIdentity', width: 120, align: 'center'},
                // {field: 'iDepartment', width: 180, align: 'center'},
                // {field: 'iTime', width: 200, align: 'center'},
                // {field: 'iUpdateTime', width: 180, align: 'center'},
                // {field: 'department', width: 150, align: 'center'},
                // {field: 'iType', width: 120, align: 'center'},
                {width: 180, title: '操作', align: 'center', toolbar: '#optBar'/*,width:"25%"*/}
            ]],
            done: function (res, curr, count) {
                $("[data-field='iName']").children().each(function () {
                    if ($(this).text() == '') {
                        $(this).text("根目录");
                    } else {
                        $(this).text($(this).text());
                    }
                });
                pageCurr = curr;

            }
        });


        //监听工具条
        table.on('tool(layList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                del(data, data.id);
            } else if (obj.event === 'edit') {
                //编辑
                edit(data);
            }
        });

        // //监听提交
        // form.on('submit(permissionSubmit)', function (data) {
        //     formSubmit(data);
        //     return false;
        // });

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
                        url:'/effectiveUser',
                        page: {
                            curr: 1 //重新从第 1 页开始
                        },
                        where: {
                            username: deviceNumber.val(),//这里传参  向后台
                            department:department.val()
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
            layer.msg("保养记录");
        };

        function repairRecord() {
            layer.msg("维修记录");

        };

    });

});

//提交表单
function formSubmit(obj) {
    $.ajax({
        type: "post",
        data: $("#permissionForm").serialize(),
        url: "/permission/setPermission",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg, function () {
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试", function () {
                layer.closeAll();
                load(obj);
            });
        }
    });
}

//新增
function add() {
    edit(null, "新增");
}

//打开编辑框
function edit(data, title) {
    var parentId = null;
    if (data == null) {
        $("#id").val("");
    } else {
        //回显数据
        $("#id").val(data.id);
        $("#name").val(data.name);
        $("#descpt").val(data.descpt);
        $("#url").val(data.url);
        parentId = data.pid;
    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);
    $.ajax({
        url: '/permission/parentPermissionList',
        dataType: 'json',
        async: true,
        success: function (data) {
            $("#pid").html("<option value='0'>根目录</option>");
            $.each(data, function (index, item) {
                if (!parentId) {
                    var option = new Option(item.name, item.id);
                } else {
                    var option = new Option(item.name, item.id);
                    // // 如果是之前的parentId则设置选中
                    if (item.id == parentId) {
                        option.setAttribute("selected", 'true');
                    }
                }
                $('#pid').append(option);//往下拉菜单里添加元素
                form.render('select'); //这个很重要
            })
        }
    });

    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        area: ['550px'],
        content: $('#setPermission'),
        end: function () {
            cleanPermission();
        }
    });
}

function cleanPermission() {
    $("#name").val("");
    $("#descpt").val("");
    $("#url").val("");
}

//重新加载table
function load(obj) {
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

//删除
function del(obj, id) {
    if (null != id) {
        layer.confirm('您确定要删除吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/permission/del", {"id": id}, function (data) {
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
