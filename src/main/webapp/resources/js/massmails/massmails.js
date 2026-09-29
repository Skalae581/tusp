$(document).ready(function(){
	
	

	 var $submit = $("input[type=submit]"),
		 $inputs = $('#subject');
		
		 function checkEmpty() {
		     return $inputs.filter(function() {
		         return !$.trim(this.value);
		     }).length === 0;
		 }
		
		 $inputs.on('blur', function() {
		     $submit.prop("disabled", !checkEmpty());
		 }).blur(); 
	
	/**
	 * HTML Editor 
	 */
	ClassicEditor
    .create( document.querySelector( '#editor' ), {
    	
        heading: {
            options: [
                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' },
                { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
                { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
                { model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5' },
                { model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6' }
            ]
        },
        toolbar: [
	        'heading', '|',
	        'alignment', '|',
	        'bold', 'italic', 'strikethrough', 'underline', 'subscript', 'superscript', '|',
	        'link', '|',
	        'bulletedList', 'numberedList', 
	        'fontFamily', 'fontsize', 'fontColor', 'fontBackgroundColor', '|',
	        'code', 'codeBlock', '|',
	        'insertTable', '|',
	        'outdent', 'indent', '|',
	        'uploadImage', 'blockQuote', '|',
	        'undo', 'redo'
	    ],
	    
        	
    } )
    .catch( error => {
        console.log( error );
    } );

	
	$("#dialog").dialog({
	     autoOpen: false,
	     modal: true 
	   });
	
	/**
	 *Wenn Anhang Btn geklickt wird öffne das Info-Dialog 
	 */
	$('#dialog').dialog('option', 'title', 'TUHH Cloud Dienst');
	$("#attachment").on("click", function(e) {
	      e.preventDefault();
	      $("#dialog").dialog("open");
	});
	
	/*
	 * jquery.blockUI  Plugin
	 */
	 $('.mailSubmit').click(function(){
		
		 	$.blockUI({
                message: "<h3>Bitte warten...<h3>",
                css: { color: 'green', borderColor: 'green' }
            });
		 	
	    });
	
	/**
	 * 
	 * Betreff darf nicht leer sein!
	 */
	validateSubject();
	
});



function validateSubject(){
	
	
	
	$('#massmails').validate({
        rules: {
        	subject: {
                required: true  
            } 
        },
        messages: {
        	subject: {
				required: 'Das Feld darf nicht leer sein!'
			}
        }
     
	});
}