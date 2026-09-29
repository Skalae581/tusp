loadI18nFile(document.head.getAttribute('data-contextPath') + "/resources/js/i18n.json", function() {
	
	// Header der Tabelle steht immer oben
	$('.headerfix').each(function(){
		var headerId = "id" + Math.random().toString(16).slice(2);
		var table = $(this);
		table.attr('data-headerid', headerId);
		table.css('table-layout', 'fixed');
		table.parent().before('<table id="' + headerId+'"></table>');
		table.height();
		
		var header = $('#'+headerId);
		header.get(0).table = table;
		header.attr('class', table.attr('class'));
		header.addClass('header');
		header.css('table-layout', 'fixed');
		table.parent().height($(window).height() * 0.9 - header.height());
		table.parent().css('overflow-y', 'auto');
		header.css('padding-left', table.css('padding-left'));
		header.css('padding-right', table.css('padding-right'));
		header.append(table.find('tr').first());
		$( window ).resize(function() {
			table.find('tr').first().find('th').each(function() {
				$(this).css('width', $(this).width());
			});
		});
	});
	
	$('.sortable').not('.header').each(function() {
		var table = $(this);
		var headerTbl;
		if(table.hasClass('headerfix')) {
			headerTbl = $('#'+table.attr('data-headerid'));
		} else {
			headerTbl = table;
		}
		headerTbl.find('tr.thead').first().find('td, th').each(function() {
			$(this).css('cursor', 'pointer'); // TODO sortierbarkeit sichtbar machen
			$(this).click(function(){
				this.order = this.order == 1 ? 0 : 1;
				var order = this.order;
				var index = $(this).parent().find('td, th').index($(this));
				var rows = table.find('tr.tdata');
				var dateReg =     /^\d{2}([./-])\d{2}\1\d{4}$/;
				var datetimeReg = /^(\d{2})\.(\d{2})\.(\d{4}) (\d{2}):(\d{2}):(\d{2})$/;

				rows.each(function(i, row){
					field = row.children[index];
					if(field.value != null) {
						return;
					}
					if(field.hasAttribute("data-value")) {
						field.value = field.attributes["data-value"].value;
						if(!isNaN(field.value)) {
							field.value = parseFloat(field.value);
						} else if(field.value.match(dateReg)) {
							var dateValue = field.value.split(".");
							field.value = new Date(dateValue[2], dateValue[1] - 1, dateValue[0]);
						} else if(field.value.match(datetimeReg)) {
							var dateValue = field.value.split(/[\s.: ]+/);
							field.value = new Date(dateValue[2], dateValue[1] - 1, dateValue[0], dateValue[3] , dateValue[4], dateValue[5]);
						}
					} else if(!isNaN(field.innerText)) {
						field.value = parseFloat(field.innerText);
					} else if(field.innerText.match(dateReg)) {
						var dateValue = field.innerText.split(".");
						field.value = new Date(dateValue[2], dateValue[1] - 1, dateValue[0]);
					} else if (field.innerText.match(datetimeReg)){
						var dateValue = field.innerText.split(/[\s.: ]+/);
						field.value = new Date(dateValue[2], dateValue[1] - 1, dateValue[0], dateValue[3] , dateValue[4], dateValue[5]);
					}else {
					
						field.value = field.innerText;
					}
				});
				
				rows.sort(function(a, b){
					valA = a.children[index].value;
					valB = b.children[index].value;

					if(valA == valB) return 0;
					if(order == 1) {
						if(typeof Object.defineProperty === 'function') {
							return valA > valB ? -1 : 1;
						} else {
							return valA > valB ? 1 : -1; 
						}
					} else {
						if(typeof Object.defineProperty === 'function') {
							return valA < valB ? -1 : 1;
						} else {
							return valA < valB ? 1 : -1; 
						}
					}
				});
				rows.detach().appendTo(table); 
			});
		});
	});
	
	$('.filter-column').not('.header').each(function() {
		var table = $(this);
		var headerTbl;
		if(table.hasClass('headerfix')) {
			headerTbl = $('#'+table.attr('data-headerid'));
		} else {
			headerTbl = table;
		}
		
		headerRow = headerTbl.find('tr.thead');
		filterRow = $('<tr class="filter"></tr>').insertAfter(headerRow);
		headerRow.find('th').each(function(index, element) {
			clazz = element.classList[0];
			if($(this).hasClass('nofilter')) {
				filterRow.append('<td></td>');
			} else {
				filterRow.append('<td><input class="filter-value form-control" data-index="'+clazz+'" /></td>');
			}
		});
		filter = filterRow.find('.filter-value');
		filter.keyup(function(){
			document.test = this;
			val = this.value.toLowerCase();
			clazz = this.attributes['data-index'].value;
			$('.filter-value[data-index!="'+clazz+'"]').val("");
			table.find('.'+clazz).each(function(i, e){
				cell = $(e);
				row = $(e).parents('.tdata');
				if(cell.text().toLowerCase().includes(val)) {
					row.show();
				} else {
					row.hide();
				}
			});
		});
	});
	
	$('.filter-single').not('.header').each(function() {
		var table = $(this);
		table.parents('.table-container').find('.table-menu').prepend("<input class='search' placeholder='"+localized('table.search')+"'/>");
		var search = table.parents('.table-container').find('.search');
		search.keyup(function() {
			val = this.value.toLowerCase();
			table.find('.tdata').each(function() {
				row = $(this);
				row.hide();
				row.find('td').each(function() {
					if($(this).text().toLowerCase().includes(val)) {
						row.show();
					}
				});
			});
		});
	});
	//var input = 
});