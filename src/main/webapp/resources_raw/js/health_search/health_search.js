$('#myTab a').click(function(e) {
	e.preventDefault();// 阻止a链接的跳转行为
	$(this).tab('show');// 显示当前选中的链接及关联的content
})

var max_query_term_count = 4;

function createindex() {
	$.ajax({
		type : "get",// 请求方式
		url : "createEsIndex",// 发送请求地址
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			console.log(data)
			alert(data.info)
		}
	});
}


//全文检索
function fulltextSearch() {
	var pagefrom = 0;
	var pagesize = $('#home_page').val();
	var content = encodeURI( $("#kw").val())
	var dbtype = '';
	
	var checkbox1 = $("#Checkbox1");
	if (checkbox1.checked == true) {
		dbtype += '1';
	} else {
		dbtype += '0';
	}
	var checkbox2 = $("#Checkbox2");
	if (checkbox2.checked == true) {
		dbtype += '1';
	} else {
		dbtype += '0';
	}

	$('#note_tab')[0].style.display = "block";
	$('#note_page')[0].style.display = "block";
	query_page_fulltext(content, dbtype, pagefrom, pagesize);
}

//全文检索-查询函数
function query_page_fulltext(content, dbtype, pagefrom, pagesize) {
	$.ajax({
		type : "get",// 请求方式
		url : "fulltextSearch",// 发送请求地址
		dataType : "json",
		data : {
			//content : encodeURI(encodeURI($("#kw").val())),
			content :content,
			dbtype : dbtype,
			pagefrom : pagefrom*parseInt(pagesize),
			pagesize : pagesize
		},
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			var pagetotal = Math.min(10000, parseInt(data.total));
			var pagecount = Math.max(1, Math.ceil(parseInt(pagetotal) / parseInt(pagesize)));
			currentPage = parseInt(pagefrom) + 1;
			var options = {
				currentPage : currentPage,
				totalPages : pagecount,
				shouldShowPage : function(type, page, current) {
					switch (type) {
						case "first":
							return true;
						case "prev":
							return true;
						case "next":
							return true;
						case "last":
							return false;
						case "page":
							return true;
					}
				},
				itemTexts : function(type, page, current) {
					switch (type) {
					case "first":
						return "首页";
					case "prev":
						return "上一页";
					case "next":
						return "下一页";
					case "last":
						return "末页";
					case "page":
						return page;
					}
				},
				onPageChanged : function(e, oldPage, newPage) {
					pagefrom = parseInt(newPage) - 1;
					query_page_fulltext(content, dbtype, pagefrom, pagesize);
				}
			}
			$('#note_page').bootstrapPaginator(options);

			var text = '共检索到<strong><font color=\"blue\">' + data.total
					+ '份</font></strong>病历';
			$("#abstract").html(text);
			showMedical(data.lists);
		}
	});
}



//相似度检索
function similaritysearch() {
	var pagefrom = 0;
	var pagesize = $('#home_page').val();
	var content = encodeURI( $("#kw").val())
	var dbtype = '';//检索数据库内容（住院数据、筛查数据）
	
	var checkbox1 = $("#Checkbox1");
	if (checkbox1.checked == true) {
		dbtype += '1';
	} else {
		dbtype += '0';
	}
	var checkbox2 = $("#Checkbox2");
	if (checkbox2.checked == true) {
		dbtype += '1';
	} else {
		dbtype += '0';
	}

	$('#note_tab')[0].style.display = "block";
	$('#note_page')[0].style.display = "block";
	query_page_similarity(content, dbtype, pagefrom, pagesize);
}


//相似度检索-查询函数
function query_page_similarity(content, dbtype, pagefrom, pagesize) {
	$.ajax({
		type : "get",// 请求方式
		url : "similaritySearch",// 发送请求地址
		dataType : "json",
		data : {
			//content : encodeURI(encodeURI($("#kw").val())),
			content :encodeURI( $("#similarityInput").val()),
			dbtype : dbtype,
			pagefrom : pagefrom*parseInt(pagesize),
			pagesize : pagesize
		},
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		success : function(data) {
			var pagetotal = Math.min(10000, parseInt(data.total));
			var pagecount = Math.max(1, Math.ceil(parseInt(pagetotal) / parseInt(pagesize)));
			currentPage = parseInt(pagefrom) + 1;
			var options = {
				currentPage : currentPage,
				totalPages : pagecount,
				itemTexts : function(type, page, current) {
					switch (type) {
					case "first":
						return "首页";
					case "prev":
						return "上一页";
					case "next":
						return "下一页";
					case "last":
						return "末页";
					case "page":
						return page;
					}
				},
				onPageChanged : function(e, oldPage, newPage) {
					pagefrom = parseInt(newPage) - 1;
					query_page_fulltext(content, dbtype, pagefrom, pagesize);
				}
			}
			$('#note_page').bootstrapPaginator(options);

			var text = '共检索到<strong><font color=\"blue\">' + data.total
					+ '份</font></strong>病历';
			$("#abstract").html(text);
			showMedical(data.lists);
		}
	});
}




