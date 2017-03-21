<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看会员</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 芯助力活动管理
	</div>
		
	<table class="input">
		<tr>
			<th>
				总访问人数:
			</th>
			<td>
				<span title="总访问人数">${total!0}</span>
			</td>
			<th>
				参与活动人数：
			</th>
			<td>
				<span title="参与人数">${partinNumber!0}</span>
			</td>
			<th>
				拉助力成功人数：
			</th>
			<td>
				<span title="拉助力成功人数">${successNumber!0}</span>
			</td>
		</tr>
	
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
		
		<form id="listForm" action="index.do" method="get">
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" val="10" <#if pager.pageSize==10>class="current"</#if>>10</a>
							</li>
							<li>
								<a href="javascript:;" val="20" <#if pager.pageSize==20>class="current"</#if>>20</a>
							</li>
							<li>
								<a href="javascript:;" val="50" <#if pager.pageSize==50>class="current"</#if>>50</a>
							</li>
							<li>
								<a href="javascript:;" val="100" <#if pager.pageSize==100>class="current"</#if>>100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" <#if searchValue??>value="${searchValue}"</#if> maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "mobile"> class="current"</#if> val="mobile">手机号</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="wxOpenid">微信openid</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="wxNickname">微信昵称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="wxSex">微信性别</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="wxCountry">所在国家</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="wxCity">所在城市</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="mobile">参与手机号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="strength">力量值</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="lottery">剩余抽奖次数</a>
				</th>
				<th>
					<span>访问时间</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list invites as invite>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${invite.id}" />
					</td>
					<td>
						<span>${invite.wxOpenid}</span>
					</td>
					<td>
						<span>${invite.wxNickname}</span>
					</td>
					<td>
						<span>${invite.wxSex}</span>
					</td>
					<td>
						<span title="所在国家">${invite.wxCountry!'-'}</span>
					</td>
					<td>
						<span title="所在城市">${invite.wxCity!''}</span>
					</td>
					<td>
						<span title="参与手机号">${invite.mobile!'-'}</span>
					</td>
					<td>
						<span title="力量值">${invite.strength!0}</span>
					</td>
					<td>
						<span title="剩余抽奖次数">${invite.lottery!0}</span>
					</td>
					<td>
						<span class="首次访问时间">${invite.createDate?datetime}</span>
					</td>
					<td>
						<a href="view.do?id=${invite.id}">[查看]</a>
						<a href="edit.do?id=${invite.id}">[编辑]</a>
					</td>
				</tr>
			</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>