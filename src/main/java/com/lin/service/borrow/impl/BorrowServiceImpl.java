package com.lin.service.borrow.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.dao.borrow.BorrowDao;
import com.lin.dto.borrow.BorrowInvestDto;
import com.lin.model.borrow.Borrow;
import com.lin.service.borrow.BorrowService;

@Service("borrowService")
public class BorrowServiceImpl implements BorrowService{

	@Autowired  
    private BorrowDao borrowDao;
	
	@Override
	public List<Borrow> selectBorrows() {
		return borrowDao.selectBorrows();
	}

	@Override
	@Transactional
	public boolean invest(BorrowInvestDto borrowInvestDto) {
		Borrow lockBorrow = borrowDao.borrowForUpdate(borrowInvestDto.getBorrowId());
		borrowInvestDto.setBorrowId(lockBorrow.getId());
		return borrowDao.updateBorrow(borrowInvestDto) == 1;
	}

	@Override
	public void export(String[] titles, ServletOutputStream out) throws Exception {
		try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet();
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow hssfRow = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //居中样式
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = hssfRow.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示                
            }
            
            // 第五步，写入实体数据 
            List<Borrow> borrows = borrowDao.selectBorrows();        

            if(borrows != null && !borrows.isEmpty()){
                for (int i = 0; i < borrows.size(); i++) {
                    hssfRow = hssfSheet.createRow(i+1);                
                    Borrow borrow = borrows.get(i);
                    
                    // 第六步，创建单元格，并设置值
                    Long borrowId = null;
                    if(borrow.getId() != null){
                    	borrowId = borrow.getId();
                    }
                    hssfRow.createCell(0).setCellValue(borrowId);
                    String borrowName = "";
                    if(borrow.getBorrowName() != null){
                    	borrowName = borrow.getBorrowName();
                    }
                    hssfRow.createCell(1).setCellValue(borrowName);
                    BigDecimal borrowAmount = null;
                    if(borrow.getBorrowAmount() != null){
                    	borrowAmount = borrow.getBorrowAmount();
                    }
                    hssfRow.createCell(2).setCellValue(borrowAmount.toString());
                    BigDecimal hasInvestAmount = null;
                    if(borrow.getHasInvestAmount() != null) {
                    	hasInvestAmount = borrow.getHasInvestAmount();
                    }
                    hssfRow.createCell(3).setCellValue(hasInvestAmount.toString());
                }
            }
            
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();
            } catch (Exception e) {
            	workbook.close();
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }    
	}

}
