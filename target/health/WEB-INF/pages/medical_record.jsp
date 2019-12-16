<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String zyhid = request.getParameter("zyh");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>电子病历</title>

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
    <%--<link href="resources/css/layout.css" rel="stylesheet" type="text/css"/>--%>
    <%--<script src="resources/js/layout.js" type="text/javascript"></script>--%>

    <style type="text/css">
        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }
    </style>
</head>

<body class="body">

<div class="container" style="margin-top: 30px;">
    <div class="starter-template">
        <h1>电子病历</h1>
    </div>
    <div class="row">
        <div class="col-md-6">
            <span class="text-primary">姓名：</span>
            <span id="inpatient_xm" name="inpatient_xm"></span>
        </div>
        <div class="col-md-6 text-right">
            <span class="text-primary">病案号：</span>
            <span id="inpatient_zyh" name="inpatient_zyh"></span>
            <a class="btn-primary btn-xs" href="javascript:;"
                    onclick="similaritySearch()"><i class="fa fa-search"></i>
                相似病历</a>
        </div>
    </div>
    <hr>
    <h3>基本信息</h3>
    <div class="row">
        <div class="col-md-4">
            <span class="text-primary">姓名：</span>
            <span id="medical_xm" name="medical_xm"></span>
        </div>
        <div class="col-md-4">
            <span class="text-primary">性别：</span>
            <span id="medical_xb" name="medical_xb"></span>
        </div>
        <div class="col-md-4">
            <span class="text-primary">年龄：</span>
            <span id="medical_nl" name="medical_nl"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <span class="text-primary">医院：</span>
            <span id="medical_dwbm" name="medical_dwbm"></span>
        </div>
        <div class="col-md-4">
            <span class="text-primary">科室：</span>
            <span id="medical_rykbmc" name="medical_rykbmc"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <span class="text-primary">诊断：</span>
            <span id="medical_cyzdbm" name="medical_cyzdbm"></span>
        </div>
        <div class="col-md-4">
            <span class="text-primary">转归：</span>
            <span id="medical_lyfsmc" name="medical_lyfsmc"></span>
        </div>
    </div>
    <div class="row row_line"></div>
    <h3>入院记录</h3>
    <div class="row">
        <div class="col-md-12">
            <span class="text-primary">主诉：</span>
            <span id="medical_ryzs"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <span class="text-primary">症状：</span>
            <span id="medical_zz"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <span class="text-primary">现病史：</span>
            <span id="medical_xbs"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <span class="text-primary">既往史：</span>
            <span id="medical_jws"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <span class="text-primary">个人史：</span>
            <span id="medical_grs"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <span class="text-primary">家族史：</span>
            <span id="medical_jzs"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <span class="text-primary">月经及生育史：</span>
            <span id="medical_yjjsys"></span>
        </div>
    </div>
    <div class="row row_line"></div>
    <h3>检查项目</h3>
    <div class="row">
        <div class="col-md-12">
            <div id="medical_jybgbdmcs"></div>
        </div>
    </div>
    <div class="row row_line"></div>
    <h3>影像结果</h3>
    <div class="row">
        <div class="col-md-12">
            <div id="medical_yxlgs"></div>
        </div>
    </div>
    <div class="row row_line"></div>
    <h3>手术操作</h3>
    <div class="row">
        <div class="col-md-12">
            <div id="medical_ssczmcs"></div>
        </div>
    </div>
    <div class="row row_line"></div>
</div>
<script>

    jQuery(document).ready(
            function () {
                search_tags();
            });

    function search_tags() {
        var zyhid = "<%=zyhid%>";
        $.get("/medical/inpatient", {zyh: zyhid}, function (data) {
            console.log(data);
            if (data.xm.trim() == '') {
                data.xm = 'XXX';
            }
            $('#inpatient_xm').html(data.xm);
            document.getElementById('inpatient_zyh').innerText = data.zyh;

            $('#medical_xm').html(data.xm);
            $('#medical_xb').html(format_sex(data.xb));
            $('#medical_nl').html(data.nl + '岁');

            $('#medical_dwbm').html(data.dwbm);
            $('#medical_rykbmc').html(data.rykbmc);

            $('#medical_cyzdbm').html(data.cyzdbm);
            $('#medical_lyfsmc').html(data.lyfsmc);

            $('#medical_ryzs').html(data.inrecord.ryzs);
            $('#medical_zz').html(data.inrecord.zz);
            $('#medical_xbs').html(data.inrecord.xbs);
            $('#medical_jws').html(data.inrecord.jws);
            $('#medical_grs').html(data.inrecord.grs);
            $('#medical_jzs').html(data.inrecord.jzs);
            $('#medical_yjjsys').html(data.inrecord.yjjsys);

            var jybgbdmcs = data.jybgbdmcs;
            if (jybgbdmcs.length > 0) {
                var text = '<ul class="list-group">';
                for (x in jybgbdmcs)
                {
                    var subtext = '<li class="list-group-item">'+jybgbdmcs[x]+'</li>';
                    text += subtext;
                }
                text += '</ul>';
                $("#medical_jybgbdmcs").html(text);
            }

            var yxlgs = data.yxlgs;
            if (yxlgs.length > 0) {
                var text = '<ul class="list-group">';
                for (x in yxlgs)
                {
                    var subtext = '<li class="list-group-item">'+yxlgs[x]+'</li>';
                    text += subtext;
                }
                text += '</ul>';
                $("#medical_yxlgs").html(text);
            }

            var ssczmcs = data.ssczmcs;
            if (ssczmcs.length > 0) {
                var text = '<ul class="list-group">';
                for (x in ssczmcs)
                {
                    var subtext = '<li class="list-group-item">'+ssczmcs[x]+'</li>';
                    text += subtext;
                }
                text += '</ul>';
                $("#medical_ssczmcs").html(text);
            }

        });
    }

    function format_sex(code) {
        if (code == '1') {
            return '男'
        } else {
            return '女'
        }
    }

    function similaritySearch() {
        var zyhid = "<%=zyhid%>";
        alert(zyhid)
    }


</script>

</body>
</html>