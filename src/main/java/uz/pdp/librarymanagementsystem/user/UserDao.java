package uz.pdp.librarymanagementsystem.user;

import uz.pdp.librarymanagementsystem.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static List<User> getAllUsers() {
        try (Connection connection = DbConnection.getConnection();) {
            List<User> userList = new ArrayList<>();
            String query = "select * from users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = User.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .password(password)
                        .build();

                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addNewUser(User user) {
        try {
            Connection connection = DbConnection.getConnection();
            String insertUser = "insert into users (name, email, password) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertUser);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            int executeUpdate = preparedStatement.executeUpdate();

            return executeUpdate == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int update(User user) {
        int status = 0;
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "update users set name=?,password=?,email=? where id=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getId());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static User getEmployeeById(int id) {
        User user = new User();

        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

}
