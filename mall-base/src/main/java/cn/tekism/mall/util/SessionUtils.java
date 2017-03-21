package cn.tekism.mall.util;

import java.util.HashMap;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * Session共享方案
 * @author zonghuan
 *
 */

public abstract class SessionUtils
{
	private static final HashMap<String, HttpSession> sessions = new HashMap<>();
	
	/**
	 * 根据Sessionid 获取Session
	 * @param sid
	 * @return
	 */
	public static HttpSession getSession(String sid)
	{
		HttpSession session = null;
		if(StringUtils.isNotEmpty(sid))
		{
			session = sessions.get(sid);
		}
		
		return session;
	}
	
	/**
	 * 添加session
	 * @param session
	 */
	public static synchronized void addSession(HttpSession session)
	{
		if(session == null)
		{
			return;
		}
		
		String sid = session.getId();
		if(sessions.get(sid) == null)
		{
			sessions.put(sid, session);
		}
	}
	
	/**
	 * 移除session
	 * @param session
	 */
	public static synchronized void removeSession(HttpSession session)
	{
		if(session == null)
		{
			return;
		}
		
		String sid = session.getId();
		if(sessions.containsKey(sid))
		{
			sessions.remove(sid);
		}
	}
	
	/**
	 * 获取保存的Session数量
	 * @return
	 */
	public static long getCount()
	{
		return sessions.size();
	}
}
