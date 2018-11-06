package oracle_database;

public class TableMetaInfo {

    private String tableName;
    private String tableNameCom;
    private String columnName;
    private String columnNameCom;
    private String columnType;
    private String constraintType;

    public String getTableNameCom() {
        return tableNameCom;
    }
    public void setTableNameCom(String tableNameCom) {
        this.tableNameCom = tableNameCom;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getColumnNameCom() {
        return columnNameCom;
    }
    public void setColumnNameCom(String columnNameCom) {
        this.columnNameCom = columnNameCom;
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getColumnType() {
        return columnType;
    }
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    public String getConstraintType() {
        return constraintType;
    }
    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

}

