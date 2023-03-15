package hb.rj.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import hb.rj.dao.UserDao;
import hb.rj.dbutils.Page;
import hb.rj.pojo.User;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;

public class DeleteByIdAction extends ActionSupport implements ModelDriven<User> {
    private User user;
    UserDao userDao;
    private int pageNum;


    @Override
    public User getModel() {
        return user;
    }

    @Override
    public String execute() throws Exception {
        //获取request对象
        HttpServletRequest request = ServletActionContext.getRequest();

        Session session = userDao.getSessionFactory().openSession();
        userDao.deleteUser(session, user.getId()); //删除指定id的用户

        Page page = (Page) request.getSession().getAttribute("page");
        page.pageCount(session, page.getPageRows());//重新计算总页数
        pageNum = page.getPageNum(); //获取删除记录所在的页号
        session.close();
        return SUCCESS;
    }


}
