package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController extends BaseController {
    @Resource
    UserService userService;

    @GetMapping("main")
    public String main(HttpServletRequest request) {
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println(id);
        request.setAttribute("user", userService.selectByPrimaryKey(id));
        return "main";
    }




}
