/**
 * 从controller中获取数据，然后在弹窗中显示
 */
var aPublisher, aDepartment, aContent, aPlate,aTime;
var nPublisher, nDepartment, nContent, nPlate,nTime;
/**
 * 公告弹窗内容显示
 * @type {Function}
 */
window.display = (function (target) {
    var anName = $(target).text();
    console.log(anName);
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery, layer = layui.layer, form = layui.form; //独立版的layer无需执行这一句
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            url: '/announcementsData',
            data: {"anName": anName},
            dataType: "json",
            error: function () {
                alert('访问错误！');
            },
            success: function (data) {
                var announcements = JSON.stringify(data);// //jsonData是该下路下的所有区间（json格式）
                var jsonData = JSON.parse(announcements);
                //获取对应的内容
                aPublisher = jsonData[0]['apublisher'];
                aTime = jsonData[0]['atime'];
                aDepartment = jsonData[0]['adepartment'];
                aContent = jsonData[0]['acontent'];
                aPlate = jsonData[0]['aplate'];
            }
        });
        layer.open({
            type: 2,
            title: ['公告', 'text-align:center;font-size:20px;'],
            content: ['/newsDisplay', 'text-align:center;'],
            anim: 3, //弹出方式
            //maxmin: true, //开启最大化最小化按钮
            area: ['1200px', '800px'], //设置宽高
            skin: 'layui-layer-rim', //加上边框
            // data: {'anName': anName},
            success: function (layero, index) {   //成功获得加载界面时，预先加载，将值从父窗口传到子窗口

                var body = layer.getChildFrame('body', index);
                // var iframeWindow = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象
                //给弹窗界面填充内容
                body.contents().find("#title").val(anName);
                body.contents().find("#aPlate").val(aPlate);
                body.contents().find("#aDepartment").val(aDepartment);
                body.contents().find("#aPublisher").val(aPublisher);
                body.contents().find("#aTime").val(aTime);
                body.contents().find("#aContent").val(aContent);
            }
        });
    })
});
/**
 *新闻弹窗内容显示
 * @type {Function}
 */
window.news = (function (target) {
    var newsName = $(target).text();
    console.log(newsName);
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery, layer = layui.layer, form = layui.form; //独立版的layer无需执行这一句
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            url: '/newsData',
            data: {"newsName": newsName},
            dataType: "json",
            error: function () {
                alert('访问错误！');
            },
            success: function (data) {
                var announcements = JSON.stringify(data);// //jsonData是该下路下的所有区间（json格式）
                var jsonData = JSON.parse(announcements);
                //获取对应的内容
                nPublisher = jsonData[0]['npublisher'];
                nTime = jsonData[0]['ntime'];
                nDepartment = jsonData[0]['ndepartment'];
                nContent = jsonData[0]['ncontent'];
                nPlate = jsonData[0]['nplate'];
            }
        });
        layer.open({
            type: 2,
            title: ['新闻', 'text-align:center;font-size:20px;'],
            content: ['/newsDisplay', 'text-align:center;'],
            anim: 3, //弹出方式
            //maxmin: true, //开启最大化最小化按钮
            area: ['1200px', '800px'], //设置宽高
            skin: 'layui-layer-rim', //加上边框
            // data: {'anName': anName},
            success: function (layero, index) {   //成功获得加载界面时，预先加载，将值从父窗口传到子窗口

                var body = layer.getChildFrame('body', index);
                // var iframeWindow = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象
                //给弹窗界面填充内容
                body.contents().find("#title").val(newsName);
                body.contents().find("#aPlate").val(nPlate);
                body.contents().find("#aDepartment").val(nDepartment);
                body.contents().find("#aPublisher").val(nPublisher);
                body.contents().find("#aTime").val(nTime);
                body.contents().find("#aContent").val(nContent);
            }
        });
    })
});