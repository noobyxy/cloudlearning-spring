package com.yxy.cl.controller;

import com.yxy.cl.controller.base.BaseController;
import com.yxy.cl.entity.User;
import com.yxy.cl.entity.dto.form.UserLoginForm;
import com.yxy.cl.entity.dto.form.UserRegisterForm;
import com.yxy.cl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.yxy.cl.consts.CookieConsts.*;
@Controller
public class LoginController extends BaseController {
    @Autowired
    private IUserService mUserService;

    @GetMapping("/")
    public String indexPage(HttpServletRequest request){
        mUserService.checkCookie(request);
        return "index";
    }

    /**
     * 前台用户登录
     * 表单提交
     */
    @PostMapping("/userlogin.f")
    public String fFrontUserLogin(HttpServletRequest request, Model model, UserLoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) throws Exception {
        // System.out.println("this func is called");
        // System.out.println("username is " + loginForm.getUsername());
        // System.out.println("username is " + loginForm.getPassword());
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            addModelAtt(model, VIEW_MSG, errors.get(0).getDefaultMessage());
            return "login-register";
        }
        User user = mUserService.loginAuthentication(loginForm);
        if (null != user) {
            System.out.println("login now");
            mUserService.joinSession(request, user);
            Cookie usercookie = new Cookie(COOKIE_USERNAME, loginForm.getUsername());
            usercookie.setMaxAge(24 * 60 * 60);
            Cookie userpassword = new Cookie(COOKIE_PASSWORD, loginForm.getPassword());
            usercookie.setPath(request.getContextPath());
            userpassword.setMaxAge(24 * 60 * 60);
            userpassword.setPath(request.getContextPath());
            response.addCookie(usercookie);
            response.addCookie(userpassword);
            return "redirect:/";
        }
        addModelAtt(model, VIEW_LABEL, 1);
        addModelAtt(model, VIEW_MSG, "用户名或密码错误");
        return "login-register";
    }

    /**
     * 前台用户注册
     * 表单提交
     */
    @PostMapping("/userregister.f")
    public String fFrontUserRegister(UserRegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request, Model model, User user) throws Exception {
        System.out.println("username is " + registerForm.getUsername());
        System.out.println("username is " + registerForm.getPassword());
        if (bindingResult.hasErrors()) {
            System.out.println(1);
            List<ObjectError> errors = bindingResult.getAllErrors();
            return "login-register";
        }
        if (mUserService.registerUsernameCheckExist(registerForm)) {
            addModelAtt(model, VIEW_MSG,"该用户已注册");
            addModelAtt(model, VIEW_LABEL, 2);
            return "login-register";
        }
        //再次进行密码一致校验
        if (!(registerForm.getPassword().equals(registerForm.getConfirmpassword()))) {
            System.out.println(3);
            addModelAtt(model, VIEW_MSG,"两次密码不相同");
            addModelAtt(model, VIEW_LABEL, 2);
            return "login-register";
        }
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());
        user.setMoney(1000.0);
        mUserService.insertUser(user);
        //跳转登录
        addModelAtt(model, VIEW_LABEL, 1);
        return "login-register";
    }

    @GetMapping("/usersignout.c")
    public String cFrontUserSignout(HttpServletRequest request) {
        mUserService.destroySession(request);
        return "redirect:index";
    }
}
