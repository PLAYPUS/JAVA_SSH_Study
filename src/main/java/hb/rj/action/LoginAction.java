package hb.rj.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import hb.rj.dao.UserDao;
import hb.rj.dbutils.Page;
import hb.rj.pojo.Condition;
import hb.rj.pojo.User;
import hb.rj.service.Service;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
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

    @Override
    public User getModel() {
        return user;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        ActionContext actionContext = ActionContext.getContext();
        Session session = userDao.getSessionFactory().openSession();
        if (userDao.DlYz(session,user.getUserName(),user.getPassWord())){
            request.getSession().setAttribute("LoginUser",user.getUserName());
            String currentPage = request.getParameter("CurrentPage");
            int conIndex = 0;
            if (request.getParameter("conIndex") == null){
                conIndex = 0;
            }else {
                conIndex = Integer.parseInt(request.getParameter("conIndex"));
            }
            Condition condition = new Condition();
            condition.setConIndex(conIndex);
            condition.setTextSearch(request.getParameter("text_search"));
            Page page = (Page) request.getSession().getAttribute("page");
            if (page == null){
                page = new Page();
                request.getSession().setAttribute("page",page);
            }
            if (currentPage == null){
                page.setPageNum(1);
            }else {
                page.setPageNum(Integer.parseInt(currentPage));
            }
            page.setPageRows(9);
            page.pageCount(session,page.getPageRows());
            request.getSession().setAttribute("page",page);
            List<User> list = service.listPage(session,userDao,page,condition);
            request.getSession().setAttribute("userList",list);
            session.close();
            return SUCCESS;
        }else {
            actionContext.put("message","用户名或密码错误");
            return ERROR;
        }
    }
}
