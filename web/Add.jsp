<%--
  Created by IntelliJ IDEA.
  User: szkj
  Date: 2022-12-07
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script type="application/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="application/javascript" src="js/bootstrap.js"></script>
    <title>添加用户</title>
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
              <h3>新增用户</h3>
              <form  method="post"  action="add.action">
                   <label>用户名称：</label>
                   <input class="form-control" type="text" id="userName" name="name">
                   <label>密码：</label>
                   <input class="form-control" type="password" id="" name="pwd">
                   <label>再次输入密码：</label>
                   <input class="form-control" type="password" id="pwd1" name="pwd1">
                   <label>部  门：</label>
                   <select class="form-control" name="dept" id="dept">
                       <option value="IT部">IT部</option>
                       <option value="业务部">业务部</option>
                       <option value="总经办">总经办</option>
                       <option selected="selected" value="IT部">IT部</option>
                   </select>
                   <label>备注：</label>
                   <textarea class="form-control" id="area" name="area" rows="3"  resize="none"></textarea>
                   <div style="text-align: right; margin-top: 6px">
                        <button id="button1" class="btn btn-info">返回列表</button>
                        <button id="button2" class="btn btn-info">添加</button>
                        <input type="reset"  class="btn btn-warning" value="清空">
                        <button id="button3" class="btn btn-danger">退出系统</button>
                   </div>
              </form>
       </div>
</body>
<script>
     //返回列表页
    $("#button1").click(function () {
        //不论点击哪个按钮都会引发 form的提交操作，所以先终于提交。
        $("form").submit(function () {
            return false
        })
        location.href="list.jsp"  //转到列表页
    })
    //取消form提交
    function cancelPost() {
       $("form").submit(function () {
          return false;
       })
    }
    //对输入的数据进行校验
    $("#button2").click(function () {
       let user=$("#name").val();
       if (user===""){
           alert("用户名不能为空！")
           cancelPost() //终止提交
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
        $("form").submit(); //提交
    })
     $("#button3").click(function () {
         cancelPost();
         location.href = "/logOut"
     })
</script>
</html>
