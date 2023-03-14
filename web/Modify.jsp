<%--
  Created by IntelliJ IDEA.
  User: szkj
  Date: 2022-12-07
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script type="application/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="application/javascript" src="js/bootstrap.js"></script>
    <title>修改用户</title>
    <style>
        body,H3{
            background-color: dodgerblue;
            color: white;
            text-align: left;
        }
    </style>
</head>
<body>
        <%
            String user=(String)session.getAttribute("LoginUser");
            System.out.println(user);
            if (user==null){
                System.out.println("user为空");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        %>
       <div style="width:50%; margin-left: 25%;margin-top: 5%">
            <h3>修改用户信息：</h3>
            <form  method="post" action="modify">
                <input type="hidden" name="id" value=${user.id}>
                <label>用户名称：</label>
                <input class="form-control" type="text" id="name" name="name" value=${user.userName}>
                <label>密码：</label>
                <input class="form-control" type="password" id="pwd" name="pwd" value=${user.passWord}>
                <label>再次输入密码：</label>
                <input class="form-control" type="password" id="pwd1" name="pwd1" value=${user.passWord}>
                <label>部门</label>
                <select class="form-control" name="dept" id="dept">
                     <option value="IT部">IT部</option>
                     <option value="业务部">业务部</option>
                     <option value="总经办">总经办</option>
                </select>
                <label>备注：</label>
                <textarea  class="form-control" id="area" name="area" rows="3" resize="none">${user.remark}</textarea>
                <div style="text-align: right; margin-top: 6px">
                    <button id="button1" class="btn btn-info">返回列表</button>
                    <button id="button2" class="btn btn-info">修改</button>
                    <input type="reset"  class="btn btn-warning" value="复位">
                    <button id="button3" class="btn btn-danger">退出系统</button>
                </div>
            </form>
       </div>
</body>
<script>
    $( //页面打开设置下拉列表的值
        function () {
            $("#dept").val("${user.dept}")
        }
    )
    <%--取消form提交--%>
    function cancelPost() {
        $("form").submit(function () {
            return false;
        })
    }
    <%-- 返回列表页--%>
    $("#button1").click(function () {
        cancelPost();
        location.href = "/list"
    })
    <%-- 对要输入的数据进行校验 --%>
    $("#button2").click(function () {
        if (${empty LoginUser}){
            alert("请先登录！")
            location.href = "index.jsp";
            cancelPost();
            return
        }
        let user = $("#name").val();
        if (user===""){
            alert("用户名不能为空！")
            cancelPost();
            return
        }
        let p1=$("#pwd").val();
        let p2=$("#pwd1").val();
        if (p1===""){
            alert("密码不能为空，请重新输入！")
            cancelPost();
            return
        }
        if (p1!=p2){
            alert("两次密码不一致，请重新输入！")
            cancelPost();
            return
        }
        let dept=$("#dept").val();
        if (dept===""){
            alert("部门不能为空！")
            cancelPost();
            return
        }
        $("form").submit();
    })
    $("#button3").click(function () {
        cancelPost();
        location.href = "/logOut"
    })

</script>

</html>
