<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${contentPath}/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${contentPath}/css/style.css" rel="stylesheet" />
	<link href="${contentPath}/css/toPage.css" rel="stylesheet" />

	<script type="text/javascript" src="${contentPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${contentPath}/js/toPage.js" ></script>

	<jsp:include page="/js/dlang.js.jsp" flush="true"/>
	<jsp:include page="/js/dcommon.js.jsp" flush="true"/>


	<!--script type="text/javascript" src="${contentPath}/js/dlang.js.jsp" ></script>
	<script type="text/javascript" src="${contentPath}/js/dcommon.js.jsp" ></script>-->
	<title>Insert title here</title>

	<script type="text/javascript">
		$.namespace("demo.query");
		demo.query = function () {
			return{
				init : function () {
					demo.common.buildHead(1);
				},
				ajaxQuery : function (pageNum) {
					var username =$("#username").val();
					var json = {user:{"username":username,"password":"123"},"pageNum":pageNum,"pageSize":"10"};
					var postdata = JSON.stringify(json)
					$.ajax({
						type: 'POST',
						url: "ajaxQuery",
						dataType: "json",
						data: postdata ,
						contentType : "application/json;charset=utf-8",
						success: function(data) {
							if(data==null) return;
							var statTable = document.getElementById("dataTable");
							while (statTable.rows.length > 1) {
								statTable.deleteRow(1);
							}
							var content = data.content;
							var html = "";
							for ( var i = 0; i < content.length; i++) {
								var stat = content[i];
								html += "<tr>";
								html += "<td>" + stat.id + "</td>";
								html +="<td>" + stat.username + "</td>";
								html +="<td>" + stat.password + "</td>";
								html +="<td>" + stat.description + "</td>";
								html += "</tr>";
							}
							$("#dataTable").html(html);
							var obj_1={
								obj_box:'.page',//翻页容器
								total_item:data.totalCount,//条目总数
								per_num:10,//每页条目数
								current_page:pageNum,//当前页
								callBack:function (clickPage) {
									demo.query.ajaxQuery(new String(clickPage));
								}
							};

							page_ctrl(obj_1);

						}
					});
				}
			}
		}();
	</script>
</head>
<body>
<div class="container-fluid">
             <span >
                <s:message code="username"/>：<input style="" type="text" id="username"  />&nbsp;&nbsp;
                <input type="button" class="btn btn-primary lang" style="margin-bottom:10px;" onclick="javascript:demo.query.ajaxQuery('1');"
					   value="<s:message code='query'/>" />
             </span>
</div>
<div class="container-fluid">
    <h6>进入到查询用户页面：<a href="javascript:demo.query.ajaxQuery('1');">刷新</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${contentPath}/user/add" >添加用户</a></h6>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-bordered table-striped responsive-utilities" id="dataTable1">
                <thead>
                    <tr>
                        <th class="langTitle">ID</th>
                        <th>用户名</th>
                        <th>密码</th>
                        <th>描述</th>
                    </tr>
                </thead>
				<tbody id="dataTable" >
				<%--<c:forEach var="user" items="${users.content}">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.password}</td>
						<td>${user.description}</td>
					</tr>
				</c:forEach>--%>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="page"></div>
</body>
<script type="text/javascript">
	demo.query.init();
	demo.query.ajaxQuery('1');
</script>
</html>