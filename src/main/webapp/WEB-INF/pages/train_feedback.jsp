<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/plugins/steps/jquery.steps.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>医生反馈学习</h5>
                    </div>
                    <div class="ibox-content">
                        <p>反馈学习向导</p>
                        <div id="wizard">
                            <h1>第一步</h1>
                            <div class="step-content">
                                <div class="text-center m-t-md">
                                    <h2>第一步</h2>
                                    <p>这是第一步的内容</p>
                                </div>
                            </div>

                            <h1>第二步</h1>
                            <div class="step-content">
                                <div class="text-center m-t-md">
                                    <h2>第二步</h2>
                                    <p>这是第二步的内容</p>
                                </div>
                            </div>

                            <h1>第三步</h1>
                            <div class="step-content">
                                <div class="text-center m-t-md">
                                    <h2>第三步</h2>
                                    <p>这是第三步的内容</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/staps/jquery.steps.min.js"></script>
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    <script src="js/plugins/validate/messages_zh.min.js"></script>
    <script>
        $(document).ready(function(){$("#wizard").steps();$("#form").steps({bodyTag:"fieldset",onStepChanging:function(event,currentIndex,newIndex){if(currentIndex>newIndex){return true}if(newIndex===3&&Number($("#age").val())<18){return false}var form=$(this);if(currentIndex<newIndex){$(".body:eq("+newIndex+") label.error",form).remove();$(".body:eq("+newIndex+") .error",form).removeClass("error")}form.validate().settings.ignore=":disabled,:hidden";return form.valid()},onStepChanged:function(event,currentIndex,priorIndex){if(currentIndex===2&&Number($("#age").val())>=18){$(this).steps("next")}if(currentIndex===2&&priorIndex===3){$(this).steps("previous")}},onFinishing:function(event,currentIndex){var form=$(this);form.validate().settings.ignore=":disabled";return form.valid()},onFinished:function(event,currentIndex){var form=$(this);form.submit()}}).validate({errorPlacement:function(error,element){element.before(error)},rules:{confirm:{equalTo:"#password"}}})});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>