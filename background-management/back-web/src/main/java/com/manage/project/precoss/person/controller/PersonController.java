package com.manage.project.precoss.person.controller;

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
import com.manage.project.process.person.Achievement;
import com.manage.project.process.person.IPersonService;
import com.manage.project.process.person.Person;

/**
 *
 *  业务管理---用户列表
 */
@Controller
@RequestMapping("/process/person")
public class PersonController extends BaseController
{
    private String prefix = "process/person";

    @Autowired
    private IPersonService personService;
    
    
    @RequiresPermissions("process:person:view")
    @GetMapping()
    public String person()
    {
        return prefix + "/person";
    }

    
    /**
     *  用户数据列表
     * @param person
     * @return
     */
    @RequiresPermissions("process:person:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Person person)
    {
    	String page  = ServletUtils.getIntParameter(Constants.PAGENUM).toString();
        String pagesize = ServletUtils.getIntParameter(Constants.PAGESIZE).toString();
        String searchValue = person.getSearchValue();
        startPage();
        TableDataInfo pageInfo = personService.selectAllPersonList(page, pagesize, searchValue);
        return pageInfo;
        
    }
    
    
	/**
	 *  点击业绩弹框数据展示 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequiresPermissions("process:person:detail")
	@GetMapping("/detail/{userId}")
	public String detail(@PathVariable("userId") String userId, Model model) {
		Achievement achievement = personService.getAchievementDataByUserId(userId);
		if (achievement != null) {
			model.addAttribute("leftData", achievement.getLeftDataList().get(0));
			model.addAttribute("rightData", achievement.getRightDataList().get(0));

			return prefix + "/detail";
		} else {
			return "没有找到匹配数据 ! ";
		}

	}
 
}
