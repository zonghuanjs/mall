$(function(){
	var $rankCheckButton = $('#checkMemberRank');
	var member = $('#member').val();
	
	$rankCheckButton.click(function(){
		$.ajax({
			url: 'check_rank.do',
			data: {id: member},
			type: 'post',
			success: function(){
				location.reload(true);
			}
		});
	});
});