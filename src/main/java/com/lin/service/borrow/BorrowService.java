package com.lin.service.borrow;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.lin.dto.borrow.BorrowInvestDto;
import com.lin.model.borrow.Borrow;

public interface BorrowService {

	List<Borrow> selectBorrows();

	boolean invest(BorrowInvestDto borrowInvestDto);

	void export(String[] titles, ServletOutputStream out) throws Exception;

}
