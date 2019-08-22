package com.cssl.mapper;

import com.cssl.pojo.HouseType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HouseTypeMapper {
    @Select("select * from HOUSE_TYPE where id = #{Id}")
    HouseType getHouseTypeById(int Id);

    @Select("select * from house_type")
    List<HouseType> showAll();
}
