package com.cssl.servlet;

import com.alibaba.fastjson.JSON;
import com.cssl.error.SysExecption;
import com.cssl.pojo.*;
import com.cssl.service.HouseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "HouseServlet", urlPatterns = "/HouseServlet")
public class HouseServlet extends HttpServlet {
    private ApplicationContext context = new ClassPathXmlApplicationContext("com/cssl/config/applicationContext.xml");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=utf-8");
//        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HouseService hs = context.getBean(HouseService.class);
//        String action = request.getParameter("action");
//        System.out.println(action);
//        if ("add".equals(action)) {
//            //发布房型
//            addHouse(request, response, hs);
//        } else if ("type".equals(action)) {
//            //初始化房型
//            loadType(response, hs);
//        } else if ("dist".equals(action)) {
//            //初始化区县
//            loadDist(response, hs);
//        } else if ("street".equals(action)) {
//            //初始化街道
//            loadStreet(request, response, hs);
//        } else if ("load".equals(action)) {
//            //管理员初始化列表
//            loadHouse_Admin(request, response, hs);
//        } else if ("details".equals(action)) {
//            //详情页面
//            details(request, response, hs);
//        } else if ("del".equals(action)) {
//            //删除
//            delHouse(request, response, hs);
//        } else if ("find".equals(action)) {
//            //拿id查询
//            findById(request, response, hs);
//        } else if ("update".equals(action)) {
//            //修改
//            houseModify(request, response, hs);
//        } else if ("select".equals(action)) {
//            //条件查询
//            selectByWhere(request, response, hs);
//        }
    }

    private void selectByWhere(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws IOException, SysExecption {
        String pageNum1 = request.getParameter("pageNum");
        String title = request.getParameter("title");
        String type_id = request.getParameter("type_id");
        String dist_id = request.getParameter("district_id");
        String street_id = request.getParameter("street_id");
        String price = request.getParameter("price");
        String floorage = request.getParameter("floorage");
//        System.out.println("distid:"+dist_id);
////        System.out.println("type_id:"+type_id);
////        System.out.println("street_id:"+street_id);
////        System.out.println("price:"+price);
////        System.out.println("floorage:"+floorage);
////        System.out.println("title:"+title);
        int pageNum = Integer.valueOf(pageNum1 == null ? "0" : pageNum1);
//        int distid = Integer.valueOf(dist_id==null?"0":dist_id);
        int streetid = Integer.valueOf(street_id == null ? "0" : street_id);
        int typeid = Integer.valueOf(type_id == null ? "0" : type_id);
        int pricestr = 0;
        int priceend = 0;
        int floorsta = 0;
        int floorend = 0;
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
        map.put("street_id", streetid);
        map.put("pricestr", pricestr);
        map.put("priceend", priceend);
        map.put("type_id", typeid);
        map.put("floorend", floorend);
        map.put("floorsta", floorsta);
        Page pages = hs.findPageByMap(pageNum, 3, map);
        String json = JSON.toJSONString(pages, true);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private void houseModify(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws IOException, SysExecption {
        int id = Integer.valueOf(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        String title = request.getParameter("title");
        String type_id = request.getParameter("type_id");
        String floorage = request.getParameter("floorage");
        String price = request.getParameter("price");
        String houseDate = request.getParameter("houseDate");
        String street_id = request.getParameter("street_id");
        String contact = request.getParameter("contact");
        String description = request.getParameter("description");
        House house = new House(id, user_id, Integer.valueOf(type_id), title, description, Double.valueOf(price),
                Date.valueOf(houseDate), Double.valueOf(floorage), Integer.valueOf(street_id), contact);
        int i = hs.updateHouse(house);
        String url = "page/xiugai.jsp";
        if (i > 0) {
            //修改成功
            url = "page/guanli.jsp";
            response.sendRedirect(url);
        } else {
            //修改失败
            response.sendRedirect(url);
        }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws  Exception {
        int id = Integer.valueOf(request.getParameter("id"));
        House house = hs.getHouseById(id);
        List<HouseType> hType = hs.getHType();
        request.getSession().setAttribute("House", house);
        response.sendRedirect("page/xiugai.jsp");
    }

    private void delHouse(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws IOException, SysExecption {
        int id = Integer.valueOf(request.getParameter("id"));
        int i = hs.delHouse(id);
        String json = JSON.toJSONString(i);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private void details(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws IOException, SysExecption {
        int id = Integer.valueOf(request.getParameter("id"));
        House houseById = hs.getHouseById(id);
        HttpSession session = request.getSession();
        session.setAttribute("House", houseById);
        session.setAttribute("Street", hs.getStreetById(houseById.getStreet_id()));
        session.setAttribute("Dist", hs.getDistrictById(houseById.getStreet_id()));
        session.setAttribute("Type", hs.getType(houseById.getType_id()));
        response.sendRedirect("page/details.jsp");
    }

    private void loadHouse_Admin(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws IOException {
        String pageNum1 = request.getParameter("pageNum");
        int pageNum = Integer.valueOf(pageNum1);
        Page pages = hs.getPages(pageNum, 3);
        String json = JSON.toJSONString(pages, true);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private void loadStreet(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws IOException {
        String id = request.getParameter("id");
        int did = Integer.valueOf(id);
        List<Street> list = hs.getStreet(did);
        String json = JSON.toJSONString(list, true);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private void loadDist(HttpServletResponse response, HouseService hs) throws IOException {
        List<District> list = hs.getDist();
        String json = JSON.toJSONString(list, true);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private void loadType(HttpServletResponse response, HouseService hs) throws IOException {
        List<HouseType> list = hs.getHType();
        String json = JSON.toJSONString(list, true);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private void addHouse(HttpServletRequest request, HttpServletResponse response, HouseService hs) throws IOException, SysExecption {
        User user = (User) request.getSession().getAttribute("user");
        int user_id = user.getId();
        String title = request.getParameter("title");
        String type_id = request.getParameter("type_id");
        String floorage = request.getParameter("floorage");
        String price = request.getParameter("price");
        String houseDate = request.getParameter("pubdate");
        String street_id = request.getParameter("street_id");
        String contact = request.getParameter("contact");
        String description = request.getParameter("description");
        House house = new House(user_id, Integer.valueOf(type_id), title, description, Double.valueOf(price),
                Date.valueOf(houseDate), Double.valueOf(floorage), Integer.valueOf(street_id), contact);
        int i = hs.addHouse(house);
        String url = "page/fabu.jsp";
        if (i > 0) {
            //新增成功
            url = "page/guanli.jsp";
            response.sendRedirect(url);
        } else {
            //新增失败
            response.sendRedirect(url);
        }
    }
}
