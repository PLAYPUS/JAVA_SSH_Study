package hb.rj.dao;


import hb.rj.dbutils.Page;
import hb.rj.pojo.Condition;
import hb.rj.pojo.User;
import org.hibernate.Session;

import java.util.List;


public interface user {
    boolean DlYz(Session session,String userName, String passWord);//验证用户是否登录成功
    boolean searchUser(Session session,String userName); //查找用户是否存在
    int addUser(Session session, User user); //新增用户
    int deleteUser(Session session, int id); //删除用户
    User selectById(Session session, int id); //按id号查找用户
    int updateUser(Session session,User user); //修改用户信息`
    List<User> getPage(Session session, Page page, Condition condition); //按页码和条件
}













