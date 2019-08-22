package com.cssl.mapper;

import com.cssl.pojo.District;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DistrictMapper {
    @Select("select  d.id,d.name from district d,street s  where d.id = s.district_id and s.id=#{sid}")
    District findDistrictByStreet(int sid);

    @Select("select * from district where id = #{id}")
    District getDistrictById(int Id);

    @Select("select * from district")
    List<District> showAll();

}