//展示查询结果
function showMedical(data) {
	$("#tmedical>tbody").html('');
	var text = "";
	for (var i = 0; i < data.length; i++) {
		var subtext = '';
		if (data[i]._type == 'inpatient') {
			subtext = show_inpatient(data[i]);
		} else {
			subtext = show_screening(data[i]);
		}
		text += subtext;
	}
	$("#content_item").html(text);
}

//显示住院信息结果
function show_inpatient(data) {
	var rowtext = '';
	rowtext += '<tr>';
	rowtext += '<td><input type="checkbox" name="checkItem" /></td>';
	rowtext += '<td>' + data.zyh + '</td>';
	rowtext += '<td>' + sex_format(data.xb) + '</td>';
	rowtext += '<td>' + data.nl + '</td>';
	rowtext += '<td>' + data.cyzdbm + '</td>';
	rowtext += '<td><button class="btn btn-xs btn-primary" onclick=open_label_url('
			+ data.zyh + ')>查看</button></td>';
	rowtext += '<td><button class="btn btn-xs btn-primary" onclick=open_portrait_url('
			+ data.zyh + ')>查看</button></td>';
	rowtext += '</tr>';
	$("#tmedical>tbody").append(rowtext);

	var text = "";
	text += "<div class='row'>";
	// 住院号
	text += '';
	text += '<h3 class="t c-gap-bottom-small"><input type="checkbox">&nbsp;&nbsp;住院号';
	text += '<a href="/medical_record?zyh=' + data.zyh
			+ '" target="_blank">';
	text += data.zyh;
	text += '</a></h3>';
	// 摘要显示
	text += "<div>姓名：" + data.xm + "，性别：" + sex_format(data.xb) + "，年龄："
			+ data.nl + "，医院：" + data.dwbm + "，科室：" + data.rykbmc + "，主要诊断："
			+ data.cyzdbm + "，转归：" + data.lyfsmc + "</div>";

	// 影像结果
	var image = data.yxlgs[0];
	if (image == undefined) {
		image = '';
	}
	text += "<ul>";
	text += "<li>影像结果：" + image + "</li>";
	// 手术
	var operation = data.ssczmcs[0];
	if (operation == undefined) {
		operation = '';
	}
	text += "<li>手术操作：" + operation + "</li>";
	// 检验
	var lab = data.jybgbdmcs[0];
	if (lab == undefined) {
		lab = '';
	}
	text += "<li>检查项目：" + lab + "</li>";
	// 入院记录
	var ryzs = data.inrecord.ryzs;
	if (ryzs == undefined) {
		ryzs = '';
	}
	var jws = data.inrecord.jws;
	if (jws == undefined) {
		jws = '';
	}
	var grs = data.inrecord.grs;
	if (grs == undefined) {
		grs = '';
	}
	text += "<li>入院记录：<ol><li>主诉：" + ryzs + "</li><li>既往史：" + jws
			+ "</li><li>个人史：" + grs + "</li></ol></div>";
	text += "</li>";
	text += "</ul>";
	return text;
}

//显示筛查信息结果
function show_screening(data) {
	var rowtext = '';
	rowtext += '<tr>';
	rowtext += '<td><input type="checkbox" name="checkItem" /></td>';
	rowtext += '<td>' + data.acid + '</td>';
	rowtext += '<td></td>';
	rowtext += '<td></td>';
	rowtext += '<td></td>';
	rowtext += '<td><button class="btn btn-xs btn-primary" onclick=open_label_url('
			+ data.acid + ')>查看</button></td>';
	rowtext += '<td><button class="btn btn-xs btn-primary" onclick=open_portrait_url('
			+ data.acid + ')>查看</button></td>';
	rowtext += '</tr>';
	$("#tmedical>tbody").append(rowtext);

	var text = "";
	text += "<div class='row'>";
	// 住院号
	text += '';
	text += '<h3 class="t c-gap-bottom-small"><input type="checkbox">&nbsp;&nbsp;病案号';
	text += '<a href="/medical_portrait?zyh=' + data.acid
			+ '" target="_blank">';
	text += data.acid;
	text += '</a></h3>';
	// 摘要显示
	text += "<div>筛查单位：" + data.uuName + "，评分：" + data.dfStatus + "</div>";
	text += "<div>既往史：" + data.dfStroke + "，家族史：" + data.dfStrokeFamily
			+ "，TIA：" + data.dfTia + "，心脏：" + data.dfAF + "</div>";
	text += "<div>血压：" + data.dfHypertension + "，血脂：" + data.dfLDL + "，血糖："
			+ data.dfGlycuresis + "</div>";
	text += "<div>生活：" + data.dfSmoking + "，锻炼：" + data.dfSportsLack + "，体重："
			+ data.dfOverweight + "</div>";
	text += "</div>"
	return text;
}



