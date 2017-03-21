<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加促销 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/mall_message.js"></script>
<style type="text/css">
.error
{
	color: red;
}
</style>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加活动
	</div>
	<form id="inputForm" action="<#if message??>${base}/admin/salesmessage/save.do?id=${message.id}<#else>${base}/admin/salesmessage/add.do</#if>" method="post">
		<div class="tabContent">
			<table class="input">
				<tr>
					<th>
						<span class="requiredField">*</span>消息类型:
					</th>
					<td colspan="3">
						<select id="type" name="type" style="width: 90px;">
						<option value="0">请选择...</option>
						<option value="1" <#if message?exists && message.type==1>selected</#if>>系统消息</option>
						<#--<option value="2" <#if message?exists && message.type==2>selected</#if>>优惠券消息</option>-->
						<option value="3" <#if message?exists && message.type==3>selected</#if>>促销消息</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>消息标题 :
					</th>
					<td colspan="3">
						<input type="text" name="title" class="text" style="width:400px;" maxlength="200" <#if message??> value="${message.title!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>消息内容类型 :
					</th>
					<td colspan="2">
						<select id="contentType" name="contentType" style="width: 90px;">
						<option value="0">请选择...</option>
						<option value="1" <#if message?exists && message.contentType==1>selected</#if>>文本</option>
						<option value="2" <#if message?exists && message.contentType==2>selected</#if>>图片</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>适用于会员等级 :
					</th>
					<td colspan="2">
						<#list ranks as rank>
						<input type="checkbox" name="ranks" value="${rank.id}"  <#if message.forRanks?size gt 0 && message.forRanks?seq_contains(rank)>checked</#if> >${rank.name}
						<#if rank_has_next>&nbsp;&nbsp;</#if>
						</#list>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>消息内容:
					</th>
					<td colspan="3">
						<input type="text" name="content" class="text" style="width:400px;" maxlength="200" <#if message??> value="${message.content!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>推送消息开始日期:
					</th>
					<td colspan="2">
						<input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if message??>value="${message.beginDate!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>推送消息结束日期:
					</th>
					<td colspan="2">
						<input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if message??>value="${message.endDate!""}"</#if>/>
					</td>
				</tr>
				<tr id="trImgURL" style="display:<#if message?exists && message.contentType==1>none;</#if>" >
					<th>
						<span class="requiredField">*</span>消息图片URL:
					</th>
					<td colspan="3">
						<input type="text" class="text" name="imgURL" id="imgURL" <#if message??> value="${message.imgURL!""}"</#if>/>
						<input type="button" class="button browserButton" value="选择文件" />
					</td>
				</tr>
				<tr id="trMsgURL">
					<th>
						消息链接URL:
					</th>
					<td colspan="3">
						<input type="text" name="msgURL" id="msgURL" class="text" style="width:400px;" maxlength="200" <#if message??> value="${message.msgURL!""}"</#if>/>
						（全路径）
					</td>
				</tr>
				<tr>
					<th>
						启用:
					</th>
					<td>
						<input type="checkbox" name="usenow" id="usenow" value="true" <#if message?? && message.isEnabled==true>checked</#if>/>
					</td>
				</tr>
			</table>
						
		</div>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/salesmessage/list.do'" />
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>