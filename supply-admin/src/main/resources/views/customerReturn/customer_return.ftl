<!DOCTYPE html>
<html>
<head>
	<title>客户退货</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<input type="hidden" name="customerReturnNumber" value="${customerReturnNumber!}" >
	<input type="hidden" name="goodsJson" >
	<blockquote class="layui-elem-quote quoteBox">
		<fieldset class="layui-elem-field site-demo-button" >
			<legend>单号:${customerReturnNumber!}</legend>
			<br/>
			<div class="layui-row">
				<div class="layui-col-xs3">
					<label class="layui-form-label">客户名称</label>
					<div class="layui-input-block"style="width: 70%">
						<select id="customerId" name="customerId"  >
							<option value="0" >请选择</option>
						</select>
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label">应付金额</label>
					<div class="layui-input-block"style="width: 70%">
						<input type="text" class="layui-input amountPayable"
							   name="amountPayable" id="amountPayable" >
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label">实付金额</label>
					<div class="layui-input-block"style="width: 70%">
						<input type="text" class="layui-input amountPaid"
							   name="amountPaid" id="amountPaid" >
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label">退货日期</label>
					<div class="layui-input-block" style="width: 70%">
						<input type="text" name="customerReturnDate" id="customerReturnDate" lay-verify="customerReturnDate"
							   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
					</div>
				</div>
			</div>
			<br/>
			<div class="layui-row">
				<div class="layui-col-xs3">
					<label class="layui-form-label">是否付款</label>
					<div class="layui-input-block"style="width: 70%">
						<select id="state" name="state"   class="select">
							<option value="1" >已付</option>
							<option value="0" >未付</option>
						</select>
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
					<div class="layui-input-block" style="width: 70%">
						<input  type="text" class="layui-input remarks"
							   name="remarks" id="remarks" >
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label"></label>
					<button style="width: 280px;background-color: #1aa094" class="layui-btn layui-btn-normal" lay-submit=""
							lay-filter="addCustomerReturnList">保 &nbsp;&nbsp;存
					</button>
				</div>
			</div>
			<br/>
		</fieldset>
	</blockquote>
	<table id="customerReturnList" class="layui-table"  lay-filter="customerReturns"></table>
	<#--操作-->
	<script id="goodsListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>


	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加
			</a>
		</div>
	</script>
</form>
<script type="text/javascript" src="${ctx.contextPath}/js/customerReturn/customer.return.js"></script>

</body>
</html>