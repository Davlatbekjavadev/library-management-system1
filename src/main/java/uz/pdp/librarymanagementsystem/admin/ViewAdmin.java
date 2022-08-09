package uz.pdp.librarymanagementsystem.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.librarymanagementsystem.log.Login;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class ViewAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Admin> adminList = AdminDao.getAllAdmin();
        if(Login.d ==1){
            req.setAttribute("role","Super Admin");
        }else if(Login.d ==2){
            req.setAttribute("role","Admin");
        }
        System.out.println(Login.d);
        req.setAttribute("adminList", adminList);


        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }
}
