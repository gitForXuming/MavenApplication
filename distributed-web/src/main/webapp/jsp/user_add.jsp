<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加用户</title>
    <link href="${contentPath}/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${contentPath}/css/style.css" rel="stylesheet" />

    <script type="text/javascript" language="javascript" src="${contentPath}/js/jquery.min.js"></script>
    <script type="text/javascript" language="javascript" src="${contentPath}/js/util.js"></script>
    <script type="text/javascript" language="javascript" src="${contentPath}/js/alert.js"></script>
    <script type="text/javascript">
        $().ready(function () {
            $("#btn").click(function () {
                var username = $("#username").val();
                if (username.length == 0) {
                    $("#alert_info").html("信息输入不合规，请留意红色输入框").parent().show();
                    $("html,body").animate({scrollTop:0}, 500);
                    $("#username").addClass("alertshow");
                    style="border:1px solid red"
                } else {
                    document.addsubmit.reset();
                    var json ={"username": username}
                    var postdata = JSON.stringify(json)
                    $.ajax({
                        url:"ajaxCheck",
                        type:"POST",
                        dataType: "json",
                        data:postdata,
                        contentType : "application/json;charset=utf-8",
                        success: function (data) {
                            if (null != data.errorMessage && "" != data.errorMessage) {
                                alert(data.errorMessage);
                                return false;
                            }
                            $("#number").attr("value", data.id);
                            $("#username").attr("value", data.username);
                            $("#password").attr("value", data.password);
                            $("#description").attr("value", data.description);
                        }
                    });
                }
            });
            $("#submit").click(function (){
                var username = $("#username").val();
                var password = $("#password").val();
                var description = $("#description").val();
                if(!checkNotNull(username) || !checkNotNull(password) || !checkNotNull(description)){
                    $("#alert_info").html("信息输入不合规，请留意红色输入框").parent().show();
                    if(!checkNotNull(username)){
                        $("#alert_info").append(" <br><b>请输入姓名!</b>")
                        $("#username").addClass("alertshow");
                    }
                    if(!checkNotNull(password)){
                        $("#alert_info").append(" <br><b>请输入密码!</b>")
                        $("#password").addClass("alertshow");
                    }
                    if(!checkNotNull(description)){
                        $("#alert_info").append(" <br><b>请输入描述!</b>")
                        $("#description").addClass("alertshow");
                        $("#description").scrollTop(100);
                    }
                    return false;
                }
            })
        });

    </script>
</head>
<body>

<form  action="addsubmit" name="addsubmit" method="post" role="form">
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-2">
            <h3>进入到添加页面${contentPath}</h3>
        </div>
    </div>
    <div class="form-group"  >
        <div class="alert alert-block" id="alert_div" style="width:16.3333% ;display: none;">
            <a class="close"  id="alert" data-dismiss="alert">×</a>
            <h4 class="alert-heading">Warning!</h4>
            <span id="alert_info">What are you doing?! this will delete all files!!</span>
        </div>
    </div>
    <div class="form-group">
        <label for="number" class="col-sm-2 control-label">编号：</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" id="number" name="number" placeholder="无需输入,自动生成" readonly/>
        </div>
    </div>
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label"><s:message code="username"/>：</label>
        <div class="col-sm-3">
            <div class="input-group">
                <input type="text"  class="form-control"  id="username" name="username"  title="默认的 Tooltip"  tabindex="1"/>

                <span class="input-group-btn">
                        <button class="btn btn-dark" style="margin-bottom: 9px;" id="btn" type="button">检查用户名</button>
                    </span>

            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label"><s:message code="password"/>：</label>
        <div class="col-sm-2">
            <input type="text"  class="form-control" id="password" name="password" tabindex="1"/>
        </div>
    </div>
    <div class="form-group">
        <label for="description" class="col-sm-2 control-label"><s:message code="description"/>：</label>
        <div class="col-sm-2">
            <input type="text"  class="form-control" id="description" name="description" tabindex="1"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-2">
            <input type="submit" class="btn btn-primary" id="submit" value="<s:message code="submit"></s:message> ">
        </div>
     </div>
</form>
</body>
<script type="text/javascript">

    document.getElementById("username").focus();
    document.addsubmit.reset();
</script>
</html>