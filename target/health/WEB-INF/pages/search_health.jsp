<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<html>
<!-- BEGIN HEAD -->
<head>
<title>病案检索</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="resources/css/bootstrap-combined.min.css" rel="stylesheet">
<link href="resources/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="resources/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="resources/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="resources/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="resources/css/layout.css" rel="stylesheet" type="text/css" />
<link id="style_color" href="resources/css/themes/light2.css"
	rel="stylesheet" type="text/css" />
	<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
	
<style type="text/css">
ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

ul li {
	display: block;
	padding: 0;
	margin: 0;
}

#search {
	border: 1px solid #518bcb;
	margin-bottom: 13px;
}

#search table {
	border: 0;
	padding: 0;
	margin: 0;
}

#search table tr td {
	vertical-align: top;
}

#search #left {
	width: 160px;
	border-right: 1px solid #a4c7dd;
	padding: 5px 10px 10px 10px;
	background: #f0f5fb;
}

#search #right {
	width: 821px;
	padding: 5px 10px 10px 10px;
	position: relative;
}

#search ul {
	list-style: none;
	line-height: 180%;
}

#search ul li span {
	width: 80px;
	float: left;
}

#seacrh_ul {
	background-color: #518bcb;
	height: 25px;
}

#seacrh_ul li {
	width: 108px;
	height: 25px;
	line-height: 190%;
	text-align: center;
	float: left;
}

#search h5 {
	font-size: 13px;
	text-align: center;
}

/***
Notes
***/

/* Common styles for all types */
.note {
	margin: 0 0 20px 0;
	padding: 15px 30px 15px 15px;
	border-left: 5px solid #eee;
}

.note h1, .note h2, .note h3, .note h4 {
	margin-top: 0;
}

.note p:last-child {
	margin-bottom: 0;
}

.note code, .note .highlight {
	background-color: #fff;
}

/* Variations */
.note-danger {
	background-color: #FAEAE6;
	border-color: #ed4e2a;
}

.note-warning {
	background-color: #FCF3E1;
	border-color: #fcb322;
}

.note-info {
	background-color: #E8F6FC;
	border-color: #57b5e3;
}

.note-success {
	background-color: #EBFCEE;
	border-color: #3cc051;
}
</style>

</head>
<!-- END HEAD -->

