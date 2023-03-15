package hb.rj.action;

import com.opensymphony.xwork2.ActionSupport;
import hb.rj.dao.UserDao;
import hb.rj.dbutils.Page;
import hb.rj.pojo.User;
import hb.rj.service.Service;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

public class AddUserAction extends ActionSupport {
    private User user;
    private UserDao userDao;
    private Service service;
    private int pageNum; //要打开的页码


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String execute() throws Exception {
        //获取request对象
        HttpServletRequest request = ServletActionContext.getRequest();
        Session session=userDao.getSessionFactory().openSession();
        user.setUserName(request.getParameter("name"));
        user.setPassWord(request.getParameter("pwd"));
        user.setDept(request.getParameter("dept"));
        user.setRemark(request.getParameter("area"));
        user.setCreateUser((String) request.getSession().getAttribute("LoginUser"));
        Timestamp t=new Timestamp(new Date().getTime()); //取当前时间
        user.setCreateDate(t);
//        System.out.println(user.toString());
        service.UserAdd(session,userDao,user); //将用户添加到数据库中
        Page page = (Page) request.getSession().getAttribute("page");
        page.pageCount(session,page.getPageRows()); //重新计算总页数
        pageNum=page.getPageNum(); //添加用户后，返回List视图，返回最后一页

        return SUCCESS;
    }
}
