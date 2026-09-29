function loadI18nFile(path, callback) {
	document.i18n = 'loading';
	$.ajax({
	  url: path,
	  context: document.body
	}).done(function(data) {
		if(document.i18n === 'loading') {
			document.i18n = data;
		} else {
			for(var locale in data) {
				for(var key in data[locale]) {
					document.i18n[locale][key] = data[locale][key];
				}
			}
		}
		waitForDocumentReady(callback);
	}).fail(function() {
		console.log("Konnte i18n-File " + path + " nicht laden.");
	});
}

function waitForDocumentReady(callback) {
	if(document.readyState == "complete") {
		callback();
	} else {
		setTimeout(function() {
			waitForDocumentReady(callback);
		}, 1);
	}
}

function localized(key) {
	if(document.i18n === undefined) {
		console.log("i18n wurde noch nicht geladen.");
		return key;
	}
	
	var locale = document.head.getAttribute('data-locale');
	
	if(typeof document.i18n[locale][key] !== "undefined") {
		return document.i18n[locale][key];
	}
	
	if(typeof document.i18n["de"][key] !== "undefined") {
		return document.i18n["de"][key];
	}
	
	return key;
}