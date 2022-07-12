package com.ai.gitai.file;

import com.ai.gitai.entity.CodingCommitKpi;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 将数据导出到excel文档
 */
public class FIleUtils<T> {


    public void createZhHeader(List<String> titleZhList, XSSFCellStyle cellStyle, XSSFSheet sheet) {
        int i = 0;
        //创建行
        //XSSFRow xssfRow;
        XSSFRow rowTitle = sheet.createRow(i);
        for (int cellTitle = 0; cellTitle < titleZhList.size(); cellTitle++) {
            Cell cellIndex = rowTitle.createCell(cellTitle);
            cellIndex.setCellStyle(cellStyle);
            cellIndex.setCellValue(titleZhList.get(cellTitle));
        }
    }

    private <T> void createContent(List<String> titleEnList, XSSFSheet xssfSheet, XSSFCellStyle cellStyle, Object oneRow, Class<T> clazz, int index)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // index已经创建了head
        XSSFRow rowContent = xssfSheet.createRow(index + 1);

        for (int cellContent = 0; cellContent < titleEnList.size(); cellContent++) {
            Cell cellIndex = rowContent.createCell(cellContent);
            cellIndex.setCellStyle(cellStyle);
            // 获取到内容的具体类型
            System.out.println("titleEnList: " + titleEnList.get(cellContent) );
            // EN存的是属性，获取类，根据属性拿到对应的值
            Field field = clazz.getDeclaredField(titleEnList.get(cellContent));
            field.setAccessible(true);
            String value = field.get(clazz.cast(oneRow)) != null ?  field.get(clazz.cast(oneRow)).toString() : "";
            System.out.println("value: " + value);
//            if ("class java.util.Date".equals(field.getType().toString()) && !StringUtils.isBlank(value) ) {
//                String temp = value + " GMT+0800";
//                System.out.println("temp: " + temp);
//                SimpleDateFormat date = new SimpleDateFormat(value + " GMT+0800");
//                System.out.println("simpleDate: " + value);
//            }
            cellIndex.setCellValue(value);
        }
    }

    /**
     * getWorkBook
     *
     * @param titleZhList 中文表头
     * @param titleEnList 返回map中key的值，要和中文表头对应
     * @param contentList List<T>
     * @param clazz List<T>中T的类型
     * @param sheetName  文件的名称
     * @return xwk
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    public <T> XSSFWorkbook getWriteFile(String sheetName, List<String> titleZhList, List<String> titleEnList,
                                         List<T> contentList, Class<T> clazz ) throws NoSuchFieldException, IllegalAccessException {

        // 创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        // 创建日期
        XSSFDataFormat dataFormat = xssfWorkbook.createDataFormat();
        // 床架文格式
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        cellStyle.setDataFormat(dataFormat.getFormat("@")); //文本格式
        // 创建工作表
        XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
        createZhHeader(titleZhList, cellStyle, sheet);
        int contentSize = contentList.size();
        for (int i = 0; i < contentSize; i++) {
            createContent(titleEnList, sheet, cellStyle, contentList.get(i), clazz, i);
        }
        return xssfWorkbook;

        //

        //创建行
        //XSSFRow xssfRow;  XSSFRow row = sheet.createRow(i);
        //创建列，即单元格Cell
        //XSSFCell xssfCell;
        //把List里面的数据写到excel中
//        for (int i=0;i<list.size();i++) {
//            //从第一行开始写入
//            xssfRow = xssfSheet.createRow(i);
//            //创建每个单元格Cell，即列的数据
//            List sub_list =list.get(i);
//            for (int j=0;j<sub_list.size();j++) {
//                xssfCell = xssfRow.createCell(j); //创建单元格
//                xssfCell.setCellValue((String)sub_list.get(j)); //设置单元格内容
//            }
//        }
//
//        //用输出流写到excel
//        try {
//            xssfWorkbook.write(outputStream);
//            outputStream.flush();
//            outputStream.close();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IOException {
        List<String> titleZhList = new ArrayList<String>();
        titleZhList.add("序号");
        titleZhList.add("学号");
        titleZhList.add("名字");
        titleZhList.add("年龄");
        titleZhList.add("登记时间");
        List<String> titleEnList = new  ArrayList<String>();
        titleEnList.add("commitId");
        titleEnList.add("commitKpiId");
        titleEnList.add("authorName");
        titleEnList.add("updateMessage");
        titleEnList.add("commitTime");
        List<CodingCommitKpi> contentsList = new  ArrayList<CodingCommitKpi>();
        CodingCommitKpi content = new CodingCommitKpi();
        CodingCommitKpi content1 = new CodingCommitKpi();
        content.setCommitId("1");
        content.setCommitKpiId(Long.parseLong("1234567890"));
        content.setUpdateMessage("17");
        content.setAuthorName("王勇");
        content.setCommitTime(new Date());
        content1.setCommitId("2");
        content1.setCommitKpiId(Long.parseLong("1234567891"));
        content1.setUpdateMessage("18");
        content1.setAuthorName("王勇1");
        content1.setCommitTime(new Date());
        contentsList.add(content);
        contentsList.add(content1);

        XSSFWorkbook workBook = null;
        FileOutputStream output = null;
        String sheetName = "student";
        String fileName = "D:/student.xlsx";

        CodingCommitKpi codingCommitKpi = new CodingCommitKpi();
        codingCommitKpi.setCommitTime(new Date());
        Field field=CodingCommitKpi.class.getDeclaredField("commitTime");
        field.setAccessible(true);
        //String value=field.get(codingCommitKpi)!=null?field.get(codingCommitKpi).toString():"";
        if (contentsList.size() > 0) {
            try {
                FIleUtils<CodingCommitKpi> eu = new FIleUtils<CodingCommitKpi>();
                workBook = eu.getWriteFile(sheetName, titleZhList, titleEnList, contentsList,CodingCommitKpi.class);
                output =  new FileOutputStream(fileName);
                workBook.write(output);
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
