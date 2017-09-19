package com.lin.service.user.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lin.dao.user.UserDao;
import com.lin.model.user.User;
import com.lin.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired  
    private UserDao userDao;

	@Override
	public User selectUserById(Long userId) {
		return userDao.selectUserById(userId);  
	}

	@Override
	public User login(User user) {
		return userDao.selectUserByModel(user);  
	}

	@Override
	public boolean modifyPayPassword(User user) {
		return userDao.update(user) == 1;
	}

	@Override
	@Transactional
	public boolean recharge(User user, double balance) {
		User userAccount  = userDao.selectUserForUpdate(user.getId());  
		userAccount.setBalance(new BigDecimal(balance));
		return userDao.updateBalance(userAccount) == 1;
	}

}