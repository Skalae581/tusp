<%@page contentType="text/javascript" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<c:forEach items="${status}" var="s">
const status_${s.id} = '${s.id}';
</c:forEach>

$(document).ready(function() {
	$('.fame-control').change(onControlChange);
	$('.fame-control').first().change();
});
function onControlChange(event) {
	$('.client-alert').remove();
	$('form input[type="submit"]').prop( "disabled", false ).attr('title', '');
	${script}
}
function valueOf(fieldId) {
	return $('#'+fieldId).val()
}
function empty(value) {
	return value === null || value === '' || value === 0;
}
function log(msg) {
	console.log(msg)
}
function setValue(fieldId, val) {
	$('#'+fieldId).val(val)
}
function allowOptions(fieldId, options) {
	var field = $('#'+fieldId);
	
	if(options === 'all') {
		field.find('option').prop( "disabled", false );
		return;
	}
	
	field.find('option').each(function() {
		var val =  $(this).val();
		var disable = true;
		for(var i = 0; i < options.length; i++) {
			if(options[i] == val) {
				disable = false;
				break;
			}
		}
		$(this).prop( "disabled", disable );
	});
	
	
	
	var val = field.val();
	var includes = false;
	for(var i = 0; i < options.length; i++) {
		if(options[i] == val) {
			includes = true;
			break;
		}
	}
	if(!includes) {
		fail(getName(fieldId) + ' enthält einen nicht erlaubten Wert.');
	}
}
function fail(message) {
	$('form').prepend("<div class='alert alert-warning client-alert' role='alert'>"+message+"</div>");
	$('form input[type="submit"]').prop( "disabled", true ).attr('title', message);
}
function getName(fieldId) {
	var input = $('#'+fieldId);
	var label = $('label[for="'+input.attr('name')+'"]');
	return label.html();
}