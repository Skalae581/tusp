
loadI18nFile(document.head.getAttribute('data-contextPath') + "/resources/fame/i18n.json", function() {
	$('.table-menu').append('<a href="#" class="btn btn-default group-action">'+localized('fame.group-action')+'</a>');
	
	$('.group-action').click(groupActionClick);
});

function groupActionClick(e) {
	e.preventDefault();
	
	$(this).parents('.table-container').find('.group-action').hide();
	$(this).parents('.table-container').find('.thead').prepend('<th style="width: 22px; text-align: right"><input type="checkbox" class="choose_all" /></th>');
	$(this).parents('.table-container').find('.tdata').prepend('<td style="width: 22px"><input type="checkbox" class="choose" /></td>');
	
	$(this).parents('.table-container').find(".choose_all").change(selectAllChange);
	$(this).parents('.table-container').find(".choose_all").attr('title', localized('fame.select_all'));
	
	$(this).parents('.table-container').find('.table-menu').append('<a href="#" class="btn btn-default make_pdf">'+localized('fame.make_pdf')+'</a>');
	$('.make_pdf').click(makePdfClick);
	
	$(this).parents('.table-container').find('.table-menu').append('<a href="#" class="btn btn-default make_csv">'+localized('fame.make_csv')+'</a>');
	$('.make_csv').click(makeCsvClick);
	
	$(this).parents('.table-container').find('.table-menu').append($('#moveForm'));
	
	
	$('#moveForm').css('display', 'inline-block');
	$('#moveForm').find('a[data-href]').click(moveClick);
}

function selectAllChange(e) {
	var checked = $(this).parents('.table-container').find('.choose_all:checkbox').is(':checked');
	$(this).parents('.table-container').find('.choose').prop('checked', checked);
}

function getChecked(tblMember) {
	var ids='';
	$(tblMember).parents('.table-container').find('.tdata').each(function() {
		if($(this).find('.choose').is(':checked')) {
			ids += $(this).find('.vorgangId').val() + ",";
		}
	});
	if(ids) {
		ids = ids.substring(0, ids.length -1);
	}
	return ids;
}

function makePdfClick(e) {
	e.preventDefault();
	
	var ids=getChecked(this);
	if(!ids) {
		alert(localized('fame.no_case_selected'));
		return;
	}
	
	document.location.href = document.head.getAttribute('data-contextPath') + "/fame/user/pdf?ids=" + ids;
}

function moveClick(e) {
	e.preventDefault();
	
	var ids=getChecked(this);
	if(!ids) {
		alert(localized('fame.no_case_selected'));
		return;
	}

	var url = $(this).attr('data-href');
	document.location.assign(url+'ids='+ids);
}

function makeCsvClick(e) {
	e.preventDefault();
	
	var ids=getChecked(this);
	if(!ids) {
		alert(localized('fame.no_case_selected'));
		return;
	}
	
	document.location.href = document.head.getAttribute('data-contextPath') + "/fame/user/csv?ids=" + ids;
}