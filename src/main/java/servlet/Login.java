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

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String admin = req.getParameter("admin");
        if ("1".equals(admin)) {
            req.setAttribute("admin", true);
        } else {
            req.setAttribute("admin", false);
        }
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String admin = req.getParameter("admin");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        // 管理员登录
        if ("1".equals(admin) && LoginDao.doLogin(id, password, true)) {
            HttpSession session = req.getSession();
            session.setAttribute("admin", true);
            session.setAttribute("id", id);
            session.setAttribute("name", name);
            req.getRequestDispatcher("/home").forward(req, resp);
        }
        // 普通用户登录
        else if (LoginDao.doLogin(id, password, false)) {
            HttpSession session = req.getSession();
            session.setAttribute("admin", false);
            session.setAttribute("id", id);
            session.setAttribute("name", name);
            req.getRequestDispatcher("/home").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/staff/login").forward(req, resp);
        }
    }
}
