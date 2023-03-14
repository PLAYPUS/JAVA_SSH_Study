package hb.rj.dao;

import hb.rj.dbutils.Page;
import hb.rj.pojo.Condition;
import hb.rj.pojo.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements user{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean DlYz(Session session, String userName, String passWord) {
        String hql = "from User where userName=:userName and passWord=:passWord";
        Query query = session.createQuery(hql);
        query.setParameter("userName",userName);
        query.setParameter("passWord",passWord);
        List<User> list = query.list();
        if (list.size()==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean searchUser(Session session, String userName) {
        String hql = "from User where userName=:userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName",userName);
        List<User> list = query.list();
        if (list.size()==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public int addUser(Session session, User user) {
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
            transaction.commit();
            return 1;
        }catch (Exception e) {
            transaction.rollback();
            return 0;
        }
    }

    @Override
    public int deleteUser(Session session, int id) {
        Transaction transaction = session.beginTransaction();
        try {
            User user = (User) session.get(User.class,id);
            session.delete(user);
            transaction.commit();
            return 1;
        }catch (Exception e){
            transaction.rollback();
            return 0;
        }
//        String hql = "delete from User where id=?";
//        Query query = session.createQuery(hql);
//        query.setParameter(0,id);
//        int num = query.executeUpdate();
//        transaction.commit();
//        return num;
    }

    @Override
    public User selectById(Session session, int id) {
        User user = (User) session.get(User.class,id);
        return user;
    }

    @Override
    public int updateUser(Session session, User user) {
        Transaction transaction = session.beginTransaction();
        try{
            session.update(user);
            transaction.commit();
            return 1;
        }catch (Exception e){
            transaction.rollback();
            return 0;
        }
    }

    @Override
    public List<User> getPage(Session session, Page page, Condition condition) {
        String hql = "";
        int conIndex = condition.getConIndex();
        Query query = null;
        switch (conIndex){
            case 0:
                hql = "from User";
                query = session.createQuery(hql);
                query.setFirstResult(page.getPageNum()*page.getPageRows()-page.getPageRows());
                query.setMaxResults(page.getPageRows());
                break;
            case 1:
                hql = "from User where id=:id";
                query = session.createQuery(hql);
                query.setParameter("id",Integer.parseInt(condition.getTextSearch()));
                query.setFirstResult(page.getPageNum()*page.getPageRows()-page.getPageRows());
                query.setMaxResults(page.getPageRows());
                break;
            case 2:
                hql = "from User where userName like :str";
                query = session.createQuery(hql);
                query.setParameter("str","%"+condition.getTextSearch()+"%");
                query.setFirstResult(page.getPageNum()*page.getPageRows()-page.getPageRows());
                query.setMaxResults(page.getPageRows());
                break;
            case 3:
                hql = "from User where dept =: dept";
                query = session.createQuery(hql);
                query.setParameter("dept",condition.getTextSearch());
                query.setFirstResult(page.getPageNum()*page.getPageRows()-page.getPageRows());
                query.setMaxResults(page.getPageRows());
                break;
        }
        List<User> list = query.list();
        return list;
    }
}
