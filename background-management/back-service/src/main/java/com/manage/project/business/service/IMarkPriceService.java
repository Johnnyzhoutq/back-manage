package com.manage.project.business.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.manage.project.business.pojo.MarkPrice;

/**
 *  服务层
 * 
 * @author zhoutianqi
 * @date 2018-06-08
 */
public interface IMarkPriceService 
{
	
	/**
     * 查询信息
     * 
     * @param markpriceId ID
     * @return 信息
     */
	public MarkPrice selectMarkPriceById(Integer markpriceId);
	
	/**
     * 查询列表
     * 
     * @param markPrice 信息
     * @return 集合
     */
	public List<MarkPrice> selectMarkPriceList(MarkPrice markPrice);
	
	/**
     * 新增
     * 
     * @param markPrice 信息
     * @return 结果
     */
	public int insertMarkPrice(MarkPrice markPrice);
	
	/**
     * 修改
     * 
     * @param markPrice 信息
     * @return 结果
     */
	public int updateMarkPrice(MarkPrice markPrice);
	
	/**
     * 保存
     * 
     * @param markPrice 信息
     * @return 结果
     */
	public int saveMarkPrice(MarkPrice markPrice);
	
	/**
     * 删除信息
     * 
     * @param markpriceId ID
     * @return 结果
     */
	public int deleteMarkPriceById(Integer markpriceId);
	
	/**
     * 批量删除信息
     * 
     * @param markpriceIds 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteMarkPrice(Integer[] markpriceIds);

	
	public int saveMarkPrice(String price, String selectDate);

	public JSONArray selectAllMarkPrice();
	
}
