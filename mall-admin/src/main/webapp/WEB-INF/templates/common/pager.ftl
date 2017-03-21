<input type="hidden" id="pageSize" name="pageSize" value="${pager.pageSize}" />
		<input type="hidden" id="searchProperty" name="searchProperty" <#if searchProperty??>value="${searchProperty}"</#if> />
		<input type="hidden" id="orderProperty" name="orderProperty" <#if orderProperty??>value="${orderProperty}"</#if>/>
		<input type="hidden" id="orderDirection" name="orderDirection" <#if orderDirection??>value="${orderDirection}"</#if>/>
		<input type="hidden" id="pageCount" value="${pager.pageCount}" />
		<div class="pagination">			
			<a class="firstPage" href="javascript: $.pageSkip(1);">&nbsp;</a>
		<#if pager.currentIdx gt 1>
			<a class="previousPage" href="javascript: $.pageSkip(${pager.currentIdx -1});">&nbsp;</a>
		</#if>
		<#if pager.prePageBreak>
			<span class="pageBreak">...</span>
		</#if>
		<#list pager.start .. pager.end as k>
			<#if k==pager.currentIdx>
				<span class="currentPage">${k}</span>
			<#else>
				<a href="javascript: $.pageSkip(${k});">${k}</a>
			</#if>
		</#list>
		<#if pager.nextPageBreak>
			<span class="pageBreak">...</span>
		</#if>
		<#if pager.currentIdx lt pager.end>
			<a class="nextPage" href="javascript: $.pageSkip(${pager.currentIdx + 1});">&nbsp;</a>
		</#if>
			<a class="lastPage" href="javascript: $.pageSkip(${pager.pageCount});">&nbsp;</a>			
			<span class="pageSkip">
				共${pager.pageCount}页 到第<input id="pageNumber" name="pageNumber" value="${pager.currentIdx}" maxlength="9" onpaste="return false;" />页<button type="submit">&nbsp;</button>
			</span>
		</div>