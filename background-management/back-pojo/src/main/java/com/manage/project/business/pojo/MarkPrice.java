package com.manage.project.business.pojo;

import com.manage.project.base.pojo.BaseEntity;
import java.util.Date;

/**
 * 表 business_mark_price
 * 
 * @author zhoutianqi
 * @date 2018-06-08
 */
public class MarkPrice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 今日牌价ID */
	private Integer markpriceId;
	
	/** 今日牌价价格 */
	private String markPrice;
	
	/** 牌价日期 */
	private String markPriceTime;
	
	/** 操作日期 */
	private Date operDate;
	
	/** 操作员 */
	private Long operUser;

	/**
	 * 设置：今日牌价ID
	 */
	public void setMarkpriceId(Integer markpriceId) 
	{
		this.markpriceId = markpriceId;
	}
	
	/**
	 * 获取：今日牌价ID
	 */
	public Integer getMarkpriceId() 
	{
		return markpriceId;
	}
	
	/**
	 * 设置：今日牌价价格
	 */
	public void setMarkPrice(String markPrice) 
	{
		this.markPrice = markPrice;
	}
	
	/**
	 * 获取：今日牌价价格
	 */
	public String getMarkPrice() 
	{
		return markPrice;
	}
	
	
	public String getMarkPriceTime() {
		return markPriceTime;
	}

	public void setMarkPriceTime(String markPriceTime) {
		this.markPriceTime = markPriceTime;
	}

	/**
	 * 设置：操作日期
	 */
	public void setOperDate(Date operDate) 
	{
		this.operDate = operDate;
	}
	
	/**
	 * 获取：操作日期
	 */
	public Date getOperDate() 
	{
		return operDate;
	}

	public Long getOperUser() {
		return operUser;
	}

	public void setOperUser(Long operUser) {
		this.operUser = operUser;
	}

	
}
