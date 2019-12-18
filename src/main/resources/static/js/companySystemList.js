$(function () {
    layui.use(['table', 'form', 'layer'], function () {
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        form.render();

        tableIn = table.render({
            elem: '#layListId',
            id: 'layTableId',
            url: '/companySystemListData',
            method: 'post', //默认：get请求
            page: true,
            //toolbar: '#toolbarDemo',//开启头部工具栏，并为其绑定左侧模板
            //defaultToolbar: ['filter', 'exports', 'print'],
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
            height: 620,
            width: 900,
            cols: [[
                {field: 'iid', title: 'ID', display: 'none', hide: true},
                {field: 'iname', width: 420, align: 'center', title: '制度名称'},
                {
                    field: 'idepartment', width: 150, align: 'center', title: '发布部门',
                    templet: function (res) {
                        console.log(res);
                        if (res.idepartment == "研发部") {
                            return "研发部";
                        } else if (res.idepartment == "销售部") {
                            return "销售部";
                        } else if (res.idepartment == "综合管理部") {
                            return "综合管理部";
                        } else {
                            return " "
                        }
                    }
                },
                {field: 'itime', width: 180, align: 'center', title: '发布时间'},
                {width: 150, title: '操作', align: 'center', toolbar: '#barDemo'}
            ]],
            done: function (res, curr, count) {
                $("[data-field='username']").children().each(function () {
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
            var Data = obj.data;
            if (obj.event === 'edit') {
                //查看
                edit(Data);
            }
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
                        url: '/effectiveInstitution',
                        page: {
                            curr: 1 //重新从第 1 页开始
                        },
                        where: {
                            iName: deviceNumber.val(),//这里传参  向后台
                            iDepartment: department.val()
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

//打开编辑框
function edit(data, title) {
    console.log(data);
    //弹窗显示数据
    layer.open({
        type: 2,
        title: ['制度', 'text-align:center;font-size:25px;letter-spacing: 30px;'],
        anim: 2, //弹出方式
        area: ['1200px', '900px'], //设置宽高
        content: ['/companySystemDataShow', 'text-align:center;'],
        success: function (layero, index) {   //成功获得加载界面时，预先加载，将值从父窗口传到子窗口
            var body = layer.getChildFrame('body', index);
            // var iframeWindow = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象
            //给弹窗界面填充内容
            body.contents().find("#iName").val(data.iname);
            body.contents().find("#iDepartment").val(data.ipublisher);
            body.contents().find("#iPublisher").val(data.idepartmentt);
            body.contents().find("#iTime").val(data.itime);
            body.contents().find("#iContent").val(data.icontent);
        },
        end: function () {
            cleanUser();
        }
    });
}

function cleanUser() {
    $("#iName").val("");
    $("#iPublisher").val("");
    $("#iDepartment").val("");
    $("#iTime").val("");
    $("#iContent").val("");
}

//重新加载table
function load(obj) {
    tableIn.reload('layTableId', {});
}