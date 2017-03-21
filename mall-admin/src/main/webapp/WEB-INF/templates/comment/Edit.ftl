
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑评论</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
</head>

<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 编辑评论
	</div>
	<form id="inputForm" action="${base}/admin/comment/edit!${comment.id}.do" method="post">
		<input type="hidden" name="id" value="56" />
		<table class="input">
		<#if comment??>	
			<tr>
				<th>
					商品:
				</th>
				<td>
					<a href="${base}/product/product!${comment.orderItem.product.id}" target="_blank">${comment.orderItem.fullName}</a>
				</td>
			</tr>
			<tr>
				<th>
					会员:
				</th>
				<td>
						${comment.member.username}
				</td>
			</tr>
			<tr>
				<th>
					IP:
				</th>
				<td>
					${comment.ip}
				</td>
			</tr>
			<tr>
				<th>
					评分:
				</th>
				<td>
					${comment.score}
				</td>
			</tr>
			<tr>
				<th>
					内容:
				</th>
				<td>
					${comment.content}
				</td>
			</tr>
			<tr>
				<th>
					是否显示:
				</th>
				<td>
					<input type="checkbox" name="isShow" value="true" <#if !comment.deleted>checked="checked"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='javascript:history.go(-1);'" />
				</td>
			</tr>
		</#if>
		</table>
	</form>
</body>
</html>