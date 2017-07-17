$(document).ready(function() {
	toastr.options = {
		'closeButton': false,
		'debug': false,
		'newestOnTop': false,
		'progressBar': true,
		'positionClass': 'toast-bottom-center',
		'preventDuplicates': true,
		'onclick': null,
		'showDuration': '1000',
		'hideDuration': '1000',
		'timeOut': '5000',
		'extendedTimeOut': '1000',
		'showEasing': 'swing',
		'hideEasing': 'linear',
		'showMethod': 'fadeIn',
		'hideMethod': 'fadeOut'
	};
	
	$('.edit-url').click(function() {
		$('#edit-url').val($(this).data('url'));
		$('#edit-start').val($(this).data('start'));
		$('#edit-end').val($(this).data('end'));
		$('#edit-password').val($(this).data('password'));
		$('#edit-clicks').val($(this).data('clicks'));
		if ($(this).data('captcha') == true) $('#edit-captcha').attr('checked', 'checked');

		$('#edit-url').modal('show');
	});

	$('#edit-submit').click(function() {
		var url = $('#edit-url').val();
		var start = $('#edit-start').val();
		var end = $('#edit-end').val();
		var password = $('#edit-password').val();
		var clicks = $('#edit-clicks').val();
		var captcha = $("#edit-captcha").is(':checked') ? '1' : '0';

		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'manageUrl',
			data : { shortUrl : url, startDate : start, expireDate : end, urlPassword : password, nbClick : clicks, captchaCheckbox : captcha } 
		}).done(function(data){
			if(data.status == true) {
				$('#row-' + url + ' .start').html(start);
				$('#row-' + url + ' .end').html(end);
				$('#row-' + url + ' .password').html(password);
				$('#row-' + url + ' .clicks').html(clicks);
				
				if (captcha == '1') $('#row-' + url + ' .captcha span').addClass('glyphicon-ok').addClass('green').removeClass('glyphicon-remove').removeClass('red');
				else $('#row-' + url + ' .captcha span').addClass('glyphicon-remove').addClass('red').removeClass('glyphicon-ok').removeClass('green');
				
				$('#edit-url').modal('hide');
				toastr['success'](data.message, 'Success');
			}
			else if(data.status == false) { 
				toastr['error'](data.message, 'Error');
			}
		});
	});
});