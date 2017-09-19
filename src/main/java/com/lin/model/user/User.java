package com.lin.model.user;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户类
 * @author Lin
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 2095613811671927354L;
	
	/**
	 * 用户编号
	 */
	private Long	id;  
	
    /**
     * 用户名
     */
    private String	name;  
    
    /**
     * 用户密码
     */
    private String 	password;

    /**
     * 支付密码
     */
    private String 	payPassword;
    
    /**
     * 账户余额
     */
    private BigDecimal balance;
    
    @Override  
    public String toString() {  
        return "User [id=" + id + ", name=" + name + ", password=" + password + "]";  
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	} 
    
}