$(document).ready(function(){
	// ALLE Fehlermitteilungen ausblenden
	$("[id^=error]").hide();
	
	// 'kleingedruckt' sollte nur angezeigt werden, wenn sich ein "*" im ECTS-Bogen befindet
	$('#kleingedruckt').addClass('hidden');  
	if ($('[id^=ectsFach]:contains("*")').length > 0) {
		$('#kleingedruckt').removeClass('hidden');   
	}
	
	// Für Special-Templates wie LIM
	$('[class^=ectsZusatz]:contains("Gesamt"):not(:contains("Vertiefung"))').each(function (i, val) {
		val = $(this).attr('class').split("_", 2)[1];
		$('table.ectsTable tr#ectsBereich'+val).removeClass("ectsBereich").attr('id', 'ectsFach'+val);  
		$('table.ectsTable input#bereichSumme_'+val).css('background', 'transparent').removeAttr("name");
		$('table.ectsTable input#bereichErwerb_'+val).css('background', 'transparent').removeAttr("name");
		$('table.ectsTable input#bereichModul_'+val).css('background', 'transparent').removeAttr("name");
		$('table.ectsTable input#bereichNachweis_'+val).css('background', 'transparent').removeAttr("name");
		$("input[name=bereichPunkte_"+val+"]").removeAttr("name");
	});
	
	var berechnungsAlgorithmus = function() {
		// Generell sollen die Fehlermitteilungen ausgeblendet sein, daher nochmal am Anfang der Funktion
		$("[id^=error]").hide();
		
		var curr_id = $(this).attr('id');
		var curr_idVal = curr_id.split("_", 2)[1];
		var curr_class = $(this).attr('class');
		if(typeof curr_class !== "undefined") {
			var curr_classVal = curr_class.split("_", 2)[1];
		}
		var total = 0;
		var max = 0;
		
		/* Nachkommastellen prüfen, erlaubt sind nur 0.5 und 0.75 */
		var decimalCheck = ($("#" + curr_id).val() % 0.5 == 0) || ($("#" + curr_id).val() % 0.25 == 0) ? true : false;
	
		if($.isNumeric($("#" + curr_id).val()) == false) {
			$("#error_"+curr_id).show();
			$("#" + curr_id).val('');
		} else if ($("#" + curr_id).val() < 0) {
			$("#errorNegative_"+curr_id).show();
			$("#" + curr_id).val('');
		} else if($("#" + curr_id).val() % 1 != 0){
			if(decimalCheck == false) {
				$("#errorDecimal_"+curr_id).show();
				$("#" + curr_id).val('');
			}
		}
		
		// Falls ein Zusatzinfo-Feld "Gesamt" enthält, wird die Summe anders berechnet
		if ($('[class^=ectsZusatz]:contains("Gesamt")').length > 0) {
			(function($){
				   $.fn.calculate = function(uberId, idArray) {
					   var maxArray = new Array();
					   if(uberId != null){   
						   $.each(idArray, function(i){
							   max += Number($("#bereichSumme_" + idArray[i]).val());
							});
						   $("#bereichSumme_"+uberId).val(max);
					   }
				   }; 
				})( jQuery );
			
			if($('[class^=ectsZusatz]:contains("Kenntnisse")').length > 0) {
				var uberId = $('[class^=ectsZusatz]:contains("Kenntnisse")').attr('class').split("_", 2)[1];
			} else {
				var uberId = null;
			}

			// Array anlegen für Ids von classes der "Gesamt"-Bereiche
			var idArray = new Array();

			$('[class^=ectsZusatz]:contains("Gesamt")').each(function (i) {
				idArray[i] = $(this).attr('class').split("_", 2)[1];
			});

			// Summe einer "Gesamt"-Gruppe mit separater Funktion berechnen
			if(typeof $(this).attr('class') !== "undefined") {
				var currId = $(this).attr('class').split("_", 2)[1];
			}
			
			// Summen der restlichen Gruppen werden regulär berechnet, d.h. einfach Zahlen zusammenrechnen
			var regular = function() {
							$(".bereich_"+curr_classVal).each(function() {
								total += Number($(this).val());
								$("#bereichSumme_"+curr_classVal).val(total);
							});
						}
					
			if(jQuery.inArray(currId, idArray) != -1 ){
					regular();
					$(this).calculate(uberId, idArray);
			} else {
				regular();
			}

		} else {
			$(".bereich_"+curr_classVal).each(function() {
				total += Number($(this).val());
				$("#bereichSumme_"+curr_classVal).val(total);
			});
		}
	}


	
	if ($('[id^=fachInput]').filter(function() { return $(this).val();}).length > 0) {
		$('[id^=fachInput]').each(berechnungsAlgorithmus);
	}
	
	// Fehlermitteilung anzeigen, sobald sich der Fokus außerhalb der Zelle befindet und keine Zahl eingegeben worden ist
	$('[id^=fachInput]').blur(berechnungsAlgorithmus);

	// .blur() verhindert das Anzeigen des Mauszeigers in Readonly-Feldern
    $('input[readonly]').focus(function(){
        this.blur();
    });
});

