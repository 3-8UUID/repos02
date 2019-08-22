package com.cssl.mapper;

import com.cssl.pojo.House;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface HouseDaoMapper {
    int addHouseFile(House house);

    //新增租房信息
    int addHouse(House house);

    //查询单个租房信息
    @Select("select * from house where id=#{id}")
    House findHouseById(int id);

    //修改租房信息
    int updateHouse(House house);

    //删除租房信息
    @Delete("delete from house where id = #{id}")
    int delHouse(int id);

    //根据条件分页查询查询租房信息
    List<House> selectByWhere(Map<String, Object> map);

    //传统分页查询
    List<House> findAllByHouse(Map<String, Object> map);

    //查询总数
    int getCount(Map<String, Object> map);
    @Select("select * from house")
    List<House> findAll();
}
