package com.mall.view;

import java.util.LinkedList;
import java.util.List;

import com.mall.entity.Admin;

public class AdminView {
	private Admin admin;// 管理员数据

	public AdminView(Admin admin) {
		this.admin = admin;
	}

	/**
	 * 管理员权限列表
	 * 
	 * @return
	 */
	public List<String> getAuthorities() {
		List<String> list = new LinkedList<>();
		list.addAll(admin.getAuthorities());
		return list;
	}
}
