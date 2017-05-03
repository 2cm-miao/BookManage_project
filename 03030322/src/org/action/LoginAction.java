package org.action;

import java.util.Map;

import org.dao.LoginDao;
import org.model.Login;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private Login login;
	private String message;
	@Override
	public String execute() throws Exception {
		LoginDao loginDao=new LoginDao();
		Login log=loginDao.checkLogin(login.getName(), login.getPassword());
		if(log!=null){
			Map session=ActionContext.getContext().getSession();
			session.put("login", log);
			//return "admin";
			//登录成功，盘算角色为管理员还是学生，true表示管理员，false表示学生
			if(log.isRole()){
				return "admin";
			}else{
				return "student";
			}
		}else{
			return ERROR;
		}
	}
	
	//增加验证功能，验证登录名和密码是否为空
	public void validate(){
		if(login.getName()==null||login.getName().equals("")){
			this.addFieldError("name","用户名不能为空！");
		}else if(login.getPassword()==null||login.getPassword().equals("")){
			this.addFieldError("password", "密码不能为空！");
		}
	}
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
	//属性message的get/set方法
	public String getMessage() {
		return message;
	}
	public void setMessage(String message){
		this.message=message;
	}

}
