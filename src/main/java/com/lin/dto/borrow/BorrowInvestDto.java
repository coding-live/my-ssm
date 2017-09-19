package com.lin.dto.borrow;

import java.math.BigDecimal;

public class BorrowInvestDto {
	
	private Long borrowId;
	
	private Long userId;
	
	private BigDecimal investAmount;

	public Long getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	
}
