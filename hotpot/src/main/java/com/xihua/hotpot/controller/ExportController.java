package com.xihua.hotpot.controller;

import com.xihua.hotpot.consts.Const;
import com.xihua.hotpot.dto.OrderQueryDto;
import com.xihua.hotpot.pojo.Food;
import com.xihua.hotpot.pojo.Order;
import com.xihua.hotpot.service.FoodService;
import com.xihua.hotpot.service.OrderService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 菜品信息导出到excel
 */
@RestController
@RequestMapping(Const.hg + "/export")
public class ExportController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/food/{type}")
    public void exportExcel(HttpServletResponse response, @PathVariable(value = "type") Integer type){
        //查询数据
        List<Food> list = foodService.getFoodBytepe(type);
        InputStream is = this.getClass().getResourceAsStream("/templates/foodList.xls");
        try (HSSFWorkbook wb = new HSSFWorkbook(is)) {
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            CellStyle originalStyle = getBorderCellStyle(wb, true, (short) 11);
            CellStyle textStyle = getBorderCellStyle(wb, false, (short) 11);
            //头部样式
            row = sheet.getRow(0);
            cell = row.createCell(0);
            CellStyle style = wb.createCellStyle();
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            Font font = wb.createFont();
            font.setFontHeightInPoints((short)13);
            font.setFontName("宋体");
            style.setFont(font);
            String name = "菜单列表";
            cell.setCellValue(name);
            cell.setCellStyle(style);

            for (int i = 0; i < list.size(); i++) {
                row = sheet.getRow(i + 2);
                if (row == null) {
                    row = sheet.createRow(i + 2);
                }
                Food food = list.get(i);

                cell = row.createCell(0);
                cell.setCellValue(i + 1);
                cell.setCellStyle(originalStyle);
                cell = row.createCell(1);
                cell.setCellValue(food.getName());
                cell.setCellStyle(originalStyle);
                cell = row.createCell(2);
                cell.setCellValue(food.getCategoryName());
                cell.setCellStyle(textStyle);
                cell = row.createCell(3);
                cell.setCellValue(String.valueOf(food.getPrice()));
                cell.setCellStyle(textStyle);
                cell = row.createCell(4);
                cell.setCellValue(String.valueOf(food.getNumber()));
                cell.setCellStyle(textStyle);
            }
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xls");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建excel表格异常");
        }
    }
    @GetMapping(value = "/order/{type}")
    public void OrderList(HttpServletResponse response,@PathVariable(value = "type") Integer type){
        //查询数据
        List<Order> list = orderService.getOrderListAll(type);
        InputStream is = this.getClass().getResourceAsStream("/templates/orderList.xls");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try (HSSFWorkbook wb = new HSSFWorkbook(is)) {
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            CellStyle originalStyle = getBorderCellStyle(wb, true, (short) 11);
            CellStyle textStyle = getBorderCellStyle(wb, false, (short) 11);
            //头部样式
            row = sheet.getRow(0);
            cell = row.createCell(0);
            CellStyle style = wb.createCellStyle();
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            Font font = wb.createFont();
            font.setFontHeightInPoints((short)13);
            font.setFontName("宋体");
            style.setFont(font);
            String name = "订单列表";
            cell.setCellValue(name);
            cell.setCellStyle(style);

            for (int i = 0; i < list.size(); i++) {
                row = sheet.getRow(i + 2);
                if (row == null) {
                    row = sheet.createRow(i + 2);
                }
                Order order = list.get(i);

                cell = row.createCell(0);
                cell.setCellValue(i + 1);
                cell.setCellStyle(originalStyle);
                cell = row.createCell(1);
                cell.setCellValue(order.getUserName());
                cell.setCellStyle(originalStyle);
                cell = row.createCell(2);
                cell.setCellValue(order.getContent());
                cell.setCellStyle(textStyle);
                cell = row.createCell(3);
                cell.setCellValue(String.valueOf(order.getMoney()));
                cell.setCellStyle(textStyle);
                cell = row.createCell(4);
                cell.setCellValue( formatter.format(order.getCreateTime()));
                cell.setCellStyle(textStyle);
            }
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xls");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建excel表格异常");
        }
    }

    public static CellStyle getBorderCellStyle(Workbook workbook, Boolean isCenter, short fontSize) {
        CellStyle originalStyle = workbook.createCellStyle();
        originalStyle.setWrapText(true);
        if (Boolean.TRUE.equals(isCenter)) {
            originalStyle.setAlignment(CellStyle.ALIGN_CENTER);
        }
        originalStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //字体大小
        Font originalFont = workbook.createFont();
        originalFont.setFontHeightInPoints(fontSize);
        originalFont.setFontName("等线");
        originalStyle.setFont(originalFont);
        //边框
        originalStyle.setBorderTop(CellStyle.BORDER_THIN);
        originalStyle.setBorderRight(CellStyle.BORDER_THIN);
        originalStyle.setBorderBottom(CellStyle.BORDER_THIN);
        originalStyle.setBorderLeft(CellStyle.BORDER_THIN);
        return originalStyle;
    }

}
