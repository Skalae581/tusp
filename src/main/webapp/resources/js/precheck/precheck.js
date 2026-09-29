

$(document).ready(function(){
	
	validateForm1();
	validateForm2();
	
	showConfirmationBox('.loeschen', "Löschen","Wollen sie dieses Datensatz wirklich löschen?");
	//showConfirmationBox('.mailSend', "Mail","Wollen sie diese E-Mail wirklich verschicken?"); 
	showConfirmationBox('.anschalten', "Anschalten",
			"Wollen sie dieses Datensatz wieder anschalten? " +
			"Dieser Datensatz wurde schon mal bearbeitet und eine E-Mail an den Interessenten geschickt!");
	
	//keine INLINE FUnktionen deswegen...
	document.getElementById("countryList").addEventListener("change", submitForm);
	document.getElementById("statusList").addEventListener("change", submitForm);
	document.getElementById("studyList").addEventListener("change", submitForm);
	document.getElementById("doneList").addEventListener("change", submitForm);
	document.getElementById("search").addEventListener("click", submitForm);
	document.getElementById("order").addEventListener("change", submitForm);
	document.getElementById("counterValues").addEventListener("change", submitForm);
	document.getElementById("doubles").addEventListener("change", submitForm);
	document.getElementById("doubles_mail").addEventListener("change", submitForm);

	
	
	
	$(document).on('click', '.mailSend', function() {
		
	  var attr = $(this).attr('id');
	  var num = attr.slice(8);
	  
	  var status = $("#statusValue"+num).val();
	
	  console.log("id: " + num);
	  console.log("status: " + status);
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
		    	  return true;
		      },
		     
		    }
		  });
	  }
	  else{
		  
		  window.location.href ="/tusp/precheck/bearbeiter/sendMail?id=" + num  + '&status=' + status;

	  }

	  
	 
	});
	


