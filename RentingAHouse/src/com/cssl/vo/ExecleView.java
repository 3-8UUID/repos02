package com.cssl.vo;


import com.cssl.pojo.House;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ExecleView extends AbstractXlsView {

    @Override
    public void buildExcelDocument(Map<String, Object> map, Workbook workbook,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        String fileName = "house.xlsx";

        //设置响应头信息
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);

        //根据工作簿创建excle表
        Sheet sheet = workbook.createSheet("sheet1");//house1 :设置名称


        //设置表头
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("价格");
        row.createCell(2).setCellValue("标题");
        row.createCell(3).setCellValue("时间");
        row.createCell(4).setCellValue("详情");
        row.createCell(5).setCellValue("联系方式");

        if (map == null) {
            return;
        } else {
            List<House> list = (List<House>) map.get("list");
            for (int i = 0; i < list.size(); i++) {
                House house = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(house.getId());
                row.createCell(1).setCellValue(house.getPrice());
                row.createCell(2).setCellValue(house.getTitle());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String format = sdf.format(house.getPubdate());
                row.createCell(3).setCellValue(format);
                row.createCell(4).setCellValue(house.getDescription());
                row.createCell(5).setCellValue(house.getContact());
            }

            ServletOutputStream sos = response.getOutputStream();
            workbook.write(sos);
            sos.flush();
            sos.close();
        }
    }
}

