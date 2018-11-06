package oracle_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParseDAO {
    public static DBManage manager = DBManage.getInstance();
    public static PreparedStatement pstmt = null;
    public static Connection conn = null;
    public static ResultSet rs = null;
    private static Runtime run = Runtime.getRuntime();

    public static List<TableMetaInfo> getOracleLgTabMetaInfo()
    {
        //不带约束类型
        /*String sql1 = "select t.table_name,f.comments tabComm,b.column_name,b.data_type,"
            + "c.COMMENTS from user_tables t,"
            + "user_tab_comments f,user_tab_columns b,user_col_comments c "
            + "where t.table_name = f.table_name and t.table_name=b.table_name  "
            + "and t.table_name=c.table_name and b.column_name=c.column_name "
            + "order by t.table_name,b.column_id";*/

        //带约束类型
        String sql1 = "select t.table_name,f.comments tabComm,b.column_name,b.data_type,"
                + "b.data_length,b.DATA_PRECISION,b.DATA_SCALE,c.COMMENTS from user_tables t,"
                + "user_tab_comments f,user_tab_columns b,user_col_comments c "
                + "where t.table_name = f.table_name and t.table_name=b.table_name "
                + "and t.table_name=c.table_name and b.column_name=c.column_name "
                + "order by t.table_name,b.column_id";
        List<TableMetaInfo> list = new ArrayList<TableMetaInfo>();
        try {
            conn = manager.getLgConnection();
            pstmt = conn.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            TableMetaInfo userTables=null;
            while(rs.next()) {
                userTables = new TableMetaInfo();
                userTables.setTableName(rs.getString("table_name"));
                userTables.setTableNameCom(rs.getString("tabComm"));
                userTables.setColumnName(rs.getString("column_name"));
                userTables.setColumnType(rs.getString("data_type"));
                userTables.setColumnNameCom(rs.getString("COMMENTS"));
                //userTables.setConstraintType(rs.getString("constraint_type"));
                list.add(userTables);
            }
            return list;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            manager.close();
        }
        return null;
    }

    public static List<TableMetaInfo> getTableNameColumnInfo(String tableName) {
        String sql = "select t1.comments table_com, t.column_name,t.comments col_com from user_col_comments t join user_tab_comments t1 on t.table_name = t1.table_name where t.table_name = '"+tableName+"'";
        List<TableMetaInfo> list = new ArrayList<TableMetaInfo>();
        try {
            conn = manager.getLgConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            TableMetaInfo userTables=null;
            while(rs.next()) {
                userTables = new TableMetaInfo();
                userTables.setTableNameCom(rs.getString("table_com"));
                userTables.setColumnName(rs.getString("column_name"));
                userTables.setColumnNameCom(rs.getString("col_com"));
                list.add(userTables);
            }
            return list;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            manager.close();
        }
        return null;
    }
}


