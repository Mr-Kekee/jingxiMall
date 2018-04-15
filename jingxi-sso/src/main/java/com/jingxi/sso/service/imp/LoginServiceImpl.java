package com.jingxi.sso.service.imp;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.CookieUtils;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.mapper.TbUserMapper;
import com.jingxi.model.TbUser;
import com.jingxi.model.TbUserExample;
import com.jingxi.sso.dao.JedisClient;
import com.jingxi.sso.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	
	@Value("${REDIS_SESSION_EXPIRE}")
	private Integer REDIS_SESSION_EXPIRE;
	
	private static String TT_TOKEN="TT_TOKEN";
	
	@Override
	public JingXiResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		
		//有效性验证
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return JingXiResult.build(400, "有户名和密码不能为空");
		}
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.isEmpty()) {
			return JingXiResult.build(400, "用户名或密码错误");
		}
		//判断密码是否正确
		TbUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return JingXiResult.build(400, "用户名或密码错误");
		}
		//生成token
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		//把用户信息写入redis
		//把用户的密码清空，为了安全。
		user.setPassword(null);
		jedisClient.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		jedisClient.expire(REDIS_SESSION_KEY + ":" + token, REDIS_SESSION_EXPIRE);
		//把token写入cookie
		CookieUtils.setCookie(request, response, TT_TOKEN, token);
		//返回token
		return JingXiResult.build(200, "OK", token);
	}

}
