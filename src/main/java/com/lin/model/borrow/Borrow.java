package com.lin.model.borrow;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 标的类
 */
public class Borrow implements Serializable {
	
	private static final long serialVersionUID = 2095613811671927354L;
	
	/**
	 * 编号
	 */
	private Long	id;  
	
    /**
     * 标名
     */
    private String	borrowName;  
    
    /**
     * 标的金额
     */
    private BigDecimal borrowAmount;
    
    /**
     * 已投金额
     */
    private BigDecimal hasInvestAmount;
    
    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public BigDecimal getHasInvestAmount() {
		return hasInvestAmount;
	}

	public void setHasInvestAmount(BigDecimal hasInvestAmount) {
		this.hasInvestAmount = hasInvestAmount;
	}

	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
    
}