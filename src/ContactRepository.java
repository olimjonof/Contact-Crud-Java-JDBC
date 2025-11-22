import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContactRepository {



    public boolean saveContact(Contact contact){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson","db_user","1234");
            String sql = "insert into contact(name, surname, phone) values(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getPhone());
            preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public Contact getByPhone(String phone){
        Contact contact = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson","db_user","1234");
            String sql ="select * from contact where phone = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                contact = new Contact();

                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }
    public List<Contact> getList(){
        Connection connection = null;
        List<Contact> contactList = new LinkedList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson","db_user","1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contact");

            while (resultSet.next()){
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                contactList.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactList;
    }
    public int delete(String phone){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson","db_user","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("Delete * from contact where phone=?0");
            preparedStatement.setString(1,phone);
            int effectedRows = preparedStatement.executeUpdate();
            return effectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
    public List<Contact> search(String query){
        Connection connection = null;
        List<Contact> contactList = new LinkedList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson","db_user","1234");
            String sql = "select * from contact where lower(name) like ? or lower(surname) like ? or phone like ?;";
            String param = "%"+query.toLowerCase() + "%";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,param);
            preparedStatement.setString(2,param);
            preparedStatement.setString(3,param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setPhone(resultSet.getString("phone"));
                contactList.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactList;
    }
}
