package com.shsxt.crm;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.exceptions.NoLoginException;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();

        if (e instanceof NoLoginException) {
            NoLoginException ne = (NoLoginException) e;
            httpServletRequest.setAttribute("ctx", httpServletRequest.getContextPath());
            mv.setViewName("noLogin");
            mv.addObject("code", ne.getCode());
            mv.addObject("msg", ne.getMsg());
            return mv;
        }
        mv.setViewName("error");
        mv.addObject("code", 500);
        mv.addObject("msg", "服务异常,请重试");
        if (o instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) o;
            ResponseBody responseBody = hm.getMethod().getDeclaredAnnotation(ResponseBody.class);
            if (responseBody == null) {
                if (e instanceof ParamsException) {
                    ParamsException pm = (ParamsException) e;
                    mv.addObject("code", pm.getCode());
                    mv.addObject("msg", ((ParamsException) e).getMsg());
                    return mv;
                }
            } else {
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统错误,请稍候在试");
                if (e instanceof ParamsException) {
                    ParamsException pm = (ParamsException) e;
                    resultInfo.setCode(pm.getCode());
                    resultInfo.setMsg(pm.getMsg());
                }
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter pw = null;
                try {
                    pw = httpServletResponse.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                    pw.flush();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (pw != null) {
                        pw.close();
                    }
                }
                return null;
            }

        }
        return mv;
    }
}
