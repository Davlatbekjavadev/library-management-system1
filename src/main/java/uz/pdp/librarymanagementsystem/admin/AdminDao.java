package uz.pdp.librarymanagementsystem.admin;

import uz.pdp.librarymanagementsystem.db.DbConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    public static List<Admin> getAllAdmin() {
        try (Connection connection = DbConnection.getConnection();) {
            List<Admin> adminList = new ArrayList<>();
            String query = "select * from admin";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                Admin admin = Admin.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .password(password)
                        .role(role)
                        .build();

                adminList.add(admin);
            }
            return adminList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addNewAdmin(Admin admin) {
        try {
            Connection connection = DbConnection.getConnection();
            String insertUser = "insert into admin (name, email, password,role) VALUES (?, ?, ?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertUser);

            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setString(4, admin.getRole());
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
            PreparedStatement ps = con.prepareStatement("delete from admin where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int update(Admin admin) {
        int status = 0;
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "update admin set name=?,password=?,email=?,role=? where id=?");
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getEmail());
            ps.setString(4, admin.getRole());
            ps.setLong(5, admin.getId());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static Admin getEmployeeById(int id) {
        Admin admin = new Admin();

        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from admin where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin.setId(rs.getLong(1));
                admin.setName(rs.getString(2));
                admin.setEmail(rs.getString(3));
                admin.setPassword(rs.getString(4));
                admin.setRole(rs.getString(5));
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return admin;
    }
}
