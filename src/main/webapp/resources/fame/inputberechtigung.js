$(document).ready(function() {
	
	initView();
    $( ".combobox" ).combobox();
});

var roles = null;
var berechtigungen = null;

function initView() {
	var table = $('#inputberechtigung');
	var id =  table.attr('data-workflowid');
	
	loadRoles(id);
	loadPermissions(id);
	
	$('.add').click(addClickHandler);
	$('#addForm button').click(addBtnClickHandler);
	$('.glyphicon-remove').click(removeClickHandler);
}

function addClickHandler() {
	$(this).hide();
	$(this).after($('#addForm'));
	$('#addForm').show();
}

function addBtnClickHandler() {
	var cell = $('#addForm').parent();
	var addBtn = cell.find('.add');
	
	var inputId = addBtn.attr('data-inputid');
	var statusId = addBtn.attr('data-statusid');
	var recht = $('#addForm #rechte').val();
	var rolle = $('#addForm #rolle').val();

	$.ajax({
	  url: "add?rolleId=" + rolle + "&recht=" + recht + "&statusId=" + statusId + "&inputId=" + inputId,
	  dataType: 'json'
	}).done(function(object) {
		addBtn.before(createRoleSpan(roles[object.rolleId], recht, object.id));
		$('.glyphicon-remove').click(removeClickHandler);
	}).fail(function(){
		console.log("Speichern fehlgeschlagen");
	});
	
	addBtn.show();
	$('#addForm').hide();
}

function removeClickHandler() {
	var elem = $(this);
	var cell = $(this).parents('.cell');
	var id = $(this).parent().attr('data-inputBerechtigungId');
	$.ajax({
	  url: "remove?id=" + id,
	  dataType: 'json'
	}).done(function() {
		elem.parent().remove();
	}).fail(function(){
		console.log("konnte inputberechtigung nicht löschen");
	});
}

function loadRoles(id) {
	$.ajax({
	  url: "rollen?id=" + id,
	  dataType: 'json'
	}).done(function(objects) {
		roles = {};
		objects.forEach(function(elem){
			roles[elem.id] = elem.name.substring(5);
		});
		initTable();
	}).fail(function(){
		console.log("konnte rollen nicht laden");
	});
}

function loadPermissions(id) {
	$.ajax({
	  url: "inputberechtigung?id=" + id,
	  dataType: 'json'
	}).done(function(objects) {
		berechtigungen = objects;
		initTable();
	}).fail(function(){
		console.log("konnte inputberechtigung nicht laden");
	});
}

function initTable() {
	if(roles == null || berechtigungen == null) {
		return;
	}
	
	$('.cell').children().not('.add').remove();
	
	berechtigungen.forEach(function(elem){
		if(elem.recht == 'NONE') {
			return;
		}
		
		if(roles[elem.rolleId] == null) {
			console.log('Rolle nicht geladen: ' + elem.rollenId);
			return;
		}
		
		var addButton = $('#td' + elem.statusId + '-' + elem.inputId).children('.add');
		var role = roles[elem.rolleId];
		addButton.before(createRoleSpan(role, elem.recht, elem.id));
		$('.glyphicon-remove').click(removeClickHandler);
	});
}

function createRoleSpan(role, recht, inputBerechtigungId) {
	icon = '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>';
	small = '<small>('+recht+')</small>';
	return '<span data-inputBerechtigungId='+inputBerechtigungId+'>'+role+' '+small+' '+icon+'<br /></span>';
}

