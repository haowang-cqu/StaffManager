package servlet;

import bean.Administrator;
import bean.Staff;
import dao.AdministratorDao;
import dao.StaffDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断是否登录
        HttpSession session = req.getSession();
        if (session.getAttribute("id") != null) {
            int id = (int)session.getAttribute("id");
            // 管理员
            if ((boolean) session.getAttribute("admin")) {
                Administrator admin = AdministratorDao.getAdministartorById(id);
                req.setAttribute("admin", admin);
            }
            // 员工
            else {
                Staff staff = StaffDao.getStaffById(id);
                req.setAttribute("staff", staff);
            }
            req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
