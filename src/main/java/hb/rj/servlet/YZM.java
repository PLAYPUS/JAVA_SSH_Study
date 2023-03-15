package hb.rj.servlet;

import hb.rj.dbutils.Yzm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "YZM", value = "/Yzm")
public class YZM extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Yzm yzm=(Yzm)request.getServletContext().getAttribute("Yzm");  //从上下文中取一个验证码对象出来
        BufferedImage bufferedImage=yzm.getImage(); //生成验证码图像
        String code_text=yzm.getText(); //获取验证码字符
        request.getSession().setAttribute("code_text",code_text); //将验证码存入session中，以备和用户输入的验证码进行对比
        System.out.println("保存到session中的验证码为:"+code_text);  //方便调试的语句，看一下验证码是什么。
        yzm.output(bufferedImage,response.getOutputStream());  //将图片发送到客户端页面的img处
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}














