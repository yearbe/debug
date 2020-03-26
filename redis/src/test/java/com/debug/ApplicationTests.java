package com.debug;

import java.io.Serializable;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private Jedis jedis;

	@Test
	public void contextLoads() {
		/*redisTemplate.opsForValue().set("test_demo", "mingdao-info.com");
		logger.info("{} - {}", "test_demo", redisTemplate.opsForValue().get("test_demo"));
		redisTemplate.delete("test_demo");*/

		jedis.set("test_demo", "mingdao-info.com");
		logger.info("{} - {}", "test_demo", jedis.get("test_demo"));
		jedis.del("test_demo");

		/*Jedis jedis = new Jedis("192.168.8.134", 6379);
		jedis.connect();
		jedis.set("test-key", "test-value");
		Set<String> keys = jedis.keys("*");
		logger.info("-----------{}-----------", keys);
		for (String key : keys) {
			logger.info("==={}: {}====", key, jedis.get(key));
		}
		jedis.del(keys.toArray(new String[0]));*/
	}

}
