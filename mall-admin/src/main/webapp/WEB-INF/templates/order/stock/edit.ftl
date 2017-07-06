<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>库存编辑</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css"/>
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/js/product_stock.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/index.do">首页</a> &raquo; 库存编辑
	</div>
<#if product?has_content>
	<form id="inputForm" action="save.do?id=${product.id}" method="post">
		<table class="input">
			<tr>
				<th>
					商品编号:
				</th>
				<td>
					<span>${product.sn}</span>
				</td>
			</tr>
			<tr>
				<th>
					商品名称:
				</th>
				<td>
					<span>${product.name}</span>
				</td>
			</tr>
			<tr>
				<th>
					ERP库存:
				</th>
				<td>
					<span id="erpStock" stock="0"></span>
				</td>
			</tr>
			<tr>
				<th>
					系统库存:
				</th>
				<td>
					<span id="stock" pid="${product.id}" stock="${product.stock!0}">${product.stock!0}</span>${product.unit!'件'}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>库存存操作:
				</th>
				<td>
					<fieldset>
						<label>
							<input type="radio" name="stockOperation" value="add" checked="checked" />增加
						</label>
						<label>
							<input type="radio" name="stockOperation" value="substract" />减少
						</label>
					</fieldset>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>操作值:
				</th>
				<td>
					<input type="text" name="stock" class="text" value="0" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button"  class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.back(-1);" />
				</td>
			</tr>
		</table>
	</form>
</#if>
</body>
</html>