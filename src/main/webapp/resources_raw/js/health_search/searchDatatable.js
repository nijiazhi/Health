var searchDatatable = function () {

    var initPickers = function () {
        //init date pickers
        $('.date-picker').datepicker({
            rtl: Metronic.isRTL(),
            autoclose: true,
//            defaultDate : new Date()
        });
    }

    var handleProducts = function() {
        var applyDataGrid = new Datatable();

        applyDataGrid.init({
            src: $("#datatable_products"),
            onSuccess: function (applyDataGrid) {
                // execute some code after table records loaded
//            	console.log(applyDataGrid);
            	//applyDataGrid.setMotalClickEvent();
            },
            onError: function (applyDataGrid) {
                // execute some code on network or other general error  
            },
            loadingMessage: '加载中...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 

                // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
                // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/scripts/datatable.js). 
                // So when dropdowns used the scrollable div should be removed. 
                //"dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r>t<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>",

                "lengthMenu": [
                     [2,10, 20, 50, 100, 150, -1],
                     [2,10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                
                "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

                "ajax": {
                    "url": "/healthcare/applydata/getdocdata_user", // ajax source
                },
                "order": [
                    [1, "asc"]
                ] // set first column as a default sort by asc
            }
        });
        
        applyDataGrid.getDataTable().on( 'draw.dt', function (e, settings, data) {
        	//console.log(data);
//            console.log("hello");
//            console.log(data);
            applyDataGrid.setMotalClickEvent();
            applyDataGrid.setEditApplyDisable();
        } );
        
        //table-advanced-search button click --- 高级搜索工具
        applyDataGrid.getTableWrapper().on('click', '.table-advanced-search', function (e) { 
        	//是否隐藏
        	if( $('#filter_panel').is(":hidden")){
        		applyDataGrid.resetFilter();
        		$('#filter_panel').show();
        		$('.table-advanced-search').html('收起 <i class="fa fa-arrow-up"></i>');
        	}else{
        		$('#filter_panel').hide();
        		$('.table-advanced-search').html('<i class="fa fa-search"></i> 高级搜索 <i class="fa fa-arrow-down"></i>');
        		applyDataGrid.resetFilter();
        	}
        		
        });
        
        
        // handle group actionsubmit button click ---- 删除功能
        applyDataGrid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
        	if(!confirm('确定将这些记录删除?'))
        		return;
            e.preventDefault();
            if (applyDataGrid.getSelectedRowsCount() == 0){
            	 Metronic.alert({
                     type: 'danger',
                     icon: 'warning',
                     message: '没有要删除的选中记录',
                     container: applyDataGrid.getTableWrapper(),
                     place: 'prepend'
                 });
            }else{
            	$.ajax(delete_options={ 
    					type : "get",//请求方式 
    					url : "/healthcare/applydata/deleteapplydata",//发送请求地址
    					dataType : "json", 
    					data:{ 
    						id: applyDataGrid.getSelectedRows(),
    						deleteType: 'data'
    					}, 
    					success :function(data) {
//    						alert(data);
//    						console.log('applydata-->delete');
    						alert('您已经成功删除了所选数据  \n    序号 ： '+ applyDataGrid.getSelectedRows())
    						applyDataGrid.getDataTable().ajax.reload();
    					} 
    				});
            	//$.ajax(delete_options);
    		}
    		
         
//            applyDataGrid.setMotalClickEvent();
            
      /*      var action = $(".table-group-action-input", applyDataGrid.getTableWrapper());
            if (action.val() != "" && applyDataGrid.getSelectedRowsCount() > 0) {
                applyDataGrid.setAjaxParam("customActionType", "group_action");
                applyDataGrid.setAjaxParam("customActionName", action.val());
                applyDataGrid.setAjaxParam("id", applyDataGrid.getSelectedRows());
                applyDataGrid.getDataTable().ajax.reload();
                applyDataGrid.clearAjaxParams();
            } else if (action.val() == "") {
                Metronic.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'Please select an action',
                    container: applyDataGrid.getTableWrapper(),
                    place: 'prepend'
                });
            } else if (applyDataGrid.getSelectedRowsCount() == 0) {
                Metronic.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: '没有要删除的选中记录',
                    container: applyDataGrid.getTableWrapper(),
                    place: 'prepend'
                });
            }*/
            
        });
        
        $("#logoutbutton").click(function(){
        	$("#logoutform").submit();
        });
        
        $('#filter_panel').hide();
        
     
    }

    return {

        //main function to initiate the module
        init: function () {

            handleProducts();
            initPickers();
            
            
        }

    };
    
}();