package com.dimple.controller;

import com.dimple.annotation.Log;
import com.dimple.entity.SysUser;
import com.dimple.service.SysUserService;
import com.dimple.utils.RestResponse;
import com.dimple.utils.web.AjaxResult;
import com.dimple.utils.web.BaseController;
import com.dimple.utils.web.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2019-05-01 00:15:15
 */
@Controller
@RequestMapping("system/user")
public class SysUserController extends BaseController {

    // 注入对象
    @Autowired
    private SysUserService sysUserService;

    @GetMapping()
    public String user() {
        return "system/user/user";
    }


    @GetMapping("/add")
    public String add() {
        return "system/user/add";
    }

    //delete：删除；get：查看；put：修改；post：添加
    @PostMapping("/add")//从客户端将对应数据传给服务端
    @ResponseBody
    @Log("添加用户")
    public AjaxResult addSave(SysUser user) {
        user.setCreateBy(getSysUser().getId());
        return toAjax(sysUserService.insert(user));
    }

    //获取用户ID，明确修改那一条数据，
    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("user", sysUserService.selectSysyUserById(id));
        return "system/user/update";
    }

    @PutMapping("/update")
    @ResponseBody
    @Log("修改用户")
    public AjaxResult updateSave(SysUser sysUser) {
        return toAjax(sysUserService.update(sysUser));
    }

    //页面加载完成后，加载表格数据，返回数表结果集
    @GetMapping("list")
    @ResponseBody
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> sysUsers = sysUserService.selectUserList(sysUser);
        return getDataTable(sysUsers);
    }

    //删除操作，返回操作结果，获取需要删除的ID
    @DeleteMapping()
    @ResponseBody
    @Log("删除用户")
    public AjaxResult delete(String ids) {
        return toAjax(sysUserService.deleteSysUserByIds(ids));
    }
}