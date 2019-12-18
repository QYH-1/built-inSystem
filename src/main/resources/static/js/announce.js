// 添加文件
var showTable = document.getElementById("showTable");
var fileName, indexNum, uploadListIns, data;
layui.use(['form', 'laydate', 'upload'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var upload = layui.upload;
    var aTime;
    //执行一个laydate实例
    laydate.render({
        elem: '#aTime',
         showBottom: false,
        change: function (value, date) { //监听日期被切换
            lay('#testView').html(value);
        },
        done: function (value, date, endDate) {
            aTime = value;
            console.log(aTime);
        }
    });
    form.on('select(hc_select)', function (data) {   //选择移交单位 赋值给input框
        $("#aPlate").val(data.value);
        $("#hc_select").next().find("dl").css({"display": "none"});
        form.render();
    });
    window.search = function () {
        var value = $("#HandoverCompany").val();
        $("#hc_select").val(value);
        form.render();
        $("#hc_select").next().find("dl").css({"display": "block"});
        var dl = $("#hc_select").next().find("dl").children();
        var j = -1;
        for (var i = 0; i < dl.length; i++) {
            if (dl[i].innerHTML.indexOf(value) <= -1) {
                dl[i].style.display = "none";
                j++;
            }
            if (j == dl.length - 1) {
                $("#hc_select").next().find("dl").css({"display": "none"});
            }
        }

    };
    //执行实例
    uploadListIns = upload.render({
        elem: '#file', //绑定元素
        accept: 'file',//允许上传的文件类型
        field: 'file',
        type: "file",
        //auto: false,//是否选完文件后自动上传。
        //bindAction: '#submit',//指向一个按钮触发上传，一般配合 auto: false 来使用。
        url: '/uploadFile',//上传接口
        method: 'post',
        before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            layer.load(); //上传loading
        },
        choose: function (obj) { //选择文件后的回调函数。返回一个object参数
            //layer.closeAll('loading'); //关闭loading
            showTable.style.display = "block";
            //将每次选择的文件追加到文件队列
            var files = obj.pushFile();
            //预读本地文件，如果是多文件，则会遍历。(不支持ie8/
            obj.preview(function (index, file, result) {
                //console.log(index); //得到文件索引
                //console.log(result); //得到文件base64编码，比如图片
                console.log(file); //得到文件对象
                fileName = file.name;
                var reader = new FileReader;
                reader.readAsText(file, 'gb2312');
                reader.onload = function (evt) {
                    data = evt.target.result;
                }
                var row = $("#fileList").clone();
                row.find("#fileName").text(file.name);
                row.find("#fileSize").text(file.size + " kB");
                row.find("#operation").text("删除");
                row.appendTo("#datas");
                indexNum = index;
            });
        },
        progress: function (n) {
            var percent = n + '%' //获取进度百分比
            element.progress('demo', percent); //可配合 layui 进度条元素使用
        },
        done: function (data) {
            //上传完毕回调
        },
        error: function (index, upload) {
            //请求异常回调
            layer.closeAll('loading'); //关闭loading
        }
    });

    form.render();
    form.on('submit(sub)', function (data) {
        console.log(aTime);
        $.ajax({
            type: "post",
            data: {
                "aName": data.field.aName,
                "aPublisher": data.field.aPublisher,
                "aTime": aTime,
                "aDepartment": data.field.aDepartment,
                "aPlate": data.field.aPlate,
                "aContent": data.field.aContent
            },
            url: "/addAnnouncement",
            success: function (data) {
                layer.alert(data.msg);
            },
            error: function () {
                layer.alert("操作请求错误，请您稍后再试", function () {
                    layer.closeAll();
                    load(data);
                });
            }
        });

    });
});
window.content = function () {
    console.log(data);
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.open({
            title: [fileName, 'text-align:center;font-size:20px;'],
            area: ['800px', '600px'],
            content: data
        });
    });
};
//删除表格中的一行
window.delRow = function (obj) {
    console.log(11);
    var tr = this.getRowObj(obj);
    console.log(tr);
    if (tr != null) {
        tr.parentNode.removeChild(tr);
    } else {
        throw new Error("the given object is not contained by the table");
    }
};

//得到行对象
function getRowObj(obj) {
    var i = 0;
    while (obj.tagName.toLowerCase() != "tr") {
        obj = obj.parentNode;
        if (obj.tagName.toLowerCase() == "table") return null;
    }
    return obj;
};