<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/page_setting.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/page.js"></script>

<link type="text/css" rel="stylesheet" href="${base}/resources/jquery-ui-1.12.1.custom/jquery-ui.min.css"/>
<script type="text/javascript" src="${base}/resources/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>

</head>
<body>
    <input type="hidden" id="basePath" value="${base}" />
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 页面配置
	</div>
	<div class="bar">
		<a href="javascript:void(0)" class="iconButton" id="addButton">
			<span class="addIcon">&nbsp;</span>添加模块
		</a>
		<a href="javascript:void(0)" class="iconButton" id="listButton">
			<span class="editIcon">&nbsp;</span>模块列表
		</a>
	</div>

	<div style="display:none;overflow:hidden;padding:3px;height:500px;" id="dialog">
	<iframe id="addFrame" border="0" marginwidth="0" marginheight="0" >
	
	</iframe>
	</div>
	<div class="frame_container">
		<iframe name="previewFrame" id="previewFrame" class="preview_frame" src="preview.do?page=${page}">浏览器不支付iframe</iframe>	
	</div>
</body>
</html>