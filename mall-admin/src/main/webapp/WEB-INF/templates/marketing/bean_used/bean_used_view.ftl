<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看芯豆使用信息</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript">
$().ready(function() {
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 查看芯豆使用信息
	</div>
		<ul id="tab" class="tab">			
			<li>
				<input type="button" value="芯豆使用信息" />
			</li>			
		</ul>
		
		
	<table class="input tabContent">
				<tr>
					<th>
						会员名称:
					</th>
					<td>
						${beanUsed.member.username}
					</td>
				</tr>
				<tr>
					<th>
						使用时间:
					</th>
					<td>
						${beanUsed.createDate!""}
					</td>
				</tr>
				<tr>
					<th>
						使用类型:
					</th>
					<td>
						${beanUsed.typeView}
					</td>
				</tr>
				<tr>
					<th>
						使用数量:
					</th>
					<td>
						${beanUsed.optValue}
					</td>
				</tr>
				<tr>
					<th>
						关联订单:
					</th>
					<td>
						<#if beanUsed.order?has_content>${beanUsed.order.sn}<#else>-</#if>
					</td>
				</tr>
				<tr>
					<th>
						备注:
					</th>
					<td>
						${beanUsed.memo!"-"}
					</td>
				</tr>
				<tr>
					<th>
						消费来源:
					</th>
					<td>
						
					</td>
				</tr>
				
				
		</table>
		<table class="list">
			<tr>								
				<th>
					领取时间
				</th>
				<th>
					领取类型
				</th>
				<th>
					领取数量
				</th>
				<th>
					是否被使用
				</th>
				<th>
					用完时间
				</th>
				<th>
					是否已经失效
				</th>
				<th>
					自然失效时间
				</th>
				<th>
					已使用数量
				</th>
				
			</tr>
			<#if beanUsed.sources?exists>
			                			            
			<#list beanUsed.sources?keys?sort_by("createDate")?reverse as beanRetain>
				<tr>										
					<td>
						${beanRetain.createDate!""}
					</td>					
					<td>
						${beanRetain.typeView}
					</td>
					<td>
						${beanRetain.optValue}
					</td>
					<td>
						${beanRetain.used?string('已使用', '未使用')}
					</td>
					<td>
						${beanRetain.usedTime!"-"}
					</td>
					<td>
						${beanRetain.isExpired?string('已过期', '未过期')}
					</td>
					<td>
						${beanRetain.expiredTime!"-"}
					</td>
					<td>
						${beanRetain.usedValue}
					</td>
					
				</tr>
			</#list>	
			</#if>
		</table>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.go(-1);"/>
				</td>
			</tr>
		</table>
</body>
</html>