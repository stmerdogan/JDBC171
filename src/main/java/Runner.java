import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Runner {

    public static void main(String[] args) throws SQLException {

        //JDBCUtils.connectToDatabase();
        //JDBCUtils.createStatement();

        //Execute methodunu çalıştır
        JDBCUtils.execute("select * from companies");

        //campanies table'ının tüm sütunlarını yazdır
        ResultSet resultSet = JDBCUtils.executeQuery("select * from companies");

        List<String> list = new ArrayList<>();
        while (resultSet.next()){
            System.out.println(resultSet.getString("company"));
        }

        //company sütunun değerlerini bir list içerisine al
        List<Object> companyList = JDBCUtils.getColumnList("companies","company");
        System.out.println("companyList = " + companyList);

        //Bağlantıyı kapat
        JDBCUtils.closeConnection();
    }
}
