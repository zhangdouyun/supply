<!DOCTYPE html>
<html>
<head>
	<title>月销售统计</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-row">
				<div class="layui-input-inline layui-col-md3">
					<label class="layui-form-label">开始日期</label>
					<div class="layui-input-block">
						<input type="text" name="startDate" id="startDate" lay-verify="startDate"
							   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input" >
					</div>
				</div>
				<div class="layui-input-inline layui-col-md3">
					<label class="layui-form-label">结束日期</label>
					<div class="layui-input-block">
						<input type="text" name="endDate" id="endDate" lay-verify="endDate"
							   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly"  class="layui-input" >
					</div>
				</div>
				<div class="layui-input-inline layui-col-md3">
					<a class="layui-btn layui-btn-radius search_btn" data-type="reload" style="margin-left: 120px"><i class="layui-icon">&#xe615;</i> 搜索</a>
				</div>
			</div>
		</form>
	</blockquote>
	<table id="monthSale" class="layui-table"  lay-filter="monthSale"></table><#--此处ID和month.sale.js中#monthSale-->
	<script type="text/html" id="toolbarDemo"></script><#--此处ID和month.sale.js中的#toolbarDemo-->
</form>
<script type="text/javascript" src="${ctx.contextPath}/js/count/month.sale.js"></script>

</body>
</html>