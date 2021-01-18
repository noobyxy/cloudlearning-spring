package com.yxy.cl.service.Impl;

import com.yxy.cl.consts.SessionConstants;
import com.yxy.cl.entity.User;
import com.yxy.cl.entity.dto.form.ModifyPassWordForm;
import com.yxy.cl.entity.dto.form.UserLoginForm;
import com.yxy.cl.entity.dto.form.UserRegisterForm;
import com.yxy.cl.mapper.UserMapper;
import com.yxy.cl.service.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.yxy.cl.consts.CookieConsts.COOKIE_PASSWORD;
import static com.yxy.cl.consts.CookieConsts.COOKIE_USERNAME;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper mMapper;

    @Override
    public User loginAuthentication(UserLoginForm loginForm) {
        List<User> userList = mMapper.findUserByNameAndPassword(loginForm.getUsername(),DigestUtils.md2Hex(loginForm.getPassword()));
        System.out.println(DigestUtils.md2Hex(loginForm.getPassword()));
        if (userList != null && userList.size() == 1) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public boolean registerUsernameCheckExist(UserRegisterForm registerForm) {
        List<User> userList = mMapper.findUserByName(registerForm.getUsername());
        return (userList != null && userList.size() == 1);
//        return false;
    }

    @Override
    public void insertUser(User user) {
        String pwdStr = user.getPassword();
        user.setPassword(DigestUtils.md2Hex(pwdStr));
        mMapper.save(user);
    }

    @Override
    public void changeUser(User user) {
        mMapper.save(user);
    }

    @Override
    public void joinSession(HttpServletRequest request, User user) {
        HttpSession requestSession = request.getSession(true);
        requestSession.setAttribute(SessionConstants.SESSION_CURRENT_USER, user);
    }

    @Override
    public User getUserSession(HttpServletRequest request) {
        HttpSession requestSession = request.getSession(false);
        return (User) requestSession.getAttribute(SessionConstants.SESSION_CURRENT_USER);
    }

    @Override
    public void destroySession(HttpServletRequest request) {
        HttpSession requestSession = request.getSession(true);
        requestSession.removeAttribute(SessionConstants.SESSION_CURRENT_USER);
    }

    @Override
    public void modifyUserPassword(HttpServletRequest request, User user, ModifyPassWordForm modifyPassWordForm) {

    }

    @Override
    public User findUserByName(String username) {
        List<User> userList = mMapper.findUserByName(username);
        if (userList != null && userList.size() == 1) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public void changePassWord(String username, String password) {

    }

    @Override
    public void modifyUserInfo(HttpServletRequest request, User user) {
        mMapper.save(user);
    }

    @Override
    public void checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = "";
        String password = "";
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                if (cookie.getName().equals(COOKIE_USERNAME)) {
                    username = cookie.getValue();
                } else  if (cookie.getName().equals(COOKIE_PASSWORD)) {
                    password = cookie.getValue();
                }
            }
            if (username.length() > 0 && password.length() > 0) {
                System.out.println("in here");;
                UserLoginForm loginForm = new UserLoginForm();
                loginForm.setPassword(password);
                loginForm.setUsername(username);
                User user = loginAuthentication(loginForm);
                joinSession(request, user);
            }

        }
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return mMapper.findById(id);
    }

    @Override
    public String findNameById(Integer id) {
        return mMapper.findNameById(id);
    }
}
