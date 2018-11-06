package utils;

public class Common {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static final String LIB_PATH = "lib";
    public static final String STUDENT_INFO_XLS_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2003_POSTFIX;
    public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2010_POSTFIX;
    public static final String STUDENT_INFO_XLS_OUT_PATH = "lib/student_info_2003-2007.xls";
    public static final String STUDENT_INFO_XLSX_OUT_PATH = "lib/student_info_2010.xlsx";
    public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
    public static final String PROCESSING = "Processing...";
    public static final String WRITE_DATA = "write data to file : ";

    public static final String SQOOP_IMPORT_URL = "sqoop import --connect jdbc:oracle:thin:@192.168.33.28:1521:orcl";
    public static final String SQOOP_IMPORT_USERNAME = "--username JYZGMES";
    public static final String SQOOP_IMPORT_PASSWORD = "--password JYZGMES";

    //外表拼接语句
    public static final String prefix1 = "DROP TABLE IF EXISTS lg_";
    public static final String prefix2 = "; CREATE EXTERNAL TABLE lg_";
    public static final String prefix3 = " (";
    public static final String suffix1 = ") COMMENT '";
    public static final String suffix2 = "' row format delimited fields terminated by '\u0001' stored as textfile LOCATION '/JY/ODS/LG/";
    public static final String suffix3 = "/';";

    //orc表拼接语句


}

