package com.mall.view;

import com.mall.entity.Member;
import com.mall.util.SystemConfigUtil;

public class MemberView {
	private Member member;// 会员数据

	public MemberView(Member member) {
		this.member = member;
		SystemConfigUtil.getSystemConfig();
	}

	public Member getMember() {
		return member;
	}

	/**
	 * 手机号是否绑定
	 * 
	 * @return true, 已绑定; false, 否则
	 */
	public boolean getMobileValidated() {
		boolean validated = false;

		if (this.member.getAttributes().get(Member.Keys.mobileValidated) != null) {
			validated = member.getAttributes().get(Member.Keys.mobileValidated).equals("true");
		}

		return validated;
	}

	/**
	 * 邮件是否验证
	 * 
	 * @return 已验证, 返回true; 否则, 返回false
	 */
	public boolean getMailValidated() {
		boolean validated = false;

		if (this.member.getAttributes().get(Member.Keys.mailValidated) != null) {
			validated = member.getAttributes().get(Member.Keys.mailValidated).equals("true");
		}

		return validated;
	}
}
