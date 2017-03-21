
/**
 * 复制到剪贴板扩展
 * 
 */
(function($){
	/**
	 * jquery copy 扩展
	 */
	$.copy = function(text){
		if(window.clipboardData){
			window.clipboardData.clearData();
			window.clipboardData.setData('text', text);
		}else if(navigator.userAgent.indexOf('Opera') != -1){
			window.location = text;
		}else if(window.netscape){
			try{
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			}catch(e){
				
			}
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
	        if (!clip) return;
	        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
	        if (!trans) return;
	        trans.addDataFlavor('text/unicode');
	        var str = new Object();
	        var len = new Object();
	        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
	        var copytext = text;
	        str.data = copytext;
	        trans.setTransferData("text/unicode", str, copytext.length * 2);
	        var clipid = Components.interfaces.nsIClipboard;
	        if (!clip) return false;
	        clip.setData(trans, null, clipid.kGlobalClipboard);
		}
	};
})(jQuery);