<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s"  uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>spring  demo</title>
	<link href='${contentPath}/css/bootstrap.min.css' rel="stylesheet" >
	<script type="text/javascript" src="${contentPath}/js/jquery.min.js"></script>
	<style type="text/css">
		/* Override some defaults */
		html, body {
			background-color: #eee;
		}
		body {
			padding-top: 40px;
		}
		.container {
			width: 300px;
		}

		/* The white background content wrapper */
		.container > .content {
			background-color: #fff;
			padding: 20px;
			margin: 0 -20px;
			-webkit-border-radius: 10px 10px 10px 10px;
			-moz-border-radius: 10px 10px 10px 10px;
			border-radius: 10px 10px 10px 10px;
			-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
			-moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
			box-shadow: 0 1px 2px rgba(0,0,0,.15);
		}

		.login-form {
			margin-left: 65px;
		}

		legend {
			margin-right: -50px;
			font-weight: bold;
			color: #404040;
		}

	</style>

</head>
<body>
<c:set value="${contentPath}" var="contentPath" scope="session"></c:set>
<div class="container">
	<div class="content">
		<div class="row">
			<div class="login-form">
				<h2>Login</h2>
				<form id="loginForm" method="post"  >
					<fieldset>
						<div id="alertInfo" class="alert alert-error clearfix" style="margin-bottom: 5px;width: 195px; padding: 2px 15px 2px 10px;display: none;">
							The username or password you entered is incorrect.
						</div>
						<div class="clearfix">
							<input type="text" placeholder="用户名" name="loginUsername" id="username" value="xuming" >
						</div>
						<div class="clearfix">
							<input type="password" placeholder="密码" name="loginPassword" id="password" value="123456">
						</div>
						<button id="loginBtn" class="btn btn-primary" type="button">Sign in</button>
						<button class="btn" type="reset">Reset</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div> <!-- /container -->
<script type="text/javascript">
	$.namespace("demo.login");
	demo.login = function () {
		return  {
			login : function() {
				var username =$("#username").val();
				var password =$("#password").val();
				var json ={"username":username ,"password":password};
				var postdata = JSON.stringify(json)
				$.ajax({
					type: 'POST',
					url: "login",
					dataType: "json",
					data: postdata ,
					contentType : "application/json;charset=utf-8",
					success: function(data) {
						if(null!=data && username == data.username)
							location.href = "${contentPath}/user/list";
						else {
							$("#alertInfo").show();
							$("#loginForm")[0].reset();
						}
					},
					dataType: "json"
				});
			}
		}
	}();

	$(document).ready(function() {
		$("#loginBtn").click(demo.login.login);
	});
</script>
</body>
</html>