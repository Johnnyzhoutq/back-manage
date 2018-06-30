package com.manage.project.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.common.util.StringUtils;
import com.manage.common.utils.security.ShiroUtils;
import com.manage.project.business.dao.MarkPriceMapper;
import com.manage.project.business.pojo.MarkPrice;

/**
 *  服务层实现
 * 
 * @author zhoutianqi
 * @date 2018-06-08
 */
@Service
public class MarkPriceServiceImpl implements IMarkPriceService 
{
	@Autowired
	private MarkPriceMapper markPriceMapper;
	
	/**
     * 查询信息
     * 
     * @param markpriceId ID
     * @return 信息
     */
	 @Override
	public MarkPrice selectMarkPriceById(Integer markpriceId)
	{
	    return markPriceMapper.selectMarkPriceById(markpriceId);
	}
	
	/**
     * 查询列表
     * 
     * @param markPrice 信息
     * @return 集合
     */
	 @Override
	public List<MarkPrice> selectMarkPriceList(MarkPrice markPrice)
	{
	    return markPriceMapper.selectMarkPriceList(markPrice);
	}
	
    /**
     * 新增
     * 
     * @param markPrice 信息
     * @return 结果
     */
	 @Override
	public int insertMarkPrice(MarkPrice markPrice)
	{
	    return markPriceMapper.insertMarkPrice(markPrice);
	}
	
	/**
     * 修改
     * 
     * @param markPrice 信息
     * @return 结果
     */
	 @Override
	public int updateMarkPrice(MarkPrice markPrice)
	{
	    return markPriceMapper.updateMarkPrice(markPrice);
	}
	
	/**
     * 保存
     * 
     * @param markPrice 信息
     * @return 结果
     */
	 @Override
	public int saveMarkPrice(MarkPrice markPrice)
	{
	    Integer markpriceId = markPrice.getMarkpriceId();
		int rows = 0;
		if (StringUtils.isNotNull(markpriceId))
        {
		    rows = markPriceMapper.updateMarkPrice(markPrice);
		}
		else
        {
		    rows = markPriceMapper.insertMarkPrice(markPrice);
		}
		return rows;
	}
	
	/**
     * 删除信息
     * 
     * @param markpriceId ID
     * @return 结果
     */
	 @Override
	public int deleteMarkPriceById(Integer markpriceId)
	{
	    return markPriceMapper.deleteMarkPriceById(markpriceId);
	}
	
	/**
     * 批量删除对象
     * 
     * @param markpriceIds 需要删除的数据ID
     * @return 结果
     */
	 @Override
	public int batchDeleteMarkPrice(Integer[] markpriceIds)
	{
		return markPriceMapper.batchDeleteMarkPrice(markpriceIds);
	}

	 
	@Override
	public int saveMarkPrice(String price, String selectDate) {
		     int count = 0;
		     if (StringUtils.isNotNull(price) && StringUtils.isNotNull(selectDate))
		     {
		    	 MarkPrice selectResult = markPriceMapper.selectMarkPriceBySelectDate(selectDate);
		    	 MarkPrice markPrice = new MarkPrice();
		    	 if(selectResult == null) {
				     markPrice.setMarkPrice(price);
				     markPrice.setMarkPriceTime(selectDate);
				     markPrice.setOperUser(ShiroUtils.getUserId());
			         count = markPriceMapper.insertMarkPrice(markPrice);
		    	 } else {
		    		 markPrice.setMarkpriceId(selectResult.getMarkpriceId());
		    		 markPrice.setMarkPrice(price);
				     markPrice.setMarkPriceTime(selectDate);
				     markPrice.setOperUser(ShiroUtils.getUserId());
		    		 count = markPriceMapper.updateMarkPriceBySelectDate(markPrice);
				}
	        }
	        
	        return count;
		}

	
	@Override
	public JSONArray selectAllMarkPrice() {
		JSONArray jsArray = new JSONArray();
    	
    	List<MarkPrice> markPricesList = markPriceMapper.selectAllMarkPrice();
    	for(MarkPrice markPrice : markPricesList) {
    		JSONObject jsonParam = new JSONObject();
    		jsonParam.put("title", markPrice.getMarkPrice());
    		jsonParam.put("start",  markPrice.getMarkPriceTime());
    		jsonParam.put("end",  markPrice.getMarkPriceTime());
    		jsArray.add(jsonParam);
    	}
    	
    	return jsArray;
	}
}
	

