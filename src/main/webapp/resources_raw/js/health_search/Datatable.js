/***
Wrapper/Helper Class for datagrid based on jQuery Datatable Plugin
***/
var Datatable = function() {

    var tableOptions; // main options
    var dataTable; // datatable object
    var table; // actual table jquery object
    var tableContainer; // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var the;
    var countSelectedRecords = function() {
        var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        var text = tableOptions.dataTable.language.metronicGroupActions;
        if (selected > 0) {
            $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
        } else {
            $('.table-group-actions > span', tableWrapper).text("");
        }
    };

    return {

        //main function to initiate the module
        init: function(options) {

            if (!$().dataTable) {
                return;
            }

            the = this;

            // default settings
            options = $.extend(true, {
                src: "", // actual table  
                filterApplyAction: "filter",
                filterCancelAction: "filter_cancel",
                resetGroupActionInputOnSuccess: true,
                loadingMessage: 'Loading...',
                dataTable: {
                    "dom": "<'row'<'col-md-8 col-sm-12'<'table-group-actions1'>><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r><'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>", // datatable layout
                    "pageLength": 20, // default records per page
                    "language": { // language settings
                        // metronic spesific
                        "metronicGroupActions": "选择了 _TOTAL_ 条记录:  ",
                        "metronicAjaxRequestGeneralError": "无法完成请求，请检查您的网络连接",

                        // data tables spesific
                        "lengthMenu": "<span class='seperator'>|</span>每页显示 _MENU_ 条记录",
                        "info": "<span class='seperator'>|</span>共  _TOTAL_ 条记录",
                        "infoEmpty": "没有找到需要显示的数据",
                        "emptyTable": "列表中没有可选的数据",
                        "zeroRecords": "没有找到匹配的记录",
                        "paginate": {
                            "previous": "Prev",
                            "next": "Next",
                            "last": "Last",
                            "first": "First",
                            "page": "",
                            "pageOf": "》"
                        }
                    },

                    "orderCellsTop": true,
                    "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                        'orderable': false,
                        'targets': [0]
                    }],

                    "pagingType": "bootstrap_extended", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                    "autoWidth": false, // disable fixed width and enable fluid table
                    "processing": false, // enable/disable display message box on record load
                    "serverSide": true, // enable/disable server side ajax loading

                    "ajax": { // define ajax settings
                        "url": "", // ajax URL
                        "type": "GET", // request type
                        "timeout": 80000,
                        "data": function(data) { // add request parameters before submit
                            $.each(ajaxParams, function(key, value) {
                                data[key] = value;
                            });
                            Metronic.blockUI({
                                message: tableOptions.loadingMessage,
                                target: tableContainer,
                                overlayColor: 'none',
                                cenrerY: true,
                                boxed: true
                            });
                        },
                        "dataSrc": function(res) { // Manipulate the data returned from the server
                            if (res.customActionMessage) {
                                Metronic.alert({
                                    type: (res.customActionStatus == 'OK' ? 'success' : 'danger'),
                                    icon: (res.customActionStatus == 'OK' ? 'check' : 'warning'),
                                    message: res.customActionMessage,
                                    container: tableWrapper,
                                    place: 'prepend'
                                });
                            }

                            if (res.customActionStatus) {
                                if (tableOptions.resetGroupActionInputOnSuccess) {
                                    $('.table-group-action-input', tableWrapper).val("");
                                }
                            }

                            if ($('.group-checkable', table).size() === 1) {
                                $('.group-checkable', table).attr("checked", false);
                                $.uniform.update($('.group-checkable', table));
                            }

                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, the);
                            }

                            Metronic.unblockUI(tableContainer);

                            return res.data;
                        },
                        "error": function() { // handle general connection errors
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, the);
                            }

                            Metronic.alert({
                                type: 'danger',
                                icon: 'warning',
                                message: tableOptions.dataTable.language.metronicAjaxRequestGeneralError,
                                container: tableWrapper,
                                place: 'prepend'
                            });

                            Metronic.unblockUI(tableContainer);
                        }
                    },

                    "drawCallback": function(oSettings) { // run some code on table redraw
                        if (tableInitialized === false) { // check if table has been initialized
                            tableInitialized = true; // set table initialized
                            table.show(); // display table
                        }
                        Metronic.initUniform($('input[type="checkbox"]', table)); // reinitialize uniform checkboxes on each table reload
                        countSelectedRecords(); // reset selected records indicator

                        // callback for ajax data load
                        if (tableOptions.onDataLoad) {
                            tableOptions.onDataLoad.call(undefined, the);
                        }
                    }
                }
            }, options);

            tableOptions = options;

            // create table's jquery object
            table = $(options.src);
            tableContainer = table.parents(".table-container");

            // apply the special class that used to restyle the default datatable
            var tmp = $.fn.dataTableExt.oStdClasses;

            $.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper + " dataTables_extended_wrapper";
            $.fn.dataTableExt.oStdClasses.sFilterInput = "form-control input-small input-sm input-inline";
            $.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";

            // initialize a datatable
            dataTable = table.DataTable(options.dataTable);

            // revert back to default
            $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
            $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
            $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

            // get table wrapper
            tableWrapper = table.parents('.dataTables_wrapper');

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
                $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
            }
            
            // build table group actions panel---1
            if ($('.table-actions-wrapper1', tableContainer).size() === 1) {
                $('.table-group-actions1', tableWrapper).html($('.table-actions-wrapper1', tableContainer).html()); // place the panel inside the wrapper
                $('.table-actions-wrapper1', tableContainer).remove(); // remove the template container
            }
            
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

            // handle filter submit button click
            table.on('click', '.filter-submit', function(e) {
                e.preventDefault();
                the.submitFilter();
            });

            // handle filter cancel button click
            table.on('click', '.filter-cancel', function(e) {
                e.preventDefault();
                the.resetFilter();
            });
        },

        //判断filter条件
        submitFilter: function() {

        	alert("go apply_datatable_user.js");
            the.setAjaxParam("action", tableOptions.filterApplyAction);


            // get all typeable inputs
            console.log($('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', table));
            $('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', table).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all checkboxes
            $('input.form-filter[type="checkbox"]:checked', table).each(function() {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input.form-filter[type="radio"]:checked', table).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            dataTable.ajax.reload();
        },

        //取消filter条件
        resetFilter: function() {
        	
            $('textarea.form-filter, select.form-filter, input.form-filter', table).each(function() {
                $(this).val("");
            });
            
            $('input.form-filter[type="checkbox"]', table).each(function() {
                $(this).attr("checked", false);
            });
            the.clearAjaxParams();
            the.addAjaxParam("action", tableOptions.filterCancelAction);
            dataTable.ajax.reload();
        },

        getSelectedRowsCount: function() {
            return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        },

        getSelectedRows: function() {
            var rows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function() {
                rows.push($(this).val());
            });

            return rows;
        },
        
        setMotalClickEvent: function() {
//        	alert('setMotalClickEvent here');
//        	var test=$('tbody > tr > td:nth-child(7)', table);
//        	console.log($('tbody > tr > td:nth-child(7)', table)+"");
        	var url = window.location.href;
        	
    		if(url.indexOf("env") < 0){
    			the.userDataStatusModal();//数据申请
    		}else{
    			the.userEnvStatusModal();//虚拟环境申请
    		}
    		
        },
        
        setEditApplyDisable: function(){
        	 $('tbody > tr > td:nth-child(7)', table).each(function() {
        		if( $(this).text()!='待审核' ){
        			$(this).next().find('.btn-editable').hide();
        		}
        	 });
        },
        
        //模态框部分
        fillStatusModal : function (hc_doc){
        	$('#modal_applyid').html(hc_doc.idApplydata);
        	$('#modal_username').html(hc_doc.name);
        	$('#modal_projectname').html(hc_doc.proName);
        },
        
        userEnvStatusModal : function (){
            $('tbody > tr > td:nth-child(7) button[class*=\'motalButton\']', table).each(function() {
                $(this).on('click',function(){
                	
                	var applyID = $(this).attr('id');
//                	console.log(applyID);
                	applyID = applyID.substring(1);
                	
                	$.ajax({ 
     					type : "get",//请求方式 
     					url : "getdocdatabyapplyid",//发送请求地址
     					dataType : "json", 
     					data:{ 
     						applyid : applyID
     					}, 
     					success :function(data) {
     						//alert(data); 
//     						console.log(applyID+" : "+data);
     						the.fillStatusModal(data);
     						var applyStatus = data.flagApplydata;
     						
     					  	switch(applyStatus){
     					  	case '1' ://待审核---//卒中数据中心ing
                    			the.screeningSelect($('.screening-select.select-1'), 0);
                    			break;
                    		case '2' ://卒中数据中心ed---//卒中防治委员会ing
                    			the.screeningSelect($('.screening-select.select-2'), 130);
                    			break;
                    		case '3' ://卒中数据办公室ed---//分配环境ing
                    			the.screeningSelect($('.screening-select.select-3'), 260);
                    			break;
                    		case '4' ://环境分配ed---//审核通过
                    			the.screeningSelect($('.screening-select.select-5'), 520);
                    			break;
                    		case '5' ://审核失败
                    			var applyRejectReason = data.applyRejectReason;
                    			$('#rejectReason').html(applyRejectReason);
                    			$('#failPanel').show();
                    			$('#status_final > a').html('审核失败');
                    			the.screeningSelect($('.screening-select.select-5'), 520);
                    			break;
                    		default :
                    			$('#rejectReason').html('无法提交申请，请联系系统管理员！');
                    			$('#failPanel > h5').html('<b>系统提示</b>');
                				$('#failPanel').show();
                    			the.screeningSelect($('.screening-select.select-1'), 0);
                    			break;
     					  	}
     						
     					} 
             		});
                	
                	$('#large').modal('show');
                
                });
            });
        },
        
        userDataStatusModal : function (){
            $('tbody > tr > td:nth-child(7) button[class*=\'motalButton\']', table).each(function() {
                $(this).on('click',function(){
                	
                	var applyID = $(this).attr('id');
//                	console.log(applyID);
                	applyID = applyID.substring(1);
                	
                	$.ajax({ 
     					type : "get",//请求方式 
     					url : "getdocdatabyapplyid",//发送请求地址
     					dataType : "json", 
     					data:{ 
     						applyid : applyID
     					}, 
     					success :function(data) {
     						//alert(data); 
//     						console.log(applyID+" : "+data);
     						the.fillStatusModal(data);
     						var applyStatus = data.flagApplydata;
     						
     					  	switch(applyStatus){
	     					  	case '1' ://待审核---//卒中数据中心ing
	                    			the.screeningSelect($('.screening-select.select-1'), 0);
	                    			break;
	                    		case '2' ://卒中数据中心ed---//卒中防治委员会ing
	                    			the.screeningSelect($('.screening-select.select-2'), 130);
	                    			break;
	                    		case '3' ://卒中数据办公室ed---//分配环境ing
	                    			the.screeningSelect($('.screening-select.select-3'), 260);
	                    			break;
	                    		case '4' ://环境分配ed---//审核通过
	                    			the.screeningSelect($('.screening-select.select-5'), 520);
	                    			break;
	                    		case '5' ://审核失败
	                    			var applyRejectReason = data.applyRejectReason;
	                    			$('#rejectReason').html(applyRejectReason);
	                    			$('#failPanel').show();
	                    			$('#status_final > a').html('审核失败');
	                    			the.screeningSelect($('.screening-select.select-5'), 520);
	                    			break;
	                    		default :
	                    			$('#rejectReason').html('无法提交申请，请联系系统管理员！');
	                    			$('#failPanel > h5').html('<b>系统提示</b>');
	                				$('#failPanel').show();
	                    			the.screeningSelect($('.screening-select.select-1'), 0);
	                    			break;
     					  	}
     						
     					} 
             		});
                	
                	$('#ajax').modal('show');
                
                });
            });
        },
        
        
        screeningSelect : function (_parent, _postX){
//    		var _postX = _parent.position().left;
//        	var _postX=130;
    		_parent.siblings(".screening-select").removeClass("current");
    		_parent.addClass("current");
    		_parent.siblings(".project-screening-yellow").animate({ width: _postX }, 300);
    		_parent.siblings(".select-1-yellow").animate({ left: _postX - 5 }, 300);
    		_parent.prevAll(".screening-select").css("background", "none");
    		_parent.nextAll().removeAttr("style");
        },
        
        setAjaxParam: function(name, value) {
            ajaxParams[name] = value;
        },

        addAjaxParam: function(name, value) {
            if (!ajaxParams[name]) {
                ajaxParams[name] = [];
            }

            skip = false;
            for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
                if (ajaxParams[name][i] === value) {
                    skip = true;
                }
            }

            if (skip === false) {
                ajaxParams[name].push(value);
            }
        },

        clearAjaxParams: function(name, value) {
            ajaxParams = {};
        },

        getDataTable: function() {
            return dataTable;
        },

        getTableWrapper: function() {
            return tableWrapper;
        },

        gettableContainer: function() {
            return tableContainer;
        },

        getTable: function() {
            return table;
        }

    };

};