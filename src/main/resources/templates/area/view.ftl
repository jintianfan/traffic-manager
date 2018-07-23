<#include "../templ/global.ftl">

<#include "../templ/begin_of_page.ftl">
<#include "../templ/page_head.ftl">
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
<style type="text/css">
    #mylimit-container {width:auto; height: auto;min-height: 600px }


    #tip {
        background-color: #ddf;
        color: #333;
        border: 1px solid silver;
        box-shadow: 3px 4px 3px 0px silver;
        position: absolute;
        top: 10px;
        right: 10px;
        border-radius: 5px;
        overflow: hidden;
        line-height: 20px;
    }
    #tip input[type="text"] {
        height: 25px;
        border: 0;
        padding-left: 5px;
        width: 280px;
        border-radius: 3px;
        outline: none;
    }


</style>

<div style="display:none" id="m2" value="area-view"></div>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
<#include "../templ/header.ftl">


<!-- BEGIN CONTAINER -->
<div class="page-container">
<#include "../templ/sidebar.ftl">
<#--<#include "content.ftl">-->
    <div class="page-content-wrapper">
        <!-- BEGIN CONTENT BODY -->
            <div class="page-content" style="min-height:1318px">
                <!--开始真正的内容-->
                <div id="mylimit-container"></div>
                <div class="button-group">
                    <input type="button" class="button" value="鼠标绘制面" id="polygon"/>
                </div>

                <div id="tip">
                    <input type="text" id="keyword" name="keyword" value="请输入关键字：(选定后搜索)" onfocus='this.value=""'/>
                </div>

                <#--<div id="myPageTop">-->
                    <#--<table>-->
                        <#--<tr>-->
                            <#--<td>-->
                                <#--<label>请输入关键字：</label>-->
                            <#--</td>-->
                        <#--</tr>-->
                        <#--<tr>-->
                            <#--<td>-->
                                <#--<input id="tipinput"/>-->
                            <#--</td>-->
                        <#--</tr>-->
                    <#--</table>-->
                <#--</div>-->


            </div>
        <!-- END CONTENT BODY -->
    </div>
<#include "../templ/quick_sidebar.ftl">
</div>
<!-- END CONTAINER -->
<#include "../templ/footer.ftl">
<#include "../templ/end_of_page.ftl">


<script src="https://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="https://webapi.amap.com/maps?v=1.4.8&key=345ca07dafcddd2f1998225091c97604&plugin=AMap.MouseTool,AMap.Autocomplete"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
<#--<script>-->
    <#--$(document).ready(function() {-->
        <#--//TableDatatablesEditable.init();-->
    <#--});-->
<#--</script>-->

<script>
    var map = new AMap.Map('mylimit-container', {
        resizeEnable: true,
        zoom:11
        //center: [116.397428, 39.90923] 不设置center，显示当前定位
    });

    AMap.plugin(['AMap.Autocomplete','AMap.PlaceSearch'],function(){
        var autoOptions = {
            city: "杭州", //城市，默认全国
            input: "keyword"//使用联想输入的input的id
        };
        autocomplete= new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            city:'杭州',
            map:map
        })
        AMap.event.addListener(autocomplete, "select", function(e){
            //TODO 针对选中的poi实现自己的功能
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name)
        });
    });


    // 构造矢量圆形
    var circle = new AMap.Circle({
        center: new AMap.LngLat("116.403322", "39.920255"), // 圆心位置
        radius: 1000,  //半径
        strokeColor: "#F33",  //线颜色
        strokeOpacity: 1,  //线透明度
        strokeWeight: 3,  //线粗细度
        fillColor: "#ee2200",  //填充颜色
        fillOpacity: 0.35 //填充透明度
    });
    map.add(circle);

    //在地图中添加MouseTool插件
    var mouseTool = new AMap.MouseTool(map);
    AMap.event.addDomListener(document.getElementById('polygon'), 'click', function() {
        mouseTool.polygon();
    }, false);


    AMap.event.addListener(mouseTool, 'draw', function(type,obj) {
        mouseTool.close();//释放绘制绑定
        var polygonItem = type.obj;
        var path = polygonItem.getPath();//取得绘制的多边形的每一个点坐标
        console.log("创建完成新的多边形");
        console.log(path);
        //添加覆盖物事件
        polygonItem.on('click', function() {
            alert('您点击了覆盖物'+this.getPath());
            map.remove(this);
        });
    });



</script>