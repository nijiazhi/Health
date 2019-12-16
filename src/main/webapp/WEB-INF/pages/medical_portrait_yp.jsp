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
		<!-- 用药分析 -->
		<div id="part_4_1" class="target_sub">
			<div class="row">
				<div class="col-md-12">
					<div id="report_echarts_4_1" class="main echart_size_1"></div>
				</div>
			</div>
			<div class="row row_line">
				<div class="col-md-12">
					<div>
						<div class="main echart_size_1">
							<div id="head"
								style="height: 8%; line-height: 35px; text-align: right;">
								<div>
									<span class="text-primary">药品分类</span> <select name="select"
										id="part1_yp" onchange="yp_fl(this)">
										<option value="1">降压药</option>
										<option value="2">降糖药</option>
										<option value="3">调脂药</option>
									</select>
								</div>
							</div>
							<div id="report_echarts_4_2" class="echart_size_1"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row row_line"></div>
		<div id="part_4_2" class="row row_p target_sub">
			<div class="col-md-12">
				<div>
					<div id="report_echarts_4_3" class="main echart_size_1"
						style='width: 40%; float: left; margin-right: 0; padding-right: 0; border-right-width: 0'></div>
					<div id="report_echarts_4_4" class="main echart_size_1"
						style='width: 60%; margin-left: 0; padding-left: 0; border-left-width: 0'></div>
				</div>
			</div>
		</div>
		<div class="row row_line"></div>
		<div id="part_4_3" class="row row_p target_sub">
			<div class="col-md-12">
				<div>
					<div id="report_echarts_4_5" class="main echart_size_1"
						style='width: 40%; float: left; margin-right: 0; padding-right: 0; border-right-width: 0'></div>
					<div id="report_echarts_4_6" class="main echart_size_1"
						style='width: 60%; margin-left: 0; padding-left: 0; border-left-width: 0'></div>
				</div>
			</div>
		</div>
	</div>
