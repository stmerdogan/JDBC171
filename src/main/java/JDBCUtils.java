import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JDBCUtils {

    private static Connection connection;
    private static Statement statement;


    //Bu method database ile bağlantı kurup Connection data döner
//    public static Connection connectToDatabase() {
//
//        try {
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return connection;
//    }

    public static Connection connectToDatabase() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    //Bu method connectToDatabase() methodunu içinde çağırarak bir statement objesi oluşturup return yapar
    public static Statement createStatement() {

        try {
            statement = connectToDatabase().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }


    //Bu method bir SQL query'yi çalıştırıp data dönüyorsa true, dönmüyorsa false verir
    public static boolean execute(String sql) {

        try {
            return createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Ödev: executeUpdate methodu için bir reusable method oluşturunuz


    //Bu method bir SQL query'yi çalıştırıp sonucu ResultSet olarak döner
    public static ResultSet executeQuery(String sql) {

        try {
            return createStatement().executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Bu method istediğimiz bir table'ın isteğimiz bir sütunu list olarak döner
    public static List<Object> getColumnList(String tableName, String columnName) throws SQLException {

        List<Object> list = new ArrayList<>();

        ResultSet resultSet = executeQuery("select " + columnName + " from " + tableName);

        while (resultSet.next()) {
            list.add(resultSet.getObject(columnName));
        }

        return list;

    }

    //Bu method bağlantıyı kapatır
    public static void closeConnection() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}