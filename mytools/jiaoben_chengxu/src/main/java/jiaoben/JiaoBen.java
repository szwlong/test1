package jiaoben;

import oracle_database.ParseDAO;
import oracle_database.TableMetaInfo;
import utils.ExcelUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static utils.Common.*;

public class JiaoBen {
    public void lg(String excelPath, String writePath) {
        try {
            List<TableMetaInfo> list = new ExcelUtil().readXlsx(excelPath);
            if (list != null) {
                List listTabName = new ArrayList();
                for (TableMetaInfo tmi : list) {
                    if (!listTabName.contains(tmi.getTableName())) {
                        listTabName.add(tmi.getTableName());
                    }
                }
                System.out.println("一共多少张表:" + listTabName.size());
                FileOutputStream out = new FileOutputStream(writePath);
                PrintStream p = new PrintStream(out);

                for (int i = 0; i < listTabName.size(); i++) {
                    String tabName = (String) listTabName.get(i);
                    if (tabName != null) {
                        String columnStr = "--columns '";

                        for (TableMetaInfo tmi : list) {
                            if (tabName.equals(tmi.getTableName())) {
                                columnStr += tmi.getColumnName() + ",";
                            }
                        }

                        String ss = columnStr.substring(0, columnStr.length() - 1) + "'";

                        //if(map.containsKey(tabName))
                        if (tabName.contains("LT_HYS_GLZ")) {
                            p.println("sqoop import --connect jdbc:oracle:thin:@172.16.89.19:1521:orcl --username JYLGMES --password JYLGMES --query 'select \"pk\" as PK_bak,\"cAssayCode\" as cAssayCode_bak,\"dAssayDate\" as dAssayDate_bak,\"S\" as S_bak,\"Si\" as Si_bak,\"Ca\" as Ca_bak ,\"Mg\" as Mg_bak,\"Al\" as Al_bak,\"P\" as P_bak,\"Ti\" as Ti_bak,\"Rz\" as Rz_bak,\"def1\" as def1_bak,\"def2\" as def2_bak,\"def3\" as def3_bak,\"def4\" as def4_bak,\"def5\" as def5_bak,\"dUpDate\" as dUpDate_bak,\"cMemo\" as cMemo_bak from LT_HYS_GLZ WHERE 1=1 AND $CONDITIONS' --target-dir '/zhongye/lg/data/LT_HYS_GLZ' --fields-terminated-by '\001' --hive-delims-replacement --hive-drop-import-delim --delete-target-dir --null-string '\\\\N' --null-non-string '\\\\N' -m 1;");
                        } else if (tabName.contains("LT_HYS_HT")) {
                            p.println("sqoop import --connect jdbc:oracle:thin:@172.16.89.19:1521:orcl --username JYLGMES --password JYLGMES --query 'select \"pk\" as PK_bak,\"cAssayCode\" as cAssayCode_bak,\"dAssayDate\" as dAssayDate_bak,\"Si\" as Si_bak,\"Mn\" as Mn_bak,\"P\" as P_bak ,\"S\" as S_bak,\"Ti\" as Ti_bak,\"def1\" as def1_bak,\"def2\" as def2_bak,\"def3\" as def3_bak,\"def4\" as def4_bak,\"def5\" as def5_bak,\"dUpDate\" as dUpDate_bak,\"cMemo\" as cMemo_bak from LT_HYS_GLZ WHERE 1=1 AND $CONDITIONS' --target-dir '/zhongye/lg/data/LT_HYS_GLZ' --fields-terminated-by '\001' --hive-delims-replacement --hive-drop-import-delim --delete-target-dir --null-string '\\\\N' --null-non-string '\\\\N' -m 1;");
                        } else if (tabName.contains("LT_HYS_JS")) {
                            p.println("sqoop import --connect jdbc:oracle:thin:@172.16.89.19:1521:orcl --username JYLGMES --password JYLGMES --query 'select \"pk\" as PK_bak,\"cAssayCode\" as cAssayCode_bak,\"dAssayDate\" as dAssayDate_bak,\"Fe\" as Fe_bak,\"S\" as S_bak,\"Si\" as Si_bak ,\"Ca\" as Ca_bak,\"Mg\" as Mg_bak,\"Al\" as Al_bak,\"P\" as P_bak,\"Ti\" as Ti_bak,\"Rz\" as Rz_bak,\"def1\" as def1_bak,\"def2\" as def2_bak,\"def3\" as def3_bak,\"def4\" as def4_bak,\"def5\" as def5_bak,\"dUpDate\" as dUpDate_bak,\"cMemo\" as cMemo_bak from LT_HYS_GLZ WHERE 1=1 AND $CONDITIONS' --target-dir '/zhongye/lg/data/LT_HYS_GLZ' --fields-terminated-by '\001' --hive-delims-replacement --hive-drop-import-delim --delete-target-dir --null-string '\\\\N' --null-non-string '\\\\N' -m 1;");
                        } else if (tabName.contains("LT_HYS_ST")) {
                            p.println("sqoop import --connect jdbc:oracle:thin:@172.16.89.19:1521:orcl --username JYLGMES --password JYLGMES --query 'select \"pk\" as PK_bak,\"cAssayCode\" as cAssayCode_bak,\"dAssayDate\" as dAssayDate_bak,\"Si\" as Si_bak,\"Mn\" as Mn_bak,\"P\" as P_bak ,\"S\" as S_bak,\"Ti\" as Ti_bak,\"def1\" as def1_bak,\"def2\" as def2_bak,\"def3\" as def3_bak,\"def4\" as def4_bak,\"def5\" as def5_bak,\"dUpDate\" as dUpDate_bak,\"cMemo\" as cMemo_bak from LT_HYS_GLZ WHERE 1=1 AND $CONDITIONS' --target-dir '/zhongye/lg/data/LT_HYS_GLZ' --fields-terminated-by '\001' --hive-delims-replacement --hive-drop-import-delim --delete-target-dir --null-string '\\\\N' --null-non-string '\\\\N' -m 1;");
                        } else {
                            p.println("sqoop import --connect jdbc:oracle:thin:@172.16.89.19:1521:orcl --username JYLGMES --password JYLGMES --table " + tabName + " " + ss + " --target-dir '/zhongye/lg/data/" + tabName.replace("#", "").replace("$", "") + "' --fields-terminated-by '\001' --hive-delims-replacement --hive-drop-import-delim --delete-target-dir --null-string '\\\\N' --null-non-string '\\\\N'  -m 1;");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void ext_lg(String excelPath, String writePath) {
        String ss = pretreatment(excelPath);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(writePath);
            PrintStream p = new PrintStream(out);
            p.println(ss);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    public void ext_lg(String excelPath, String writePath) {
        try {
            //1、读取数据字典
            List<TableMetaInfo> list = new ExcelUtil().readXlsx(excelPath);
            FileOutputStream out = new FileOutputStream(writePath);
            PrintStream p = new PrintStream(out);
            if (list != null) {
                List listTabName = new ArrayList();
                for (TableMetaInfo tmi : list) {
                    if (!listTabName.contains(tmi.getTableName())) {
                        listTabName.add(tmi.getTableName());
                    }
                }
                System.out.println("一共多少张表:" + listTabName.size());
                for (int i = 0; i < listTabName.size(); i++) {
                    String tabName = (String) listTabName.get(i);
                    if (tabName != null) {
                        String prefix = "";
                        String columnStr = "";
                        String tableCom = "";
                        String columnName = "";
                        String columnCom = "";
                        for (TableMetaInfo tmi : list) {
                            if (tabName.equals(tmi.getTableName())) {
                                tableCom = tmi.getTableNameCom();
                                columnName = tmi.getColumnName().replace("#", "_");
                                columnCom = tmi.getColumnNameCom();
                                if (columnCom != null) {
                                    columnStr += columnName + " STRING COMMENT '" + columnCom.replace("’", "'") + "', ";
                                } else {
                                    columnStr += columnName + " STRING COMMENT '" + columnCom + "', ";
                                }
                            }
                        }
                        prefix = prefix1 + tabName + prefix2 + tabName + prefix3;
                        String ss = prefix + columnStr.substring(0, columnStr.length() - 2);
                        if (tableCom != null) {
                            ss += suffix1 + tableCom.replace("’", "'");
                        } else {
                            ss += suffix1 + tableCom;
                        }
                        ss += suffix2 + tabName + suffix3;
                        p.println(ss);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void orc_lg(String excelPath, String writePath) {
        //1、读取数据字典
        List<TableMetaInfo> list = null;
        try {
            list = new ExcelUtil().readXlsx(excelPath);
            FileOutputStream out = new FileOutputStream(writePath);
            PrintStream p = new PrintStream(out);
            if (list != null) {
                List listTabName = new ArrayList();
                for (TableMetaInfo tmi : list) {
                    if (!listTabName.contains(tmi.getTableName())) {
                        listTabName.add(tmi.getTableName());
                    }
                }
                System.out.println("一共多少张表:" + listTabName.size());
                for (int i = 0; i < listTabName.size(); i++) {
                    String tabName = (String) listTabName.get(i);
                    if (tabName != null) {
                        String prefix = "";
                        String columnStr = "";
                        String tableCom = "";
                        String columnName = "";
                        String columnCom = "";
                        String fentong = "";
                        for (TableMetaInfo tmi : list) {
                            if (tabName.equals(tmi.getTableName())) {
                                tableCom = tmi.getTableNameCom();
                                columnName = tmi.getColumnName().replace("#", "_");
                                columnCom = tmi.getColumnNameCom();
                                if (columnCom != null) {
                                    columnStr += columnName + " STRING COMMENT '" + columnCom.replace("’", "'") + "', ";
                                } else {
                                    columnStr += columnName + " STRING COMMENT '" + columnCom + "', ";
                                }
                                //1、如果字段中有head_id,则以head_id进行分桶。
                                if (tmi.getColumnName().equalsIgnoreCase("heat_no") || tmi.getColumnName().equalsIgnoreCase("heat_id")
                                        || tmi.getColumnName().equalsIgnoreCase("heatno") || tmi.getColumnName().equalsIgnoreCase("headid")) {

                                    fentong = "CLUSTERED BY (" + tmi.getColumnName() + ") INTO 7 BUCKETS";
                                }
                            }
                        }
                        if(fentong != null || "".equals(fentong)){

                        }

                        prefix = prefix1 + tabName + prefix2 + tabName + prefix3;
                        String ss = prefix + columnStr.substring(0, columnStr.length() - 2);
                        if (tableCom != null) {
                            ss += suffix1 + tableCom.replace("’", "'");
                        } else {
                            ss += suffix1 + tableCom;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




                    /*//根据表名获取oracle数据库中的元数据
                    List<TableMetaInfo> table_Column_Info = ParseDAO.getTableNameColumnInfo(tableName);
                    String columnStr = "";
                    String tableNameStr = "";
                    String fentong = "";
                    //获取表注释、列注释, 并组装info
                    for(TableMetaInfo tmi : table_Column_Info) {
                        if(listTableName.equals(tmi.getTableName())) {
                            if (tmi.getColumnNameCom() != null) {
                                columnStr += tmi.getColumnName().replace("#", "_") + " STRING COMMENT '" + tmi.getColumnNameCom().replace("’", "'") + "', ";
                            } else {
                                columnStr += tmi.getColumnName().replace("#", "_") + " STRING COMMENT '" + tmi.getColumnNameCom() + "', ";
                            }
                            if (tmi.getTableNameCom() != null) {
                                tableNameStr += tmi.getTableNameCom().replace("'", "");

                            }
                            if (tmi.getColumnName().equalsIgnoreCase("heat_no") || tmi.getColumnName().equalsIgnoreCase("heat_id")
                                    || tmi.getColumnName().equalsIgnoreCase("heatno") || tmi.getColumnName().equalsIgnoreCase("headid")) {

                                fentong = "CLUSTERED BY (" + tmi.getColumnName() + ") INTO 7 BUCKETS";
                            }
                        }
                    }
                    if(fentong == null || "".equals(fentong) && table_Column_Info != null && table_Column_Info.size() > 0){
                        if(table_Column_Info.size() == 1){
                            TableMetaInfo tableMetaInfo1 = table_Column_Info.get(0);
                            fentong = "CLUSTERED BY (" + tableMetaInfo1.getColumnName() + ") INTO 7 BUCKETS";
                            System.out.println("主键名称：" + tableMetaInfo1.getColumnName() + " 表名："  );
                        }

                    }*/


    }
}
