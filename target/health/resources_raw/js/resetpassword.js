var FormResetpassword = function() {
	var xmlhttp;
	// 回调函数
	function zswFun() {
		alert("接收返回状态！");
		alert(xmlhttp.readyState);
		alert(xmlhttp.status);
		alert(xmlhttp.responseText);
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var b = xmlhttp.responseText;
			if (b == "true") {
				alert("发送消息成功！");
			} else {
				alert("发送消息失败！");
			}
		}
	}
	function testCode() {
		//alert($("#userTel").val());
		//alert(returnCitySN["cip"]);
		var values = {
				"tel":$("#userTel").val(),
				"massage":$("#massage").val()
			};
		options = {
			type : "get",
			url : "testCode",
			//dataType : "json",
			data : values,
			success :function(data) {
				//alert('send success');
				alert(data);
			}
		}
		$.ajax(options);
	}
	function TestApi2(values, httpurl) {
		alert('发送短信！' + httpurl);
		var options;
		$.ajax({
			type : "POST",
			data : values,
			contentType : "application/json",
			dataType : "json",
			url : "sendMSG",
			success : function(data) {
				alert(data);
			}
		});
		options = {
			type : "POST",
			url : "sendMSG",
			dataType : "json",
			data : values,
			success : function(data) {
				alert(data);
			}
		}
		$.ajax(options);

		// 创建 XMLHttpRequest 对象
		// try{
		// // code for IE7+, Firefox, Chrome, Opera, Safari
		// xmlhttp=new XMLHttpRequest();
		// }catch(e){
		// // code for IE6, IE5
		// xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		// }
		// // 初始化请求参数
		// // if(url.indexOf("http://")>=0){
		// // url.replace("?","&");
		// // url="Proxy?url=" +url;
		// // }
		// xmlhttp.onreadystatechange = zswFun;
		// xmlhttp.open("POST",url,true);
		// xmlhttp.setRequestHeader("Access-Control-Allow-Origin",url);
		// xmlhttp.setRequestHeader("893475","7gHkjnNctNgQjak");
		// xmlhttp.setRequestHeader("Content-Type", "x-json");
		// // xmlhttp.setRequestHeader("dataType", "JSONP");
		// 
		// // 发送请求
		// alert('发送请求！');
		// xmlhttp.send(values);
		// alert('发送完毕！');

	}
	
	
	return {

		// main function to initiate the module
		init : function() {
			// if (!jQuery().bootstrapWizard) {
			// return;
			// }

			var form = $('#submit_form');
			var error = $('.alert-danger', form);
			var success = $('.alert-success', form);

			form.validate({
				doNotHideMessage : true, // this option enables to show the
											// error/success messages on tab
											// switch.
				errorElement : 'span', // default input error message container
				errorClass : 'help-block help-block-error', // default input
															// error message
															// class
				focusInvalid : false, // do not focus the last invalid input
				rules : {
					// 用户信息
					username : {
						required : true
					},
					password : {
						required : true
					},
					password2 : {
						required : true
					},
					name : {
						required : true,
					// equalTo: "#submit_form_password"
					},
					userAddress : {
						required : true,
					},
					userTel : {
						// digits: true,
						required : true,
					},
					userDepartment : {
						// digits: true,
						required : true,
					},
					userEmail : {
						required : true,
					// email: true
					}
				},

			});

			$('#button-submit').click(function() {
				$('#submit_form').attr("action", "/healthcare/resetpass");
				$('#submit_form').submit();
			}).show();

		}// end init
	};

}();