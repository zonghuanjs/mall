package com.mall.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mall.dao.MemberDao;
import com.mall.entity.Member;
import com.mall.service.MemberService;
import com.mall.util.CommonUtil;

@Service
public class MemberServiceImpl extends BaseServiceImpl<Long, Member> implements MemberService
{
	//会员删除事件
	//private ServiceEvent deleteEvent = new ServiceEvent();
	
	public MemberServiceImpl()
	{
		
	}
	
	/*@Resource
	public void addObserver(MemberDeleteEventObserver observer)
	{
		//deleteEvent.addObserver(observer);
	}*/
	
	
	@Override
	public boolean add(Member model)
	{
		if(model != null)
		{
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}
	
	@Override
	public boolean update(Member model)
	{
		if(model != null)
		{
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}
	
	@Override
	public boolean delete(Long id)
	{
		Member member = this.get(id);
		
		if(member != null)
		{
			//this.deleteEvent.setChanged();
			//this.deleteEvent.notifyObservers(member);
		}
		
		return super.delete(id);
	}
	
	@Override
	public boolean delete(Long[] ids)
	{
		List<Member> list = this.get(ids);
		
		Iterator<Member> iter = list.iterator();
		while(iter.hasNext())
		{
			Member member = iter.next();
			//this.deleteEvent.setChanged();
			//this.deleteEvent.notifyObservers(member);
		}
		
		return super.delete(ids);
	}

	@Override
	public boolean checkUserNameExists(String name)
	{
		List<Member> list = this.getListFromProperty("username", name);
		return list.size() > 0;
	}

	@Override
	public boolean checkEmailExists(String email)
	{
		List<Member> list = this.getListFromProperty("email", email);
		return list.size() > 0;
	}

	@Override
	public boolean checkMobileExists(String mobile)
	{
		List<Member> list = this.getListFromProperty("mobile", mobile);
		return list.size() > 0;
	}
	
	

	@Override
	public Member getByUsername(String username)
	{
		MemberDao dao = (MemberDao)this.getDao();
		return dao.getByUsername(username);
	}

	@Override
	public Member getFromKey(String key)
	{
		Assert.hasText(key);
		List<Member> list = this.getListFromProperty("tmpKey", key);
		Member member = list.isEmpty() ? null : list.iterator().next();
		if(member != null)
		{
			//失效key
			Date dt = new Date();
			if(dt.getTime() > member.getExpired().getTime())
			{
				//已过期
				member = null;
			}
		}
		return member;
	}

	@Override
	public boolean resetPassword(String key, String username, String password)
	{
		// TODO Auto-generated method stub
		Assert.hasText(password);
		Assert.hasText(username);
		boolean ret = true;
		Member member = this.getFromKey(key);
		if(member != null && member.getExpired() != null && member.getUsername().equals(username))
		{
			//失效key
			member.setTmpKey(null);
			Date dt = new Date();
			if(dt.getTime() > member.getExpired().getTime())
			{
				//已过期
				ret = false;
			}
			else
			{
				member.setPassword(CommonUtil.toMD5(password));
			}
			member.setExpired(null);
			if(!this.update(member))
				ret = false;
		}
		else
			ret = false;
		return ret;
	}

	@Override
	public Member getByMobile(String mobile)
	{
		Member member = null;
		if(StringUtils.isNotEmpty(mobile))
		{
			List<Member> list = this.getListFromProperty("mobile", mobile);
			member = list.isEmpty() ? null : list.iterator().next();
		}
		return member;
	}

}
