import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/postgres", "postgres", "491646Me.");
        Statement statement = connection.createStatement();

        //1. Örnek: companies3 tablosundan en yüksek ikinci number_of_employees değeri olan
        // company ve number_of_employees değerlerini çağırın.

        System.out.println("\n=================Ornek1- 1.Yol=======================\n");

        String sql1 = "select * from companies3 where number_of_employees =" +
                "( select number_of_employees from companies3 order by number_of_employees desc offset 1 limit 1)";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        while (resultSet1.next()) {

            System.out.println(resultSet1.getString(1) + " || " + resultSet1.getString(2) +
                    " || " + resultSet1.getInt(3));
        }

        //2.Yol -Subquery kullanarak

        System.out.println("\n=================Ornek1- 2.Yol=======================\n");

        String sql2 = "select * from companies3 where number_of_employees = (select max(number_of_employees) from companies3  " +
                "where number_of_employees < (select max(number_of_employees) from companies3))";
        ResultSet resultSet2 = statement.executeQuery(sql2);
        while (resultSet2.next()) {

            System.out.println(resultSet2.getString(1) + " || " + resultSet2.getString(2) +
                    " || " + resultSet2.getInt(3));
            connection.close();
            statement.close();
        }
    }
}