function sex_format(code) {
	if (code == '<span style="color:red">1</span>') {
		return '<span style="color:red">男</span>'
	} else if (code == '<span style="color:red">2</span>') {
		return '<span style="color:red">女</span>'
	} else if (code == '1') {
		return '男'
	} else {
		return '女'
	}
}

function addQueryTerm() {
	var index = getQueryTermCount();
	console.log(index)
	if (index < max_query_term_count) {
		var divid = 'query_term_' + index;
		var text = '<div id="' + divid
				+ '" class="row" style="margin-top: 5px;">'
		text += '		<div class="col-md-1"></div>'
		text += '		<div class="col-md-9">'
		text += '			<select name="select" id="es_type_' + index
				+ '" class="" style="width: 10%;">'
		text += '				<option value="dwbm">医院</option>'
		text += '				<option value="rykbmc">科室</option>'
		text += '				<option value="cyzdbm">诊断</option>'
		text += '				<option value="lyfsmc">转归</option>'
		text += '				<option value="yxlgs">影像</option>'
		text += '				<option value="ssczmcs">手术</option>'
		text += '				<option value="jybgbdmcs">检验</option>'
		text += '				<option value="inrecord.ryzs">主诉</option>'
		text += '				<option value="inrecord.jws">既往史</option>'
		text += '				<option value="inrecord.grs">个人史</option>'
		text += '			</select> <select name="select" id="es_match_' + index
				+ '" class="" style="width: 10%;">'
		text += '				<option value="0">模糊</option>'
		text += '				<option value="1">精确</option>'
		text += '			</select> <input id="es_value_' + index
				+ '" name="es_value_' + index + '" value="" autocomplete="off"'
		text += '				style="width: 68%;"> <select name="select"'
		text += '				id="es_condition_' + index
				+ '" class="" style="width: 10%;">'
		text += '				<option value="0">与</option>'
		text += '				<option value="1">或</option>'
		text += '				<option value="2">非</option>'
		text += '			</select>'
		text += '		</div>'
		text += '	</div>'
		text += '</div>'
		$('#query_div').append(text);
	}
}

function delQueryTerm() {
	var index = getQueryTermCount();
	if (index > 0) {
		var divid = 'query_term_' + parseInt(index - 1);
		$('#' + divid).remove();
	}
}


//高级检索
function advancedSearch() {
	var es_age = $('#home_age').val();
	var es_sex = $('#home_sex').val();
	var es_zd = $('#home_zd').val();
	var es_zs = $('#home_zs').val();

	$.ajax({
		type : "get",// 请求方式
		url : "advancedSearch",// 发送请求地址
		dataType : "json",
		data : {
			es_age : es_age,
			es_sex : es_sex,
			es_zd : es_zd,
			es_zs : es_zs
		},
		success : function(data) {
			var text = '<div>共检索到<strong><font color=\"blue\">' + data.total
					+ '份</font></strong>病历</div>';
			$("#abstract").html(text);
			showMedical(data.lists);
		}
	});
}



//多内容检索
function multiTermSearch() {
	$('#note_tab')[0].style.display = "block";
	$('#note_page')[0].style.display = "block";

	var es_query = {};
	es_query.age = $('#es_age').val();
	es_query.sex = $('#es_sex').val();
	es_query.pagesize = $('#home_page').val();
	es_query.pagefrom = 0;

	var itemArray = [];
	var es_item = {};
	es_item.type = $('#es_type').val();
	es_item.match = $('#es_match').val();
	es_item.value = $('#es_value').val();
	if ($.trim(es_item.value) == "") {
		alert('搜索条件不能为空');
		$('#es_value').focus();
		return false;
	}
	es_item.condition = $('#es_condition').val();
	itemArray.push(es_item);
	var count = getQueryTermCount();
	for (var i = 0; i < count; i++) {
		var es_item = {};
		es_item.type = $('#es_type_' + i).val();
		es_item.match = $('#es_match_' + i).val();
		es_item.value = $('#es_value_' + i).val();
		if ($.trim(es_item.value) == "") {
			alert('搜索条件不能为空');
			$('#es_value_' + i).focus();
			return false;
		}
		es_item.condition = $('#es_condition_' + i).val();
		itemArray.push(es_item);
	}
	es_query.items = itemArray;
	query_page_multiTerm(es_query, 0);
}


