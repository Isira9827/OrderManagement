$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateOrderForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hiduIdSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "OrderAPI", 
		 type : type, 
		 data : $("#formOrder").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onOrderSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hiduIdSave").val($(this).data("orderID"));
	$("#orderName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#orderCategory").val($(this).closest("tr").find('td:eq(1)').text());
	$("#paymentMethod").val($(this).closest("tr").find('td:eq(2)').text());
	$("#orderPayment").val($(this).closest("tr").find('td:eq(3)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "OrderAPI", 
	 type : "DELETE", 
	 data : "orderID=" + $(this).data("orderID"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onOrderDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validateOrderForm()
	{
	// Order Name
	if ($("#orderName").val().trim() == "")
	{
	return "Insert Order Name.";
	}
	// is string value
	var tmpoName = $("#orderName").val().trim();
	if ($.isNumeric(tmpoName))
	{
	return "Order Name cannot be just a value.";
	}
	// Category Name
	if ($("#orderCategory").val().trim() == "")
	{
	return "Insert order Category.";
	}
	// is string value
	var tmpcName = $("#orderCategory").val().trim();
	if ($.isNumeric(tmpcName))
	{
	return "order Category cannot be just a value.";
	}
	// Payment Method-------------------------------
	if ($("#paymentMethod").val().trim() == "")
	{
	return "Insert payment Method.";
	}
	
	// Order Payment-------------------------------
	if ($("#orderPayment").val().trim() == "")
	{
	return "Insert order Payment.";
	}
	// is numerical value
	var tmpPayment = $("#orderPayment").val().trim();
	if (!$.isNumeric(tmpPayment))
	{
	return "order Payment cannot contain letters.";
	}
}

function onOrderSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 } 
	 $("#hiduIdSave").val(""); 
	 $("#formOrder")[0].reset(); 
}

function onOrderDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 
}