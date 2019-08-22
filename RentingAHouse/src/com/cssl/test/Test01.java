package com.cssl.test;

import com.cssl.mapper.*;
import com.cssl.pojo.District;
import com.cssl.pojo.House;
import com.cssl.pojo.HouseInfo;
import com.cssl.pojo.User;
import com.cssl.util.MybatisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {
    private static SqlSessionFactory factory;
    private SqlSession session;
    private UserDaoMapper ud;
    private HouseDaoMapper hdm;

    @BeforeEach
    void befroe() {
        factory = MybatisUtil.getFactory();
        session = factory.openSession();
        ud = session.getMapper(UserDaoMapper.class);
        hdm = session.getMapper(HouseDaoMapper.class);
    }

    @AfterEach
    void afteraAll() {
        session.commit();
        session.close();
    }

    @Test
    void show() {

        List<User> users = ud.showAll2();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void userLogin() {
        User user = new User("123", "123");
        User user1 = ud.userLogin(user);
        if (user1 != null) {
            System.out.println(user1);
        } else {
            System.out.println("登录失败");
        }

    }

    @Test
    void TRegesiter() {
        User user = new User();
        user.setUserName("cool");
        user.setPassword("145635");
        user.setTelPhone("189596325014");
        user.setRealName("伍思凯");
        int i = ud.userRegiste(user);
        System.out.println(i);
    }



    @Test
    void Test1() {
        Page<Object> page = PageHelper.startPage(2, 3);
        List<House> houses = hdm.selectByWhere(null);
        houses.forEach(house -> System.out.println(house));
        System.out.println("**********************************");
        System.out.println("Pages:" + page.getPages());
        System.out.println("Total:" + page.getTotal());
        System.out.println("PageNum:" + page.getPageNum());
        System.out.println("PageSize:" + page.getPageSize());
        System.out.println("Result:" + page.getResult());
        System.out.println("*********************");
        for (Object o : page) {
            System.out.println("o:" + o);
        }

    }

    @Test
    void Test04() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        System.out.println(format);
        House house = new House(23, 5, "租豪房", "豪人培豪放", 6000, new Date(), 98.9, 6);

        int i = hdm.addHouse(house);
        System.out.println(i);
    }

    @Test
    void Test05() {
        //System.out.println(hdm.delHouse(13));  删除
        String telePhoneBYId = ud.findTelePhoneBYId(23);
        System.out.println(telePhoneBYId);
        House house = new House(12, 23, 5, "租豪房", "JKshdkjasd", 5000,
                new Date(), 150, 6, telePhoneBYId);
        int i = hdm.updateHouse(house);
        System.out.println(i);
    }

    @Test
    void Test02() {
        DistrictMapper mapper = session.getMapper(DistrictMapper.class);
        District districtByStreet = mapper.findDistrictByStreet(5);
        System.out.println(districtByStreet.getName());
        System.out.println(mapper.getDistrictById(3).getName());
        StreetMapper mapper1 = session.getMapper(StreetMapper.class);
        System.out.println("******************************");
        mapper1.getStreetByDistrict(1).forEach(street -> System.out.println(street.getName()));
        System.out.println("******************************");
        System.out.println(mapper1.getStreetById(3).getName());
    }

    @Test
    void Test03() {
        HouseTypeMapper mapper = session.getMapper(HouseTypeMapper.class);
        System.out.println(mapper.getHouseTypeById(2).getName());
    }

    @Test
    void Test06() {
        Map<String, Object> map = new HashMap<>();
//        map.put("floorsta", 1);
//        map.put("floorend", 80);
//        map.put("title", "房");
        map.put("type_id", 8);
//        map.put("rn", 6);
//        map.put("row", 9);
        List<House> houses = hdm.findAllByHouse(map);
        System.out.println(hdm.getCount(map));
        houses.forEach(house -> System.out.println(house));
    }
}
