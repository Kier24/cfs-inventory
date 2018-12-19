$(document).ready(function() {
	$('.btnEdit').on('click', function() {
		var orderId = $(this).closest('tr').attr('id');
		var orderIdTextBox = $('#changeStatmodal .modal-body #orderId');
		orderIdTextBox.val(orderId);
	});	
});
