import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //1. Adım: Driver'a kaydol ==> JDBC 4 sonrasi gerekli değil
        //Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "491646Me.");

        //3. Adım: Statement oluştur
        Statement statement = connection.createStatement();

        //4.Adim :Query calistir

        //1.Ornek :Workers adinda bir table olusturunuz

        boolean sql1 = statement.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");
        //execute() methodu parantez icerisinde belirtilen String sql komutunu database'de uygular
        System.out.println("sql1 = " + sql1);//false doner cunku bir data cagrilmadi

        /*
        execute () methodu DDL Data definition ile kullanilir (create-drop-alter-truncate) ile kullanildiginda
        data donmeyecegi icin her zaman 'false' doner

        execute() methodu DQL (select) ile kullanildiginda data cagirirsa 'true' cagirmazsa 'false' doner
         */

        //2.Ornek: Workers table'ina worker_adress adinda bir sutun ekleyiniz

        boolean sql2 = statement.execute("alter table workers add worker_adress varchar(100)");
        System.out.println("sql2 = " + sql2);//false doner cunku bir data cagrilmadi

        //3.Ornek :Workers table siliniz
        boolean sql3 = statement.execute("drop table workers");
        System.out.println("sql3 = " + sql3);

        //5.Adim :Baglantiyi kapat
        connection.close();
        statement.close();
    }
}

