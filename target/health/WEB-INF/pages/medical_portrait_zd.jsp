<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>入院途径_病情</title>

    <%--<link rel="shortcut icon" href="images/bitbug_favicon.ico"--%>
          <%--type="image/x-icon"/>--%>
    <script src="resources/js/echarts-all.js"></script>
    <script type="text/javascript" src="resources/js/jquery-2.1.1.js"
            charset="utf-8"></script>
    <script type="text/javascript"
            src="resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

    <link rel="stylesheet" type="text/css"
          href="resources/plugins/bootstrap-datepicker/css/datepicker3.css"/>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="resources/plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="resources/css/ace.min.css"/>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="resources/css/tag_analyser.css"/>


    <link href="resources/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
    <%--<link rel="stylesheet" href="resources/css/ace.min.css"/>--%>
    <%--<link href="resources/css/layout.css" rel="stylesheet" type="text/css"/>--%>
    <%--<script src="resources/js/layout.js" type="text/javascript"></script>--%>


</head>

<body class="body">
<div class="row  border-bottom white-bg dashboard-header">
    <!--  <div class="col-sm-12">
         <p>ECharts开源来自百度商业前端数据可视化团队，基于html5 Canvas，是一个纯Javascript图表库，提供直观，生动，可交互，可个性化定制的数据可视化图表。创新的拖拽重计算、数据视图、值域漫游等特性大大增强了用户体验，赋予了用户对数据进行挖掘、整合的能力。 <a href="http://echarts.baidu.com/doc/about.html" target="_blank">了解更多</a>
         </p>
         <p>ECharts官网：<a href="http://echarts.baidu.com/" target="_blank">http://echarts.baidu.com/</a>
         </p>

     </div> -->

</div>
<!-- 诊断分析 -->
<div class="container" style="margin-top: 20px;">
    <div id="part_1_1" class="target_sub">
        <div class="row">
            <div class="col-md-12">
                <div id="report_echarts_1_1" class="main echart_size_1"></div>
            </div>
        </div>

        <div class="row row_line">
            <div class="col-md-12">
                <div class="main echart_size_1">
                    <div id="head"
                         style="height: 8%; line-height: 35px; text-align: right;">
                        <div>
                            <span class="text-primary">诊断及分型</span> <select name="select"
                                                                            id="part1_zd" onchange="zd_fx(this)">
                            <option value="1">入院诊断</option>
                            <option value="2">出院诊断</option>
                        </select>
                        </div>
                    </div>
                    <div id="report_echarts_1_2" class="echart_size_1"></div>
                </div>
            </div>
        </div>

        <div class="row row_line">
            <div class="col-md-12">
                <div id="report_echarts_1_3" class="main echart_size_1"></div>
            </div>
        </div>
    </div>
