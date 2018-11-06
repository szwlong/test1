package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle_database.TableMetaInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Hongten
 * @created 2014-5-20
 */
public class ExcelUtil {

    public void writeExcel(List<TableMetaInfo> list, String path) throws Exception {
        if (list == null) {
            return;
        } else if (path == null || Common.EMPTY.equals(path)) {
            return;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    writeXls(list, path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    writeXlsx(list, path);
                }
            }else{
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
    }

    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<TableMetaInfo> readExcel(String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public List<TableMetaInfo> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        TableMetaInfo tableMetaInfo = null;
        List<TableMetaInfo> list = new ArrayList<TableMetaInfo>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null)
                {
                    System.out.println(rowNum);
                    tableMetaInfo = new TableMetaInfo();
                    XSSFCell tableName = xssfRow.getCell(0);
                    XSSFCell tableCom = xssfRow.getCell(1);
                    XSSFCell columnName = xssfRow.getCell(2);
                    XSSFCell columnType = xssfRow.getCell(3);
                    XSSFCell columnCom = xssfRow.getCell(4);

                    tableMetaInfo.setTableName(getValue(tableName));
                    tableMetaInfo.setTableNameCom(getValue(tableCom));
                    tableMetaInfo.setColumnName(getValue(columnName));
                    tableMetaInfo.setColumnType(getValue(columnType));
                    tableMetaInfo.setColumnNameCom(getValue(columnCom));

                    list.add(tableMetaInfo);
                }
            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<TableMetaInfo> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        TableMetaInfo student = null;
        List<TableMetaInfo> list = new ArrayList<TableMetaInfo>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    student = new TableMetaInfo();
                    HSSFCell tableCom = hssfRow.getCell(0);
                    HSSFCell tableName = hssfRow.getCell(1);
                    HSSFCell columnCom = hssfRow.getCell(2);
                    HSSFCell columnName = hssfRow.getCell(3);
                    HSSFCell columnType = hssfRow.getCell(4);
                    HSSFCell columnLen = hssfRow.getCell(5);

                    student.setTableNameCom(getValue(tableCom));
                    student.setTableName(getValue(tableName));
                    student.setColumnNameCom(getValue(columnCom));
                    student.setColumnName(getValue(columnName));
                    student.setColumnType(getValue(columnType));
                    //student.setColumnLen(getValue(columnLen));
                    list.add(student);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if(xssfRow!=null)
        {
            if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
                return String.valueOf(xssfRow.getBooleanCellValue());
            } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
                return String.valueOf(xssfRow.getNumericCellValue());
            } else {
                return String.valueOf(xssfRow.getStringCellValue());
            }
        }
        else
        {
            return null;
        }

    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if(hssfCell!=null)
        {
            if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(hssfCell.getBooleanCellValue());
            } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
                return String.valueOf(hssfCell.getNumericCellValue());
            } else {
                return String.valueOf(hssfCell.getStringCellValue());
            }
        }
        else
        {
            return null;
        }
    }

    public void writeXls(List<TableMetaInfo> list, String path) throws Exception {
        if (list == null) {
            return;
        }
        int countColumnNum = list.size();
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("studentSheet");
        // option at first row.
        HSSFRow firstRow = sheet.createRow(0);
        HSSFCell[] firstCells = new HSSFCell[countColumnNum];
        String[] options = { "no", "name", "age", "score" };
        for (int j = 0; j < options.length; j++) {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(new HSSFRichTextString(options[j]));
        }
        //
        for (int i = 0; i < countColumnNum; i++) {
            HSSFRow row = sheet.createRow(i + 1);
            TableMetaInfo student = list.get(i);
            for (int column = 0; column < options.length; column++)
            {
                HSSFCell tableNameCom = row.createCell(0);
                HSSFCell tableName = row.createCell(1);
                HSSFCell columnNameCom = row.createCell(2);
                HSSFCell columnName = row.createCell(3);
                HSSFCell columnType = row.createCell(4);
                HSSFCell columnLen = row.createCell(5);

                tableNameCom.setCellValue(student.getTableNameCom());
                tableName.setCellValue(student.getTableName());
                columnNameCom.setCellValue(student.getColumnNameCom());
                columnName.setCellValue(student.getColumnName());
                columnType.setCellValue(student.getColumnType());
               // columnLen.setCellValue(student.getColumnLen());
            }
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(Common.WRITE_DATA + path);
        book.write(os);
        os.close();
    }

    public void writeXlsx(List<TableMetaInfo> list, String path) throws Exception {
        if (list == null) {
            return;
        }
        //XSSFWorkbook
        int countColumnNum = list.size();
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("studentSheet");
        // option at first row.
        XSSFRow firstRow = sheet.createRow(0);
        String[] options = {"表名", "表名描述", "字段名称", "字段类型" , "字段描述", "约束类型"};
        XSSFCell[] firstCells = new XSSFCell[options.length];
        for (int j = 0; j < options.length; j++)
        {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
//			XSSFCell column = firstRow.createCell(j);
//			column.setCellValue(new XSSFRichTextString(options[j]));
        }

        for (int i = 0; i < countColumnNum; i++)
        {
            XSSFRow row = sheet.createRow(i + 1);
            TableMetaInfo student = list.get(i);

            XSSFCell tableName = row.createCell(0);
            XSSFCell tableNameCom = row.createCell(1);
            XSSFCell columnName = row.createCell(2);
            XSSFCell columnType = row.createCell(3);
            XSSFCell columnNameCom = row.createCell(4);
            XSSFCell constraintType = row.createCell(5);

            tableNameCom.setCellValue(student.getTableNameCom());
            tableName.setCellValue(student.getTableName());
            columnNameCom.setCellValue(student.getColumnNameCom());
            columnName.setCellValue(student.getColumnName());
            columnType.setCellValue(student.getColumnType());
            //constraintType.setCellValue(student.getConstraintType());
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(Common.WRITE_DATA + path);
        book.write(os);
        os.close();
    }

    public static void writeExcel(List<Map> dataList, int cloumnCount,String finalXlsxPath){
        OutputStream out = null;
        try
        {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++)
            {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                String name = dataMap.get("BankName").toString();
                String address = dataMap.get("Addr").toString();
                String phone = dataMap.get("Phone").toString();
                for (int k = 0; k <= columnNumCount; k++)
                {
                    // 在一行内循环
                    Cell first = row.createCell(0);
                    first.setCellValue(name);

                    Cell second = row.createCell(1);
                    second.setCellValue(address);

                    Cell third = row.createCell(2);
                    third.setCellValue(phone);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
//            out =  new FileOutputStream(finalXlsxPath);
//            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     * @param
     * @param
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
