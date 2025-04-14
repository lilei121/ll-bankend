package com.ll.llbankend.controller.uesr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ll.llbankend.constant.BaseResponse;
import com.ll.llbankend.constant.ErrorCode;
import com.ll.llbankend.model.request.UserLoginRequest;
import com.ll.llbankend.model.request.UserRegisterRequest;
import com.ll.llbankend.model.user.User;
import com.ll.llbankend.service.UserService;
import com.ll.llbankend.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ll.llbankend.constant.UserConstant.LOGIN_USER_STATUS;
import static com.ll.llbankend.constant.UserConstant.USER_ROLE_ADMIN;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        long id = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(id);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @GetMapping("/loginOut")
    public BaseResponse<Integer> loginOut(HttpServletRequest request) {
        int flag = userService.loginOut(request);
        return ResultUtils.success(flag);
    }

    @GetMapping("/get")
    public BaseResponse<List<User>> getUserList(String username, HttpServletRequest request) {
        if (isAdmin(request)) {
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> users = userService.list(queryWrapper);
        return ResultUtils.success(users);
    }

    @GetMapping("/getCurrent")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(LOGIN_USER_STATUS);
        if (user == null) {
            return ResultUtils.error(ErrorCode.UN_LOGIN);
        }
        User safetyUser = userService.getSafetyUser(userService.getById(user.getId()));
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/delete")
    public BaseResponse<Boolean> deleteUser(long id, HttpServletRequest request) {
        if (isAdmin(request)) {
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        boolean isDeleted = userService.removeById(id);
        return ResultUtils.success(isDeleted);
    }

    private boolean isAdmin(@NotNull HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(LOGIN_USER_STATUS);
        return user == null || user.getUserRole() != USER_ROLE_ADMIN;
    }
}
