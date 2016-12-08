<%--
  Created by IntelliJ IDEA.
  User: wangyangyang
  Date: 16/11/20
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function exception() {
            var text = document.getElementById("exec");
            alert(text.innerHTML);
            //1秒后重定向
            return ;
        }
    </script>
</head>
<body onload="exception()" id="exec">
${errorMsg}
</body>
</html>
