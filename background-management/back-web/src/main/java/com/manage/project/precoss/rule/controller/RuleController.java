package com.manage.project.precoss.rule.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.framework.web.controller.BaseController;

/**
 *  业务管理--算力管理
 * @author hht
 *
 */
@Controller
@RequestMapping("/process/rule")
public class RuleController extends BaseController
{
    private String prefix = "process/rule";

    
    /**
     *  初始化算力页面
     * @return
     */
    @RequiresPermissions("process:rule:view")
    @GetMapping()
    public String rule()
    {
        return prefix + "/rule";
    }

    
}
