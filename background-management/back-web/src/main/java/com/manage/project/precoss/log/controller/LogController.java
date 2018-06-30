package com.manage.project.precoss.log.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.common.constant.Constants;
import com.manage.common.util.ServletUtils;
import com.manage.framework.web.controller.BaseController;
import com.manage.framework.web.page.TableDataInfo;
import com.manage.project.process.log.ILogService;
import com.manage.project.process.person.LogInfo;


/**
 *  业务管理--用户列表 --日志
 * @author hht
 *
 */
@Controller
@RequestMapping("/process/log")
public class LogController extends BaseController
{
    private String prefix = "process/log";
    
    @Autowired
    private ILogService logService;


	/**
	 * 初始化日志页面
	 * @param userId
	 * @param model
	 * @return
	 */
    @RequiresPermissions("process:log:view")
    @GetMapping("/search/{userId}")
    public String log(@PathVariable("userId")String userId,Model model)
    {
    	model.addAttribute("currentUserId", userId);
        return prefix + "/search";
    }
    
  
    
    /**
     *  日志查询列表
     * @param userId
     * @param logType
     * @param model
     * @param logInfo
     * @return
     */
    @RequiresPermissions("process:person:list")
    @GetMapping("/list/{userId}/{logType}")
    @ResponseBody
    public TableDataInfo log(@PathVariable("userId") String userId, @PathVariable("logType") String logType,
    		Model model, LogInfo logInfo)
    {
    	model.addAttribute("currentUserId", userId);
    	model.addAttribute("logType", logType);
    	
		String page  = ServletUtils.getIntParameter(Constants.PAGENUM).toString();
	    String pagesize = ServletUtils.getIntParameter(Constants.PAGESIZE).toString();
	    String searchValue = logInfo.getSearchValue();
	    TableDataInfo pageInfo = logService.findUserLog(page, pagesize, userId, logType, searchValue);
	    return pageInfo;
    }
    
   
}
