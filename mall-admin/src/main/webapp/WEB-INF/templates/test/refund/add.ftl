<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${config.siteName}</title>
</head>
<style type="text/css">
body,input
{
	font:12px "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif
}
li
{
	list-style-type:none;
	margin:20px auto;
}
li input
{
	width:230px;
	padding-left:10px;
	height:30px;
	line-height:30px;
	border:1px solid #DDD;
}
</style>
<body>
	<form action="${base}/test/refund/add.do" method="post">
		<ul>
			<li>
				退款编号：<input type="text" name="refundSn" />
			</li>
		</ul>
		<input type="submit" value="创建" style="width:80px;height:30px;background:#DDD;border:none; margin-left:150px;cursor:pointer;" />
	</form>
</body>
</html>