package hb.rj.action;

import com.opensymphony.xwork2.ActionSupport;
import hb.rj.dao.UserDao;
import hb.rj.pojo.User;
import hb.rj.service.Service;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

public class AddAction extends ActionSupport {
    private User user;
    private UserDao userDao;
    private Service service;

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

    @Override
    public String execute(){
        // Get Request 对象
        HttpServletRequest request = ServletActionContext.getRequest();
        // 获取 hibernate 对象
        Session session = userDao.getSessionFactory().openSession();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setUserName(request.getParameter("userName"));
        user.setPassWord(request.getParameter("passWord"));
        user.setDept(request.getParameter("Dept"));
        user.setCreateUser(request.getParameter("CreateUser"));
        user.setRemark(request.getParameter("remark"));

        Timestamp t =  new Timestamp(new Date().getTime());
        user.setCreateDate(t);
        return null;
    }


}
