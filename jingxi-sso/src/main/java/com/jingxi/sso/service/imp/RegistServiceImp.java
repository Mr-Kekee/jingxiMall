package com.jingxi.sso.service.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.mapper.TbUserMapper;
import com.jingxi.model.TbUser;
import com.jingxi.model.TbUserExample;
import com.jingxi.sso.service.RegistService;

@Service
@Transactional
public class RegistServiceImp implements RegistService {

	@Autowired
	private TbUserMapper userMapper;
	
	@Override
	public JingXiResult checkData(String content, Integer type) throws Exception{
		//创建查询条件
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		//对数据进行校验：1、2、3分别代表username、phone、email
		//用户名校验
		if (1 == type) {
			criteria.andUsernameEqualTo(content);
		//电话校验
		} else if ( 2 == type) {
			criteria.andPhoneEqualTo(content);
		//email校验
		} else {
			criteria.andEmailEqualTo(content);
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return JingXiResult.ok(true);
		}
		return JingXiResult.ok(false);
	}

	@Override
	public JingXiResult register(TbUser user) {
		//有效性验证
		if (StringUtils.isBlank(user.getUsername())) {
			return JingXiResult.build(400, "用户名不能为空");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			return JingXiResult.build(400, "密码不能为空");
		}
		//密码进行md5加密
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(password);
		
		//补全user对象中的信息
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//把用户信息插入到数据库
		userMapper.insert(user);
		
		return JingXiResult.ok();
	}

}
