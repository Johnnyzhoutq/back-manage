package com.manage.project.system.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.manage.common.constant.Constants;
import com.manage.common.httpsignutil.HttpSignUtil;
import com.manage.common.util.HttpClientUtil;
import com.manage.common.util.StringUtils;
import com.manage.framework.aspectj.lang.annotation.Log;
import com.manage.framework.web.controller.BaseController;
import com.manage.framework.web.page.TableDataInfo;
import com.manage.project.base.pojo.Message;
import com.manage.project.system.post.pojo.Post;
import com.manage.project.system.post.service.IPostService;
import com.manage.project.system.role.pojo.Role;
import com.manage.project.system.role.service.IRoleService;
import com.manage.project.system.user.pojo.User;
import com.manage.project.system.user.pojo.UserStatus;
import com.manage.project.system.user.service.IUserService;


/**
 * 用户信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController
{

    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "系统管理", action = "用户管理-修改用户")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, Model model)
    {
        User user = userService.selectUserById(userId);
        List<Role> roles = roleService.selectRolesByUserId(userId);
        List<Post> posts = postService.selectPostsByUserId(userId);
        model.addAttribute("roles", roles);
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        return prefix + "/edit";
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "系统管理", action = "用户管理-新增用户")
    @GetMapping("/add")
    public String add(Model model)
    {
        List<Role> roles = roleService.selectRoleAll();
        List<Post> posts = postService.selectPostAll();
        model.addAttribute("roles", roles);
        model.addAttribute("posts", posts);
        return prefix + "/add";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "系统管理", action = "用户管理-重置密码")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, Model model)
    {
        User user = userService.selectUserById(userId);
        model.addAttribute("user", user);
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "系统管理", action = "用户管理-重置密码")
    @PostMapping("/resetPwd")
    @ResponseBody
    public Message resetPwd(User user)
    {
        int rows = userService.resetUserPwd(user);
        if (rows > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "系统管理", action = "用户管理-删除用户")
    @RequestMapping("/remove/{userId}")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message remove(@PathVariable("userId") Long userId)
    {
        User user = userService.selectUserById(userId);
        if (user == null)
        {
            return Message.error("用户不存在");
        }
        else if (User.isAdmin(userId))
        {
            return Message.error("不允许删除超级管理员用户");
        }
        user.setStatus(UserStatus.DELETED.getCode());
        return userService.updateUser(user) > 0 ? Message.success() : Message.error();
    }

    @RequiresPermissions("system:user:batchRemove")
    @Log(title = "系统管理", action = "用户管理-批量删除")
    @PostMapping("/batchRemove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = userService.batchDeleteUser(ids);
        if (rows > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 保存用户
     */
    @RequiresPermissions("system:user:save")
    @Log(title = "系统管理", action = "用户管理-保存用户")
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message save(User user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && User.isAdmin(user.getUserId()))
        {
            return Message.error("不允许修改超级管理员用户");
        }
        return userService.saveUser(user) > 0 ? Message.success() : Message.error();
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkLoginNameUnique(user.getLoginName());
        }
        return uniqueFlag;
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkPhoneUnique(user);
        }
        return uniqueFlag;
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkEmailUnique(user);
        }
        return uniqueFlag;
    }
    
    public void getJinseAPI() {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("access_key", Constants.ACCESS_KEY);
    	map.put("date", String.valueOf(new Date().getTime()/1000));
    	String url = "https://api.jinse.com/topic/list?";
    	String realUrl = HttpSignUtil.genJinseApiUrl(url, map);
    	String jsonStr = HttpClientUtil.doGet(realUrl);
    	JSONArray JSONaRRAY = JSON.parseArray(jsonStr);
    }
}