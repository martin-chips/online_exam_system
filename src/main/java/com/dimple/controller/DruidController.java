package com.dimple.controller;

import com.dimple.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SQL监控Druid的界面显示（管理员端的数据监控）
 */
@Controller
@RequestMapping("/druid")
public class DruidController {

    @Log("查看Druid数据")
    @GetMapping("/index")
    public String index() {
        return "druid/index";
    }
}
