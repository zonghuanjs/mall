<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/message_view.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;查看短信信息 
	</div>
	
	<table class="input">
		<tr>
			<th>
				sn:
			</th>
			<td>
				${message.sn!""}
			</td>
		</tr>
		<tr>
			<th>
				 发送状态:
			</th>
			<td>			   
			    <#switch message.status>
                      <#case 1>
                                                                                              发送中                                                                             
                       <#break>
                       <#case 0>
                                                                                              发送成功 
                       <#break>
                       <#case -1>
                                                                                               发送失败
                       <#break>                                                
                       <#default>
                                                                                              处理中
                 </#switch>
			</td>
		</tr>
		<tr>
			<th>
				发送内容:
			</th>
			<td>
			    ${message.content!""}
			</td>
		</tr>
		<tr>
			<th>
				发送结果:
			</th>
			<td>
			    ${message.result!""}
			</td>
		</tr>
		<tr>
			<th>
				描述:
			</th>
			<td>
				${message.description!""}				
			</td>
		</tr>
		<tr>
			<th>
				发送号码:
			</th>
			<td>
			  <#list message.numbers as number>
				${number}
			  </#list>		
			</td>
		</tr>
		<tr>
			<th>
				请求IP:
			</th>
			<td>
			  ${message.ip!'-'}	
			  <span ip="${message.id}" id="location"></span>
			</td>
			
		</tr>
		<tr>
			<th>
				创建日期:
			</th>
			<td>
			
				${message.createDate!""}				
			</td>
			
		</tr>
		<tr>
			<th>
				任务Id:
			</th>
			<td>
			
				${message.taskid!""}				
			</td>
			
		</tr>
		<tr>
			<th>
				失败列表:
			</th>
			<td>
			
				${message.faillist!""}				
			</td>
			
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td>			
				<input type="button" id="backButton" class="button" value="返回" />
			</td>
		</tr>
	</table>

</body>
</html>