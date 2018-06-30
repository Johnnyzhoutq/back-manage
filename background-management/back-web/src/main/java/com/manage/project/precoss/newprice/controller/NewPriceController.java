package com.manage.project.precoss.newprice.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.manage.framework.aspectj.lang.annotation.Log;
import com.manage.framework.web.controller.BaseController;
import com.manage.project.base.pojo.Message;
import com.manage.project.business.service.IMarkPriceService;

/**
 *  业务管理--牌价管理
 * @author hht
 *
 */
@Controller
@RequestMapping("/process/newprice")
public class NewPriceController extends BaseController
{
    private String prefix = "process/newprice";

    
    @Autowired
    private IMarkPriceService iMarkPriceService;
    
    /**
     *  初始化今日牌价页面
     * @return
     */
    @RequiresPermissions("process:newprice:view")
    @GetMapping()
    public String price()
    {
        return prefix + "/price";
    }
    
    /**
     *  查询牌价，页面初始化展示
     * @return
     */
    @RequiresPermissions("process:newprice:search")
    @Log(title = "业务管理", action = "牌价管理-页面初始化")
    @RequestMapping("/search")
    @ResponseBody
    public JSONArray search()
    {
    	JSONArray jsArray = new JSONArray();
    	jsArray = iMarkPriceService.selectAllMarkPrice();
       
    	return jsArray;
    }
    
    
    /**
     * 点击新增牌价页面
     */
    @RequiresPermissions("process:newprice:add")
    @Log(title = "业务管理", action = "牌价管理-新增今日牌价")
    @GetMapping("/add/{selectDate}")
    public String add(@PathVariable("selectDate") String selectDate, Model model)
    {
    	model.addAttribute("selectDate", selectDate);
        return prefix + "/add";
    }
    
    
    /**
     * 牌价--点击保存
     */
    @RequiresPermissions("process:newprice:save")
    @Log(title = "业务管理", action = "牌价管理-保存今日牌价")
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message save(String price, String selectDate)
    {
    	return iMarkPriceService.saveMarkPrice(price, selectDate) > 0 ? Message.success() : Message.error();
    } 
   
}
