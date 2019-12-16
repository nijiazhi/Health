<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String zyhid = request.getParameter("zyh");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>病历画像</title>
    <link rel="shortcut icon" href="images/bitbug_favicon.ico"
          type="image/x-icon"/>
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
    <link rel="stylesheet" href="resources/css/ace.min.css"/>
    <link href="resources/css/layout.css" rel="stylesheet" type="text/css"/>
    <script src="resources/js/layout.js" type="text/javascript"></script>

</head>

<body class="gray-bg">

<div class="container" style="margin-top: 30px;">
    <div class="row row_line"></div>
    <div class="row">
        <div class="col-md-12">
            <div class="main_p">
                <div id="patient_tags" class="echart_size_1"></div>
            </div>
        </div>
    </div>
</div>
<script>

    jQuery(document).ready(
            function () {
                Metronic.init(); // init metronic core components
            });

    var myChart = echarts.init(document.getElementById('patient_tags'));

    function search_tags() {
        var zyhid = "<%=zyhid%>";
        $.get("/lables/portrait", {zyh: zyhid}, function (data) {
            console.log(data);
            var option = {
                title: {
                    text: '病历画像：' + zyhid,
                    subtext: '数据来自中国卒中数据中心',
                    x: 'right',
                    y: 'bottom'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} : {b}'
                },
                toolbox: {
                    show: true,
                    feature: {
                        restore: {show: true},
                        magicType: {show: true, type: ['force', 'chord']},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    x: 'left',
                    data: ['父标签', '子标签', '标签值']
                },
                series: [
                    {
                        type: 'force',
                        name: "病历画像",
                        ribbonType: false,
                        categories: [
                            {
                                name: '住院号'
                            },
                            {
                                name: '父标签'
                            },
                            {
                                name: '子标签'
                            },
                            {
                                name: '标签值'
                            }
                        ],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        color: '#333'
                                    }
                                },
                                nodeStyle: {
                                    brushType: 'both',
                                    borderColor: 'rgba(255,215,0,0.4)',
                                    borderWidth: 3
                                },
                                linkStyle: {
                                    type: 'curve'
                                }
                            },
                            emphasis: {
                                label: {
                                    show: false
                                    // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                                },
                                nodeStyle: {
                                    //r: 30
                                },
                                linkStyle: {}
                            }
                        },
                        useWorker: false,
                        minRadius: 20,
                        maxRadius: 45,
                        gravity: 1.1,
                        scaling: 1.1,
                        roam: 'move',
                        nodes: data['nodes'],
                        links: data['links']
                    }
                ]
            };
            myChart.setOption(option, true);
        });
    }
    search_tags();
    window.onresize = function () {
        myChart.resize();
    }

</script>

</body>
</html>