package hb.rj.dbutils;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

//用来存放相关信息
public class Page {
    private int pageNum;//当前页码
    private int pageCount; //总页数
    private int pageRows; //每页总页数

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }
    //计算总页数
    public void pageCount(Session session,int Rows){
        String hql="select count(*) as Rows from User";
        Query query=session.createQuery(hql);
        List list=query.list(); //执行sql语句返回结果集
        Long rowCount=(Long)list.get(0);//取list集合的第一个元素值，里面是表中的记录总数
        if (list!=null){
            this.pageCount=(int) Math.ceil(rowCount/(double) Rows); //向上取整 1.5页那总页数得是2页
        }else {
            this.pageCount=0;//如果表里没有数据，那表可分为0页。
        }


    }
}



























