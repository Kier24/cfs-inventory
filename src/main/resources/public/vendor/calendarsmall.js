YUI().use(
		'calendar',
		'datatype-date',
		'cssbutton',
		function(Y) {

			// Create a new instance of calendar, placing it in
			// #mycalendar container, setting its width to 340px,
			// the flags for showing previous and next month's
			// dates in available empty cells to true, and setting
			// the date to today's date.
			var calendar = new Y.Calendar({
				contentBox : "#calendarsmall",
				width : '280px',
				showPrevMonth : true,
				showNextMonth : true,
				date : new Date()
			}).render();

			// Get a reference to Y.DataType.Date
			var dtdate = Y.DataType.Date;

			// Listen to calendar's selectionChange event.
			calendar.on("selectionChange", function(ev) {

				// Get the date from the list of selected
				// dates returned with the event (since only
				// single selection is enabled by default,
				// we expect there to be only one date)
				var newDate = dtdate.format(ev.newSelection[0]);
				var table = $("#tblGoods tbody");
			
				var actionButtons = "<td><div class=\"table-data-feature\">"+
									"<button class=\"btnEdit item\" data-toggle=\"modal\""+
									"data-target=\"#editModal\" title=\"Edit\">"+
									"<i class=\"zmdi zmdi-edit\"></i>"+
									"</button>"+
									"<button class=\"deleteAction item\" data-toggle=\"modal\""+
									"data-target=\"#deletemodal\" title=\"Delete\">"+
									"<i class=\"zmdi zmdi-delete\"></i>"+
									"</button>"+
									"</div></td>";
				
				alert(actionButtons);
				$.ajax({
					type : "GET",
					url : "/inventory/producedGoods/dateCreated/" + newDate,
					timeout : 100000,
					success : function(data) {
						table.empty();
						$.each(data, function(idx, elem) {
							table.append("<tr id=\""+elem.id+"\" class=\"tr-shadow\"><td id=\"productName\">" + elem.name
									+"</td><td id=\"price\">" +elem.price
									+ "</td><td id=\"quantity\">" + elem.quantity + "</td>   <td id=\"criticalLevel\">"
									+ elem.criticalLevel + "</td>"+actionButtons+"</tr>");
						});
					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {

					}
				});
				// Format the date and output it to a DOM
				// element.
				// Y.one("#selecteddate").setHTML(dtdate.format(newDate));
			});

			// When the 'Show Previous Month' link is clicked,
			// modify the showPrevMonth property to show or hide
			// previous month's dates
			Y.one("#togglePrevMonth").on(
					'click',
					function(ev) {
						ev.preventDefault();
						calendar.set('showPrevMonth', !(calendar
								.get("showPrevMonth")));
					});

			// When the 'Show Next Month' link is clicked,
			// modify the showNextMonth property to show or hide
			// next month's dates
			Y.one("#toggleNextMonth").on(
					'click',
					function(ev) {
						ev.preventDefault();
						calendar.set('showNextMonth', !(calendar
								.get("showNextMonth")));
					});
		});