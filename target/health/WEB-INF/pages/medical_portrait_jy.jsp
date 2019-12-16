<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入院途径_病情</title>

<link rel="shortcut icon" href="images/bitbug_favicon.ico" type="image/x-icon" /> 
<script src="resources/js/echarts-all.js"></script>
<script type="text/javascript" src="resources/js/jquery-2.1.1.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrap-datepicker/css/datepicker3.css" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="resources/plugins/font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" href="resources/css/ace.min.css"/>
<link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
<link rel="stylesheet" href="resources/css/tag_analyser.css"/>



<link href="resources/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/css/ace.min.css" />
<link href="resources/css/layout.css" rel="stylesheet" type="text/css"/>	
<script src="resources/js/layout.js" type="text/javascript"></script>

</head>

<body class="body">
	<div class="container" style="margin-top: 20px;">
		<!-- 检查分析 -->
		<div id="part_3_1" class="row row_p target_sub">
			<div class="col-md-12">
				<div class="s_p_p1">
					<div id="report_echarts_3_1" class="echart_size_1"></div>
				</div>
			</div>
		</div>
		<div class="row row_line"></div>
		<div id="part_3_2" class="row row_p target_sub">
			<div class="col-md-12">
				<div>
					<div id="report_echarts_3_2" class="main echart_size_1"
						style='width: 40%; float: left; margin-right: 0; padding-right: 0; border-right-width: 0'></div>
					<div id="report_echarts_3_3" class="main echart_size_1"
						style='width: 60%; margin-left: 0; padding-left: 0; border-left-width: 0'></div>
				</div>
			</div>
		</div>
		<div class="row row_line"></div>
		<div id="part_3_3" class="row row_p target_sub">
			<div class="col-md-12">
				<div>
					<div id="report_echarts_3_4" class="main echart_size_1"
						style='width: 40%; float: left; margin-right: 0; padding-right: 0; border-right-width: 0'></div>
					<div id="report_echarts_3_5" class="main echart_size_1"
						style='width: 60%; margin-left: 0; padding-left: 0; border-left-width: 0'></div>
				</div>
			</div>
		</div>
	</div>
<script>
var myChart_3_1 = echarts.init(document.getElementById('report_echarts_3_1'));
myChart_3_1.showLoading({
	text : "图表数据正在努力加载..."
});
$.ajax({
	type : "get",// 请求方式
	url : "/lables/analyse/jy",// 发送请求地址
	data : {
	},
	dataType : "json",
	success : function(data) {
		if (data) {
			var option_3_1 = {
				title : {
					text : '检查项目情况'
				},
				tooltip : {
					trigger : 'axis'
				},
				watermark : {
					x : 50,
					y : 150,
					row : 1,
					column : 2,
					show : true,
					text : '中国卒中数据中心',
					textStyle : {
						fontSize : 44,
						fontWeight : 'bolder',
						fontStyle : 'italic',
						color : '#DCDCDC'
					}
				},
				toolbox : {
					show : true,
					feature : {
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					axisLabel : {
						interval : 0,
						rotate : 20,
						margin : 2,
						textStyle : {
							color : "#222"
						}
					},
					data : data['xAxis']
				} ],
				yAxis : [ {
					type : 'value',
					splitNumber : '10',
					axisLabel : {
						show : true,
						interval : 'auto',
						formatter : '{value}'
					},
					name : "病案数"
				} ],
				series : [ {
					type : 'bar',
					barWidth : 18,
					itemStyle : {
						normal : {
							label : {
								show : true,
								position : 'top',
								formatter : '{c}'
							}
						}
					},
					data : data['value']
				} ]
			};
			myChart_3_1.hideLoading();
			myChart_3_1.setOption(option_3_1);
			// $('#report_tootip_3_1').html(data['text'][0]);
		}
	}
});

var myChart_3_2 = echarts.init(document.getElementById('report_echarts_3_2'));
myChart_3_2.showLoading({
	text : "图表数据正在努力加载..."
});
var myChart_3_3 = echarts.init(document.getElementById('report_echarts_3_3'));
myChart_3_3.showLoading({
	text : "图表数据正在努力加载..."
});

var myChart_3_4 = echarts.init(document.getElementById('report_echarts_3_4'));
myChart_3_4.showLoading({
	text : "图表数据正在努力加载..."
});
var myChart_3_5 = echarts.init(document.getElementById('report_echarts_3_5'));
myChart_3_5.showLoading({
	text : "图表数据正在努力加载..."
});

