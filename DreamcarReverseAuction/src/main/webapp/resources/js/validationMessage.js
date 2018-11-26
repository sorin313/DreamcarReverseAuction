var loginValidation = (function(){
	$('.validationMessages').onload(function(){
		if($(this).html().length > 0)
		{
			$(this).css('display', 'block');
		}else{
			$(this).css('display', 'none');
		}
	});
})();