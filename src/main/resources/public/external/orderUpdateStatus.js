$(document).ready(function() {
	$('.btnEdit').on('click', function() {
		var orderId = $(this).closest('tr').attr('id');
		alert(orderId);
		var orderIdTextBox = $('#changeStatmodal .modal-body #orderId');
		orderIdTextBox.val(orderId);
	});	
});
