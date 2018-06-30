package com.manage.project.process.person;

import com.manage.framework.web.page.TableDataInfo;


public interface IPersonService
{

    /**
     *  查询用户数据列表
     * @param page
     * @param pagesize
     * @param searchValue
     * @return
     */
	public TableDataInfo selectAllPersonList(String page, String pagesize, String searchValue);

	
	
	/**
	 *  点击业绩弹框数据展示 
	 * @param userId
	 * @return
	 */
	public Achievement getAchievementDataByUserId(String userId);

}
