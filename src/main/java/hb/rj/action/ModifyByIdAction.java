package hb.rj.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import hb.rj.dao.UserDao;
import hb.rj.pojo.User;
import org.hibernate.Session;

import java.io.Serializable;

public class ModifyByIdAction extends ActionSupport implements ModelDriven<User> {
    private User user;
    private UserDao userDao;

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

    @Override
    public User getModel() {
        return user;
    }

    @Override
    public String execute() throws Exception {
        Session session=userDao.getSessionFactory().openSession();
        user=userDao.selectById(session,user.getId());
        ActionContext actionContext=ActionContext.getContext();
        actionContext.getSession().put("user",user); //将user存入session域，供后面打开的视图页面使用
        session.close();
        return super.execute();
    }
}