<script>
var myChart_4_1 = echarts.init(document.getElementById('report_echarts_4_1'));
myChart_4_1.showLoading({
	text : "图表数据正在努力加载..."
});
var myChart_4_2 = echarts.init(document.getElementById('report_echarts_4_2'));
myChart_4_2.showLoading({
	text : "图表数据正在努力加载..."
});
function yp_fl(obj) {
	var obj_name = document.getElementById('part1_yp');
	var name_text = obj_name.options[obj_name.selectedIndex].text;
	$.ajax({
		type : "get",// 请求方式
		url : "/lables/analyse/ypfl",// 发送请求地址
		data : {
			name : name_text
		},
		dataType : "json",
		success : function(data) {
			if (data) {
				// 指定图表的配置项和数据
				var pie_data = [];
				for (var i = 0; i < data['val_pie'].length; i++) {
					var pie_obj = {
						value : data['val_pie'][i],
						name : data['key_pie'][i]
					};
					pie_data.push(pie_obj);
				}
				var option_4_2 = {
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'horizontal',
						y : 'bottom',
						data : data['key_pie']
					},
					calculable : true,
					toolbox : {
						show : true,
						orient : 'vertical',
						y : 'center',
						feature : {
							magicType : {
								show : true,
								type : [ 'pie', 'funnel' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					series : [ {
						name : name_text,
						type : 'pie',
						radius : '65%',
						center : [ '50%', 160 ],
						data : pie_data
					} ]
				};

				myChart_4_2.hideLoading();
				myChart_4_2.setOption(option_4_2);
			}
		}
	});
}
$.ajax({
	type : "get",// 请求方式
	url : "/lables/analyse/yp",// 发送请求地址
	data : {
	},
	dataType : "json",
	success : function(data) {
		if (data) {
			var option_4_1 = {
				title : {
					text : '药品使用情况'
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
					splitNumber : '16',
					axisLabel : {
						show : true,
						interval : 'auto',
						formatter : '{value}'
					},
					name : "病案数"
				} ],
				series : [ {
					type : 'bar',
					barWidth : 20,
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

			var pie_data = [];
			for (var i = 0; i < data['val_pie'].length; i++) {
				var pie_obj = {
					value : data['val_pie'][i],
					name : data['key_pie'][i]
				};
				pie_data.push(pie_obj);
			}
			var option_4_2 = {
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'horizontal',
					y : 'bottom',
					data : data['key_pie']
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
					orient : 'vertical',
					y : 'center',
					feature : {
						magicType : {
							show : true,
							type : [ 'pie', 'funnel' ]
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
				series : [ {
					name : '降压药',
					type : 'pie',
					radius : '65%',
					center : [ '50%', 160 ],
					data : pie_data
				} ]
			};
			myChart_4_1.hideLoading();
			myChart_4_1.setOption(option_4_1);

			myChart_4_2.hideLoading();
			myChart_4_2.setOption(option_4_2);

			myChart_4_1.connect(myChart_4_2);
			myChart_4_2.connect(myChart_4_1);

			// $('#report_tootip_4_1').html(data['text'][0]);
		}
	}
});

var myChart_4_3 = echarts.init(document.getElementById('report_echarts_4_3'));
myChart_4_3.showLoading({
	text : "图表数据正在努力加载..."
});
var myChart_4_4 = echarts.init(document.getElementById('report_echarts_4_4'));
myChart_4_4.showLoading({
	text : "图表数据正在努力加载..."
});

var myChart_4_5 = echarts.init(document.getElementById('report_echarts_4_5'));
myChart_4_5.showLoading({
	text : "图表数据正在努力加载..."
});
var myChart_4_6 = echarts.init(document.getElementById('report_echarts_4_6'));
myChart_4_6.showLoading({
	text : "图表数据正在努力加载..."
});
$.ajax({
	type : "get",// 请求方式
	url : "/lables/analyse/yphx",// 发送请求地址
	data : {
	},
	dataType : "json",
	success : function(data) {
		if (data) {
			console.log(data)
			var pie_data1 = [];
			var line_data1 = [];
			var axis = data['zd_key'];
			var zd = data['zd_val'];
			for (var i = 0; i < zd.length; i++) {
				var pie_obj = {
					value : zd[i],
					name : axis[i]
				};
				pie_data1.push(pie_obj);

				var line_obj = {
					name : axis[i],
					type:'bar',
					barWidth : 12,
		            stack: '总量',
					data : data[axis[i]]
				};
				line_data1.push(line_obj);
			}
			
			var option_4_3 = {
				title : {
					text : '卒中相关用药治疗',
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
					data : pie_data1
				} ]
			};

			var option_4_4 = {
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
				series : line_data1
			};

			myChart_4_3.hideLoading();
			myChart_4_3.setOption(option_4_3);

			myChart_4_4.hideLoading();
			myChart_4_4.setOption(option_4_4);

			myChart_4_3.connect(myChart_4_4);
			myChart_4_4.connect(myChart_4_3);
			
			var pie_data2 = [];
			var line_data2 = [];
			for (var i = 0; i < data['ss_val'].length; i++) {
				var pie_obj = {
					value : data['ss_val'][i],
					name : data['ss_key'][i]
				};
				pie_data2.push(pie_obj);

				var line_obj = {
					name : data['ss_key'][i],
					type:'bar',
					barWidth : 12,
		            stack: '总量',
					data : data[data['ss_key'][i]]
				};
				line_data2.push(line_obj);
			}
			
			var option_4_5 = {
					title : {
						text : '卒中相关用药治疗',
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

				var option_4_6 = {
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

				myChart_4_5.hideLoading();
				myChart_4_5.setOption(option_4_5);

				myChart_4_6.hideLoading();
				myChart_4_6.setOption(option_4_6);

				myChart_4_5.connect(myChart_4_6);
				myChart_4_6.connect(myChart_4_5);
		}
	}
});

window.onresize = function() {
	myChart_4_1.resize();
	myChart_4_2.resize();
	myChart_4_3.resize();
	myChart_4_4.resize();
	myChart_4_5.resize();
	myChart_4_6.resize();
}
</script>

</body>
</html>