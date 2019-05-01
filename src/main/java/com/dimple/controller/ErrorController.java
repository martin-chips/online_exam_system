package com.dimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: ErrorController
 * @description: 出异常页面跳转和显示
 * @auther: Dimple
 * @date: 04/30/19
 * @version: 1.0
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("{code}")
    public String error(@PathVariable String code, Model model) {
        if ("404".equals(code)) {
            return "common/404";
        } else if ("500".equals(code)) {
            return "common/500";
        } else if ("403".equals(code)) {
            return "common/403";
        }
        return "common/error";
    }
}
