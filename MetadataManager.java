import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetadataManager {
    public static void showTableInfo(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        String[] types = {"TABLE"};
        ResultSet tables = metaData.getTables(null, null, "%", types);

        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println("Table Name: " + tableName);

            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            System.out.println("Columns:");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                System.out.println("  " + columnName + " (" + columnType + ")");
            }
            System.out.println();
        }
    }

    public static void showKeyInfo(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, null );

        System.out.println("Primary Keys:");
        while (primaryKeys.next()) {
            String tableName = primaryKeys.getString("TABLE_NAME");
            String columnName = primaryKeys.getString("COLUMN_NAME");
            if (!tableName.startsWith("pg_")) {
                System.out.println("  " + tableName + ": " + columnName);
            }}

        ResultSet foreignKeys = metaData.getImportedKeys(null, null, null);
        System.out.println("Foreign Keys:");
        while (foreignKeys.next()) {
            String tableName = foreignKeys.getString("FKTABLE_NAME");
            String columnName = foreignKeys.getString("FKCOLUMN_NAME");
            String refTableName = foreignKeys.getString("PKTABLE_NAME");
            String refColumnName = foreignKeys.getString("PKCOLUMN_NAME");
            System.out.println("  " + tableName + "." + columnName + " -> " + refTableName + "." + refColumnName);
        }
    }
}
