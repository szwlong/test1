package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class GetExcelData {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\lizs\\Desktop\\我的脚本\\数字字典\\oracle_lg_表注释.xlsx");
        String data = readExcel(file);
        String data1 = data.replaceAll("\\(null\\)", "null");
        /*String data2 = data1.replaceAll("，", ",");
        String data3 = data2.replaceAll("（", "(");
        String data4 = data3.replaceAll("）", ")");
        String data5 = data4.replaceAll("、", ",");
        String data6 = data5.replaceAll("#", "_");
        String data7 = data6.replaceAll("‘", "'");
        String data8 = data7.replaceAll("；", ";");
        String data9 = data6.replaceAll("’", "'");*/
        //String data9 = data8.replaceAll("", ",");
        System.out.println(data1);
        //writeToLocal(data);
    }

    public static String readExcel(File file) {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = new FileInputStream(file);
            String fileName = file.getName();
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            Sheet sheet = wb.getSheetAt(0);//第一个工作表  ，第二个则为1，以此类推...

            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();
            String tableName = "";
            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    // int lastCellIndex = row.getLastCellNum();
                    //此处参数cIndex决定可以取到excel的列数。

                    for (int cIndex = firstCellIndex; cIndex < 2; cIndex++) {

                        /*if (cIndex == 0) {
                            Cell cell = row.getCell(cIndex);

                            if (cell != null) {
                                String newTableName = cell.toString();
                                if( tableName.equals(newTableName)){
                                    continue;
                                }
                                //sb.append(value + "//");
                                sb.append("\n");
                                sb.append("DROP TABLE IF EXISTS ");
                                sb.append("lg_" + newTableName);
                                sb.append("; CREATE EXTERNAL TABLE ");
                                sb.append("lg_" + newTableName + " (");
                                //System.out.print(value+"\t");
                                tableName = newTableName;

                            }
                        }*/

                        if (cIndex == 0) {
                            Cell cell = row.getCell(cIndex);

                            if (cell != null) {
                                String newTableName = cell.toString();

                                //sb.append(value + "//");
                                sb.append("\n");
                                sb.append("DROP TABLE IF EXISTS ");
                                sb.append("lg_" + newTableName);
                                sb.append("; CREATE EXTERNAL TABLE ");
                                sb.append("lg_" + newTableName + " (");
                                //System.out.print(value+"\t");
                                tableName = newTableName;

                            }
                        }

                        /*if (cIndex == 1) {
                            Cell cell = row.getCell(cIndex);
                            String biaoZhuShi = "";
                            if (cell != null) {
                                biaoZhuShi = cell.toString();
                                sb.append(biaoZhuShi + "' ");
                                sb.append("row format delimited fields terminated by '\\t' stored as textfile LOCATION '/zhongye/liangang_v1.3/");
                                sb.append("\n");
                                //System.out.print(value+"\t");
                            }
                        }*/

                       /*if (cIndex == 1) {
                            Cell cell = row.getCell(cIndex);
                            String ziduan = "";
                            if (cell != null) {
                                ziduan = cell.toString();
                                sb.append(ziduan + " STRING COMMENT '");
                                //System.out.print(value+"\t");
                            }
                        }*/
                        /*if (cIndex == 2) {
                            Cell cell = row.getCell(cIndex);
                            String zhushi = "";
                            if (cell != null) {
                                zhushi = cell.toString();
                                //sb.append(value + "//");
                                sb.append(zhushi + "', ");
                                //sb.append("' row format delimited fields terminated by '\\t' stored as textfile LOCATION '/zhongye/liangang_v1.3/");
                                //sb.append(tableName + "';");
                                //System.out.print(value+"\t");
                            }
                        }*/
                    }
                    //sb.append("\n");
                    //System.out.println(sb.toString());
                }
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToLocal(String data) {
        try {
            char[] stringArr = data.toCharArray(); //注意返回值是char数组
            OutputStream f = new FileOutputStream("C:\\Users\\lizs\\Desktop\\建外表脚本_v1.1.txt");
            for (int x = 0; x < stringArr.length; x++) {
                f.write(stringArr[x]); // writes the bytes
            }
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}