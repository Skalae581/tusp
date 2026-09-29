$.validator.addMethod(
    "date",
    function(value, element) {
        // put your own logic here, this is just a (crappy) example
        if(value.match(/^\d\d?\.\d\d?\.\d\d\d?\d?$/)) {
        	return true;
        }
        if(value.match(/^\d\d\d\d-\d\d-\d\d$/)) {
        	return true;
        }
        return false;
    },
    "Please enter a valid date."
);

$(document).ready(function(){
	$( function() {
		$( ".datepicker" ).datepicker({
			dateFormat: "yy-mm-dd" // TODO depending on Locale
		});
	} );
});

/**
 * Schließt das Fenster wenn möglich (nicht vom User selbst erstellt) oder navigiert zur URL
 * @param urlIfNotPossible Adresse auf die der Browser geht, wenn das Fenster offen bleibt
 */
function closeWindow(urlIfNotPossible) {
	// Wenn Tab nicht schießt, gehe zur Weiche
	window.location = urlIfNotPossible;

	// Tab schließen, wenn möglich
	window.close();
}