//  //disable all with unbekannt status
//  $('.table > tbody > tr').not(':first').each(function() {
//	  
//	    var status = $(this).parent().find(".statusValue").val();   
//	    
//	    console.log("Select Value: " + status) //status value of dropdowns
//	    
//	    var selectId = $(this).parent().find(".statusValue").attr("id");
//	    console.log("Select ID: " + selectId)
//	    
//	    var num = selectId.slice(11);
//		
//		console.log('sendMail' + num)
//	    
//	    
//        if(status == "2"){					
//			document.getElementById('sendMail' + num).disabled = true;		
//		}
//	    
//	   
//});
		

});


	function diableMail(){
		  	
	}
	
	function validateForm1(){
		
		$('#form1').validate({
    		rules: {
    			nachname: {
    				required: true,
    				minlength: {param: 2},
					maxlength: {param: 255}
    			},
    			vorname: {
    				required: true,
    				minlength: {param: 2},
					maxlength: {param: 255}
    			},
    			stadt: {
    				required: true,
    				minlength: {param: 2},
					maxlength: {param: 255}
    			},
    			email: {
    				required: true,
    				email: true,
					maxlength: {param: 255}
    			},
    			geschlecht: {
    				min: 1,
    			},
    			geburtsland: {
    				min: 1,
    			},
    			geburtsdatum: {
    				required: true,
    				date: true
    				
    			},
    		},
    		messages: {
    			nachname: {
    				required: 'This field is required!',
    				minlength: 'Min characters 2, max 255',
        			maxlength: 'Min characters 2, max 255'
    			},
    			vorname: {
    				required: 'This field is required!',
        			minlength: 'Min characters 2, max 255',
        			maxlength: 'Min characters 2, max 255'
    			},
    			stadt: {
    				required: 'This field is required!',
        			minlength: 'Min characters 2, max 255',
        			maxlength: 'Min characters 2, max 255'
    			},
    			email: {
    				required: 'This field is required!',
        			email: 'Please enter valid e-mail adress!'
    			},
    			geschlecht: {
    				min: 'This field is required!',
    			},
    			geburtsland: {
    				min: 'This field is required!',
    			},
    			geburtsdatum: {
    				required: 'This field is required!',
    			},
    		}
    	});
		
		$.validator.addMethod("maxDate", function(value, element) {
    	    var curDate = new Date();
    	    console.log(curDate);   	    
    	    var enteredDate = $("#geburtsdatum").val();
    	    
    	    var eingabeDate = new Date(enteredDate);
    	    
    	    console.log(eingabeDate.toLocaleDateString("de-DE"));
    	    console.log(curDate.toLocaleDateString("de-DE"));
    	    
    	    var minDate = new Date("01.01.2000");
    	    
    	    if (enteredDate <= curDate){   	    	
    	    	 return true;
    	    }  
    	    else if(enteredDate >= minDate.toLocaleDateString("de-DE")){
    	    	 return true;
    	    }
    	    return false;
    	}, "Date should not be smaller then year 2000!");   
    	
	}

	function validateForm2(){
		$('#form2').validate({
	        rules: {
	        	uni: {
	                required: true,
	                minlength: {param: 2},
	                maxlength: {param: 255}
	            },
	            studienland: {
	            	min: 1
	            },
	            institution: {   
	            	required: true
	            },
	            
	            abschluss: {
	                required: true,
	                minlength: {param: 2},
	                maxlength: {param: 255}
	            },
	            abgeschlossenesstd: {
	                required: true,
	                minlength: {param: 2},
	                maxlength: {param: 255}
	            },  
	            stdDauer: {
	            	min: 1,
	            	max: 9
	            },
	            abschlussJahr: {
	                required: true,
	            	min: 1970,
	            	max: 2022
	            },
	            minNote: {
	                required: true,
	                min: 0.1
	            },
	            maxNote: {
	                required: true,
	                min: 0.1
	            },
	            erreichteNote: {
	                required: true,
	                min: 0.1,
	                inBetweenMinMax: true
	               
	            },
	            kommentar: {},
	            stgTuhh: {
	            	min: 1,
	            },
	        },
	        messages: {
	        	uni: {
					required: 'This field is required!',
					minlength: 'Min characters 2, max 255',
	    			maxlength: 'Min characters 2, max 255'
				},
				studienland: {
					min: 'This field is required!'
				},
				institution: {
					min: 'This field is required!'
				},
				abschluss: {
					required: 'This field is required!',
					minlength: 'Min characters 2, max 255',
	    			maxlength: 'Min characters 2, max 255'
	    		},
				abgeschlossenesstd: {
					required: 'This field is required!',
					minlength: 'Min characters 2, max 255',
	    			maxlength: 'Min characters 2, max 255'
	    		},
				stdDauer: {
					min: 'Number shuld be in between 1 and 9',
					max: 'Number should be in between 1 and 9',
				},
				abschlussJahr: {
					required: 'This field is required!',
					min: 'Year should be in between 1970-2022',
					max: 'Year should be in between 1970-2022',
				},
				minNote: {
					required: 'This field is required!',
				},
				maxNote: {
					required: 'This field is required!',
				},
				erreichteNote: {
					required: 'This field is required!',
					inBetweenMinMax: 'Must be in between min amd max grade!'
				},
				stgTuhh: {
					min: 'This field is required!',
	            },
	        }

		});
		
		
		jQuery.validator.addMethod("inBetweenMinMax", function(value, element) {
			
			let min = $('#minNote').val();
			let max = $('#maxNote').val();
			
			min = parseFloat(min);
			max = parseFloat(max);
			value = parseFloat(value);
		
			if(max < min) {
				return value <= min && value >= max;
			}
			else {
				return value >= min && value <= max;
			}
		   
		}, 'Grade should be in between min and max grade');
		
		$( function() {
	    	$( ".datepicker" ).datepicker({
	    	    dateFormat: "yy-mm-dd"
	    	});
	    } );


		
		
	}
	
	

	function submitForm(){
		this.form.submit();
	}

	function showConfirmationBox(element, title, htmlText){
		
		$(element).click(function buttonAction(event) {
			  
			  $("#dialog-confirm").html(htmlText);
			  event.preventDefault();
			  $("#dialog-confirm").dialog({
			    resizable: false,
			    modal: true,
			    title: title,
			    height: 250,
			    width: 400,
			    buttons: {
			      "Ja": function() {       
					  window.location = event.currentTarget;
			    	  $(this).dialog('close');
			      },
			      "Nein": function() {
			        $(this).dialog('close'); 
			      }
			    }
			  });
			});
		
		
		$("#studienland").unbind("change"); //jquery bug fix
		$("#studienland").change(function(){	
			dynamicDropDown(this.value);
		});
		
		var url = window.location.href;		
		var value = url.substr(url.lastIndexOf('/') + 1); 
		if(value == "study_details"){
			
			var _selVal = $("#studienland").val();
			dynamicDropDown(_selVal);
		}	
	}

	
	//disable / enable mail based on status
	function setVisibility(id){	
		
		var status = $('#statusValue' + id).val();	
		console.log("setVisibility(): " + status);
		if(status == "2"){					
			document.getElementById('sendMail' + id).style.pointerEvents = 'none';				
		}else{
			document.getElementById('sendMail' + id).style.pointerEvents = 'auto';
		}
	
	}
	
	

function dynamicDropDown(_selVal){

	
	if(_selVal == "194" || _selVal == "224"){ //china
		var mySelect = $("#additional");
		mySelect.children().remove();
		mySelect.append('<option value="Other">Other</option>');
		mySelect.append('<option value="211">211-University</option>');
		var label = $("label[for='institution']").css("display", "block");
		mySelect.css("display", "block");
	}else if(_selVal == "80"){ //India
		var mySelect = $("#additional");
		mySelect.children().remove();
		mySelect.append('<option value="Other">Other</option>');
		mySelect.append('<option value="Nit">National Institute of Technology or Indian Institute of Technology</option>');
		var label = $("label[for='institution']").css("display", "block");
		mySelect.css("display", "block");
	}else{
		var mySelect = $("#additional");
		var label = $("label[for='institution']").css("display", "none");
		
		mySelect.css("display", "none");
		
	}
	
}
	
	