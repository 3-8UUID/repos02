package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.cssl.error.SysExecption;
import com.cssl.pojo.*;
import com.cssl.service.HouseService;
import com.cssl.vo.ExecleView;
import com.cssl.vo.HouseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping("houseController")
public class HouseController implements ServletContextAware {
    private ApplicationContext context =
            new ClassPathXmlApplicationContext("com/cssl/config/applicationContext.xml");
    HouseService hs = context.getBean(HouseService.class);
    private ServletContext application;//文件上传
    //多文件上传
    @RequestMapping("fileUpload")
    public String fileUpload(MultipartFile[] files,HttpSession session) throws SysExecption, IOException {
        Integer hid = (Integer) session.getAttribute("hid");
        if(hid==null){
            throw new SysExecption("您选择的房屋信息有出入！请返回上级目录刷新后再重试！" +
                    "或者联系管理员！");
        }
        House house = hs.getHouseById(hid);
        String filename = null;
//        List<String> filenames = new ArrayList<>();
        String fileName = house.getFileName();
        if (files.length>0&&files!=null){
            for (MultipartFile file:files) {
                filename = file.getOriginalFilename();
//                filenames.add(filename);
                //查询list中不能有重复
//                for (int i = 0; i < filenames.size(); i++) {
//                    if (filenames.get(i)==filename) {
//                        System.out.println("检查有重复的图片！");//不再上传
//                    }else {
//
//                    }
//                }
                filename = fileUpload(file, filename);

                if (filename!=null&&filename!=""){
                    fileName+=","+filename;
                }

                house.setFileName(fileName);//原来的文件名+加上现在的文件名
            }
        }else {
            System.out.println("null");
        }
//        System.out.println(fileName);
        hs.addHouseFile(house);
        return "loged/guanli";
    }

    //转上传页面
    @RequestMapping("upload")
    public String language(Integer id,HttpSession session) throws SysExecption {
        System.out.println(id);
        if(id!=null){
            session.setAttribute("hid",id);
        }
        return "hello";
    }

    //导出sxlx表
    @RequestMapping("sxlx")
    public ModelAndView uploadExecle() {
//        Page page = hs.getPages(1,5);
        List<House> list = hs.findAll();
//        list.forEach(house -> System.out.println(house));
        ModelAndView mv = new ModelAndView("guanli");
        mv.addObject("list",list);
        ExecleView view = new ExecleView();
        mv.setView(view);
        return mv;
    }

    //发布
    @PostMapping("add")
    public String addHouse(HouseVo houseVo, HttpSession session, MultipartFile file) throws SysExecption, IOException {
        User user = (User) session.getAttribute("user");
        int user_id = user.getId();
        House house = new House();
        BeanUtils.copyProperties(houseVo, house);
        house.setPubdate(Date.valueOf(houseVo.getPubdate()));
        house.setUser_id(user_id);
        String filename = null;
        //获取image真实路径
        filename = fileUpload(file, filename);
        house.setFileName(filename);

        int i = hs.addHouse(house);
        if (i > 0) {
            //新增成功
            return "loged/guanli";
        } else {
            //新增失败
            return "loged/fabu";
        }
    }
    //上传文件
    private String fileUpload(MultipartFile file, String filename) throws IOException {
        String path = application.getRealPath("/images");
        if (!file.isEmpty() && file != null) {
            filename = file.getOriginalFilename();//获取源文件名
            if (!new File(path).exists()) { //若此文件夹为空
                // 先得到文件的上级目录，并创建上级目录，在创建文件
                new File(path).mkdir();
            }
//            UUID uuid = UUID.randomUUID();
//            filename = uuid + filename;
            File infile = new File(path, filename);//需要存入的文件
            file.transferTo(infile);
        }
        return filename;
    }



