package uz.pdp.librarymanagementsystem.log;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.librarymanagementsystem.user.User;
import uz.pdp.librarymanagementsystem.user.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"", "/login"})
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");


        List<User> userList = UserDao.getAllUsers();
        int a = 0;
        for (User user : userList) {
            if (user.getPassword().equals(password) &&
                    user.getName().equals(password) &&
                    user.getEmail().equals(email)) {
                a = 1;
            }
        }


        if (role == null) {

            resp.sendRedirect("login.jsp");

        } else if (role.equals("Super Admin") && a == 1) {

            req.setAttribute("kalit", "Super Admin");
            req.getRequestDispatcher("/book.jsp").include(req, resp);

        } else if (role.equals("Admin") && a == 1) {

            req.setAttribute("kalit", "Admin");
            req.getRequestDispatcher("/book.jsp").include(req, resp);

        } else if (role.equals("User") && a == 1) {
            req.setAttribute("kalit", "User");
            req.getRequestDispatcher("/book.jsp").include(req, resp);
        }

    }
}