//多内容检索-查询函数
function query_page_multiTerm(es_query, pageindex) {
	$.ajax({
		type : "POST",// 请求方式
		url : "multiTermSearch",// 发送请求地址
		contentType : 'application/json',
		dataType : "json",
		data : JSON.stringify(es_query),
		success : function(data) {
			var pagetotal = Math.min(10000, parseInt(data.total));
			var pagecount = Math.max(1, Math.ceil(parseInt(pagetotal) / parseInt(es_query.pagesize)));
			var options = {
				currentPage : parseInt(pageindex) + 1,
				totalPages : pagecount,
				itemTexts : function(type, page, current) {
					switch (type) {
					case "first":
						return "首页";
					case "prev":
						return "上一页";
					case "next":
						return "下一页";
					case "last":
						return "末页";
					case "page":
						return page;
					}
				},
				onPageChanged : function(e, oldPage, newPage) {
					pageindex = parseInt(newPage) - 1;
					es_query.pagefrom = parseInt(pageindex)*parseInt(es_query.pagesize);
					query_page_multiTerm(es_query, pageindex);
				}
			}
			$('#note_page').bootstrapPaginator(options);

			var text = '共检索到<strong><font color=\"blue\">' + data.total
					+ '份</font></strong>病历';
			$("#abstract").html(text);
			showMedical(data.lists);
		}
	});
}


//获取高级查询条件数量
function getQueryTermCount() {
	var index = 0;
	var flag = true;
	for (var i = 0; i < max_query_term_count; i++) {
		var termdiv = $("#query_term_" + i)[0];
		if (termdiv == null) {
			index = i;
			flag = false;
			break;
		}
	}
	if (flag) {
		index = max_query_term_count;
	}
	return index;
}


//结果展示-详细信息
function showItemlist() {
	$('#content_item')[0].style.display = "block";
	$('#content_summary')[0].style.display = "none";
	$('#show_item')[0].setAttribute("class", "btn btn-xs btn-info");
	$('#show_summary')[0].setAttribute("class", "btn btn-xs btn-default");
}

//结果展示-表格信息
function showSummary() {
	$('#content_summary')[0].style.display = "block";
	$('#content_item')[0].style.display = "none";
	$('#show_summary')[0].setAttribute("class", "btn btn-xs btn-info");
	$('#show_item')[0].setAttribute("class", "btn btn-xs btn-default");
}


function open_label_url(zyhid) {
	window.open('/medical_portrait?zyh=' + zyhid)
}



function open_portrait_url(zyhid) {
	window.open('/medical_record?zyh=' + zyhid)
}


//开启医生反馈div
function open_feedback_div() {
	var feedback_div_status = $('#doctor_feedback')[0].style.display;
	if(feedback_div_status == "block"){
		$('#doctor_feedback')[0].style.display = "none";
	}else{
		$('#doctor_feedback')[0].style.display = "block";
	}
}

//提交医生反馈
function feed_back_commit(){
	swal("医生反馈提交成功！","", "success");
	$('#doctor_feedback')[0].style.display = "none";
}


//放弃编辑医生反馈
function feed_back_cancle(){
	$('#doctor_feedback')[0].style.display = "none";
}

//开启医生反馈模态框（不用了，冲突）
function open_feedback_window() {
	$('#feedback_modal').modal('show');
	swal(
		{
		  title: "<small><strong>相似度医生反馈</strong></small>",
		  text: "请填写<strong>建议评分</strong>和<strong>相关说明</strong>" +
		  		"<br>" +
		  		'<input id="feed_input" placeholder="请输入反馈相关说明" />',
		  type: "input",
		  showCancelButton: true,
		  cancelButtonText:"取消",
		  closeOnConfirm: false,
		  animation: "slide-from-top",
		  inputPlaceholder: "请输入建议评分(1-10)",
		  html: true
		},
		
		function(inputValue){
		  if (inputValue === false) return false;
		  
		  if (inputValue === "") {
		    swal.showInputError("您的填写为空，请补充反馈信息！");
		    return false
		  }
		  
		  swal("谢谢!", "您输入了: [" + inputValue+"]", "success");
		}
		
	)
}