    //加载
    @RequestMapping(value = "loadAdmin", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String loadHouse_Admin(int pageNum) {
//        System.out.println("加载");
//        System.out.println(pageNum);
        Page pages = hs.getPages(pageNum, 3);
        String json = JSON.toJSONString(pages, true);
//        System.out.println(json);
        return json;
    }

    //加载房屋类型
    @RequestMapping(value = "type", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String loadType() {
        List<HouseType> list = hs.getHType();
        String json = JSON.toJSONString(list, true);
        return json;
    }

    //初始化县道
    @RequestMapping(value = "dist", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String loadDist() {
        List<District> list = hs.getDist();
        String json = JSON.toJSONString(list, true);
        return json;
    }

    //初始化街道
    @RequestMapping(value = "street", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String loadStreet(int id) {
        List<Street> list = hs.getStreet(id);
        String json = JSON.toJSONString(list, true);
        return json;
    }

    //加载详情页面
    @RequestMapping("details")
    private String details(int id, HttpSession session) throws SysExecption {
        House houseById = hs.getHouseById(id);
        session.setAttribute("House", houseById);
        session.setAttribute("Street", hs.getStreetById(houseById.getStreet_id()));
        session.setAttribute("Dist", hs.getDistrictById(houseById.getStreet_id()));
        session.setAttribute("Type", hs.getType(houseById.getType_id()));
        String[] strings = houseById.getFileName().split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }
        session.setAttribute("list",list);
        return "loged/details";
    }

    //删除
    @RequestMapping(value = "del", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String delHouse(int id) throws SysExecption {
        int i = hs.delHouse(id);
        String json = JSON.toJSONString(i);
        return json;
    }

    //因Id获取其房户信息
    @RequestMapping("findById")
    private String findById(Integer id, HttpSession session) throws  Exception {
        House house = hs.getHouseById(id);
        session.setAttribute("House", house);
        return "loged/xiugai";
    }

    //修改
    @PostMapping("update")
    private String houseModify(HouseVo houseVo, HttpSession session,MultipartFile file) throws SysExecption, IOException {
        User user = (User) session.getAttribute("user");
        House house = new House();
        BeanUtils.copyProperties(houseVo, house);
        int user_id = user.getId();
        house.setUser_id(user_id);
//        System.out.println(houseVo);
        house.setPubdate(Date.valueOf(houseVo.getPubdate()));
        String filename = null;
        //获取image真实路径
        filename = fileUpload(file, filename);



        //删除原文件
//        1.获取文件名
        House houseById = hs.getHouseById(houseVo.getId());
        String fileName = houseById.getFileName();
        System.out.println("fileName:"+fileName);
//        2.获取路径
        String path = application.getRealPath("/images");
        System.out.println("fileName:"+path);

//        3.执行删除
        boolean flag = false;
        if(!fileName.isEmpty()&&!path.isEmpty()){
            File file1 = new File(path+"/"+fileName);
            if(file1.exists()){
                flag = file1.delete();
            }
        }
        house.setFileName(filename);
        System.out.println(house);
        int i = hs.updateHouse(house);
        String url = i > 0 & flag ? "loged/guanli" : "loged/xiugai";
        return url;
    }

    //根据条件查询
    @RequestMapping(value = "findByWhere", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    private String findByWhere(Integer pageNum, String title, Integer type_id, Integer street_id, String price, String floorage) throws SysExecption {
        double pricestr = 0;
        double priceend = 0;
        double floorsta = 0;
        double floorend = 0;
        if (price != null && price != "") {
            String[] split = price.split("-");
            pricestr = Integer.valueOf(split[0]);
            priceend = Integer.valueOf(split[1]);
        }
        if (floorage != null && floorage != "") {
            String[] split = floorage.split("-");
            floorsta = Integer.valueOf(split[0]);
            floorend = Integer.valueOf(split[1]);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("street_id", street_id);
        map.put("pricestr", pricestr);
        map.put("priceend", priceend);
        map.put("type_id", type_id);
        map.put("floorend", floorend);
        map.put("floorsta", floorsta);
        Page pages = hs.findPageByMap(pageNum, 3, map);
        String json = JSON.toJSONString(pages, true);
        return json;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
}
