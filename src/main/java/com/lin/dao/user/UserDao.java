package com.lin.dao.user;

import com.lin.model.user.User;

public interface UserDao {
	
	/**
	 * 根据编号获取用户
	 * @param userId
	 * @return
	 */
	public User selectUserById(Long userId);

	/**
	 * 根据用户实体查询数据
	 * @param user
	 * @return
	 */
	public User selectUserByModel(User user);

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int update(User user);

	/**
	 * 用户充值
	 * @param id
	 * @return
	 */
	public User selectUserForUpdate(Long id);

	/**
	 * 更新账户余额
	 * @param userAccount
	 * @return
	 */
	public int updateBalance(User userAccount);  
}