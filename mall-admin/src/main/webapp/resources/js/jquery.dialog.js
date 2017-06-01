(function($){
	
	var zIndex = 100;
	
	//对话框
	$.dialog = function(options){
		
		var settings = {
			width: 320,
			height: 'auto',
			modal: true,
			ok: '确定',
			cancel: '取消',
			onShow: null,
			onClose: null,
			onOk: null,
			onCancel: null
		};
		
		$.extend(settings, options);
		
		if(settings.content == null){
			return false;
		}
		
		var $dialog = $('<div class="tekDialog"></div>');
		var $dialogTitle = null;
		var $dialogClose = $('<div class="dialogClose"></div>').appendTo($dialog);
		var $dialogContent = null;
		var $dialogBottom = null;
		var $dialogOk = null;
		var $dialogCancel = null;
		var $dialogOverlay = null;
		
		if(settings.title != null){
			$dialogTitle = $('<div class="dialogTitle"></div>').appendTo($dialog);
		}
		if(settings.type != null){
			$dialogContent = $('<div class="dialogContent dialog' + settings.type + 'Icon"><\/div>').appendTo($dialog);
		}else{
			$dialogContent = $('<div class="dialogContent"><\/div>').appendTo($dialog);
		}
		if (settings.ok != null || settings.cancel != null) {
			$dialogBottom = $('<div class="dialogBottom"><\/div>').appendTo($dialog);
		}
		if (settings.ok != null) {
			$dialogOk = $('<input type="button" class="button" value="' + settings.ok + '" \/>').appendTo($dialogBottom);
		}
		if (settings.cancel != null) {
			$dialogCancel = $('<input type="button" class="button" value="' + settings.cancel + '" \/>').appendTo($dialogBottom);
		}
		
		if (!window.XMLHttpRequest) {
			$dialog.append('<iframe class="dialogIframe"><\/iframe>');
		}
		$dialog.appendTo("body");
		if (settings.modal) {
			$dialogOverlay = $('<div class="dialogOverlay"><\/div>').insertAfter($dialog);
		}
		
		var dialogX;
		var dialogY;
		if (settings.title != null) {
			$dialogTitle.text(settings.title);
		}
		$dialogContent.html(settings.content);
		$dialog.css({"width": settings.width, "height": settings.height, "margin-left": - parseInt(settings.width / 2), "z-index": zIndex ++});
		dialogShow();
		
		if ($dialogTitle != null) {
			$dialogTitle.mousedown(function(event) {
				$dialog.css({"z-index": zIndex ++});
				var offset = $(this).offset();
				if (!window.XMLHttpRequest) {
					dialogX = event.clientX - offset.left;
					dialogY = event.clientY - offset.top;
				} else {
					dialogX = event.pageX - offset.left;
					dialogY = event.pageY - offset.top;
				}
				$("body").bind("mousemove", function(event) {
					$dialog.css({"top": event.clientY - dialogY, "left": event.clientX - dialogX, "margin": 0});
				});
				return false;
			}).mouseup(function() {
				$("body").unbind("mousemove");
				return false;
			});
		}
		
		if ($dialogClose != null) {
			$dialogClose.click(function() {
				dialogClose();
				return false;
			});
		}
		
		if ($dialogOk != null) {
			$dialogOk.click(function() {
				if (settings.onOk && typeof settings.onOk == "function") {
					if (settings.onOk($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		if ($dialogCancel != null) {
			$dialogCancel.click(function() {
				if (settings.onCancel && typeof settings.onCancel == "function") {
					if (settings.onCancel($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		function dialogShow() {
			if (settings.onShow && typeof settings.onShow == "function") {
				if (settings.onShow($dialog) != false) {
					$dialog.show();
					$dialogOverlay.show();
				}
			} else {
				$dialog.show();
				$dialogOverlay.show();
			}
		}
		function dialogClose() {
			if (settings.onClose && typeof settings.onClose == "function") {
				if (settings.onClose($dialog) != false) {
					$dialogOverlay.remove();
					$dialog.remove();
				}
			} else {
				$dialogOverlay.remove();
				$dialog.remove();
			}
		}
		
		return $dialog;	
	};
	
	//浏览文件对话框
	$.fn.browseDialog = function(options){
		
		var settings = {
			type: 'image',
			title: '浏览',
			isUpload: true,
			browserUrl:'/admin/file/browser.do',
			uploadUrl: '/admin/file/upload.do',
			callback: null,
			uploadFlashExtension: 'flv',
			uploadMediaExtension: 'mp4,mpeg',
			uploadImageExtension: 'jpg,jpeg,png,gif,bmp',
			uploadFileExtension: 'apk,doc,docx,xls,xlsx,ppt,pptx'
		};
		$.extend(settings, options);
		
		var cache = {};
		
		var $browseButtons = $(this);
		$browseButtons.each(function(){
			var $browseButton = $(this);
			
			var $browserFrameId = "browserFrame" + (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
			
			$browseButton.bind('click', function(){
				var $browser = $('<div class="tekBrowser"></div>');
				var $browserBar = $('<div class="browserBar"></div>').appendTo($browser);
				var $browserFrame;
				var $browserForm;
				var $browserUploadButton;
				var $browserUploadInput;
				var $browserParentButton;
				var $browserLoadingIcon;
				var $browserList;
				
				if(settings.isUpload){
					$browserFrame = $('<iframe id="' + $browserFrameId + '" name="' + $browserFrameId + '" style="display: none;" ></iframe>').appendTo($browserBar);
					$browserForm = $('<form action="' + settings.uploadUrl + '" method="post" encType="multipart/form-data" target="' + $browserFrameId + '"><input type="hidden" id="fileType" name="fileType" value="' + settings.type + '" /></form>').appendTo($browserBar);
					$browserUploadButton = $('<a href="javascript:;" class="browserUploadButton button">选择文件</a>').appendTo($browserForm);
					$browserUploadInput = $('<input type="file" id="file" name="file" />').appendTo($browserUploadButton);
				}
				$browserLoadingIcon = $('<span class="loadingIcon" style="display: none;">&nbsp;<\/span>').appendTo($browserBar);
				
				var $dialog = $.dialog({
					title: settings.title,
					content: $browser,
					width: 470,
					modal: true,
					ok: null,
					cancel: null
				});
				
				$browserUploadInput.change(function() {
					var allowedUploadExtensions;
					if (settings.type == "flash") {
						allowedUploadExtensions = settings.uploadFlashExtension;
					} else if (settings.type == "media") {
						allowedUploadExtensions = settings.uploadMediaExtension;
					} else if (settings.type == "file") {
						allowedUploadExtensions = settings.uploadFileExtension;
					} else {
						allowedUploadExtensions = settings.uploadImageExtension;
					}
					if ($.trim(allowedUploadExtensions) == "" || !new RegExp("^\\S.*\\.(" + allowedUploadExtensions.replace(/,/g, "|") + ")$", "i").test($browserUploadInput.val())) {
						alert('非法文件格式');
						return false;
					}
					$browserLoadingIcon.show();
					$browserForm.submit();
				});
				
				$browserFrame.load(function() {
					var text;
					var io = document.getElementById($browserFrameId);
					if(io.contentWindow) {
						text = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
					} else if(io.contentDocument) {
						text = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
					}
					if ($.trim(text) != "") {
						$browserLoadingIcon.hide();
						var data;
						try{
							data = $.parseJSON(text);
						}catch(e){
							var result = $(text).html();
							data = $.parseJSON(result);
						}	
						try{
							if (data.error == 0) {
								if (settings.input != null) {
									settings.input.val(data.url);
								} else {
									$browseButton.prev(":text").val(data.url);
								}
								if (settings.callback != null && typeof settings.callback == "function") {
									settings.callback(data.url);
								}
								cache = {};
								$dialog.next(".dialogOverlay").andSelf().remove();
							} else {
								alert(data.message);
							}
						}catch(e){
							
						}
					}
				});

			});
		});
		
	};
	
})(jQuery);