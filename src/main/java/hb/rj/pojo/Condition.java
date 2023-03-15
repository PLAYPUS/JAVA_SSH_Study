package hb.rj.pojo;

//没有对应的表是pojo，有对应的表是实体类

public class Condition {
    private int conIndex; //条件序号 0.查找全部记录 1.按id号查找 2.按用户名查找 3.按部门查找
    private String textSearch; //条件的值

    public int getConIndex() {
        return conIndex;
    }

    public void setConIndex(int conIndex) {
        this.conIndex = conIndex;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }
}
