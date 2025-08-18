package com.ll.friendFrontendBanked.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ll.friendFrontendBanked.constant.ErrorCode;
import com.ll.friendFrontendBanked.exception.BaseException;
import com.ll.friendFrontendBanked.model.user.User;
import com.ll.friendFrontendBanked.service.UserService;
import com.ll.friendFrontendBanked.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import static com.ll.friendFrontendBanked.constant.UserConstant.LOGIN_USER_STATUS;

/**
 * 针对表【user(用户数据表)】的数据库操作Service实现
 * <p>
 * 2024-12-11 10:45:12
 *
 * @author lilei
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private static final String SALT = "lilei";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BaseException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if (userAccount.length() < 4 || userPassword.length() < 6 || checkPassword.length() < 6) {
            throw new BaseException(ErrorCode.PARAMS_ERROR,"账号、密码过短");
        }
        String regx = "^[a-zA-Z0-9_]+$";
        boolean matches = Pattern.matches(regx, userAccount);
        if (!matches) {
            throw new BaseException(ErrorCode.PARAMS_ERROR,"账号不符合规范");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BaseException(ErrorCode.PARAMS_ERROR,"两次密码不一致");
        }
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("userAccount", userAccount);
        User checkUser = this.getOne(qw);
        if (checkUser != null) {
            throw new BaseException(ErrorCode.NULL_ERROR,"用户已存在");
        }


        // 加密 加盐
        String md5Digest = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(md5Digest);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BaseException(ErrorCode.NULL_ERROR,"用户注册失败");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BaseException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if (userAccount.length() < 4 || userPassword.length() < 6) {
            throw new BaseException(ErrorCode.PARAMS_ERROR,"账号、密码过短");
        }
        String regx = "^[a-zA-Z0-9_]+$";
        boolean matches = Pattern.matches(regx, userAccount);
        if (!matches) {
            throw new BaseException(ErrorCode.PARAMS_ERROR,"账号不符合规范");
        }
        // 加密 加盐
        String md5Digest = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("userAccount", userAccount);
        qw.eq("userPassword", md5Digest);
        User getUser = this.getOne(qw);
        if (getUser == null) {
            throw new BaseException(ErrorCode.NULL_ERROR,"用户名密码错误");

        }
        // 用户信息脱敏
        User safeUser = getSafetyUser(getUser);
        // 设置登录态
        request.getSession().setAttribute(LOGIN_USER_STATUS, safeUser);

        return safeUser;
    }

    @Override
    public User getSafetyUser(User user) {
        User safetyUser = new User();
        if (user == null) {
            return safetyUser;
        }
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setGender(user.getGender());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setUserRole(user.getUserRole());
        return safetyUser;
    }

    @Override
    public int loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(LOGIN_USER_STATUS);
        return 1;
    }
}




