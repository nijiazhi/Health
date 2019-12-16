<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="css/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="css/plugins/cropper/cropper.min.css" rel="stylesheet">
    <link href="css/plugins/switchery/switchery.css" rel="stylesheet">
    <link href="css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
    <link href="css/plugins/nouslider/jquery.nouislider.css" rel="stylesheet">
    <link href="css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="css/plugins/ionRangeSlider/ion.rangeSlider.css" rel="stylesheet">
    <link href="css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css" rel="stylesheet">
    <link href="css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-5">

                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>模型训练 <small>设定模型初始值</small></h5>
                    </div>
                    <div class="ibox-content">
                        <div class="text-center">
                            <br/>
                            <br/>
                            <div class="m-r-md inline">
                                <input type="text" value="75" class="dial m-r-sm" data-fgColor="#1AB394" data-width="85" data-height="85" />
                            </div>
                            <div class="m-r-md inline">
                                <input type="text" value="25" class="dial m-r" data-fgColor="#1AB394" data-width="85" data-height="85" />
                            </div>
                            <div class="m-r-md inline">
                                <input type="text" value="50" class="dial m-r" data-fgColor="#1AB394" data-width="85" data-height="85" />
                            </div>
                        </div>
                        <div class="text-center">
                            <br/>
                            <br/>
                            <div class="m-r-md inline">
                                <input type="text" value="75" class="dial m-r-sm" data-fgColor="#ED5565" data-width="85" data-height="85" data-cursor=true data-thickness=.3/>
                            </div>
                            <div class="m-r-md inline">
                                <input type="text" value="25" class="dial m-r" data-fgColor="#ED5565" data-width="85" data-height="85" data-step="1000" data-min="-15000" data-max="15000" data-displayPrevious=true/>
                            </div>
                            <div class="m-r-md inline">
                                <input type="text" value="60" class="dial m-r" data-fgColor="#ED5565" data-width="85" data-height="85" data-angleOffset=-125 data-angleArc=250 />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-7">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>训练进度</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="progress progress-striped active">
                            <div style="width: 75%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="75" role="progressbar" class="progress-bar progress-bar-danger">
                                <span class="sr-only">40% Complete (success)</span>
                            </div>
                        </div>
                        <div class="progress progress-striped active">
                            <div style="width: 30%" class="progress-bar progress-bar-success">
                                <span class="sr-only">30% Complete (success)</span>
                            </div>
                            <div style="width: 20%" class="progress-bar progress-bar-warning">
                                <span class="sr-only">15% Complete (warning)</span>
                            </div>
                            <div style="width: 40%" class="progress-bar progress-bar-danger">
                                <span class="sr-only">40% Complete (danger)</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!--  训练日志 -->
        <div class="row">
        	<div class="col-sm-12">
        		<div class="ibox float-e-margins">
        		 	<div class="ibox-title">
                       <h5>训练日志</h5>
                    </div>
        		 	<div class="ibox-content timeline">
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-briefcase"></i> 6:00
                                    <br>
                                    <small class="text-navy">2 小时前</small>
                                </div>
                                <div class="col-xs-7 content no-top-border">
                                    <p class="m-b-xs"><strong>服务器已彻底崩溃</strong>
                                    </p>

                                    <p>还未检查出错误</p>

                                    <p><span data-diameter="40" class="updating-chart" style="display: none;">3,9,6,5,9,4,7,3,2,9,8,7,4,5,1,2,9,5,4,7,2,7,7,3,5,2,3,3,2,1,6,9,8,8,3,7,4</span>
                                        <svg class="peity" height="16" width="64">
                                            <polygon fill="#1ab394" points="0 15 0 10.5 1.7777777777777777 0.5 3.5555555555555554 5.5 5.333333333333333 7.166666666666666 7.111111111111111 0.5 8.88888888888889 8.833333333333332 10.666666666666666 3.833333333333332 12.444444444444443 10.5 14.222222222222221 12.166666666666666 16 0.5 17.77777777777778 2.166666666666666 19.555555555555554 3.833333333333332 21.333333333333332 8.833333333333332 23.11111111111111 7.166666666666666 24.888888888888886 13.833333333333334 26.666666666666664 12.166666666666666 28.444444444444443 0.5 30.22222222222222 7.166666666666666 32 8.833333333333332 33.77777777777778 3.833333333333332 35.55555555555556 12.166666666666666 37.33333333333333 3.833333333333332 39.11111111111111 3.833333333333332 40.888888888888886 10.5 42.666666666666664 7.166666666666666 44.44444444444444 12.166666666666666 46.22222222222222 10.5 48 10.5 49.77777777777777 12.166666666666666 51.55555555555555 13.833333333333334 53.33333333333333 5.5 55.11111111111111 0.5 56.888888888888886 2.166666666666666 58.666666666666664 2.166666666666666 60.44444444444444 10.5 62.22222222222222 3.833333333333332 64 8.833333333333332 64 15"></polygon>
                                            <polyline fill="transparent" points="0 10.5 1.7777777777777777 0.5 3.5555555555555554 5.5 5.333333333333333 7.166666666666666 7.111111111111111 0.5 8.88888888888889 8.833333333333332 10.666666666666666 3.833333333333332 12.444444444444443 10.5 14.222222222222221 12.166666666666666 16 0.5 17.77777777777778 2.166666666666666 19.555555555555554 3.833333333333332 21.333333333333332 8.833333333333332 23.11111111111111 7.166666666666666 24.888888888888886 13.833333333333334 26.666666666666664 12.166666666666666 28.444444444444443 0.5 30.22222222222222 7.166666666666666 32 8.833333333333332 33.77777777777778 3.833333333333332 35.55555555555556 12.166666666666666 37.33333333333333 3.833333333333332 39.11111111111111 3.833333333333332 40.888888888888886 10.5 42.666666666666664 7.166666666666666 44.44444444444444 12.166666666666666 46.22222222222222 10.5 48 10.5 49.77777777777777 12.166666666666666 51.55555555555555 13.833333333333334 53.33333333333333 5.5 55.11111111111111 0.5 56.888888888888886 2.166666666666666 58.666666666666664 2.166666666666666 60.44444444444444 10.5 62.22222222222222 3.833333333333332 64 8.833333333333332" stroke="#169c81" stroke-width="1" stroke-linecap="square"></polyline>
                                        </svg>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i> 7:00
                                    <br>
                                    <small class="text-navy">3小时前</small>
                                </div>
                                <div class="col-xs-7 content">
                                    <p class="m-b-xs"><strong>修复了0.5个bug</strong>
                                    </p>
                                    <p>重启服务</p>
                                </div>
                            </div>
                        </div>
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-coffee"></i> 8:00
                                    <br>
                                </div>
                                <div class="col-xs-7 content">
                                    <p class="m-b-xs"><strong>喝水、上厕所、做测试</strong>
                                    </p>
                                    <p>
                                        喝了4杯水，上了3次厕所，控制台输出出2324个错误，神啊，带我走吧
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-phone"></i> 11:00
                                    <br>
                                    <small class="text-navy">21小时前</small>
                                </div>
                                <div class="col-xs-7 content">
                                    <p class="m-b-xs"><strong>项目经理打电话来了</strong>
                                    </p>
                                    <p>
                                        TMD，项目经理居然还没有起床！！！
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-user-md"></i> 09:00
                                    <br>
                                    <small>21小时前</small>
                                </div>
                                <div class="col-xs-7 content">
                                    <p class="m-b-xs"><strong>开会</strong>
                                    </p>
                                    <p>
                                        开你妹的会，老子还有897894个bug没有修复
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-comments"></i> 12:50
                                    <br>
                                    <small class="text-navy">讨论</small>
                                </div>
                                <div class="col-xs-7 content">
                                    <p class="m-b-xs"><strong>…………</strong>
                                    </p>
                                    <p>
                                        又是操蛋的一天
                                    </p>
                                </div>
                            </div>
                        </div>
                	</div>
         		</div>
         	</div>
        </div>
 	</div>

	
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">颜色选择</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group colorpicker-component demo demo-auto">
                        <input type="text" value="" class="form-control" />
                        <span class="input-group-addon"><i></i></span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/chosen/chosen.jquery.js"></script>
    <script src="js/plugins/jsKnob/jquery.knob.js"></script>
    <script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
    <script src="js/plugins/nouslider/jquery.nouislider.min.js"></script>
    <script src="js/plugins/switchery/switchery.js"></script>
    <script src="js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    <script src="js/plugins/clockpicker/clockpicker.js"></script>
    <script src="js/plugins/cropper/cropper.min.js"></script>
    <script src="js/demo/form-advanced-demo.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>