$(document).ready(function() {
	$('.deleteAction').on('click', function() {
		alert('tite');
	});
	
	$('editModal').on('show', function() {
		var productId = $(this).closest('tr').attr('id');
		
		$.ajax({
			type : "GET",
			url : "/inventory/producedGoods/" + productId,
			timeout : 100000,
			success : function(data) {
				
				
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {

			}
		});
		
	});
	
});
