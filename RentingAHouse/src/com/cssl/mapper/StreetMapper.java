package com.cssl.mapper;

import com.cssl.pojo.Street;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StreetMapper {
    //根据区县Id查
    @Select("select  s.id,s.name from district d,street s  where d.id = s.district_id and d.id=#{did}")
    List<Street> getStreetByDistrict(int did);

    //根据本身id查
    @Select("select id,name from street where id = #{id}")
    Street getStreetById(int id);
}
