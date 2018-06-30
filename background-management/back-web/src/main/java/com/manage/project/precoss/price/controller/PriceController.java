package com.manage.project.precoss.price.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.framework.aspectj.lang.annotation.Log;
import com.manage.framework.web.controller.BaseController;
import com.manage.project.base.pojo.Message;
import com.manage.project.process.price.IPriceService;


@Controller
@RequestMapping("/process/price")
public class PriceController extends BaseController
{
    private String prefix = "process/price";

    @Autowired
    private IPriceService priceService;

    
    /**
     *  初始化今日牌价页面
     * @return
     */
    @RequiresPermissions("process:price:view")
    @GetMapping()
    public String price()
    {
        return prefix + "/price";
    }
    
    /**
     * 点击新增牌价页面
     */
    @RequiresPermissions("process:price:add")
    @Log(title = "业务管理", action = "业务管理-新增今日牌价")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }
    
    /**
     * 牌价--点击保存
     */
    @RequiresPermissions("process:price:save")
    @Log(title = "业务管理", action = "业务管理-保存今日牌价")
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message save(String price)
    {
        return priceService.savePrice(price) > 0 ? Message.success() : Message.error();
    } 
   
}
