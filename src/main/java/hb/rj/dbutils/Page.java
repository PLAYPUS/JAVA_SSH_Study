package hb.rj.dbutils;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Page {
    private int pageNum = 0;
    private int pageCount;
    private int pageRows;

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
    public void pageCount(Session session,int Rows){
        String hql = "select count(*) as Rows from User";
        Query query = session.createQuery(hql);
        List list = query.list();
        Long rowCount = (Long) list.get(0);//取list集合的第一个元素值,里面是表中的记录总数
        if (list!=null){
            this.pageCount=(int) Math.ceil(rowCount/(double) Rows);
        }else {
            this.pageCount=0;
        }
    }
}
