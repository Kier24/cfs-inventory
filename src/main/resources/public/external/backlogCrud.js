$(document).ready(function() {
	$('.btnDelete').on('click', function() {
		var itemId = $(this).closest('tr').attr('id');
		$('#deleteId').val(itemId);
	});
	
	$('.btnEdit').on('click', function() {
		var itemId = $(this).closest('tr').attr('id');
		var itemName = $(this).closest('tr').find('#itemName').text();
		var itemDate = $(this).closest('tr').find('#itemDate').text();
		var itemQuantity = $(this).closest('tr').find('#itemQuantity').text();

		$('#editId').val(itemId);
		$('#editName').val(itemName);
		$('#editDate').val(itemDate);
		$('#editQuantity').val(itemQuantity);
	});
	

});
