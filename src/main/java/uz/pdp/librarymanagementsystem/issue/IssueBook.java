package uz.pdp.librarymanagementsystem.issue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.librarymanagementsystem.books.BookDao;
import uz.pdp.librarymanagementsystem.user.User;
import uz.pdp.librarymanagementsystem.user.UserDao;

import java.io.IOException;

@WebServlet("/issue")
public class IssueBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bookList", BookDao.getAllBook());
        req.setAttribute("userList", UserDao.getAllUsers());


        req.getRequestDispatcher("/issue1.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_id = req.getParameter("user");
        String book_id = req.getParameter("book");
        String date = req.getParameter("date");
        String status = "true";


        User user = UserDao.getEmployeeById(Integer.parseInt(user_id));
        String userName = user.getName();
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();


        User user1 = User.builder()
                .id(Long.valueOf(user_id))
                .name(userName)
                .email(userEmail)
                .password(userPassword)
                .book(book_id)
                .build();

        UserDao.update(user1);

        Issue issue = Issue.builder()
                .data(date)
                .book(book_id)
                .status(status)
                .user(userName)
                .build();

        boolean result =  Dao.addNewIssue(issue);

        if (result) {
            resp.sendRedirect("/reports");
        }


    }
}
