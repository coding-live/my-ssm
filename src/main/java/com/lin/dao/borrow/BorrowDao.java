package com.lin.dao.borrow;

import java.util.List;

import com.lin.dto.borrow.BorrowInvestDto;
import com.lin.model.borrow.Borrow;

public interface BorrowDao {
	
	
	public List<Borrow> selectBorrows();

	
	public Borrow borrowForUpdate(Long borrowId);

	public int updateBorrow(BorrowInvestDto borrowInvestDto);
}