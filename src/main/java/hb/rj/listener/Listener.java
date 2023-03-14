package hb.rj.listener;

import hb.rj.dbutils.Yzm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Yzm yzm = new Yzm();
        System.out.println("验证码对象创建成功");
        sce.getServletContext().setAttribute("Yzm",yzm);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("Yzm");
        System.out.println("验证码释放成功");
    }
}
