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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Yzm yzm = (Yzm) request.getServletContext().getAttribute("Yzm");
        String code_text = yzm.getText();
        BufferedImage bufferedImage = yzm.getImage();
        request.getSession().setAttribute("code_text", code_text);
        yzm.output(bufferedImage, response.getOutputStream());
    }
}
