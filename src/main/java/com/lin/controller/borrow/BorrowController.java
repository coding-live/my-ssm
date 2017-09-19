package com.lin.controller.borrow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.dto.borrow.BorrowInvestDto;
import com.lin.model.borrow.Borrow;
import com.lin.model.user.User;
import com.lin.service.borrow.BorrowService;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

	@Autowired
	private BorrowService borrowService;
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/borrowsInit", method = RequestMethod.GET)
	public String borrowsInit(Model model) {
		System.out.println("进入borrowsInit方法");
		List<Borrow> borrows = borrowService.selectBorrows();
		model.addAttribute("borrows", borrows);
		return "borrow/borrows";
	}

	@RequestMapping(value = "/export")
	@ResponseBody
	public String export(HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		try {
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String(("BorrowInfo" + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
			String[] titles = { "产品编号", "产品名称", "借款金额", "投资金额"};
			borrowService.export(titles, out);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败";
		}
	}

	@RequestMapping(value = "/invest", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> invest(BorrowInvestDto borrowInvestDto) {
		System.out.println("进入invest方法");
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		if (user == null) {
			map.put("msg", "用户未登录");
			return map;
		}
		if (borrowInvestDto.getInvestAmount() != null
				&& borrowInvestDto.getInvestAmount().doubleValue() > 0) {
			if (borrowService.invest(borrowInvestDto)) {
				System.out.println("投资成功");
				map.put("result", true);
				map.put("msg", "投资成功");
			} else {
				System.out.println("投资失败");
				map.put("msg", "投资失败，请重试");
			}
		} else {
			map.put("msg", "投资金额不能为0");
		}
		return map;
	}
}
