import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/postgres", "postgres", "491646Me.");
        Statement statement = connection.createStatement();

        System.out.println("\n=================Ornek1=======================\n");
        //1. Örnek: Selamlama yapan bir function oluşturup callable statement ile çağırınız.
        //CallableStatement olusturmak icin:

        //1.Adim:Functuion olusturma komutunu yazin
        String sql = "create or replace  function selamlama (x text) returns text as $$ begin " +
                "return 'Merhaba ' || x || '. Nasilsin?'; end $$ language plpgsql;";

        //2.Adim:Functuion komutunu calistir
        statement.execute(sql);

        //3.Adim: Function'i cagir
        String functionQuery = "select selamlama ('Ali')";
        ResultSet resultSet = statement.executeQuery(functionQuery);
        resultSet.next();
        System.out.println(resultSet.getString(1));

        //CallableStatement objesi olustur

        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");

        //4.Adim: Soru isaretlerine atama yapiniz
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, "Ali");

        //5.Adim:CallableStatement'i calistir
        callableStatement.execute();

        //6.Adim:Data'yi CallableStatement'dan cagir
        System.out.println(callableStatement.getString(1));

        System.out.println("\n=================Ornek2=======================\n");

        //2. Örnek: İki parametreyi toplayan bir function oluşturup callable statement ile çağırınız.

        //1.Adim:Function olustrma komutunu yaz
        String sql2 = "create or replace function toplama(x int, y int) " +
                "returns numeric as $$  begin return x+y; end; $$ language plpgsql;";

        //2.Adim:Function komutunu calistir
        statement.execute(sql2);

        //3.Adim: Function'i cagir

        //CallableStatement objesi olustur
        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?, ?)}");

        //4.Adim: Soru isaretlerine atama yapiniz
        callableStatement2.registerOutParameter(1, Types.NUMERIC);
        callableStatement2.setInt(2, 6);
        callableStatement2.setInt(3, 4);

        //5.Adim:CallableStatement'i calistir
        callableStatement2.execute();

        //6.Adim:Data'yi CallableStatement'dan cagir
        System.out.println(callableStatement2.getObject(1));

        JDBCUtils.closeConnection();
    }
}
