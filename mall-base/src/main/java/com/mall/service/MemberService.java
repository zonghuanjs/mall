package com.mall.service;

import com.mall.entity.Member;

public interface MemberService extends BaseService<Long, Member> {
	/**
	 * 检测用户名是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkUserNameExists(String name);

	/**
	 * 检测email是否存在
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkEmailExists(String email);

	/**
	 * 检测手机号是否绑定
	 * 
	 * @param mobile
	 * @return
	 */
	public boolean checkMobileExists(String mobile);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public Member getByUsername(String username);

	/**
	 * 根据KEY查找用户
	 * 
	 * @param key
	 * @return
	 */
	public Member getFromKey(String key);

	/**
	 * 根据手机号查找用户
	 * 
	 * @param mobile
	 *            手机号
	 * @return 存在,返回用户对象; 否则, 返回null
	 */
	public Member getByMobile(String mobile);

	/**
	 * 重置用户密码
	 * 
	 * @param key
	 *            密码Key
	 * @param username
	 *            用户名
	 * @param password
	 *            新密码
	 * @return
	 */
	public boolean resetPassword(String key, String username, String password);

}
