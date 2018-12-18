// 验证码点击
$('#randomCode').click(function() {
	var randomCode = $(this);
	randomCode.attr('src', randomCode.attr('src') + 8);
});