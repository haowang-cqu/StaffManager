package servlet;

import bean.Absence;
import bean.Staff;
import dao.AbsenceDao;
import dao.StaffDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/absences")
public class AbsenceManage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 判断是否登录
        HttpSession session = req.getSession();
        if (session.getAttribute("id") != null && (boolean) session.getAttribute("admin")) {
            String doSomething = req.getParameter("do");
            // 批准
            if ("approve".equals(doSomething)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Absence absence = AbsenceDao.getAbsenceById(id);
                absence.setAgree("批准");
                AbsenceDao.updateAbsence(absence);
            }
            // 驳回
            if ("reject".equals(doSomething)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Absence absence = AbsenceDao.getAbsenceById(id);
                absence.setAgree("驳回");
                AbsenceDao.updateAbsence(absence);
            }
            ArrayList<Staff> staffs = StaffDao.getAllStaff();
            ArrayList<Absence> absences = AbsenceDao.getallAbsence();
            req.setAttribute("staffs", staffs);
            req.setAttribute("absences", absences);
            req.getRequestDispatcher("/WEB-INF/absences.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
