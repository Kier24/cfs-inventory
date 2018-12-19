$(document).ready(function() {
	$('#addName').on('change', function() {
		 var rawMaterialName =  $(this).val();
		 $.ajax({
	            type : "GET",
	            url : "/producedGoods/ingredient/unit/"+rawMaterialName,
	            timeout : 100000,
	            success : function(data) {
	           	$("#addUnit option").remove();
	            $.each(data, function(index, item) { 
       			$("#addUnit").append(
          				 $("<option></option>") 
               			.text(item)
               			.val(item));
  				 });
	            },
	            error : function(e) {
	                console.log("ERROR: ", e);
	            },
	            done : function(e) {
	               
	            }
	        });
	});
	
	$('#addName').on('change', function() {
		 var rawMaterialName =  $(this).val();
		 $.ajax({
	            type : "GET",
	            url : "/producedGoods/ingredient/unit/"+rawMaterialName,
	            timeout : 100000,
	            success : function(data) {
	           	$("#addUnit option").remove();
	            $.each(data, function(index, item) { 
        			$("#addUnit").append(
           				 $("<option></option>") 
                			.text(item)
                			.val(item));
   				 });
	            },
	            error : function(e) {
	                console.log("ERROR: ", e);
	            },
	            done : function(e) {
	               
	            }
	        });
	});
	
});
