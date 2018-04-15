package com.jingxi.sso.dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

/**
 * redis集群版客户端
 * <p>Title: JedisClientCluster</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年8月21日下午3:06:25
 * @version 1.0
 */
public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisClientCluster;
	
	@Override
	public String get(String key) {
		String string = jedisClientCluster.get(key);
		return string;
	}

	@Override
	public String set(String key, String value) {
		String string = jedisClientCluster.set(key, value);
		return string;
	}

	@Override
	public long incr(String key) {
		Long result = jedisClientCluster.incr(key);
		return result;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Long result = jedisClientCluster.hset(hkey, key, value);
		return result;
	}

	@Override
	public String hget(String hkey, String key) {
		String string = jedisClientCluster.hget(hkey, key);
		return string;
	}

	@Override
	public Long del(String key) {
		Long result = jedisClientCluster.del(key);
		return result;
	}

	@Override
	public Long hdel(String hkey, String key) {
		Long result = jedisClientCluster.hdel(hkey, key);
		return result;
	}

	@Override
	public Long expire(String key, int second) {
		Long result = jedisClientCluster.expire(key, second);
		return result;
	}

}
