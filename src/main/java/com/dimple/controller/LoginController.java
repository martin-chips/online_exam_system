package com.dimple.controller;

import com.dimple.annotation.Log;
import com.dimple.service.SysUserService;
import com.dimple.shiro.UserRealm;
import com.dimple.utils.Constants;
import com.dimple.utils.RestResponse;
import com.dimple.utils.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


/**
 * @className: LoginController
 * @description: 登录相关
 * @auther: Dimple
 * @date: 04/29/19
 * @version: 1.0
 */
@Controller
@Slf4j
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    SysUserService sysUserService;

    /**
     * 转到登录的页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 处理登录请求
     */
    @PostMapping("/login")
    @ResponseBody
    @Log("登录系统")
    public RestResponse login(String password, String username, String rememberMe, HttpServletRequest request) {
        //获取验证码
        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)) {
            return RestResponse.failure("验证码不能为空");
        }//username="" username =null
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return RestResponse.failure("用户名或者密码不能为空");
        }
        if (StringUtils.isBlank(rememberMe)) {
            return RestResponse.failure("记住我不能为空");
        }

        //获取正确的验证码
        HttpSession session = request.getSession();
        if (session == null) {
            return RestResponse.failure("session 超时");
        }
        String trueCode = (String) session.getAttribute(Constants.VALIDATE_CODE);
        if (StringUtils.isBlank(trueCode)) {
            return RestResponse.failure("获取验证码超时");
        }
        String errorMsg = "";
        HashMap map = new HashMap();
        //验证码不对
        if (StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())) {
            errorMsg = "验证码错误";
        } else {
            //shiro
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, Boolean.valueOf(rememberMe));
            try {
                subject.login(usernamePasswordToken);
                if (subject.isAuthenticated()) {
                    map.put("url", "index");
                }
                session.setAttribute("user", sysUserService.findSysUserByLoginName(username));
            } catch (IncorrectCredentialsException e) {
                errorMsg = "登录密码错误.";
            } catch (ExcessiveAttemptsException e) {
                errorMsg = "登录失败次数过多";
            } catch (LockedAccountException e) {
                errorMsg = "帐号已被锁定.";
            } catch (DisabledAccountException e) {
                errorMsg = "帐号已被禁用.";
            } catch (ExpiredCredentialsException e) {
                errorMsg = "帐号已过期.";
            } catch (UnknownAccountException e) {
                errorMsg = "帐号不存在";
            } catch (UnauthorizedException e) {
                errorMsg = "您没有得到相应的授权！";
            }
        }

        if (StringUtils.isBlank(errorMsg)) {
            return RestResponse.success("登录成功").setData(map);
        } else {
            return RestResponse.failure(errorMsg);
        }
    }

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
        LOGGER.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249, 205, 173), null, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

}
