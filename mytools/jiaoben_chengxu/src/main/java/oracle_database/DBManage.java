package oracle_database;

import utils.ExcelUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBManage {
    private static DBManage dbManage=null;
    private static Connection con = null;
    private Runtime run = Runtime.getRuntime();

    public DBManage() {
    }

    public static DBManage getInstance()
    {
        if(dbManage==null)
        {
            dbManage = new DBManage();
        }
        return dbManage;
    }

    public static Connection getLgConnection()
    {
        try
        {
            /*String url = "jdbc:oracle:thin:@172.16.89.19:1521:orcl";
            String usr = "JYLGMES";
            String pwd = "JYLGMES";*/
            String url = "jdbc:oracle:thin:@172.16.89.19:1521:orcl";
            String usr = "JYLGMES";
            String pwd = "JYLGMES";
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            DriverManager.setLoginTimeout(100000);
            Connection con1 = DriverManager.getConnection(url, usr, pwd);
            return con1;
            //con.setAutoCommit(autoCommit);
        } catch (SQLException ex) {
            System.out.println("SQLException"+ex);
        } catch (InstantiationException ex) {
            System.out.println("InstantiationException"+ex);
        } catch (IllegalAccessException ex) {
            System.out.println("IllegalAccessException"+ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException"+ex);
        }
        return null;
    }

    public static void readDataBase_lg1(){
        try
        {
            List<TableMetaInfo> list = ParseDAO.getOracleLgTabMetaInfo();
            System.out.println("<table>:"+list.size());
            System.out.println("======================================");//
            new ExcelUtil().writeExcel(list, "C://users//lizs//Desktop//result3.xlsx");
            System.out.println("======================================");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void close()
    {
        if (con != null)
        {
            try
            {
                con.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                con = null;
            }
        }
    }

}
