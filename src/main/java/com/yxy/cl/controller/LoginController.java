package com.yxy.cl.controller;

import com.yxy.cl.controller.base.BaseController;
import com.yxy.cl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class LoginController extends BaseController {
    @Autowired
    private IUserService mUserService;

    @GetMapping("/")
    public String indexPage(HttpServletRequest request){
        mUserService.checkCookie(request);
        return "index";
    }
}
