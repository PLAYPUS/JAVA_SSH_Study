package hb.rj.action;

import com.opensymphony.xwork2.ActionSupport;
import hb.rj.dao.UserDao;
import hb.rj.dbutils.Page;
import hb.rj.pojo.Condition;
import hb.rj.pojo.User;
import hb.rj.service.Service;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListAction extends ActionSupport {
    private UserDao userDao;
    private Service service;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String execute() {
        // Get Request 对象
        HttpServletRequest request = ServletActionContext.getRequest();
        // 获取 hibernate 对象
        Session session = userDao.getSessionFactory().openSession();
        // 创建搜索条件对象
        Condition condition = new Condition();

        // 获取URL 参数
        String currentPage = request.getParameter("CurrentPage");
        System.out.println("currentPage: " + currentPage);
        int conIndex = 0;
        System.out.println("CONINDEX: " + request.getParameter("conIndex"));
        if (request.getParameter("conIndex") == null) {
            System.out.println("request.getParameter(\"conIndex\") == null: " + request.getParameter("conIndex"));
            conIndex = 0;
        }
        conIndex = Integer.parseInt(request.getParameter("conIndex"));
        condition.setConIndex(conIndex);


        // 获取搜索条件的值
        condition.setTextSearch(request.getParameter("text_search"));

        // 取出分页对象
        Page page = (Page) request.getSession().getAttribute("page");
        if (page == null) {
            page = new Page();
            request.getSession().setAttribute("page", page);
        }
        if (currentPage == null) {
            page.setPageNum(1);
        } else {
            page.setPageNum(Integer.parseInt(currentPage));
        }

        //
        page.setPageRows(9);
        page.pageCount(session, page.getPageRows());

        // 分页信息存入Session
        request.getSession().setAttribute("page", page);

        // 获取 List User
        List<User> list = service.listPage(session, userDao, page, condition);
        // 将结果集存入 Session 以供后面的视图页面使用
        request.getSession().setAttribute("list", list);
        session.close();
        return SUCCESS;
    }


}
