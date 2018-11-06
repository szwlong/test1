package utils;

import java.io.*;

public class GetTxtData {
    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。

        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String str = "";
            String str1 = "";
            //读取文件
            fis = new FileInputStream("C:\\Users\\lizs\\Desktop\\表名.txt");
            isr = new InputStreamReader(fis, "GBK");
            br = new BufferedReader(isr);
            //写入文件
            /*fw = new   FileWriter("C:\\Users\\lizs\\Desktop\\我的脚本\\外表_字段注释.txt");
            bw = new BufferedWriter(fw);*/

            while ((str = br.readLine()) != null) {


                //select 'BBA_AUTHORITY', column_name from user_tab_cols where table_name='BBA_AUTHORITY' ;
                /*str1 += "insert into szw_table_comments select table_name, comments from user_tab_comments where table_name='" + str + "';" + "\n";
                System.out.println(str1);*/
                //insert into szw_columns_comment select * from user_col_comments cc where cc.table_name='RBRC_LF_REPORT3';
                //FileInputStream fis1 = new FileInputStream("C:\\Users\\lizs\\Desktop\\我的脚本\\1_sqoop抽取数据库\\oracle中满足excel但有且仅有412张表\\oracle_excel_lgbm.txt");// FileInputStream
                // 从文件系统中的某个文件中获取字节
                //InputStreamReader isr1 = new InputStreamReader(fis1, "utf-8");// InputStreamReader 是字节流通向字符流的桥梁,
                // BufferedReader br1 = new BufferedReader(isr1);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象

                /*while((str = br.readLine()) != null){
                    str += str + "' row format delimited fields terminated by '\\t' stored as textfile LOCATION '/zhongye/liangang_v1.3/";
                    System.out.println(str);

                }*/

                /*String str2 = "";
                str2 += str + "'";
                System.out.println(str2);
                bw.write(str2);*/

                /*String tableName = "";
                String ziDuans = "";
                tableName = str.split("!!")[0];
                ziDuans = str.split("!!")[1];
                String str2 = "";
                str2 += "sqoop import --connect jdbc:oracle:thin:@172.16.89.19:1521:orcl --username JYLGMES --password JYLGMES --table " +
                        tableName + " --columns '" + ziDuans + "'  --target-dir '/zhongye/liangang_v1.3/" + tableName +
                        "' --fields-terminated-by '\\t' --hive-delims-replacement --hive-drop-import-delim --delete-target-dir --null-string '\\\\N' --null-non-string '\\\\N'  -m 1;";
                System.out.println(str2);// 打印出str1
                bw.write(str2 + "\n");*/




            }
            //bw.write(str1);
            // 当读取的一行不为空时,把读到的str的值赋给str1
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                /*bw.close();
                fw.close();*/
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
