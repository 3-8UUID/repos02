package com.cssl.service;

import com.cssl.error.SysExecption;
import com.cssl.mapper.DistrictMapper;
import com.cssl.mapper.HouseDaoMapper;
import com.cssl.mapper.HouseTypeMapper;
import com.cssl.mapper.StreetMapper;
import com.cssl.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HouseService {
//    private  static SqlSessionFactory factory = MybatisUtil.getFactory();
//    private static SqlSession session = null;

    //等同于BeforeEach
//    public HouseDaoMapper getDhm(){
//        session = factory.openSession();
//        dhm = session.getMapper(HouseDaoMapper.class);
//        return dhm;
//    }
    //房型查询
    @Autowired
    HouseTypeMapper htmapper;
    //区县查询
    @Autowired
    DistrictMapper dmapper;
    //根据区县查询街道
    @Autowired
    StreetMapper amapper;
    //查询街道
    @Autowired
    StreetMapper smapper;
    @Autowired
    private HouseDaoMapper dhm;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addHouseFile(House house){
        return dhm.addHouseFile(house);
    }

    /**
     * 新增租房信息
     *
     * @param house
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addHouse(House house) throws SysExecption {
//        dhm = getDhm();
        int i = dhm.addHouse(house);
        if(i<=0){
            throw new SysExecption("添加失败，可能因为某种原因导致服务器维护...请联系管理员！");
        }
        return i;
    }

    /**
     * 删除租房信息
     *
     * @param id
     * @return
     */
    @Transactional
    public int delHouse(int id) throws SysExecption {
        int i = dhm.delHouse(id);
        if(i<=0){
            throw new SysExecption("删除失败，可能因为某种原因导致服务器维护...请联系管理员！");
        }
        return i;
    }

    /**
     * 修改租房信息
     */
    @Transactional
    public int updateHouse(House house) throws SysExecption {
//        dhm = getDhm();
        int i = dhm.updateHouse(house);
        if(i<=0){
            throw new SysExecption("修改失败，可能因为某种原因导致服务器维护...请联系管理员！");
        }
        return i;
    }

    //条件查询  分页显示
    public List<House> findHouseBy(Map<String, Object> map) {
//        dhm = getDhm();
        List<House> allByHouse = dhm.findAllByHouse(map);
        return allByHouse;
    }

    public List<HouseType> getHType() {
//        session = factory.openSession();
//        HouseTypeMapper mapper = session.getMapper(HouseTypeMapper.class);
        return htmapper.showAll();
    }

    public List<District> getDist() {
//        session = factory.openSession();
//        DistrictMapper mapper = session.getMapper(DistrictMapper.class);
        return dmapper.showAll();
    }

    //条件查询

    public List<Street> getStreet(int did) {
//        session = factory.openSession();
//        StreetMapper mapper = session.getMapper(StreetMapper.class);
        return amapper.getStreetByDistrict(did);
    }
    //条件搜查
    public Page findPageByMap(int pageNum, int pageSize, Map<String, Object> map) throws SysExecption {
//        session = factory.openSession();
//        HouseDaoMapper mapper = session.getMapper(HouseDaoMapper.class);
        map.put("row", pageNum * pageSize);
        map.put("rn", (pageNum - 1) * pageSize);
//        List<House> houses = mapper.findAllByHouse(map);
        List<House> houses = dhm.findAllByHouse(map);
        map.remove("row");
        map.remove("rn");
//        int count = mapper.getCount(map);
        int count = dhm.getCount(map);
        if(count<=0){
            throw new SysExecption("查询结果为空！请重新查询");
        }
        Page page = new Page();
        page.setList(houses);
        page.setTotalCount(count);

        page.setPageSize(pageSize);
        page.setPageIndex(pageNum);
        page.setTotalPage(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        return page;
    }


    //查询所有房户
    public Page getPages(int pageNum, int pageSize) {
//        session = factory.openSession();
//        HouseDaoMapper mapper = session.getMapper(HouseDaoMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("row", pageNum * pageSize);
        map.put("rn", (pageNum - 1) * pageSize);
        int count = dhm.getCount(null);
        List<House> houses = dhm.findAllByHouse(map);
        Page page = new Page();
        page.setList(houses);
        page.setTotalCount(count);
        page.setPageSize(pageSize);
        page.setPageIndex(pageNum);
        page.setTotalPage(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        return page;
    }
    //查询房户信息
    public House getHouseById(int id) throws SysExecption {
//        session = factory.openSession();
//        HouseDaoMapper mapper = session.getMapper(HouseDaoMapper.class);
        House houseById = dhm.findHouseById(id);
        if(houseById==null){
            throw new SysExecption("未找到房源信息");
        }else {
            return houseById;
        }
    }

    public Street getStreetById(int id) {
//        session = factory.openSession();
//        StreetMapper mapper = session.getMapper(StreetMapper.class);
        Street StreetById = smapper.getStreetById(id);
        return StreetById;
    }

    //查询区县
    public District getDistrictById(int id) {
//        session = factory.openSession();
//        DistrictMapper mapper = session.getMapper(DistrictMapper.class);
        District DistrictById = dmapper.findDistrictByStreet(id);
        return DistrictById;
    }

    //查询房型
    public HouseType getType(int id) {
//        session = factory.openSession();
//        HouseTypeMapper mapper = session.getMapper(HouseTypeMapper.class);
        HouseType houseTypeById = htmapper.getHouseTypeById(id);
        return houseTypeById;
    }


    public List<House> findAll(){
        return dhm.findAll();
    }
}
