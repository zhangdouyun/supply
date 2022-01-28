<!DOCTYPE html>
<html>
<head>
	<title>销售单据查询</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<!-- 左树 -->
		<div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 60%" >
			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<fieldset class="layui-elem-field site-demo-button" >
									<legend>客户退货单据查询</legend>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">退货单号</label>
											<div class="layui-input-block"style="width: 60%">
												<input type="text" class="layui-input customerReturnNumber"
													   name="customerReturnNumber" id="customerReturnNumber" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">客户名称</label>
											<div class="layui-input-block"style="width: 60%">
												<select id="customerId" name="customerId"  >
													<option value="" >请选择</option>
												</select>
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">是否付款</label>
											<div class="layui-input-block" style="width: 60%">
												<select id="state" name="state"   class="select">
													<option value="" >全部</option>
													<option value="1" >已付</option>
													<option value="0" >未付</option>
												</select>
											</div>
										</div>
									</div>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">开始日期</label>
											<div class="layui-input-block">
												<input type="text" name="startDate" id="startDate" lay-verify="startDate"
													   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
											</div>
										</div>

										<div class="layui-col-xs4">
											<label class="layui-form-label">结束日期</label>
											<div class="layui-input-block">
												<input type="text" name="endDate" id="endDate" lay-verify="endDate"
													   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label"></label>
											<div class="layui-input-block">
												<a class="layui-btn layui-btn-normal search_btn" data-type="reload"><i
															class="layui-icon">&#xe615;</i> 搜索</a>
											</div>
										</div>
									</div>
									<br/>
								</fieldset>
							</form>
						</blockquote>

						<table id="customerReturnList" class="layui-table"  lay-filter="customerReturnList"></table>


						<script id="customerReturnListtBar" type="text/html">
							<a class="layui-btn layui-btn-xs" id="edit" lay-event="search">货单</a>
							<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
						</script>
					</form>
				</div>
			</div>
		</div>
		<!-- 右表 -->
		<div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 40%">

			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<fieldset class="layui-elem-field site-demo-button" >
									<legend>退货单商品信息</legend>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">退货单号</label>
											<div class="layui-input-block" style="width: 55%">
												<input type="text" class="layui-input customerReturnNumber_"
													   name="customerReturnNumber_" id="customerReturnNumber_" readonly="readonly" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">客户名称</label>
											<div class="layui-input-block"style="width: 55%">
												<input type="text" class="layui-input customerName"
													   name="customerName_" id="customerName_"  readonly="readonly" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">退货金额</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input purchaseNumber"
													   name="amountPayable_" id="amountPayable_" readonly="readonly" >
											</div>
										</div>
									</div>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">支付状态</label>
											<div class="layui-input-block"style="width: 55%">
												<input type="state_" id="state_"  class="layui-input" readonly="readonly" >
											</div>
										</div>

										<div class="layui-col-xs4">
											<label class="layui-form-label">操作人员</label>
											<div class="layui-input-block"style="width: 55%">
												<input type="text"  name="userName_" id="userName_" class="layui-input" readonly="readonly" >
											</div>
										</div>

										<div class="layui-col-xs2 layui-col-xs-offset1">
											<#--<label class="layui-form-label"></label>-->
											<div>
												<a class="layui-btn layui-btn-normal search_btn02" data-type="reload"style="margin-left: 30px"><i class="layui-icon">&#xe615;</i> 重置</a>
											</div>
										</div>
									</div>
									<br/>
								</fieldset>
							</form>
						</blockquote>
						<table id="customerReturnListGoods" class="layui-table"  lay-filter="customerReturnListGoods"></table>
					</form>

				</div>
			</div>
		</div>

	</div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/customerReturn/customer.return.search.js"></script>
</body>
</html>