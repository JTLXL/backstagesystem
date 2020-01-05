<%--
  Created by IntelliJ IDEA.
  User: JT.L
  Date: 2020/1/3
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=request.getAttribute("username")%>
<br>
<%="id:"+request.getAttribute("id")%>
</body>
</html>
