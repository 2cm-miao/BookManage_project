package org.model;

public class Login {
	private Integer id;//用户ID
	private String name;//用户名
	private String password;//密码
	private boolean role;//角色
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passwrod) {
		this.password = passwrod;
	}
	public boolean isRole() {
		return role;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Login [id=" + id + ", name=" + name + ", passwrod=" + password
				+ ", role=" + role + "]";
	}

	
}
