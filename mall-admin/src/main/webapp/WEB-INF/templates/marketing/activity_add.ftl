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
<script type="text/javascript">
$().ready(function() {

    var $inputForm = $("#inputForm");
	
	//editor
	if(typeof(KindEditor) != 'undefined'){
		KindEditor.ready(function(K){
			editor = K.create('#editor', {
				height: '350px',
				syncType: 'form',
				afterChange: function(){
					this.sync();
				},
				filterMode: false
			});
		});
	}
	
	$inputForm.validate({
		rules: {
			title: 'required',
			beginDate: 'required',
			endDate: 'required',
			thumbnail: 'required',
			url: 'required'
		},
		messages: {
			title: '必填',
			thumbnail: '必填',
			beginDate: '必填',
			endDate: '必填',
			url:  '必填',
		},
		errorPlacement: function(error, element){
			if(element.is(':radio')){
				error.appendTo(element.closest('fieldset'));
			}
			else
				error.appendTo(element.parent());				
		}
	});
	
	$inputForm.submit(function(e){
	
		var value = $("#type").children("option:selected").val();
		if(0==parseInt(value)){
			 e.preventDefault();
			$("#type").after('<label for="type" class="error">必填</label>'); 
		}
	});
});
</script>
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
	<form id="inputForm" action="<#if activity??>${base}/admin/activity/save.do?id=${activity.id}<#else>${base}/admin/activity/add.do</#if>" method="post">
		<div class="tabContent">
			<table class="input">
				<tr>
					<th>
						<span class="requiredField">*</span>活动标题:
					</th>
					<td colspan="3">
						<input type="text" name="title" class="text" style="width:300px;" maxlength="200" <#if activity??> value="${activity.title!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>起始日期:
					</th>
					<td colspan="2">
						
						<input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if activity??>value="${activity.beginDate!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>结束日期:
					</th>
					<td colspan="2">
						<input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if activity??>value="${activity.endDate!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>活动图片URL:
					</th>
					<td colspan="3">
						<input type="text" name="thumbnail" class="text" style="width:300px;" maxlength="200" <#if activity??> value="${activity.thumbnail!""}"</#if>/>
						（全路径）
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>活动链接URL:
					</th>
					<td colspan="3">
						<input type="text" name="url" class="text" style="width:300px;" maxlength="200" <#if activity??> value="${activity.url!""}"</#if>/>
						（全路径）
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>活动类型:
					</th>
					<td colspan="2">
					<#--//活动类型 1：减价 打折促销  2：节日促销  3：积分促销   4：买就送促销（买赠送其他商品）5：抽奖活动  6：限时限量抢购  7：众筹促销  8：答题竞猜促销  9：参与游戏促销-->
						<select name="type" id="type">
						<option value=0>请选择...</option>
						<option value=1 <#if activity?exists && activity.type==1>selected</#if>>销价打折促销</option>
						<option value=2 <#if activity?exists && activity.type==2>selected</#if>>节日促销</option>
						<option value=3 <#if activity?exists && activity.type==3>selected</#if>>积分促销</option>
						<option value=4 <#if activity?exists && activity.type==4>selected</#if>>买就送促销</option>
						<option value=5 <#if activity?exists && activity.type==5>selected</#if>>抽奖活动</option>
						<option value=6 <#if activity?exists && activity.type==6>selected</#if>>限时限量抢购</option>
						<option value=7 <#if activity?exists && activity.type==7>selected</#if>>众筹促销</option>
						<option value=8 <#if activity?exists && activity.type==8>selected</#if>>答题竞猜促销</option>
						<option value=9 <#if activity?exists && activity.type==9>selected</#if>>参与游戏促销</option>
						</select>
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
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/activity/list.do'" />
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>