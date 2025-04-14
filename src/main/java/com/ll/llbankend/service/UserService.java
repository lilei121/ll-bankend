package com.ll.llbankend.service;

import com.ll.llbankend.model.user.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author lilei
* @description 针对表【user(用户数据表)】的数据库操作Service
* @createDate 2024-12-11 10:45:12
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return 注册成功的用户ID
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取脱敏的用户信息
     * @param user
     * @return 脱敏后的用户信息
     */
    User getSafetyUser(User user);

    /**
     * 登出
     * @param request
     * @return
     */
    int  loginOut(HttpServletRequest request);
}
