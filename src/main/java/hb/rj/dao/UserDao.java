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

    //验证用户是否登录成功
    @Override
    public boolean DlYz(Session session, String userName, String passWord) {
        String hql="from User where userName=:userName and passWord=:passWord";
        Query query=session.createQuery(hql);
        query.setParameter("userName",userName); //向hql语句传入参数userName
        query.setParameter("passWord",passWord);//向hql语句传入参数passWord
        List<User> list=query.list(); //执行hql语句，返回的结果集是list集合
        if (list.size()==0){
            return false;
        }else{
            return true;
        }
    }

    //查找用户是否存在
    @Override
    public boolean searchUser(Session session, String userName) {
        String hql="from User where userName=:userName";
        Query query=session.createQuery(hql);
        query.setParameter("userName",userName);
        List<User> list=query.list();
        if (list.size()==0){
            return false;
        }else {
            return true;
        }
    }
    //增加用户
    @Override
    public int addUser(Session session, User user) {
        Transaction transaction= session.beginTransaction();
        try {
            session.save(user);//将user保存到数据库中
            transaction.commit();
            return 1;
        }catch (Exception e){
            transaction.rollback();
            return 0;
        }

    }

    //删除用户
    @Override
    public int deleteUser(Session session, int id) {
        Transaction transaction=session.beginTransaction(); //事务
        try {
            User user=(User) session.get(User.class,id);//找到对应id的User对象
            session.delete(user);//删除找到的user对象
            transaction.commit(); //提交事务
            return 1;
        }catch (Exception e){
            transaction.rollback(); //删除失败，数据库回滚
            return 0;
        }

    }

    //按id查找
    @Override
    public User selectById(Session session, int id) {
        User user=(User) session.get(User.class, id); //按id号查找用户
        return user;
    }

    //修改用户信息
    @Override
    public int updateUser(Session session, User user) {
        Transaction transaction=session.beginTransaction(); //开启事务
        try{
            session.update(user); //调用update方法更改记录
            transaction.commit(); //提交事务
            return 1;
        }catch (Exception e){
            transaction.rollback(); //更新失败回滚数据
            return 0;
        }

    }

    @Override
    public List<User> getPage(Session session, Page page, Condition condition) {
        String hql="";
        int conIndex=condition.getConIndex(); //获取查询条件
        Query query=null;
        switch (conIndex){
            case 0: // 0.查找全部记录
                hql="from User";
                query=session.createQuery(hql);
                //当前页第一条记录的索引位置，第一页的第一条记录从0开始
                query.setFirstResult(page.getPageNum()*page.getPageRows()-page.getPageRows()); //得到第一页第一条的索引位置
                query.setMaxResults(page.getPageRows()); //每一页的记录行数
                break;
            case 1: //1.按id号查找
                hql="from User where id=:id";
                query=session.createQuery(hql);
                query.setParameter("id",Integer.parseInt(condition.getTextSearch())); //按id号查找
                break;
            case 2: //2.按用户名查找
                hql="from User where userName like :str";
                query=session.createQuery(hql);
                query.setParameter("str","%"+condition.getTextSearch()+"%");
                //当前页第一条记录的索引位置，第一页的第一条记录从0开始
                query.setFirstResult(page.getPageNum()*page.getPageRows()-page.getPageRows()); //得到第一页第一条的索引位置
                query.setMaxResults(page.getPageRows()); //每一页的记录行数
                break;
            case 3: // 3.按部门查找

                hql="from User where dept=:dept";
                query=session.createQuery(hql);
                query.setParameter("dept",condition.getTextSearch()); //取部门名称
                //当前页第一条记录的索引位置，第一页的第一条记录从0开始
                query.setFirstResult(page.getPageNum()*page.getPageRows()-page.getPageRows()); //得到第一页第一条的索引位置
                query.setMaxResults(page.getPageRows()); //每一页的记录行数
                break;
        }
        List<User> list=query.list();
        return list;
    }
}
