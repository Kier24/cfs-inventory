$(document).ready(function() {
	$('.btnDelete').on('click', function() {
		var productId = $(this).closest('tr').attr('id');
		$('#deleteId').val(productId);
	});
	$('.btnEdit').on('click', function() {
		var productId = $(this).closest('tr').attr('id');
		var productName = $(this).closest('tr').find('#name').text();
		var unit = $(this).closest('tr').find('#unit').text();
		var quantity = $(this).closest('tr').find('#quantity').text();
		var criticalLevel= $(this).closest('tr').find('#criticalLevel').text();

		$('#editId').val(productId);
		$('#editName').val(productName);
		$('#editUnit').val(unit);
		$('#editQuantity').val(quantity);
		$('#editCriticalLevel').val(criticalLevel);
	});
	
	$('.btnView').on('click', function() {
		var productId = $(this).closest('tr').attr('id');
		var productName = $(this).closest('tr').find('#name').text();
		var unit = $(this).closest('tr').find('#unit').text();
		var quantity = $(this).closest('tr').find('#quantity').text();
		
		$('#viewId').val(productId);
		$('#viewName').val(productName);
		$('#viewUnit').val(unit);
		$('#viewQuantity').val(quantity);
	});

});
