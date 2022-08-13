package com.jd.edi.utils;



import com.jd.edi.xStream.Partner;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ExcelUti<T> {

    // 创建一个空的XSS
    public <T> XSSFWorkbook createXSSFWorkbook(String name, List<String> titles, List<String> keys,
                                               List<T> values, Class<T> clazz)
            throws NoSuchFieldException, IllegalAccessException {
        System.out.println("创建");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFDataFormat format = workbook.createDataFormat();
        //文本格式
        style.setDataFormat(format.getFormat("@"));
        createHeader(sheet, titles, style, 0);
        for (int i = 0; i < values.size(); i++) {
            Object object = values.get(i);
            createContent(sheet, keys, object , style, clazz, i);
            object = null;
        }

        return workbook;
    }



    private <T> void createContent(XSSFSheet xssfSheet, List<String> keys, Object object,
                                   XSSFCellStyle style, Class<T> clazz ,int i)
            throws NoSuchFieldException, IllegalAccessException {
        XSSFRow row = xssfSheet.createRow(i + 1);
        System.out.println("创建content");
        for (int index = 0; index < keys.size(); index++) {
            XSSFCell cell = row.createCell(index);
            cell.setCellStyle(style);
            Field field = clazz.getDeclaredField(keys.get(index));
            field.setAccessible(true);
            String val = field.get(clazz.cast(object)) != null ? field.get(clazz.cast(object)).toString() : "";
            if("class java.util.Date".equals(field.getType().toString()) && !StringUtils.isBlank(val)){
                Date date = new Date(val+" GMT+0800");
                val = TimeUtil.format(date, "yyyy-MM-dd HH:mm:ss");
            }
            cell.setCellValue(val);
        }
    }

    // 创建一个header
    private void createHeader(XSSFSheet xssfSheet, List<String> titles, XSSFCellStyle style, int i) {
        // 创建第几行
        System.out.println("创建1");
        XSSFRow row = xssfSheet.createRow(i);
        for (int index = 0; index < titles.size(); index++) {
            // 创建单元格-一个格子
            System.out.println("i : " + index + " , val :" + titles.get(index));
            XSSFCell cell = row.createCell(index);
            cell.setCellStyle(style);
            cell.setCellValue(titles.get(index));
        }
        System.out.println("创建header");
    }

    public static void main(String[] args) throws IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
        List<String> titleZhList = new ArrayList<String>();
        titleZhList.add("序号");
        titleZhList.add("学号");
        titleZhList.add("名字");
        titleZhList.add("年龄");
        titleZhList.add("登记时间");
        List<String> titleEnList = new  ArrayList<String>();
        titleEnList.add("name");
        titleEnList.add("filePath");
        titleEnList.add("alias");
//        titleEnList.add("updateMessage");
//        titleEnList.add("commitTime");
        List<Partner> contentsList = new  ArrayList<>();
        Partner content = new Partner();
        Partner content1 = new Partner();
        content.setName("1");
        content.setAlias("alias1");
        content1.setName("2");
        content1.setAlias("alias2");
        contentsList.add(content);
        contentsList.add(content1);

        XSSFWorkbook workBook = null;
        FileOutputStream output = null;
        String sheetName = "student";
        String fileName = "D:/student.xlsx";

        if (contentsList.size() > 0) {
            try {
                ExcelUti<Partner> eu = new ExcelUti<>();
                workBook = eu.createXSSFWorkbook(sheetName, titleZhList, titleEnList, contentsList, Partner.class);
                output =  new FileOutputStream(fileName);
                workBook.write(output);
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } finally{
                if(output != null){
                    output.flush();
                    output.close();
                }
                if (workBook != null) {
                    workBook.close();
                }
            }
        }
    }


}
