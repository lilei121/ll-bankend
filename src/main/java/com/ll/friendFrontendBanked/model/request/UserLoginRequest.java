package com.ll.friendFrontendBanked.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 5071827629764179440L;

    private String userAccount;

    private String userPassword;
}
