package com.manage.project.business.dao;

import java.util.List;

import com.manage.project.business.pojo.MarkPrice;	

/**
 *  数据层
 * 
 * @author zhoutianqi
 * @date 2018-06-08
 */
public interface MarkPriceMapper 
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
     * 删除
     * 
     * @param markpriceId ID
     * @return 结果
     */
	public int deleteMarkPriceById(Integer markpriceId);
	
	/**
     * 批量删除
     * 
     * @param markpriceIds 需要删除的数据ID
     * @return 结果
     */
	public int batchDeleteMarkPrice(Integer[] markpriceIds);

	public List<MarkPrice> selectAllMarkPrice();

	public MarkPrice selectMarkPriceBySelectDate(String selectDate);

	public int updateMarkPriceBySelectDate(MarkPrice markPrice);

}