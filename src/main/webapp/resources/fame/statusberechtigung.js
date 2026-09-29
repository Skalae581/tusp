$(document).ready(function() {
	
	initView();
    $( ".combobox" ).combobox();
});

var roles = null;
var berechtigungen = null;
var dialog = null;

function initView() {
	var table = $('#statusberechtigung');
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
	var statusFromId = addBtn.attr('data-statusFromId');
	var statusToId = addBtn.attr('data-statusToId');
	var rolle = $('#addForm #rolle').val();

	$.ajax({
	  url: "add?rolleId=" + rolle + "&statusToId=" + statusToId + "&statusFromId=" + statusFromId,
	  dataType: 'json'
	}).done(function(object) {
		addBtn.before(createRoleSpan(roles[object.rolleId], object.id));
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
	var id = $(this).parent().attr('data-statusBerechtigungId');
	$.ajax({
	  url: "remove?id=" + id,
	  dataType: 'json'
	}).done(function() {
		elem.parent().remove();
	}).fail(function(){
		console.log("konnte statusberechtigung nicht löschen");
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
	  url: "statusberechtigung?id=" + id,
	  dataType: 'json'
	}).done(function(objects) {
		berechtigungen = objects;
		initTable();
	}).fail(function(){
		console.log("konnte statusberechtigung nicht laden");
	});
}

function initTable() {
	if(roles == null || berechtigungen == null) {
		return;
	}
	
	$('.cell').children().not('.add').remove();
	
	berechtigungen.forEach(function(elem){
		if(roles[elem.rolleId] == null) {
			console.log('Rolle nicht geladen: ' + elem.rollenId);
			return;
		}
		
		var addButton = $('#td' + elem.statusToId + '-' + elem.statusFromId).children('.add');
		var role = roles[elem.rolleId];
		addButton.before(createRoleSpan(role, elem.id));
		$('.glyphicon-remove').click(removeClickHandler);
	});
}

function createRoleSpan(role, statusBerechtigungId) {
	// Link zum editieren
	icon = '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>';
	return '<span data-statusBerechtigungId='+statusBerechtigungId+'>'+role+' '+icon+'<br /></span>';
}

