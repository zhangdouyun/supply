<!DOCTYPE html>
<html>
<head>
    <title>库存报警</title>
    <#include "../common.ftl">
    <link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
</head>
<body class="childrenBody">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form">
                    <blockquote class="layui-elem-quote quoteBox">
                        <form class="layui-form">
                            <div class="layui-inline">
                                <div class="layui-input-inline"><#--同一行-->
                                    <input type="text" name="goodsName" class="layui-input searchVal" placeholder="商品名"/>
                                </div> &nbsp;&nbsp;
                                <a class="layui-btn layui-btn-normal search_btn" style="background: #1aa094"
                                   data-type="reload"><i class="layui-icon">&#xe615;</i> 搜 &nbsp;索</a> <#--通过a标签创建搜索按钮-->
                            </div>
                        </form>
                    </blockquote>
                    <table id="goodsList" class="layui-table" lay-filter="goods"></table>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx.contextPath}/js/common/alarm.js"></script>
</body>
</html>