
    $(document).ready(function() {
	    $('.do').click(function(e) {
	    	e.preventDefault();
	    	$(this).hide();
	    	$(this).parent().find('form').show();
	    });
	    $('.cancel').click(function(e) {
	    	e.preventDefault();
	    	$('.do').show();
	    	$(this).parent().hide();
	    });
    })