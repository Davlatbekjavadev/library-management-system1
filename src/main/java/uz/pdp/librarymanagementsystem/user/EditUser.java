package uz.pdp.librarymanagementsystem.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/edit")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        User user = UserDao.getEmployeeById(id);


        req.setAttribute("user", user);
        req.getRequestDispatcher("edit-student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        String sid=req.getParameter("id");
        Long id=Long.parseLong(sid);
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String email=req.getParameter("email");

        User user = User.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();

        UserDao.update(user);
        resp.sendRedirect("/students");
    }
}
