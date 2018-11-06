package client;

import jiaoben.JiaoBen;
import oracle_database.ParseDAO;
import oracle_database.TableMetaInfo;
import sun.nio.cs.ext.EUC_CN;
import utils.ExcelUtil;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        String dataDictionary = "C://users//lizs//Desktop//tets/lg_mes.xlsx";
        String writePath = "C://users//lizs//Desktop//tets//oracle_meta9.xlsx";
        JiaoBen jiaoBen = new JiaoBen();
        //readDataBase_lg1();
        //jiaoBen.lg(dataDictionary, writePath);
        //jiaoBen.ext_lg(dataDictionary, writePath);
        System.out.println("oracle元数据信息获取中......");
        List<TableMetaInfo> oracleLgTabMetaInfo = ParseDAO.getOracleLgTabMetaInfo();
        System.out.println("<table>:"+oracleLgTabMetaInfo.size());
        try {
            new ExcelUtil().writeExcel(oracleLgTabMetaInfo, writePath);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
