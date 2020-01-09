package com.shsxt.base;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @ModelAttribute
    public void ctx(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
    }
}
