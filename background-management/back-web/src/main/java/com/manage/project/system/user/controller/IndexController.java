package com.manage.project.system.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.manage.common.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import com.manage.common.util.RuoYiConfig;
import com.manage.framework.web.controller.BaseController;
import com.manage.project.system.menu.pojo.Menu;
import com.manage.project.system.menu.service.IMenuService;
import com.manage.project.system.user.pojo.User;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{
	
	
	 /**
     *   首页信息接口
     */
    private  static  final String  urlFirstPageInfo = "http://47.52.91.119:8083/admin/firstpage";
    
    
    @Autowired
    private IMenuService menuService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(Model model)
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);
        model.addAttribute("copyrightYear", ruoYiConfig.getCopyrightYear());
        return "index";
    }

    
 // 系统介绍
    @GetMapping("/system/main")
    public String main(Model model)
    {

        model.addAttribute("version", ruoYiConfig.getVersion());
        // 当前用户数量
        model.addAttribute("userAmount", 10000);
        // 全部锁仓总额
        model.addAttribute("totalAmountLocks", 10000);
        // 今日增加用户数
        model.addAttribute("increaseNumberUsers",10000);
        // 今日提现总额
        model.addAttribute("totalToday", 10000);
        model.addAttribute("lockOrders", 10000);
        model.addAttribute("todayAmountLocks",10000);

        return "main";
    }

}
