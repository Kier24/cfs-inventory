$(document).ready(function() {
	$('.btnDelete').on('click', function() {
		var productId = $(this).closest('tr').attr('id');
		$('#deleteId').val(productId);
	});
	$('.btnEdit').on('click', function() {
		var productId = $(this).closest('tr').attr('id');
		var productName = $(this).closest('tr').find('#name').text();
		var productDescription = $(this).closest('tr').find('#description').text();
		var quantity = $(this).closest('tr').find('#quantity').text();
		var criticalLevel= $(this).closest('tr').find('#criticalLevel').text();

		$('#editId').val(productId);
		$('#editName').val(productName);
		$('#editDesc').val(productDescription);
		$('#editQuantity').val(quantity);
		$('#editCriticalLevel').val(criticalLevel);
	});
	
	$('.btnView').on('click', function() {
		var productId = $(this).closest('tr').attr('id');
		var productName = $(this).closest('tr').find('#name').text();
		var productDescription = $(this).closest('tr').find('#description').text();
		var quantity = $(this).closest('tr').find('#quantity').text();
		
		$('#viewId').val(productId);
		$('#viewName').val(productName);
		$('#viewDesc').val(productDescription);
		$('#viewQuantity').val(quantity);
	});

});
