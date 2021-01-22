package com.yxy.cl.service;

import com.yxy.cl.entity.User;
import com.yxy.cl.entity.dto.form.ModifyPassWordForm;
import com.yxy.cl.entity.dto.form.UserLoginForm;
import com.yxy.cl.entity.dto.form.UserRegisterForm;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

/**
 * 用户信息操作接口
 */
public interface IUserService {
    /**
     * 进行登录验证
     *
     * @param loginForm 登录表单
     *
     * @return 如果验证通过返回 User 实体
     */
    User loginAuthentication(UserLoginForm loginForm);

    /**
     *
     *
     */
    boolean registerUsernameCheckExist(UserRegisterForm registerForm);

    void insertUser(User user);

    void changeUser(User user);

    void joinSession(HttpServletRequest request,User user);

    User getUserSession(HttpServletRequest request);

    void destroySession(HttpServletRequest request);

    void modifyUserPassword(HttpServletRequest request, User user, ModifyPassWordForm modifyPassWordForm);

    User findUserByName(String username);

    void changePassWord(String username, String password);

    void modifyUserInfo(HttpServletRequest request, User user);

    void checkCookie(HttpServletRequest request);

    Optional<User> findUserById(Long id);

    String findNameById(Long id);
}