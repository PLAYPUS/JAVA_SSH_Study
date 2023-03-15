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

    @Override
    public String execute() throws Exception {
        //获取request对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //获取hibernate数据库会话对象
        Session session = userDao.getSessionFactory().openSession();
        //通过request对象获取url中的参数
        String currentPage = request.getParameter("CurrentPage"); //获取当前页
        int conIndex = 0; //查询类型  0.全部记录  1.id号  2.用户名 3.按部门查找
        if (request.getParameter("conIndex") == null) {
            conIndex = 0;
        } else {
            conIndex = Integer.parseInt(request.getParameter("conIndex")); //取list页面传来的查询类型
        }
        Condition condition = new Condition(); //创建搜索条件对象
        condition.setConIndex(conIndex); //设置查询条件
        condition.setTextSearch(request.getParameter("text_search")); //获取搜索条件的值

        Page page = (Page) request.getSession().getAttribute("page"); //取出分页对象
        if (page == null) {
            page = new Page();
            request.getSession().setAttribute("page", page);
        }
        if (currentPage == null) {
            page.setPageNum(1); //当前页如果为空，那取第1页
        } else {
            page.setPageNum(Integer.parseInt(currentPage)); //设置当前页为url传入的页号
        }
        page.setPageRows(9); //每页的记录数
        page.pageCount(session, page.getPageRows()); //计算总页数
        request.getSession().setAttribute("page", page); //将分页信息存入session中

        List<User> list = service.listPage(session, userDao, page, condition);
        request.getSession().setAttribute("userList", list); //将结果集存入Session中，供视图页使用
        session.close(); //释放数据库连接资源
        return SUCCESS;
    }
}
