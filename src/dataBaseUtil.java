import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class dataBaseUtil {


    public static void createTable(){
        String sql = "create table if not exists contact (" +
                " id serial primary key," +
                " name varchar(25) not null," +
                " surname varchar(25) not null," +
                " phone varchar(12) not null unique" +
                ")";
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson","db_user","1234");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
