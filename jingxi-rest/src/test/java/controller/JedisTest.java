package controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-service.xml");
		JedisPool pool = (JedisPool) context.getBean("redisClient");		
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	
	//@Test
//	public void testJedisSingle() {
//		Jedis jedis = new Jedis("192.168.17.128",6379);
//		jedis.set("key1", "jedis test");
//		String string = jedis.get("key1");
//		System.out.println(string);
//		jedis.close();
//	}
	
//	@Test
//	public void testJedisPool() {
//		JedisPool pool =new JedisPool("192.168.17.128",6379);
//		Jedis jedis = pool.getResource();
//		String string = jedis.get("key1");
//		System.out.println(string);
//		jedis.close();
//		pool.close();
//	}
}
