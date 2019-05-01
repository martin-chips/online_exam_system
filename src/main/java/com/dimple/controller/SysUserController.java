package com.dimple.controller;

import com.dimple.entity.SysUser;
import com.dimple.service.SysUserService;
import com.dimple.utils.RestResponse;
import com.dimple.utils.web.BaseController;
import com.dimple.utils.web.TableDataInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService sysUserService;

    @GetMapping()
    public String user() {
        return "system/user/list";
    }

    @GetMapping("/changePassword")
    public String changePassword() {
        return "system/user/changePassword";
    }

    @GetMapping("/add")
    public String add() {
        return "system/user/add";
    }

    @PostMapping("/add")
    public RestResponse addSave(SysUser user) {

        return null;
    }

    @GetMapping("/update")
    public String update() {
        return "system/user/update";
    }

    @PutMapping("/update")
    public RestResponse updateSave(SysUser sysUser) {
        return null;
    }


    @GetMapping("list")
    @ResponseBody
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> sysUsers = sysUserService.selectUserList(sysUser);
        return getDataTable(sysUsers);
    }

    @GetMapping("/userInfo")
    public String userInfo() {
        return "system/user/userInfo";
    }
}