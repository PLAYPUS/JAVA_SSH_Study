package hb.rj.dao;

import hb.rj.dbutils.Page;
import hb.rj.pojo.User;
import org.hibernate.Session;

import java.util.List;
import hb.rj.pojo.Condition;


public interface user {
    boolean DlYz(Session session,String userName,String passWord);
    boolean searchUser(Session session,String userName);
    int addUser(Session session, User user);
    int deleteUser(Session session,int id);
    User selectById(Session session, int id);
    int updateUser(Session session,User user);
    List<User> getPage(Session session, Page page, Condition condition);
}
