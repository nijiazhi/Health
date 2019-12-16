var FormSuggestion = function() {
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
					suggestiontype : {
						 maxlength: 200,
						required : true
					},
					suggestion : {
                        maxlength: 800,
						required : true
					}
				},

			});

			$('#button-submit').click(function() {
				$('#submit_form').attr("action", "/healthcare/savesuggestion");
				$('#submit_form').submit();
//				var suggestiontype=document.getElementsByName("suggestiontype");
//				var suggestion=document.getElementsByName("suggestion");
//				options = {
//						type : "get",// 请求方式
//						url : "savesuggestion",// 发送请求地址
//						dataType : "json",
//						data : {
//							operation : "add_suggestion",
//							suggestiontype:suggestiontype,
//							suggestion:suggestion
//						},
//						success : function(data) {
//							alert(data);
//
//						}
//					}
//				$.ajax(options);
			}).show();

		}// end init
	};

}();