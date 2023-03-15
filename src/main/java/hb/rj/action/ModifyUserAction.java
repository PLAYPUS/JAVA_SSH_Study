package hb.rj.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import hb.rj.dao.UserDao;
import hb.rj.dbutils.Page;
import hb.rj.pojo.User;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

public class ModifyUserAction extends ActionSupport implements ModelDriven<User> {
    private User user;
    private UserDao userDao;
    private int pageNum; //被修改的记录所在的页号


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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public User getModel() {
        return user;
    }

    @Override
    public String execute() throws Exception {
        //获取request对象
        HttpServletRequest request= ServletActionContext.getRequest();
        Session session=userDao.getSessionFactory().openSession();
        user.setCreateUser((String) request.getSession().getAttribute("LoginUser"));
        Timestamp t=new Timestamp(new Date().getTime());
        user.setCreateDate(t);
        userDao.updateUser(session,user); //修改用户记录
        Page page=(Page) request.getSession().getAttribute("page");
        page.pageCount(session,page.getPageRows());
        pageNum=page.getPageNum(); //取当前页号
        session.close();
        return super.execute();
    }
}
