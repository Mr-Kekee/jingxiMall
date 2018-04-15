package com.jingxi.rest.service.imp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.mapper.TbItemMapper;
import com.jingxi.mapper.TbItemParamItemMapper;
import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemParamItem;
import com.jingxi.model.TbItemParamItemExample;
import com.jingxi.rest.dao.JedisClient;
import com.jingxi.rest.service.ItemService;

@Service
public class ItemServiceImp implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Autowired
	private JedisClient jedisClient;
	
	private final String itemBaseInfo = "itemBaseInfo";
	private final String itemParam = "itemParam";
	
	@Override
	public JingXiResult getItemBaseInfo(long itemId) {
		try {
			//添加缓存逻辑
			//从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":"+itemBaseInfo);
			//判断是否有值
			if (!StringUtils.isBlank(json)) {
				//把json转换成java对象
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return JingXiResult.ok(item);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//根据商品id查询商品信息
			TbItem item = itemMapper.selectByPrimaryKey(itemId);
			try {
				//把商品信息写入缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":"+itemBaseInfo, JsonUtils.objectToJson(item));
				//设置key的有效期
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":"+itemBaseInfo, REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JingXiResult.ok(item);
	}

	@Override
	public JingXiResult getItemParam(long itemId) {
		//添加缓存
		try {
			//添加缓存逻辑
			//从缓存中取商品信息，商品id对应的信息
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":"+itemParam);
			//判断是否有值
			if (!StringUtils.isBlank(json)) {
				//把json转换成java对象
				TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return JingXiResult.ok(paramItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据商品id查询规格参数
		//设置查询条件
		TbItemParamItemExample example = new TbItemParamItemExample();
		TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size()>0) {
			TbItemParamItem paramItem = list.get(0);
			try {
				//把商品信息写入缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":"+itemParam,
						JsonUtils.objectToJson(paramItem));
				//设置key的有效期
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":"+itemParam, REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
			e.printStackTrace();
			}
			return JingXiResult.ok(paramItem);
		}
		return JingXiResult.build(400, "无此商品规格");	
	}

}
