<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
$.namespace("demo.common");
demo.common = function () {
    var statViewOrderBy = '';
    var statViewOrderBy_old = '';
    var statViewOrderType = 'asc';
    var isOrderRequest = false;
    // only one page for now
    var sqlViewPage = 1;
    var sqlViewPerPageCount = 1000000;

    return {
        init : function () {
            this.buildFooter();
            demo.lang.init();
        },
        buildHead : function (index) {
            $.get('${contentPath}/html/header.html' ,function (html) { //发起一个http请求到 header.html并拿到结果值 这里是拿html $.get("/try/ajax/demo_test.php",function(data,status)
                $(document.body).prepend(html);//将html 追加到当前页面body的最前面
                demo.lang.trigger();
                $(".navbar .nav li").eq(index).addClass("active");
            },"html");

        },
        buildFooter : function() {
            $.get('${contentPath}/html/footer.html' ,function (html) { //发起一个http请求到 header.html并拿到结果值 这里是拿html $.get("/try/ajax/demo_test.php",function(data,status)
                $(document.body).append(html);//将html 追加到当前页面body的最前面
            },"html");
        },
    }
}();
demo.common.init();
</script>