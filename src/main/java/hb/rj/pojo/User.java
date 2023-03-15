package hb.rj.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "userName", nullable = false, length = 12)
    private String userName;
    @Basic
    @Column(name = "passWord", nullable = true, length = 10)
    private String passWord;
    @Basic
    @Column(name = "dept", nullable = true, length = 10)
    private String dept;
    @Basic
    @Column(name = "createUser", nullable = true, length = 12)
    private String createUser;
    @Basic
    @Column(name = "createDate", nullable = true)
    private Timestamp createDate;
    @Basic
    @Column(name = "remark", nullable = true, length = 100)
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
