package hb.rj.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LoginOutAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        //获取request对象
        HttpServletRequest request= ServletActionContext.getRequest();
        request.getSession().removeAttribute("LoginUser"); //移除会话中的LoginUser对象
        request.getSession().removeAttribute("");
        return SUCCESS;
    }
}
