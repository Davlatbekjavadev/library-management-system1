package uz.pdp.librarymanagementsystem.issue;

import uz.pdp.librarymanagementsystem.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    public static int update(Issue issue) {
        int status = 0;
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "update issue set status=?where id=?");
            ps.setString(1, issue.getStatus());
            ps.setLong(2, issue.getId());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static List<Issue> getAllIssue() {
        try (Connection connection = DbConnection.getConnection();) {
            List<Issue> issueList = new ArrayList<>();
            String query = "select * from issue";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String data = resultSet.getString("date");
                String book = resultSet.getString("book");
                String user = resultSet.getString("users");
                String status = resultSet.getString("status");


                Issue issue = Issue.builder()
                        .id(id)
                        .user(user)
                        .book(book)
                        .data(data)
                        .status(status)
                        .build();

                issueList.add(issue);
            }
            return issueList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addNewIssue(Issue issue) {
        try {
            Connection connection = DbConnection.getConnection();
            String insertUser = "insert into issue (date, users, book,status) VALUES (?, ?, ?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertUser);

            preparedStatement.setString(1, issue.getData());
            preparedStatement.setString(2, issue.getUser());
            preparedStatement.setString(3, issue.getBook());
            preparedStatement.setString(4, issue.getStatus());

            int executeUpdate = preparedStatement.executeUpdate();

            return executeUpdate == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
