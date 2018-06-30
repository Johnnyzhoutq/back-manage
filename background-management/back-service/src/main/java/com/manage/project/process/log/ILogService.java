package com.manage.project.process.log;

import com.manage.framework.web.page.TableDataInfo;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface ILogService
{


	/**
	 *  日志查询列表
	 * @param page
	 * @param pagesize
	 * @param userId
	 * @param type
	 * @param searchValue
	 * @return
	 */
	TableDataInfo findUserLog( String page,String pagesize, String userId, String type, String searchValue);
}
