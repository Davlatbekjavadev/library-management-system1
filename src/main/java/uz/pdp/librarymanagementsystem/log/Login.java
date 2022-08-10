package uz.pdp.librarymanagementsystem.log;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.librarymanagementsystem.admin.Admin;
import uz.pdp.librarymanagementsystem.admin.AdminDao;
import uz.pdp.librarymanagementsystem.user.User;
import uz.pdp.librarymanagementsystem.user.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/login")
public class Login extends HttpServlet {
    public static int d = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");


        List<User> userList = UserDao.getAllUsers();
        List<Admin> adminList = AdminDao.getAllAdmin();
        int a = 0;
        if (!role.equals("User")) {
            for (Admin admin : adminList) {
                if (admin.getName().equals(name) &&
                        admin.getPassword().equals(password) &&
                        admin.getEmail().equals(email)) {
                    a = 1;
                }
            }
        }

        if (role == null) {

            resp.sendRedirect("login.jsp");

        } else if (role.equals("Super Admin") && a == 1) {
            d = 1;

            req.getRequestDispatcher("/admin").include(req, resp);

        } else if (role.equals("Admin") && a == 1) {
            d = 2;
            req.getRequestDispatcher("/admin").include(req, resp);

        } else if (role.equals("User")) {
            int b = 0;
            String book = null;
            for (User user : userList) {
                if (user.getPassword().equals(password) &&
                        user.getName().equals(name) &&
                        user.getEmail().equals(email)) {
                    book = user.getBook();
                    b = 1;
                }
            }
            if (b == 1) {
                req.setAttribute("book",book);
                req.getRequestDispatcher("profile.jsp").include(req, resp);

            } else {
                req.getRequestDispatcher("index.jsp").include(req, resp);
            }
        }

    }
}
