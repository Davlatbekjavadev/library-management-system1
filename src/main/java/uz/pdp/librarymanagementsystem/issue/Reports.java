package uz.pdp.librarymanagementsystem.issue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/reports")
public class Reports extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Issue>issueList = Dao.getAllIssue();
        req.setAttribute("issueList",issueList);
        req.getRequestDispatcher("reports.jsp").forward(req,resp);
    }
}
