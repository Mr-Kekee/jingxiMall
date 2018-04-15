package com.jingxi.rest.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.mapper.TbItemCatMapper;
import com.jingxi.model.TbItemCat;
import com.jingxi.model.TbItemCatExample;
import com.jingxi.model.TbItemCatExample.Criteria;
import com.jingxi.rest.dao.JedisClient;
import com.jingxi.rest.pojo.CatNode;
import com.jingxi.rest.pojo.CatResult;
import com.jingxi.rest.service.ItemCatService;

@Service
public class ItemCatServiceImp implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ITEM_CAT_KEY}")
	private String ITEM_CAT_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		try{
			String json = jedisClient.get(ITEM_CAT_KEY);
			if(!StringUtils.isBlank(json)){
				List<CatNode> catNode = JsonUtils.jsonToList(json, CatNode.class);
				//对每一个 itemCat处理
				catResult.setData(catNode);
				return catResult;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		//查询分类列表
		catResult.setData(getCatList(0));
		try{
			jedisClient.set(ITEM_CAT_KEY, JsonUtils.objectToJson(getCatList(0)));
			jedisClient.expire(ITEM_CAT_KEY, REDIS_ITEM_EXPIRE);
		}catch (Exception e){
			e.printStackTrace();
		}
		return catResult;
	}
	/**
	* 查询分类列表
	* <p>Title: getCatList</p>
	* <p>Description: </p>
	* @param parentId
	* @return
	*/
	private List<?> getCatList(long parentId) {
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return makeResultList(list);
	}
	
	//传入查询结果List<TbItemCat>，转为网页需要的结果
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TbItemCat> makeResultList (List<TbItemCat> list) {
		//返回值 list
		List resultList = new ArrayList<>();
		//向 list 中添加节点
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (tbItemCat.getParentId() == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count ++;
				//第一层只取 14 条记录
				if (tbItemCat.getParentId() == 0 && count >=14) {
					break;
				}
				//如果是叶子节点
				} else {
					resultList.add("/products/"+tbItemCat.getId()+".html|" +
					tbItemCat.getName());
			}
		}
		return resultList;
	}
}