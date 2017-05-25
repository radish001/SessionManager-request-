package com.platform.sessionmanager.impl;


import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.platform.sessionmanager.SessionConfig;
import com.platform.sessionmanager.SessionException;
import com.platform.sessionmanager.SessionHandle;
import com.platform.sessionmanager.ThreadLocalUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @description
 * @author 胡晓东
 * @date 2017年5月24日上午10:02:51
 */

public class RedisSessionImpl implements SessionHandle {

	@Autowired
	private SessionConfig sessionConfig;

	private static JedisPool pool = null;

	public synchronized void init() {
		if (pool == null) {
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxTotal(sessionConfig.getRedisMaxTotal());
			jedisPoolConfig.setMaxIdle(sessionConfig.getRedisMinIdle());
			jedisPoolConfig.setMinIdle(sessionConfig.getRedisMinIdle());
			jedisPoolConfig.setTestOnBorrow(true);
			pool = new JedisPool(jedisPoolConfig, sessionConfig.getRedisIp(), sessionConfig.getRedisPort());
		}
	}

	private void expire(Jedis jedis, String sessionId) {
		jedis.expire(sessionId, sessionConfig.getRedisSessionValid());// 设置生存时间
	}

	@Override
	public void setAttribute(String name, String value) throws SessionException {
		String sessionId = ThreadLocalUtil.get();
		if (sessionId != null && !sessionId.equals("")) {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				expire(jedis, sessionId);
				jedis.hset(sessionId, name, value); // 如果filed已经存在，返回0，新建返回1
			} finally {
				jedis.close();
			}
		} else {
			throw new SessionException("ThreadLocalUtil中session不存在");
		}
	}

	@Override
	public String getAttribute(String name) throws SessionException {
		String sessionId = ThreadLocalUtil.get();
		if (sessionId != null && !sessionId.equals("")) {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				if (jedis.exists(sessionId)) {
					expire(jedis, sessionId);
					return jedis.hget(sessionId, name);
				} else {
					return null;
				}
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		} else {
			return null;
		}
	}

	@Override
	public void removeAttribute(String name) throws SessionException {
		String sessionId = ThreadLocalUtil.get();
		if (sessionId != null && !sessionId.equals("")) {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				if (jedis.exists(sessionId)) {
					expire(jedis, sessionId);
					jedis.hdel(sessionId, name);
				}
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.platform.sessionmanager.SessionHandle#getAttributeNames()
	 */
	@Override
	public Enumeration<?> getAttributeNames() throws SessionException {
		String sessionId = ThreadLocalUtil.get();
		if (sessionId != null && !sessionId.equals("")) {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				if (jedis.exists(sessionId)) {
					expire(jedis, sessionId);
					Vector<String> list = new Vector<String>();
					for(Entry<String, String> entry :jedis.hgetAll(sessionId).entrySet()){
						list.add(entry.getKey());
					}
					return list.elements();
				}
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.platform.sessionmanager.SessionHandle#invalidate()
	 */
	@Override
	public void invalidate() throws SessionException {
		String sessionId = ThreadLocalUtil.get();
		if (sessionId != null && !sessionId.equals("")) {
			Jedis jedis = null;
			try {
				jedis = pool.getResource();
				if (jedis.exists(sessionId)) {
					jedis.del(sessionId);
				}
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}


	@Override
	public String getId() {
		return ThreadLocalUtil.get();
	}

}
