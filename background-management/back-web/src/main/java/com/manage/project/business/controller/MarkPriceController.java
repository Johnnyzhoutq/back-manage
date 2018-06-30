package com.manage.project.business.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.framework.web.controller.BaseController;
import com.manage.framework.web.page.TableDataInfo;
import com.manage.project.base.pojo.Message;
import com.manage.project.business.pojo.MarkPrice;
import com.manage.project.business.service.IMarkPriceService;

/**
 *  信息操作处理
 * 
 * @author zhoutianqi
 * @date 2018-06-08
 */
@Controller
@RequestMapping("/module/markPrice")
public class MarkPriceController extends BaseController
{
    private String prefix = "module/markPrice";
	
	@Autowired
	private IMarkPriceService markPriceService;
	
	@GetMapping()
	@RequiresPermissions("module:markPrice:view")
	public String markPrice()
	{
	    return prefix + "/markPrice";
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("module:markPrice:list")
	@GetMapping("/list")
	@ResponseBody
	public TableDataInfo list(MarkPrice markPrice)
	{
		startPage();
        List<MarkPrice> list = markPriceService.selectMarkPriceList(markPrice);
		return getDataTable(list);
	}
	
	/**
	 * 新增
	 */
	@RequiresPermissions("module:markPrice:add")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("module:markPrice:edit")
	@GetMapping("/edit/{markpriceId}")
	public String edit(@PathVariable("markpriceId") Integer markpriceId, Model model)
	{
		MarkPrice markPrice = markPriceService.selectMarkPriceById(markpriceId);
		model.addAttribute("markPrice", markPrice);
	    return prefix + "/edit";
	}
	
	/**
	 * 保存
	 */
	@RequiresPermissions("module:markPrice:save")
	@PostMapping("/save")
	@ResponseBody
	public Message save(MarkPrice markPrice)
	{
		if (markPriceService.saveMarkPrice(markPrice) > 0)
		{
			return Message.success();
		}
		return Message.error();
	}
	
	/**
	 * 删除
	 */
	@RequiresPermissions("module:markPrice:remove")
	@PostMapping( "/remove/{markpriceId}")
	@ResponseBody
	public Message remove(@PathVariable("markpriceId") Integer markpriceId)
	{
		if (markPriceService.deleteMarkPriceById(markpriceId) > 0)
		{
		    return Message.success();
		}
		return Message.error();
	}
	
	/**
	 * 批量删除
	 */
	@RequiresPermissions("module:markPrice:batchRemove")
	@PostMapping( "/batchRemove")
	@ResponseBody
	public Message remove(@RequestParam("ids[]") Integer[] markpriceIds)
	{
		int rows = markPriceService.batchDeleteMarkPrice(markpriceIds);
		if (rows > 0)
        {
            return Message.success();
        }
        return Message.error();
	}
	
}