<body class="gray-bg">

	<div class="clearfix"></div>

	<!-- BEGIN CONTAINER -->
	<div style="margin-left: 50px; margin-right: 50px">

		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
			<div class="col-md-12">
			
				<!-- search part start-->
				<div id="search" style="margin-top: 30px;">
					<table class="table">
						<tbody>
							<tr>
								<td id="left">
									<h5>选择数据库</h5>
									<ul id="checkboxDB">
										<li class="checkbox"><label class="checkbox-inline"><input
												type="checkbox" id="Checkbox1" checked="checked"
												value="inpatient" disabled="disabled">住院数据</label></li>
										<li class="checkbox"><label class="checkbox-inline"><input
												type="checkbox" id="Checkbox2" checked="checked"
												value="screening" disabled="disabled">筛查数据</label></li>
									</ul>
								</td>
								<td id="right">
									<ul class="nav nav-tabs" id="myTab">
										<li role="presentation" class="active" id="tab_fulltext"><a
											href="#fulltext">全文检索</a></li>
										<li role="presentation" id="tag_advancedsearch"><a
											href="#advancedsearch">高级查询</a></li>
										<li role="presentation" id="tag_similaritysearch"><a
											href="#similaritysearch">相似度检索</a></li>
									</ul>

									<div class="tab-content" style="margin-top: 20px;">

										<div role="tabpanel" class="tab-pane fade active in"
											id="fulltext">
											<div class="row">
												<!-- 					<div class="col-lg-1"></div> -->
												<div class="col-md-10">
													<div class="input-group">
														<input id="kw" name="wd" type="text" class="form-control"
															placeholder="请输入待检索关键词"> <span
															class="input-group-btn"> <a
															class="btn btn-primary" href="javascript:;"
															onclick="fulltextSearch()"><i class="fa fa-search"></i>
																综合搜索</a>
														</span>
													</div>
													<!-- /input-group -->
												</div>
												<div class="col-md-2"></div>
											</div>
											<!-- /.row -->
										</div>

										<div role="tabpanel" class="tab-pane fade" id="advancedsearch">
											<div id="query_div">
												<div class="row">
													<div class="col-md-1">
														<a class="btn btn-info btn-xs" href="javascript:;"
															onclick="addQueryTerm()"><i class="fa fa-plus"></i></a> <a
															class="btn btn-info btn-xs" href="javascript:;"
															onclick="delQueryTerm()"><i class="fa fa-minus"></i></a>
													</div>
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />
													<div class="col-md-9">
														<select name="select" id="es_type" class=""
															style="width: 10%;">
															<option value="dwbm">医院</option>
															<option value="rykbmc">科室</option>
															<option value="cyzdbm">诊断</option>
															<option value="lyfsmc">转归</option>
															<option value="yxlgs">影像</option>
															<option value="ssczmcs">手术</option>
															<option value="jybgbdmcs">检验</option>
															<option value="inrecord.ryzs">主诉</option>
															<option value="inrecord.jws">既往史</option>
															<option value="inrecord.grs">个人史</option>
														</select> <select name="select" id="es_match" class=""
															style="width: 10%;">
															<option value="0">模糊</option>
															<option value="1">精确</option>
														</select> <input id="es_value" name="es_value" value=""
															autocomplete="off" style="width: 68%;"> <select
															name="select" id="es_condition" class=""
															style="width: 10%;">
															<option value="0">与</option>
															<option value="1">或</option>
															<option value="2">非</option>
														</select>
													</div>
												</div>
											</div>

											<div class="row" style="margin-top: 10px;">
												<div class="col-md-1"></div>
												<div class="col-md-9">
													<span class="text-primary">年龄</span> <select name="select"
														id="es_age" style="width: 10%;">
														<option value="0,200">不限</option>
														<option value="0,39">40以下</option>
														<option value="40,49">40-49</option>
														<option value="50,59">50-59</option>
														<option value="60,69">60-69</option>
														<option value="60,79">60-79</option>
														<option value="80,200">80以上</option>
													</select> <span>&nbsp;&nbsp;</span> <span class="text-primary">性别</span>
													<select name="select" id="es_sex" class=""
														style="width: 10%;">
														<option value="0">不限</option>
														<option value="1">男</option>
														<option value="2">女</option>
													</select> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> <a
														class="btn btn-primary btn-sm" href="javascript:;"
														onclick="multiTermSearch()"><i class="fa fa-search"></i>
														高级搜索</a>
												</div>
											</div>
											<!-- 				<div style="margin-top: 50px;"></div> -->

											<div style="display: none">
												<div class="row">
													<div class="col-md-2">
														<span class="text-primary">年龄</span> <select name="select"
															id="home_age">
															<option value="0,200">不限</option>
															<option value="0,39">40以下</option>
															<option value="40,49">40-49</option>
															<option value="50,59">50-59</option>
															<option value="60,69">60-69</option>
															<option value="60,79">60-79</option>
															<option value="80,200">80以上</option>
														</select>
													</div>
													<div class="col-md-2">
														<span class="text-primary">性别</span> <select name="select"
															id="home_sex" class="">
															<option value="0">不限</option>
															<option value="1">男</option>
															<option value="2">女</option>
														</select>
													</div>
													<div class="col-md-3">
														<span class="text-primary">诊断</span> <input id="home_zd"
															name="home_zd" value="" autocomplete="off">
													</div>
													<div class="col-md-3">
														<span class="text-primary">主诉</span> <input id="home_zs"
															name="home_zs" value="" autocomplete="off">
													</div>
													<div class="col-md-2 text-right">
														<a class="btn btn-info btn-sm" href="javascript:;"
															onclick="advancedSearch()"><i class="fa fa-search"></i>
															高级搜索</a>
													</div>
												</div>
											</div>
										</div>

										<!-- 开始相似度检索部分 -->
										<div role="tabpanel" class="tab-pane fade"
											id="similaritysearch">
											<div class="row">
												<div class="col-md-10">
													<div class="input-group">
														<input id="similarityInput"
															placeholder="请输入待检索病人ID"
															name="similarityInput" type="text" class="form-control">
														<span class="input-group-btn"> <a
															class="btn btn-primary" href="javascript:;"
															onclick="similaritysearch()"><i class="fa fa-search"></i>
																相似度查询</a>
														</span> <span> <a>&nbsp;</a>
														</span> <span class="input-group-btn"> <a
															class="btn btn-primary" href="javascript:;"
															onclick="open_feedback_div()"><i class="fa"></i>
																医生反馈</a>
														</span>
													</div>
													<!-- /input-group -->
												</div>
												<div class="col-md-2"></div>
											</div>
											<!-- /.row -->
										</div>
										<!-- 结束相似度检索部分 -->
									
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- search part end-->

				<!-- doctor feedback part start -->
				<div id="doctor_feedback" style="height:135px;display:none; margin-top: 10px;border: 1px solid #318bcb;">
					<table class="table">
						<tbody>
							<tr>
								<td id="doctor_feedback_1" align="center">
									<div class="col-md-12">
										<h5><strong>反馈说明</strong></h5>
										<p>1、反馈评分范围：1-10</p>
										<p>2、反馈建议需合理有效</p>
									</div>
								</td>
								<td id="doctor_feedback_2" align="center">
									<div class="col-md-12">
										<div class="input-group">
											<p><strong>反馈评分:</strong></p>
											<input id="feedback_score" type="text" class="form-control" placeholder="(1-10)"> 
										</div>
									</div>
								</td>
								<td id="doctor_feedback_3">
									<div class="col-md-12">
										<div class="input-group">
											<p><strong>反馈建议:</strong></p>
											<textarea id="feedback_input" class="form-control" style="width:500px;height:80px;" placeholder="请输入反馈建议内容"></textarea>
										</div>
									</div>
								</td>
								<td id="doctor_feedback_4">
									<div class="col-md-12" style="margin-top: 15px;">
											<span class="input-group-btn"> 
												<a class="btn btn-danger" href="javascript:;" onclick="feed_back_commit()">确定提交</a>
											</span>
											<br/>
											<span class="input-group-btn"> 
												<a class="btn btn-info" href="javascript:;" onclick="feed_back_cancle()">取消</a>
											</span>
									</div>
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
				<!-- doctor feedback part end-->
				

				<!-- result part start-->
				<div id="result">
					<hr>
					<div id="note_tab" class="note note-info" style="display: none;">
						<span>显示模式：</span> 
						<a id="show_item" class="btn btn-xs btn-default" href="javascript:;" onclick="showItemlist()">
							<i class="fa fa-list"></i>
						</a>
						<a id="show_summary" class="btn btn-xs btn-info" href="javascript:;" onclick="showSummary()">
							<i class="fa fa-list-alt"></i>
						</a> &nbsp;&nbsp;
						<span id="abstract"></span>
						<span class="pull-right">每页显示<select name="select"
							id="home_page">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="50">50</option>
						</select></span>
					</div>
					<div id="content_item" style="display: none;"></div>
					
					<div id="content_summary" style="display: block;">
						<table id="tmedical" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th><input type="checkbox" id="checkAll" name="checkAll" /></th>
									<th>病案号</th>
									<th>性别</th>
									<th>年龄</th>
									<th>主要诊断</th>
									<th>标签详情</th>
									<th>病历详情</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					
					<div id="note_page" style="display: none;"></div>
				</div>
				<!-- result part end-->
			</div>
		</div>


	</div>
	<!-- END CONTAINER -->

	<!-- BEGIN CORE PLUGINS -->
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="resources/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="resources/plugins/jquery-migrate.min.js" type="text/javascript"></script>
	<script src="resources/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="resources/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="resources/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script	src="resources/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="resources/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="resources/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="resources/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script	src="resources/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript" src="resources/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="resources/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="resources/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="resources/js/metronic.js" type="text/javascript"></script>
	<script src="resources/js/layout.js" type="text/javascript"></script>
	<script src="resources/js/quick-sidebar.js" type="text/javascript"></script>
	<script src="resources/js/demo.js" type="text/javascript"></script>
	<script src="resources/js/bootstrap-paginator.min.js" type="text/javascript"></script>
	<script src="resources/js/health_search/Datatable.js"></script>
	<script src="resources/js/health_search/searchDatatable.js"></script>
	<script src="resources/js/health_search/health_search.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core components
			searchDatatable.init();

			$("#ajax").draggable({
				handle : ".modal-header"
			});
		});
	</script>
	
</body>
<!-- END BODY -->
</html>