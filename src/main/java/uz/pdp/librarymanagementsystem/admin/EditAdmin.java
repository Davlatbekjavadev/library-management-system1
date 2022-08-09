package uz.pdp.librarymanagementsystem.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editAdmin")
public class EditAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Admin admin = AdminDao.getEmployeeById(id);


        req.setAttribute("admin", admin);
        req.getRequestDispatcher("edit-admin.jsp").forward(req, resp);
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

        Admin admin = Admin.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .role(AdminDao.getEmployeeById(Math.toIntExact(id)).getRole())
                .build();

        AdminDao.update(admin);
        resp.sendRedirect("/admin");
    }
}
