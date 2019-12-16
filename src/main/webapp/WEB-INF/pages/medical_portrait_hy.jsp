<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>ECHarts</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <link rel="stylesheet" href="resources/css/tag_analyser.css"/>

</head>

<body class="gray-bg">
<div class="row  border-bottom white-bg dashboard-header">
    <!--  <div class="col-sm-12">
         <p>ECharts开源来自百度商业前端数据可视化团队，基于html5 Canvas，是一个纯Javascript图表库，提供直观，生动，可交互，可个性化定制的数据可视化图表。创新的拖拽重计算、数据视图、值域漫游等特性大大增强了用户体验，赋予了用户对数据进行挖掘、整合的能力。 <a href="http://echarts.baidu.com/doc/about.html" target="_blank">了解更多</a>
         </p>
         <p>ECharts官网：<a href="http://echarts.baidu.com/" target="_blank">http://echarts.baidu.com/</a>
         </p>

     </div> -->

</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>折线图</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="main echart_size_1" id="report_echarts_3_1"></div>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>柱状图</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div>
                        <div id="report_echarts_3_2" class="main echarts"
                             style='width: 40%; float: left; margin-right: 0; padding-right: 0; border-right-width: 0'></div>
                        <div id="report_echarts_3_3" class="main echarts"
                             style='width: 60%; margin-left: 0; padding-left: 0; border-left-width: 0'></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>柱状图</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div id="report_echarts_3_4" class="main echart_size_1"
                         style='width: 40%; float: left; margin-right: 0; padding-right: 0; border-right-width: 0'></div>
                    <div id="report_echarts_3_5" class="main echart_size_1"
                         style='width: 60%; margin-left: 0; padding-left: 0; border-left-width: 0'></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.5"></script>
<script src="resources/js/echarts-all.js"></script>
<script src="js/content.min.js?v=1.0.0"></script>
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