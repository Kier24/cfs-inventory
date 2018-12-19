$('.viewGoods').on('click', function() {	
		alert("hello");
	
		
	});
	$("#viewOrdermodal").on("hidden.bs.modal", function(){
	    $('#viewOrderTable tbody').empty();
	});
	 
	 $('#viewOrdermodal').on('show.bs.modal', function (e) {
		 var productionDate = $('#productionDate').val();
		 var encoder = $('#encoder').val();
		 $.ajax({
	            type : "GET",
	            url : "/producedGoods/date/"+productionDate+"/encoder/"+encoder,
	            data: {},
	            timeout : 100000,
	            success : function(data) {

	            	var tableData= '';
	            	for(var i = 0; i < data.length; i++) {
	            		 var item = data.[i];
		            	 var productName = item.product.name;
		            	 var productContainer = item.containerType;
		            	 var price = item.product.price;
					     var quantity=item.quantity;
						 tableData += '<tr><td>' + productName + '</td><td>' + productContainer + '</td><td>' + quantity +'</td><td>'+price+'</td></tr>';
	            	}
	            	
					 $('#viewOrderTable tbody').append(tableData);
					
	            },
	            error : function(e) {
	                console.log("ERROR: ", e);
	            },
	            done : function(e) {
	               
	            }
	        });
	 })
