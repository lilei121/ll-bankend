package com.ll.friendFrontendBanked.model.request;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求类
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 2418177474384893804L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
