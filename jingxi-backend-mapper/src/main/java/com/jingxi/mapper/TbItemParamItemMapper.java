package com.jingxi.mapper;

import com.jingxi.model.TbItemParamItem;
import com.jingxi.model.TbItemParamItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemParamItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    long countByExample(TbItemParamItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int deleteByExample(TbItemParamItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int insert(TbItemParamItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int insertSelective(TbItemParamItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    List<TbItemParamItem> selectByExampleWithBLOBs(TbItemParamItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    List<TbItemParamItem> selectByExample(TbItemParamItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    TbItemParamItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int updateByExampleSelective(@Param("record") TbItemParamItem record, @Param("example") TbItemParamItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") TbItemParamItem record, @Param("example") TbItemParamItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int updateByExample(@Param("record") TbItemParamItem record, @Param("example") TbItemParamItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int updateByPrimaryKeySelective(TbItemParamItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(TbItemParamItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_param_item
     *
     * @mbg.generated Wed Sep 13 15:26:51 CST 2017
     */
    int updateByPrimaryKey(TbItemParamItem record);
}