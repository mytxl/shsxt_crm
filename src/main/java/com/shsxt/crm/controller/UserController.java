package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.service.UserService;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;

@Controller
public class UserController extends BaseController {
    @Resource
  UserService userService;

}
