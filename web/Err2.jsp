<%--
  Created by IntelliJ IDEA.
  User: szkj
  Date: 2022-12-05
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统错误</title>
</head>
<script type="text/javascript">
    $(function () {
        setTimeout(function () {
                location.href = "index.jsp";},
            3000);
    });
</script>
<style>
    div{
        text-align: center;margin-top: 15%;
        border: red  2px solid;
        border-radius: 10px;width:80%;
        margin-left: 10%;background-color: dodgerblue;
    }
</style>
<body style="background-image:url(imgs/1.png)">
<div>
    <h1 style="color: red">服务器错误:数据库相关对象为空</h1>
    <h2 style="color: red"><a href="index.jsp">点击返回登录页面</a></h2>
</div>
</body>
</html>
