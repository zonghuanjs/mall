
/**
 * jQuery 上传文件扩展
 * ChenMingcai
 * 2014-11-20
 * 
 */

$(function($){
	/**
	 * options: 选项
	 */
	$.fn.fileUpload = function(options){
		
		var settings = {
			type: 'image',
			url: null,
			input: null,
			callback: null,
			extensions: 'jpg,jpeg,png,bmp,gif'
		};
		
		$.extend(settings, options);
		
		if(settings.url == null)
			return false;
		
		var $uploadButtons = $(this);
		$uploadButtons.each(function(){
			var $dialogBg = $('<div class="dialogBg"></div>');
			$dialogBg.css({
				'z-index':99
			});
			var $dialog = $('<div></div>');
			$dialog.css({
				display: 'none',
				position: 'fixed',
				top: '25%',
				left: '50%',
				'z-index': 100,
				overflow: 'hidden',
				border: '1px solid #779cb0',
				background: 'url(http://file.tekism.cn/images/common.gif) 0px -660px repeat-x #ffffff'
			});
			var $dialogColse = $('<div></div>').appendTo($dialog);
			$dialogColse.css({
				width: '25px',
				height: '19px',
				position: 'absolute',
				top: '0px',
				right: '10px',
				cursor: 'pointer',
				background: 'url(http://file.tekism.cn/images/common.gif) 0px -330px no-repeat'
			});
			
			var $title = $('<div>文件上传</div>').appendTo($dialog);
			$title.css({
				height: '40px',
				'line-height': '40px',
				'padding-left': '10px',
				color: '#666666',
				'font-weight': 'bold',
				cursor: 'move',
				background: 'url(http://file.tekism.cn/images/common.gif) 0px -210px repeat-x'
			});
			
			var $frameId = "uploadFrame" + (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
			var $frame = $('<iframe style="display: none;"></iframe>');
			$frame.attr('name', $frameId);
			$frame.attr('id', $frameId);
			var $fileForm = $('<form method="post" enctype="multipart/form-data"></form>').appendTo($dialog);
			$fileForm.attr('action', settings.url);
			$fileForm.attr('target', $frameId);
			$frame.insertAfter($fileForm);
			
			$fileForm.css({
				padding: '30px'
			});
			
			$('<input type="hidden" name="fileType" id="fileType" value="' + settings.type + '" />').appendTo($fileForm);
			var $fileInput = $('<input type="file" name="file" id="file" />').appendTo($fileForm);
			var $loadingIcon = $('<span>&nbsp;</span>').appendTo($dialog);
			$loadingIcon.css({
				width: '20px',
				heigth: '20px',
				'line-height': '20px',
				display: 'none',
				zoom: 1,
				background: 'url(http://file.tekism.cn/images/loading_icon.gif) center no-repeat'
			});
						
			$dialogBg.appendTo('body');
			$dialog.appendTo('body');
			$dialogBg.show();
			$dialog.show();
		
			$dialogBg.height($(document.body).height());
			
			$dialogColse.click(function(){
				$dialogBg.remove();
				$dialog.remove();
			});
			
			$fileInput.bind('change', function(){
				var $express = new RegExp("^\\S.*\\.(" + settings.extensions.replace(/,/g, "|") + ")$", "i");
				if($.trim(settings.extensions) == null || !$express.test($fileInput.val())){
					alert('非法文件:' + $fileInput.val());
					return false;
				}
				$loadingIcon.show();
				$fileForm.submit();
			});
			
			$frame.load(function(){
				var text = null;
				var io = document.getElementById($frameId);
				if(io.contentWindow) {
					text = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
				} else if(io.contentDocument) {
					text = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
				}
				if ($.trim(text) != "") {
					$loadingIcon.hide();
					var data;
					try{
						data = $.parseJSON(text);
					}catch(e){
						var result = $(text).html();
						data = $.parseJSON(result);
					}	
					try{
						if (data.errCode == 0) {
							if (settings.input != null) {
								settings.input.val(data.url);
							}
							if (settings.callback != null && typeof settings.callback == "function") {
								settings.callback(data.url);
							}						
						} else {
							alert(data.message);
						}
					}catch(e){
						alert(e);
					}
					clearFrorm();
				}
				
			});
			
			//清理现场
			function clearFrorm(){
				$dialogBg.remove();
				$dialog.remove();
			}
		});
	};
});