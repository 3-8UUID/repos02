package com.cssl.mapper;

import com.cssl.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDaoMapper {

    List<User> showAll2();

    /**
     * 登录用户
     */
    User userLogin(User user);

    /**
     * 注册用户
     */
    int userRegiste(User user);

    @Select("select telephone from house_user where id = #{id}")
    String findTelePhoneBYId(int id);

    //查询是否为管理员
    int isAdmin(int id);

    //查询是否重名
    @Select("select count(0) from house_user where username = #{name}")
    int reName(String name);

    //查询是否重复号码
    @Select("select count(0) from house_user where username = #{tel}")
    int reTelePhone(String tel);
}
