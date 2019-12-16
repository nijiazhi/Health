var ajaxTable1 = function() {
	function restoreRow(oTable, nRow) {
		var aData = oTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);

		for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
			oTable.fnUpdate(aData[i], nRow, i, false);
		}

		oTable.fnDraw();
	}
    var countSelectedRecords = function() {
        var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        var text = tableOptions.dataTable.language.metronicGroupActions;
        if (selected > 0) {
            $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
        } else {
            $('.table-group-actions > span', tableWrapper).text("");
        }
    };
	function editRow(oTable, nRow) {

		var aData = oTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);
		// jqTds[0].innerHTML = '<input type="text" class="form-control
		// input-small" value="' + aData[0] + '">';
		//jqTds[1].innerHTML = '<input type="text" class="form-control input-xsmall" value="'
		//		+ aData[1] + '">';
//		jqTds[2].innerHTML = '<input type="text" class="form-control input-xsmall" value="'
//				+ aData[2] + '">';
//		 jqTds[3].innerHTML = '<input type="text" class="form-control input-small" value="'
//		 + aData[3] + '">';
//		jqTds[4].innerHTML = '<input type="text" class="form-control input-xsmall" value="'
//			+ aData[4] + '">';
//		jqTds[5].innerHTML = '<input type="text" class="form-control input-small" value="'
//			+ aData[5] + '">';
		jqTds[6].innerHTML = '<input type="text" class="form-control input-small" value="'
			+ aData[6] + '">';
		jqTds[7].innerHTML = '<a class="edit" href="">保存</a>';
		jqTds[8].innerHTML = '<a class="cancel" href="">取消</a>';
		ComponentsPickers.init();
	}

	function saveRow(oTable, nRow) {
		var jqInputs = $('input', nRow);
		var jqSelect = $('select', nRow);


		oTable.fnUpdate(jqInputs[1].value, nRow, 6, false);
		
		oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow,7, false);
		oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 8, false);
		oTable.fnDraw();
		// 向后台更改数据逻辑
		var aData = oTable.fnGetData(nRow);
		var options;

		 {

			options = {
				type : "get",// 请求方式
				url : "/healthcare/updatesuggestion",// 发送请求地址
				dataType : "json",

				data : {
					operation : "update_user",
				id : aData[1],
					suggesresult:aData[6]
				},
				success : function(data) {
					alert(data);
					// data.instance.refresh();
					
				}
			}
			// alert("添加更新接口");
		}

		$.ajax(options);
		 //location.reload(true);
	}
	var table = $('#usertable');

	var oTable = table.dataTable({
		"lengthMenu" : [ [ 10, 15, 20, -1 ], [ 10, 15, 20, "所有" ] // change
		// per
		// page
		// values
		// here
		],
		"pageLength" : 10,

		"language" : {
			"lengthMenu" : "每页显示  _MENU_ 条记录",
			"paging" : {
				"previous" : "Prev",
				"next" : "Next"
			}
		},
		"columnDefs" : [ { // set default column settings
			'orderable' : false,
			'targets' : [ 0 ]
		}, {
			"searchable" : true,
			"targets" : [ 0 ]
		} ],
		"order" : [ [ 1, "asc" ] ]
	// set first column as a default sort by asc
	});
	 // handle group checkboxes check/uncheck
    $('.group-checkable', table).change(function() {
        var set = $('tbody > tr > td:nth-child(1) input[type="checkbox"]', table);
        var checked = $(this).is(":checked");
        $(set).each(function() {
            $(this).attr("checked", checked);
        });
        $.uniform.update(set);
        countSelectedRecords();
    });

    // handle row's checkbox click
    table.on('change', 'tbody > tr > td:nth-child(1) input[type="checkbox"]', function() {
        countSelectedRecords();
    });

	var tableWrapper = $("#editable_1_wrapper");

	tableWrapper.find(".dataTables_length select").select2({
		showSearchInput : false
	// hide search box with special css class
	}); // initialize select2 dropdown

	var nEditing = null;
	var nNew = false;
	var maxId = 0;
	table.on('click', '.delete', function(e) {
		e.preventDefault();

		if (confirm("确定删除该行 ?") == false) {
			return;
		}

		var nRow = $(this).parents('tr')[0];

		var aData = oTable.fnGetData(nRow);

		$(function() {
			options = {
				type : "get",// 请求方式
				url : "/healthcare/deletesuggestion",// 发送请求地址
				dataType : "json",
				data : {
					operation : "deletesuggestion",
					id : aData[1],
				},
				success : function(data) {
					alert(data);
					// data.instance.refresh();

				}
			}
			$.ajax(options);
			oTable.fnDeleteRow(nRow);
			location.reload(true);
			/*
			 * var url = "deletepath.action?path.pid="+aData[0]; $.getJSON(url,
			 * function(data) { if(data=="删除路线成功！"){ oTable.fnDeleteRow(nRow);
			 * alert(data); } });
			 */
		});

	});

    // handle group actionsubmit button click ---- 删除功能
	$('#user_editable_1_delete').click(
			function(e) {
				e.preventDefault();
				if(!confirm('确定将这些记录删除?'))
		    		return;
				var str=document.getElementsByName("ids");
				//var str='#ids';
			
				var objarray=str.length;
				var chestr="";
				for (i=0;i<objarray;i++)
				{
				  if(str[i].checked == true)
				  {
				   chestr+=str[i].value+",";
				  }
				}
				if(chestr == "")
				{
				  alert("请先选择要删除的数据！");
				}
				else
				{
				 // alert("您先择的是："+chestr);
					$(function() {
						// alert("添加删除接口");
						options = {
							type : "get",// 请求方式
							url : "/healthcare/deletesuggestions",// 发送请求地址
							dataType : "json",
							data : {
								operation : "delete_files",
								ids :chestr ,
							},
							success : function(data) {
								alert(data);
								// data.instance.refresh();

							}
						}
						$.ajax(options);
						//oTable.fnDeleteRow(nRow);
						location.reload(true);
						/*
						 * var url = "deletepath.action?path.pid="+aData[0]; $.getJSON(url,
						 * function(data) { if(data=="删除路线成功！"){ oTable.fnDeleteRow(nRow);
						 * alert(data); } });
						 */
					});
				}

			});
	  // handle group actionsubmit button click ---- 清理数据功能
	$('#user_editable_1_deleteold').click(
			function(e) {
				var chestr="";
				e.preventDefault();
				if(!confirm('确定清理过期数据?'))
		    		return;

				{
				 // alert("您先择的是："+chestr);
					$(function() {
						// alert("添加删除接口");
						options = {
							type : "get",// 请求方式
							url : "deleteoldfiles",// 发送请求地址
							dataType : "json",
							data : {
								ids :chestr ,
							},
							success : function(data) {
								alert(data);
								// data.instance.refresh();

							}
						}
						$.ajax(options);
						//oTable.fnDeleteRow(nRow);
						location.reload(true);
						/*
						 * var url = "deletepath.action?path.pid="+aData[0]; $.getJSON(url,
						 * function(data) { if(data=="删除路线成功！"){ oTable.fnDeleteRow(nRow);
						 * alert(data); } });
						 */
					});
				}

			});
	table.on('click', '.cancel', function(e) {
		e.preventDefault();
		if (nNew) {
			oTable.fnDeleteRow(nEditing);
			nEditing = null;
			nNew = false;
		} else {
			restoreRow(oTable, nEditing);
			nEditing = null;
		}
	});
	table.on('click', '.edit', function(e) {
		e.preventDefault();

		/* Get the row as a parent of the link that was clicked on */
		var nRow = $(this).parents('tr')[0];

		var aData = oTable.fnGetData(nRow);

		if (nEditing !== null && nEditing != nRow) {
			/*
			 * Currently editing - but not this row - restore the old before
			 * continuing to edit mode
			 */
			restoreRow(oTable, nEditing);
			editRow(oTable, nRow);
			nEditing = nRow;
		} else if (nEditing == nRow && this.innerHTML == "保存") {
			/* Editing this row and want to save it */

			saveRow(oTable, nEditing);
			nEditing = null;
		} else {
			/* No edit in progress - let's start one */
			editRow(oTable, nRow);
			nEditing = nRow;
		}
	});
}
ajaxTable1();
var oTable1 = $('#usertable').dataTable();
$(function() {
	var url = "/healthcare/getallsuggestion";
	$.getJSON(url, function(data) {
		$.each(data, function(i, user) {
			oTable1.fnAddData(['<input type=\'checkbox\' name=' + 'ids' +  ' value='+
			                   user.id + '>', user.id, user.username, user.type,
					user.suggestion,user.suggestiontime,user.suggesresult,'<a class="edit" href="">编辑</a>',
					'<a class="delete" href="">删除</a>' ]);
		});
	});
});