import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/postgres", "postgres", "491646Me.");
        Statement statement = connection.createStatement();

        //1. Örnek:  region id'si 1 olan satir değerlerini çağırın.
        System.out.println("\n=================Ornek1=======================\n");
        String sql1 = "select * from countries where region_id = 1";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//true doner cunku select komutu ile data cagirdik

        //Not: SQL Query ile cagrilan data'yi gorebilmek icin executeQuery() methodu kullanmaliyiz
        //executeQuery() methodu cagrilan datayi Resultset datasi olarak doner
        //Default olarak Resultset bir pointer ile doner ve bu pointer sutun isimlerini gosterir.
        //Data'yi okuyabilmek icin next() methodu ile pointer'i siradaki satira tasimaliyiz
        //next() methodu sirada satir varsa true yoksa false doner

        //1.YOL
        //ResultSet resultSet = statement.executeQuery(sql1);
        //resultSet.next();
        //resultSet.next();
        //resultSet.next();
        //System.out.println(resultSet.getString("country_name"));

        //2.YOL
        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()) {
            System.out.println("country_id: " + resultSet.getString("country_id") +
                    "country_name: " + resultSet.getString("country_name") +
                    "region_id: " + resultSet.getString("region_id"));
        }

        //2.Ornek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        System.out.println("\n=================Ornek2=======================\n");

        String sql2 = "select country_id, country_name from countries where region_id >2";
        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getString(1) + "  ||  " + resultSet2.getString(2));

        }

        // 3.Ornek : "number_of_employees" degeri en dusuk olan satirin tum degerlerini cagirin

        System.out.println("\n=================Ornek3=======================\n");

        String sql3 = "select * from companies where number_of_employees = (select min(number_of_employees) from companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3);
        resultSet3.next();

        System.out.println(resultSet3.getString(1) + " || " + resultSet3.getString(2) +
                " || " + resultSet3.getString(3));
        connection.close();
        statement.close();
    }
}
