package uz.pdp.librarymanagementsystem.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class ViewAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Admin> adminList = AdminDao.getAllAdmin();

        req.setAttribute("adminList", adminList);
        for (Admin admin : adminList) {
            System.out.println(admin.getEmail());
            System.out.println(admin.getName());
            System.out.println(admin.getId());
            System.out.println(admin.getRole());
            System.out.println(admin.getPassword());
        }
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }
}
