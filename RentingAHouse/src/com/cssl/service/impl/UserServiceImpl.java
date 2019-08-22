package com.cssl.service.impl;

import com.cssl.error.SysExecption;
import com.cssl.mapper.UserDaoMapper;
import com.cssl.pojo.User;
import com.cssl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    //    private static SqlSessionFactory factory = MybatisUtil.getFactory();
//    private SqlSession session = factory.openSession();
    @Autowired
    private UserDaoMapper ud;

    @Override
    public User userLogin(User user) {
        User user1 = ud.userLogin(user);
        return user1;
    }

    @Override
    @Transactional
    public int userRegiste(User user) throws SysExecption {
        int i = ud.userRegiste(user);
        if(i<=0){
            throw new SysExecption("注册失败，可能因为某种原因导致服务器维护...请联系管理员！");
        }
        return i;
    }

    //查询是否为管理员
    public int isAdmin(int id) {
        return ud.isAdmin(id);
    }

    //重名检验
    public int reUserName(String name) {
        return ud.reName(name);
    }

    //校验电话
    public int reTelPhone(String tel) {
        return ud.reTelePhone(tel);
    }
}
