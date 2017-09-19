package com.lin.service.user;

import com.lin.model.user.User;

public interface UserService {

	/**
	 * 按ID查询用户信息
	 * @param userId
	 * @return
	 */
	User selectUserById(Long userId);

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * 更新用户支付密码
	 * @param user
	 * @return
	 */
	boolean modifyPayPassword(User user);

	/**
	 * 用户充值
	 * @param user
	 * @return
	 */
	boolean recharge(User user, double balance);

}
