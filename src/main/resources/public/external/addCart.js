$(document).ready(function() {
	$('.btnAddToCart').on('click', function() {
	var productId = $(this).closest('tr').attr('id');
	$('#productId').val(productId);
	});
	
	
	$(document).on('submit', '#frmAddToCart', function(e) {
		e.preventDefault();
		var productId=$('#productId').val();
		var quantity=$('#productquantity').val();
	     $.ajax({
	            type : "POST",
	            url : $(this).attr('action'),
	            data: {"id":productId,"quantity":quantity},
	            timeout : 100000,
	            success : function(data) {
	            $('#addToCartModal').modal('hide');	
	            showNotification();
	            
	            },
	            error : function(e) {
	                console.log("ERROR: ", e);
	            },
	            done : function(e) {
	               
	            }
	        });
	});

});

function showNotification() {
    var snackbar = document.getElementById("snackbar");
    snackbar.className = "show";
    setTimeout(function(){ snackbar.className = snackbar.className.replace("show",""); }, 3000);
}

