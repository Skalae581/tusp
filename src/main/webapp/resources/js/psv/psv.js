
$(document).ready(function(){

	//------------------VARIABLES-----------------------------------------------------------
	var logInterval = null;
	var updateInterval = null;
	var notifyInterval = null;
	
	
    var maxID = 0;
   // var date = moment().format('YYYY-MM-DD hh:mm:ss.SSS'); 
    var updateId = 0;
    var url = window.location.href;   
    var currentImportName = "ldap";

    document.ldapRunning = $('#thread_ldap').val(); 
    document.unihhRunning = $('#thread_unihh').val();
    document.mobonRunning = $('#thread_mobon').val();

    //---------------------------------------------------------------------------------------
    
  
    
    //------------------------------------EVENTS---------------------------------------------
    /**
     * Tab event listener 
     */
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		
		currentImportName = $(e.target).attr("href");		
		currentImportName = currentImportName.substring(1);

		/**
		 * bekomme letze id 
		 */
		getLastId(currentImportName);
		
		if(document.ldapRunning == "false"){   	
	    	$('#ldap_animation').hide();
	    }
		
		if(document.unihhRunning == "false"){   	
	    	$('#unihh_animation').hide();
	    }
		
		if(document.mobonRunning == "false"){   	
	    	$('#mobon_animation').hide();
	    }
		
		
	});
	
	//------------------------------------AUfRUFE-----------------------------------------
    
	
	/**
	 * bekomme letze id 
	 */
	getLastId(currentImportName);
	
	/***
	 * am anfang verstecke diese Animation
	 */
	
	if(document.ldapRunning == 'false'){   	
    	$('#ldap_animation').hide();
    }
	if(document.unihhRunning == 'false'){   	
    	$('#unihh_animation').hide();
    }
	if(document.mobonRunning == 'false'){   	
    	$('#mobon_animation').hide();
    }
	
	notifyUI("ldap");
	notifyUI("unihh");
	notifyUI("mobon");
	
	notify("ldap");
	notify("unihh");
	notify("mobon");
    
    /***
     * starte den unihh import
     */
	$("#unihhImportBtnStart").click(function(){		
		startImport(currentImportName,true);
		disableTabs(currentImportName);
	});
	/***
     * beende den unihh import
     */
	$("#unihhImportBtnStop").click(function(){
		stopImport(currentImportName,false);
		enableTabs(currentImportName);
		clearInterval(updateInterval);
	});
	
	/***
     * starte den ldap import
     */
	$("#ldapImportBtnStart").click(function(){		
		startImport(currentImportName,true);
		disableTabs(currentImportName);		
	});
	/***
     * beende den ldap import
     */
	$("#ldapImportBtnStop").click(function(){
		stopImport(currentImportName,false);
		enableTabs(currentImportName);
		clearInterval(updateInterval);
	});
	
	 /***
     * starte den mobon import
     */
	$("#mobonImportBtnStart").click(function(){		
		startImport(currentImportName,true);
		disableTabs(currentImportName);
	});
	/***
     * beende den mobon import
     */
	$("#mobonImportBtnStop").click(function(){
		stopImport(currentImportName,false);
		enableTabs(currentImportName);
		clearInterval(updateInterval);
	});
	
	

	//---------------------------------------------------------------------------------------
	
	
	
	/**
	 * Während ein Import läuft alle andere Tabs ausblenden
	 * @param importName
	 * @returns
	 */
	function disableTabs(importName){
		
		var runningImport = $("#" + importName +"Tab");

		var id = runningImport.attr("id");
		
		console.log(id );
		var tabs = ["ldap","mobon","unihh","sync"];
		
		for(var i=0;i<tabs.length;i++)
		{
			if(id !=  $("#" + tabs[i] +"Tab").attr("id")){
				
				$("#" + tabs[i] + "Tab").removeAttr("data-toggle");
				$("#" + tabs[i] + "Tab").removeAttr("href");
				
				$("#" + tabs[i] + "Tab").attr("style" , "background-color:#eee;cursor:not-allowed;");
				
			}
		}
	}
	

	/**
	 * Tabs einblenden
	 * @param importName
	 * @returns
	 */
	function enableTabs(importName){
		
		var runningImport = $("#" + importName +"Tab");
	
		var id = runningImport.attr("id");
		
		console.log(id);
		var tabs = ["ldap","mobon","unihh","sync"];
		
		for(var i=0;i<tabs.length;i++)
		{
			if(id !=  $("#" + tabs[i] +"Tab").attr("id")){
				
				$("#" + tabs[i] + "Tab").attr("data-toggle", "tab");
				$("#" + tabs[i] + "Tab").attr("href", "#"+tabs[i]); 
				
				$("#" + tabs[i] + "Tab").removeAttr("style");
			}
		}
	}
	/**
	 * Statistik anzeigen
	 * @param importName
	 * @returns
	 */
	function showStatistics(importName){
		if(document.ldapRunning == "true"){
			_updateStatistics(importName);
		}
		if(document.unihhRunning == "true"){
			_updateStatistics(importName);
		}
		if(document.mobonRunning == "true"){
			_updateStatistics(importName);
		}
	}
	//==================================INTEERVALE=========================================
	/**
	 * notify ui interval
	 * @param importName
	 * @returns
	 */
	function notify(importName){
		
		notifyInterval = setInterval(function(){
			if(document.ldapRunning == "true"){
				notifyUI("ldap");
			}
			if(document.unihhRunning == "true"){
				notifyUI("unihh");
			}
			if(document.mobonRunning == "true"){
				notifyUI("mobon");
			}
		}, 1000);
		
	}
	
	/***
	 * Startet den Interval und übergibt die Logger-Nachrichten an UI jede Sekunde
	 * @returns
	 */
	function appendMessageToConsole(importName){

		logInterval = setInterval(function(){
			if(document.ldapRunning == "true"){				
				writeToConsole("ldap");
			}
			if(document.unihhRunning == "true"){
				writeToConsole("unihh");
			}
			if(document.mobonRunning == "true"){
				writeToConsole("mobon");
			}
		}, 500);
	}
	
	
	/**
	 * Update Interval
	 * @param importName
	 * @returns
	 */
	function _updateStatistics(importName){
		updateInterval = setInterval(function(){
			
			updateStatistics(importName);
			
		}, 1000);
	}
	
	//==========================================================================================================
	
	//============================================AJAX===================================================================
	/**
	 * Schreibt logger-Ausgaben auf der Konsole
	 * @param importName
	 * @returns
	 */
	function writeToConsole(importName){
		
			$.ajax({
			 
		    url: url+'dataLog?service='+importName,
		    type : 'GET',
		    data: {"maxID": maxID},
		    success : function(data) {
		    
		  
	    	   var _console = $("#" + importName + "Console");
	    	   
	    	   //text hinzufügen
	    	   for(var i = 0; i < data.length; i++){	    		
	    		   _console.append("<span class=\"log_" + data[i].priority + "\">" + data[i].logDate + " " + data[i].priority
	   					+ "->" + data[i].message + "</span><br/>");	  
	    		   
	    		   if(data[i].id > maxID){
		    		   maxID = data[i].id;
		    	   } 
		    	  	 
	    		   
	    	   }
	    	   
	    	   //scroll
	    	   if(_console.length){
	    		   _console.scrollTop(_console[0].scrollHeight - _console.height());
	    	   }

		    }				
	  });
	 
	}
	
	
	/**
	 * statistik für import ajax Aufruf
	 * @returns
	 */
	
	function updateStatistics(importName){

			$.ajax({
			 
			    url: url +  'updateStatistics?service=' + importName,
			    type : 'GET',
			    data: {"updateId": updateId},
			    success : function(data) {
			    	console.log(data);
			    	//text hinzufügen
			    	   $("#"+importName+"StatisticsOutput").html				    	   
			    	   ("<span id='statisticsText'> Fehler:" +data.stat_error+ "</span><br/>" + 		
			    	    "<span> Neue: " +data.stat_new+ "</span><br/>" + 
			    	    "<span> Aktualisierte: " +data.stat_updated+ "</span><br/>"
			    	   ); 
			    }					
		  });
	
	}
	
	
	/**
	 * 
	 * @param importName
	 * @returns
	 */
	function importStatistics(importName){

      	$.ajax({
      		
		    url: url + 'importStatistics?service='+importName,
		    type : 'GET',				
		    success : function(statID) {		   
		    	updateId = statID;
		    	
		    	/*statistik soll jede 2 sekunden geupdatet werden*/
		    	showStatistics(importName);
		   }
	    });
      
	}
	
	
	/***
	 * wartet bis import fertig ist dann löscht alle intervale
	 * @returns
	 */
	function notifyUI(importName){
					
				$.ajax({
					 
				    url: url +"threadRunning?service="+importName,
				    type : 'GET',				
				    success : function(terminated) {		   
				   
				       if(terminated == false){		
				    	   
					       	$("#"+importName+"ImportBtnStop").attr("disabled","disabled");
		                	$("#"+importName+"ImportBtnStart").attr("disabled", false);
		                	$('#'+importName+'_animation').hide(); 
					    	   
				    	    if(importName == "ldap"){
		                		document.ldapRunning = "false";
		                	}
		                	if(importName == "unihh"){
		                		document.unihhRunning = "false";
		                	}
		                	
		                	enableTabs(importName);

						    clearInterval(logInterval);						 
						    clearInterval(notifyInterval);
						    clearInterval(updateInterval);
			    	   			  
				       }
				          
				   }
			  });
	}
	
	/**
	 * letze id aus der db
	 * @param importName
	 * @returns
	 */
	function getLastId(importName){
		$.ajax({
			 
		    url: url+'getLastId?service='+importName,
		    type : 'GET',
		    success : function(lastId) {
		    
		    	maxID = lastId;
		    	
		    	console.log("max id: " + maxID);
		    }				
	  });
	}
	
	/**
	 * startet den Import 
	 * @returns
	 */
	function startImport(importName, btnClicked){	

		alert("Starting import for: " + importName);
		
        	$.ajax({
        		url: url +"startStop?service=" + importName,
                type: 'GET',
                data: {"startBtn": btnClicked},
                success: function(res) {
                	
                	if(importName == "ldap"){
                		document.ldapRunning = "true";
                	}
                	if(importName == "unihh"){
                		document.unihhRunning = "true";
                	}
                	if(importName == "mobon"){
                		document.mobonRunning = "true";
                	}
                	
                	/*logger-Anzeige auf dem UI*/
                	appendMessageToConsole(importName);
                	/*falls import beendet wird informiere UI*/
                	notify(importName);
                	
                	$("#"+importName+"ImportBtnStart").attr("disabled", "disabled");
                	$("#"+importName+"ImportBtnStop").attr("disabled", false);
                	$('#'+importName+'_animation').show();
                	
                	/*wenn gestartet statistik soll in die db eingetragen werden */
                	importStatistics(importName);
                                    
                }
            });
       
	}
	
	
	/**
	 * stopt den Import
	 * @returns
	 */
	function stopImport(importName, btnClicked){
		
        	$.ajax({
        		url: url +"startStop?service=" + importName,
                type: 'GET',
                data: {"startBtn": btnClicked},
                success: function(res) {
                	
                	
                	
                	if(importName == "ldap"){
                		document.ldapRunning = "false";
                		console.log(document.ldapRunning); 
                	}
                	if(importName == "unihh"){
                		document.unihhRunning = "false";
                		console.log(document.unihhRunning); 
                	}
                	if(importName == "mobon"){
                		document.mobonRunning = "false";
                		console.log(document.mobonRunning); 
                	}
                	
                	$("#"+importName+"ImportBtnStop").attr("disabled","disabled");
                	$("#"+importName+"ImportBtnStart").attr("disabled", false);
                	$('#'+importName+'_animation').hide(); 
                }
            });
        
	}
	
	//===========================================================================================================

});



