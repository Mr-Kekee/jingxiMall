package com.jingxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.EUTreeNode;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.mapper.TbContentCategoryMapper;
import com.jingxi.model.TbContentCategory;
import com.jingxi.model.TbContentCategoryExample;

@Service
public class ContentCategoryServiceImp implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			//创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
	
	@Override
	public JingXiResult insertContentCategory(long parentId, String name) {
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		//'状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//添加记录
		contentCategoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat =
		contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if(!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		//返回结果
		return JingXiResult.ok(contentCategory);
	}

	@SuppressWarnings("static-access")
	@Override
	public JingXiResult deleteContentCategory(long id) {
		contentCategoryMapper.deleteByPrimaryKey(id);
		return new JingXiResult().ok();
	}

	@Override
	public JingXiResult updateContentCategory(long id,String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		Date upDate = new Date();
		contentCategory.setUpdated(upDate);
		//查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat =
			contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
			//判断是否为true
			if(!parentCat.getIsParent()) {
				parentCat.setIsParent(true);
				//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
			}
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return new JingXiResult(name);
	}
}