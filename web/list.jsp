<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
User: szkj
  Date: 2022-12-06
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script type="application/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="application/javascript" src="js/bootstrap.js"></script>
</head>
<style>
       body{
            background-color: dodgerblue;
            color: white;
            text-align: left;
       }
       div{
            width:70%;
           margin: auto;
           text-align: right;
           /*border: red 2px solid;*/
       }
       table{
           width: 100%;
           background-color: aliceblue;
           color:black;
       }
       th,td{
           border: 1px solid darkblue;
           color:black;
           height: 35px;
           word-break: break-all; word-wrap:break-word; /*表格内容自动折行*/
       }
</style>
<body>
        <%
            String user=(String)session.getAttribute("LoginUser");
            System.out.println(user);
            if (user==null){
                System.out.println("user为空");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        %>
        <div>
              <div style="width: 100%;margin-top: 30px;margin-bottom: 10px">
                   <table style="width: 100%;border: 0px red solid;background-color: dodgerblue">
                         <tr style="border: 0px">
                              <td style="border: 0px;text-align: left;vertical-align: bottom;">
                                    <H3 style="color:white">用户列表</H3>
                                    <H4 style="color:white;margin-bottom: 0px">当前用户:${LoginUser}</H4>
                              </td>
                              <td style="vertical-align: bottom;border: 0px;text-align: right">
                                    <form action="list.action" method="post" style="margin: 0px" class="form-inline">
                                          <H4 style="color: white;display: inline">按：</H4>
                                          <select class="form-control" name="conIndex"  id="select1">
                                                  <option value="0">全部</option>
                                                  <option value="1">id号</option>
                                                  <option value="2">用户名</option>
                                                  <option value="3">部门</option>
                                          </select>
                                          <input class="form-control" name="text_search"  type="text">
                                          <input class="form-control" type="submit"  value="查找用户">
                                    </form>
                              </td>
                              <td style="text-align: right; border: 0px; vertical-align: bottom">
                                    <button class="btn btn-info" id="button1" >添加用户</button>
                                    <button class="btn btn-danger" id="button2" >退出系统</button>
                              </td>
                         </tr>
                   </table>
              </div>
              <table class="table  table-hover">
                      <tr>  <!--列头是固定的部分，就不变化了。-->
                           <th>序号</th><th>id号</th><th>用户名</th><th>密码</th><th>部门</th>
                           <th>创建人</th><th>创建日期</th><th>备注</th><th>操作项</th>
                      </tr>
                      <c:forEach items="${userList}" var="user" varStatus="st1">
                         <tr>
                                 <td>${st1.count}</td>
                                 <td>${user.id}</td>
                                 <td>${user.userName}</td>
                                 <td>${user.passWord}</td>
                                 <td>${user.dept}</td>
                                 <td>${user.createUser}</td>
                                 <td>${user.createDate}</td>
                                 <td>${user.remark}</td>
                                 <td>
                                      <a  href="/deleteById.action?id=${user.id}">删除</a>
                                      <a  href="/modifyById.action?id=${user.id}">修改</a>
                                 </td>
                         </tr>
                      </c:forEach>
              </table>
        </div>
        <div>
               共计：<span id="spanPage1">${page.pageCount}</span>页
               当前第：<span id="spanPage2">${page.pageNum}</span>页
               <button class="btn btn-info" id="b1" onclick='Fpage(${page.pageNum})'>上一页</button>
               <button class="btn btn-info" id="b2" onclick='Npage(${page.pageNum})'>下一页</button>
        </div>
</body>
<script>
        //添加用户
        $("#button1").click(function () {
            location.href="add.action"
        })
        //退出系统
        $("#button2").click(function () {
            location.href="logOut.action"
        })
        //原生javascript写法
        function Fpage(pageNum) { //前翻一页
            let CurrentPage=pageNum-1;
            if (CurrentPage==0){  //如果前面没有页码了，那改为第1页
                CurrentPage=1;
            }
            window.location.href="list.action?CurrentPage="+CurrentPage;//将要显示的页码传入名为list的Servlet中
        }
        function Npage(pageNum) { //后翻一页
            let  CurrentPage=pageNum+1;
            if (CurrentPage>${page.pageCount}){ //如果当前页加一后大于总页数，说明当前页是最后一页。
                CurrentPage=${page.pageCount}
            }
            window.location.href="list.action?CurrentPage="+CurrentPage;//将要显示的页码传入名为list的Servlet中
        }
</script>
</html>
