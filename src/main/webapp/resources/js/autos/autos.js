
$(document).ready(function(){
	
	
	console.log("Kalender Data:" + getJsonData());
	console.log("Dummy Kalender Data:" + getDummyData());
	
	
	//TODO Datum Format
	//Kalender definition für die Felder von und bis @JQuery Date
	$(function() {
		
		 $.datepicker.setDefaults({
		        onClose:function(date, inst){
		            $("#von").html(date);
		        }
		    });
	    $("#von").datepicker();
	    $("#bis").datepicker();
	  });
	
	
	validateUserForm();
	
	
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
    	
    	 initialView: 'dayGridMonth',
    	    initialDate: '2021-11-07',
    	    headerToolbar: {
    	      left: 'prev,next today',
    	      center: 'title',
    	      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    	    },
    	    height: "500px",
    	    events: getJsonData()
      
    			  
    });
    calendar.render();
	
  
});


function getJsonData(){
	
	if (top.location.pathname === '/tusp/autos/benutzer/buchung'){
		var data = $("#jsonData").val();
		
		var jsonData = jQuery.parseJSON(data);
		
		console.log(jsonData);
		return jsonData;
	}
}


function getDummyData(){
	
	return [
		      
	  	      { 
	  	        title: 'Long Event',
	  	        start: '2021-11-07',
	  	        end: '2021-11-10',
	  	        color: "#000"
	  	      },
	  	      {
	  	        title: 'Conference',
	  	        start: '2021-11-11',
	  	        end: '2021-11-13'
	  	      },
	  	      {
	  	        title: 'Click for Google',
	  	        url: 'http://google.com/',
	  	        start: '2021-11-28'
	  	      }
  	    ]
	
	
}





//TODO Valiederung für alle Forms
/**
 * Form Validierung. Die Variable-Namen = pojo namen
 * @returns
 */
function validateUserForm(){
	
	$('#userForm').validate({
		rules: {
			vorname: {
				required: true,
				minlength: {param: 1},
				maxlength: {param: 255}
			},
			nachname: {
				required: true,
				minlength: {param: 1},
				maxlength: {param: 255}
			},
			tel: {
				required: true,
				minlength: {param: 1},
				maxlength: {param: 255}
			},
			email: {
				required: true,
				minlength: {param: 1},
				maxlength: {param: 255}
			},
			fuererschein: {
				required: true,
				minlength: {param: 1},
				maxlength: {param: 255}
			},
			gueltigkeit: {
				required: true,
				minlength: {param: 1},
				maxlength: {param: 255}
			}
		},
		messages: {
			nachname: {
				required: 'This field is required!',
				minlength: 'Min characters 1, max 255',
    			maxlength: 'Min characters 2, max 255'
			},
			vorname: {
				required: 'This field is required!',
    			minlength: 'Min characters 1, max 255',
    			maxlength: 'Min characters 2, max 255'
			},
			tel: {
				required: 'This field is required!',
    			minlength: 'Min characters 1, max 255',
    			maxlength: 'Min characters 2, max 255'
			},
			email: {
				required: 'This field is required!',
    			minlength: 'Min characters 1, max 255',
    			maxlength: 'Min characters 2, max 255'
			},
			fuererschein: {
				required: 'This field is required!',
    			minlength: 'Min characters 1, max 255',
    			maxlength: 'Min characters 2, max 255'
			},
			gueltigkeit: {
				required: 'This field is required!',
    			minlength: 'Min characters 1, max 255',
    			maxlength: 'Min characters 2, max 255'
			}
		}
	});
	
}