</div>
<script>
    function gourl(url) {
        $.ajax(gourl_option = {
            type: "get",//请求方式
            url: "/goto_url",//发送请求地址
            dataType: "json",
            data: {
                url: url
            },
            success: function (data) {

            }
        });

    }

    var myChart_1_1 = echarts.init(document
            .getElementById('report_echarts_1_1'));
    myChart_1_1.showLoading({
        text: "图表数据正在努力加载..."
    });
    var myChart_1_2 = echarts.init(document
            .getElementById('report_echarts_1_2'));
    myChart_1_2.showLoading({
        text: "图表数据正在努力加载..."
    });
    var myChart_1_3 = echarts.init(document
            .getElementById('report_echarts_1_3'));
    myChart_1_3.showLoading({
        text: "图表数据正在努力加载..."
    });
    function zd_fx(obj) {
        var zdfx = $('#part1_zd').val();
        $.ajax({
            type: "get",// 请求方式
            url: "/lables/analyse/zdfx",// 发送请求地址
            data: {},
            dataType: "json",
            success: function (data) {
                if (data) {
                    // 指定图表的配置项和数据
                    var pie_data = [];
                    var name = '';
                    var axis = data['xAxis'];
                    if (zdfx === '1') {
                        name = '入院诊断';
                        var ryzd = data['ryzd'];
                        for (var i = 0; i < ryzd.length; i++) {
                            var pie_obj = {
                                value: ryzd[i],
                                name: axis[i]
                            };
                            pie_data.push(pie_obj);
                        }
                    } else {
                        var cyzd = data['cyzd'];
                        name = '出院诊断';
                        for (var i = 0; i < cyzd.length; i++) {
                            var pie_obj = {
                                value: cyzd[i],
                                name: axis[i]
                            };
                            pie_data.push(pie_obj);
                        }
                    }
                    var option_1_2 = {
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'horizontal',
                            y: 'bottom',
                            data: data['xAxis']
                        },
                        watermark: {
                            x: 50,
                            y: 150,
                            row: 1,
                            column: 2,
                            show: true,
                            text: '中国卒中数据中心',
                            textStyle: {
                                fontSize: 44,
                                fontWeight: 'bolder',
                                fontStyle: 'italic',
                                color: '#DCDCDC'
                            }
                        },
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            y: 'center',
                            feature: {
                                magicType: {
                                    show: true,
                                    type: ['pie', 'funnel']
                                },
                                restore: {
                                    show: true
                                },
                                saveAsImage: {
                                    show: true
                                }
                            }
                        },
                        calculable: true,
                        series: [{
                            name: name,
                            type: 'pie',
                            radius: '65%',
                            center: ['50%', 150],
                            data: pie_data
                        }]
                    };

                    myChart_1_2.hideLoading();
                    myChart_1_2.setOption(option_1_2);
                }
            }
        });
    }
    $.ajax({
        type: "get",// 请求方式
        url: "/lables/analyse/zd",// 发送请求地址
        data: {},
        dataType: "json",
        success: function (data) {
            if (data) {
                // 指定图表的配置项和数据
                var zdfx = $('#part1_zd').val();
                var ryzd = data['ryzd'];
                var axis = data['xAxis'];
                var pie_data = [];
                for (var i = 0; i < ryzd.length; i++) {
                    var pie_obj = {
                        value: ryzd[i],
                        name: axis[i]
                    };
                    pie_data.push(pie_obj);
                }
                var option_1_1 = {
                    title: {
                        text: '诊断分型情况'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (a) {
                            var refval = '';
                            refval += a[0].name;
                            refval += '<br/>';
                            refval += a[0].seriesName;
                            refval += ': ';
                            refval += a[0].value;
                            refval += '例<br/>';
                            refval += a[1].seriesName;
                            refval += ': ';
                            refval += a[1].value;
                            refval += '例';
                            return refval;
                        }
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            magicType: {
                                show: true,
                                type: ['line', 'bar']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    watermark: {
                        x: 50,
                        y: 150,
                        row: 1,
                        column: 2,
                        show: true,
                        text: '中国卒中数据中心',
                        textStyle: {
                            fontSize: 44,
                            fontWeight: 'bolder',
                            fontStyle: 'italic',
                            color: '#DCDCDC'
                        }
                    },
                    calculable: true,
                    legend: {
                        orient: 'horizontal',
                        y: 'top',
                        data: ['入院诊断', '出院诊断']
                    },
                    xAxis: [{
                        type: 'category',
                        axisLabel: {
                            interval: 0,
                            rotate: 20,
                            margin: 2,
                            textStyle: {
                                color: "#222"
                            }
                        },
                        data: data['xAxis']
                    }],
                    yAxis: [{
                        type: 'value',
                        name: '病案数',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }],
                    series: [

                        {
                            name: '入院诊断',
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        position: 'top',
                                        formatter: '{c}'
                                    }
                                }
                            },
                            data: data['ryzd']
                        }, {
                            name: '出院诊断',
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        position: 'top',
                                        formatter: '{c}'
                                    }
                                }
                            },
                            data: data['cyzd']
                        }]
                };

                var option_1_2 = {
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'horizontal',
                        y: 'bottom',
                        data: data['xAxis']
                    },
                    watermark: {
                        x: 50,
                        y: 150,
                        row: 1,
                        column: 2,
                        show: true,
                        text: '中国卒中数据中心',
                        textStyle: {
                            fontSize: 44,
                            fontWeight: 'bolder',
                            fontStyle: 'italic',
                            color: '#DCDCDC'
                        }
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        y: 'center',
                        feature: {
                            magicType: {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    calculable: true,
                    series: [{
                        name: '入院诊断',
                        type: 'pie',
                        radius: '65%',
                        center: ['50%', 150],
                        data: pie_data
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart_1_1.hideLoading();
                myChart_1_1.setOption(option_1_1);

                myChart_1_2.hideLoading();
                myChart_1_2.setOption(option_1_2);

                ////////////////////////////////////
                var option_1_3 = {
                    title: {
                        text: '诊断符合率'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (a) {
                            var refval = '';
                            refval += a[0].name;
                            refval += '<br/>';
                            refval += a[0].seriesName;
                            refval += ': ';
                            refval += a[0].value;
                            refval += '例<br/>';
                            refval += a[1].seriesName;
                            refval += ': ';
                            refval += a[1].value;
                            refval += '例<br/>';
                            refval += a[2].seriesName;
                            refval += ': ';
                            refval += a[2].value;
                            refval += '%';
                            return refval;
                        }
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            magicType: {
                                show: true,
                                type: ['line', 'bar']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    watermark: {
                        x: 50,
                        y: 150,
                        row: 1,
                        column: 2,
                        show: true,
                        text: '中国卒中数据中心',
                        textStyle: {
                            fontSize: 44,
                            fontWeight: 'bolder',
                            fontStyle: 'italic',
                            color: '#DCDCDC'
                        }
                    },
                    calculable: true,
                    legend: {
                        orient: 'horizontal',
                        y: 'top',
                        data: ['入院诊断', '出院诊断', '诊断符合率']
                    },
                    xAxis: [{
                        type: 'category',
                        axisLabel: {
                            interval: 0,
                            rotate: 20,
                            margin: 2,
                            textStyle: {
                                color: "#222"
                            }
                        },
                        data: data['xAxis']
                    }],
                    yAxis: [{
                        type: 'value',
                        name: '病案数',
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }, {
                        type: 'value',
                        name: '百分比',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }],
                    series: [

                        {
                            name: '入院诊断',
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        position: 'top',
                                        formatter: '{c}'
                                    }
                                }
                            },
                            data: data['ryzd']
                        }, {
                            name: '出院诊断',
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        position: 'top',
                                        formatter: '{c}'
                                    }
                                }
                            },
                            data: data['qrzd']
                        }, {
                            name: '诊断符合率',
                            type: 'line',
                            yAxisIndex: 1,
                            data: data['iszd']
                        }]
                };
                myChart_1_3.hideLoading();
                myChart_1_3.setOption(option_1_3);
            }
        }
    });

    window.onresize = function () {
        myChart_1_1.resize();
        myChart_1_2.resize();
        myChart_1_3.resize();
    }
</script>

</body>
</html>