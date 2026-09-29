$(function(){

    /** Den (als hidden dargestellten) Wert von Zusatz als selected markieren **/
    var $selectedZusatz = $(".selectedZusatz");
    
    $("[class^=selectedZusatz]").each(function () {
    	var curr_id = $(this).attr('class');
    	var curr_idVal = curr_id.split("_", 2)[1];
    	
    	var zusatz = document.getElementById("zusatz_"+curr_idVal).value;
    	console.log(zusatz);
    	
    	if (zusatz != null) {
			$(this).val(zusatz);
		}
    });
    
   /** Tooltips aktivieren **/
   $('[data-toggle="tooltip"]').tooltip();
   
   
   /** Size der Input-Felder an Größe des Values anpassen **/
   function resizeInput() {
	    $(this).attr('size', $(this).val().length);
	}

	$('input[type="text"]').each(resizeInput);
 
});
