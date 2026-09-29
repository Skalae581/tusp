


$(document).ready(function(){
	
	saueleAuswahl();
	 
});


function saueleAuswahl(){
	$("#konfquelle").hide();
	$(".quelleChecbox").change(function() {
	    if(this.checked) {
	    
	    	$("#konfquelle").show();
	    }else{
	    	if( !$(".quelleChecbox").is(':checked')){
	    		//nur wenn alle ausgeschaltet sind
		    	$("#konfquelle").hide();
	    	}
	    	

	    }
	});
}


	

