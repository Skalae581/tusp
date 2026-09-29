$(document).ready(function(){
	
	
	$(document).on('click', '#editBtn', function() {
		
		console.log("update button clicked!");
		
		var url = window.location.href;		
		var value = url.substr(url.lastIndexOf('=') + 1); 

		var num = value;
		
		console.log("Edit ID: " + num);
		
		
		var status = $("#statusValueEdit").val();
		console.log("Edit STATUS: " + status);
		
		
		if(2 == status){
			  $("#dialog-confirm").html("Keine E-Mail wird beim Status UNBEKANNT gesendet!");
			  event.preventDefault();
			  $("#dialog-confirm").dialog({
			    resizable: false,
			    modal: true,
			    title: "Info",
			    height: 200,
			    width: 400,
			    buttons: {
			      "Ok": function() {       
					  window.location = event.currentTarget;
			    	  $(this).dialog('close');
			    	  window.location.href ="/tusp/precheck/bearbeiter/sendMail?id=" + num  + '&status=' + status;

			      },
			     
			    }
			  });
		}
		
	});
	
});