package servlet;

import dao.LoginDao;
import utils.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/staff/login")
public class StaffLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Log.write("Request login page");
        req.getRequestDispatcher("/WEB-INF/staff_login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (LoginDao.doStaffLogin(id, password)) {
            Log.write("DEBUG: " + id + " login");
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            session.setAttribute("name", name);
            req.getRequestDispatcher("/staff/home").forward(req, resp);
        } else {
            Log.write("DEBUG: 用户名或密码错误!");
            req.setAttribute("error", "用户名或密码错误");
            req.getRequestDispatcher("/staff/login").forward(req, resp);
        }
    }
}
