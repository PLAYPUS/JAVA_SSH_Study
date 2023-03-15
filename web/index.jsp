<%--
  Created by IntelliJ IDEA.
  User: szkj
  Date: 2022-12-02
  Time: 8:51
  To change this template use File | Settings | File Templates.
  本项目基于  servlet + JSP +mysql +c3p0连接池
            HTML+Javascript+JQuery+CSS+JSTL+EL+Bootstrap
  项目结构是依据技术选型创建的。

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script type="application/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="application/javascript" src="js/bootstrap.js"></script>
    <title>用户登录:</title>
  </head>
  <body style="background-image: url(imgs/1.png)">
        <div style="border: red 2px solid;width: 40%;
                   margin-left: 30%;margin-top:10%;text-align: center;border-radius: 10px">
              <div style="width: 90%;margin-left: 5%;">
                    <h1 style="color: white">用户管理系统</h1>
                    <form action="login.action" method="post" onsubmit="onsub()">
                        <div  style="margin-top: 5px">
                              <div style="text-align: left;margin-top: 0px">
                                   <label style="font-size: 20px;">用户名称</label>
                              </div>
                              <input class="form-control" id="username" name="userName" type="text"><br>
                        </div>
                        <div style="margin-top: 5px">
                              <div style="text-align: left;margin-top: 0px">
                                    <label style="font-size: 20px">密码</label>
                              </div>
                              <input class="form-control" id="password" name="passWord" type="password"><br>
                        </div>
                        <div style="margin-top: 5px">
                              <div style="text-align: left">
                                    <label style="font-size: 20px">验证码</label>
                              </div>
                              <input class="form-control" id="code_text" name="code_text"><br>
                              <label color="red" id="label1"></label>
                              <img src="Yzm" id="img_src">
                              <a href="javascript:changeYzm()">换一张</a><br>
                        </div>
                        <div style="margin-top: 5px;width: 100%">
                              <button id="B_submit">登录</button>
                              <button id="reset">清空</button>
                        </div>
                    </form>
              </div>
        </div>
  </body>
  <script>
      $(
         function () {
               $("#username").focus(); //网页打开时，用户名input 获得输入焦点。
         }
      )
      //当验证码的输入框失去焦点时，向服务器发送检查验证码是否正确的请求
      $("#code_text").blur(function () {
          $.post(
              "CheckYzm",  //向服务器发送的请求
              {code_text:$("#code_text").val()},//请求所携带的参数名和值
              function (data) { //服务返回的响应信息
                  if(data=="F"){ //返回F,验证码错误
                      $("#label1").text("验证码错误")
                  }else{
                      $("#label1").empty();
                  }
              }
          )
      })
      function changeYzm() {
          let  imgElem=document.getElementById("img_src")
          imgElem.src="http://localhost:8080/Yzm?a="+new Date().getTime();
          $("#label1").empty();
      }
      $('#B_submit').click(function () {
          onsub()  //提交安钮点击时，执行onsub()方法。
      })
      function  onsub(){
          $("form").submit(function () {
              if ($("#label1").text()=="验证码错误"){
                  return  false
              } else{
                  return  true
              }
          })
      }
  </script>
</html>
