package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping()
public class UserController extends BaseController {
    @Resource
    UserService userService;

    @GetMapping("login")
    public String login() {
        return "index";
    }

    @PostMapping("userLogin")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {
        return success("登录成功",userService.userLogin(userName, userPwd));
    }
    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updatePwd(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        userService.updatePwd(request, oldPassword, newPassword, confirmPassword);
        return success("密码更新成功");
    }
}
