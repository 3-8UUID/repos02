package com.cssl.service;

import com.cssl.error.SysExecption;
import com.cssl.pojo.User;

public interface UserService {
    /**
     * 登录用户
     */
    User userLogin(User user);

    /**
     * 注册用户
     */
    int userRegiste(User user) throws SysExecption;
}