$.ajax({
	type : "get",// 请求方式
	url : "/lables/analyse/jyhx",// 发送请求地址
	data : {
	},
	dataType : "json",
	success : function(data) {
		if (data) {
			var pie_data = [];
			var line_data = [];
			var axis = data['zd_key'];
			var zd = data['zd_val'];
			for (var i = 0; i < zd.length; i++) {
				var pie_obj = {
					value : zd[i],
					name : axis[i]
				};
				pie_data.push(pie_obj);

				var line_obj = {
					name : axis[i],
					type:'bar',
					barWidth : 12,
		            stack: '总量',
					data : data[axis[i]]
				};
				line_data.push(line_obj);
			}
			
			var option_3_2 = {
				title : {
					text : '卒中相关检查项目',
					subtext : '病种画像',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'horizontal',
					y : 'bottom',
					data : data['zd_key']
				},
				watermark : {
					x : 50,
					y : 150,
					row : 1,
					column : 1,
					show : true,
					text : '中国卒中数据中心',
					textStyle : {
						fontSize : 44,
						fontWeight : 'bolder',
						fontStyle : 'italic',
						color : '#DCDCDC'
					}
				},
				calculable : true,
				series : [ {
					name : '访问来源',
					type : 'pie',
					radius : '50%',
					center : [ '50%', 200 ],
					data : pie_data
				} ]
			};

			var option_3_3 = {
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'shadow'
					}
				},
				legend : {
					data : data['zd_key']
				},
				toolbox : {
					show : true,
					orient : 'vertical',
					y : 'center',
					feature : {
						magicType : {
							show : true,
							type : [ 'line', 'bar', 'stack', 'tiled' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				watermark : {
					x : 50,
					y : 150,
					row : 1,
					column : 1,
					show : true,
					text : '中国卒中数据中心',
					textStyle : {
						fontSize : 44,
						fontWeight : 'bolder',
						fontStyle : 'italic',
						color : '#DCDCDC'
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					axisLabel : {
						interval : 0,
						rotate : 20,
						margin : 2,
						textStyle : {
							color : "#222"
						}
					},
					data : data['ss_key']
				} ],
				yAxis : [ {
					type : 'value',
					splitArea : {
						show : true
					}
				} ],
				grid : {
					x2 : 40
				},
				series : line_data
			};

			myChart_3_2.hideLoading();
			myChart_3_2.setOption(option_3_2);

			myChart_3_3.hideLoading();
			myChart_3_3.setOption(option_3_3);

			myChart_3_2.connect(myChart_3_3);
			myChart_3_3.connect(myChart_3_2);
			
			/////////////////////////////////////////////
			var pie_data2 = [];
			var line_data2 = [];
			var axis2 = data['ss_key'];
			var zd2 = data['ss_val'];
			for (var i = 0; i < zd2.length; i++) {
				var pie_obj = {
					value : zd2[i],
					name : axis2[i]
				};
				pie_data2.push(pie_obj);

				var line_obj = {
					name : axis2[i],
					type:'bar',
					barWidth : 12,
		            stack: '总量',
					data : data[axis2[i]]
				};
				line_data2.push(line_obj);
			}
			var option_3_4 = {
					title : {
						text : '卒中相关检查项目',
						subtext : '病种画像',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'horizontal',
						y : 'bottom',
						data : data['ss_key']
					},
					watermark : {
						x : 50,
						y : 150,
						row : 1,
						column : 1,
						show : true,
						text : '中国卒中数据中心',
						textStyle : {
							fontSize : 44,
							fontWeight : 'bolder',
							fontStyle : 'italic',
							color : '#DCDCDC'
						}
					},
					calculable : true,
					series : [ {
						name : '访问来源',
						type : 'pie',
						radius : '50%',
						center : [ '50%', 200 ],
						data : pie_data2
					} ]
				};

				var option_3_5 = {
					tooltip : {
						trigger : 'axis',
						axisPointer : {
							type : 'shadow'
						}
					},
					legend : {
						data : data['ss_key']
					},
					toolbox : {
						show : true,
						orient : 'vertical',
						y : 'center',
						feature : {
							magicType : {
								show : true,
								type : [ 'line', 'bar', 'stack', 'tiled' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					watermark : {
						x : 50,
						y : 150,
						row : 1,
						column : 1,
						show : true,
						text : '中国卒中数据中心',
						textStyle : {
							fontSize : 44,
							fontWeight : 'bolder',
							fontStyle : 'italic',
							color : '#DCDCDC'
						}
					},
					calculable : true,
					xAxis : [ {
						type : 'category',
						axisLabel : {
							interval : 0,
							rotate : 20,
							margin : 2,
							textStyle : {
								color : "#222"
							}
						},
						data : data['zd_key']
					} ],
					yAxis : [ {
						type : 'value',
						splitArea : {
							show : true
						}
					} ],
					grid : {
						x2 : 40
					},
					series : line_data2
				};

				myChart_3_4.hideLoading();
				myChart_3_4.setOption(option_3_4);

				myChart_3_5.hideLoading();
				myChart_3_5.setOption(option_3_5);

				myChart_3_4.connect(myChart_3_5);
				myChart_3_5.connect(myChart_3_4);
		}
	}
});

window.onresize = function() {	
	myChart_3_1.resize();
	myChart_3_2.resize();
	myChart_3_3.resize();
	myChart_3_4.resize();
	myChart_3_5.resize();
}
</script>         
    
</body>
</html>