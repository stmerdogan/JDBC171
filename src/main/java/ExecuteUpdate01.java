import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/postgres", "postgres", "491646Me.");
        Statement statement = connection.createStatement();

        //Örnek: companies table'indan number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        int updateEdilenSatirSayisi = statement.executeUpdate
                ("update companies set number_of_employees = 16000 where number_of_employees <(select avg(number_of_employees) from companies)");
        System.out.println("sql1 = " + updateEdilenSatirSayisi);

        connection.close();
        statement.close();
    }
}
