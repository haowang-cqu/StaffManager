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
import java.sql.Date;


@WebServlet(urlPatterns = "/am")
public class AbsenceManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String doSomething = req.getParameter("do");
        // 用户撤销请假
        if ("withdraw".equals(doSomething)) {
            if (session.getAttribute("id") != null && !(boolean) session.getAttribute("admin")) {
                int id = Integer.parseInt(req.getParameter("id"));
                AbsenceDao.deleteAbsence(id);
            }
            resp.sendRedirect("/staff");
            return;
        }
        // 管理员批准
        if ("approve".equals(doSomething)) {
            if (session.getAttribute("id") != null && (boolean) session.getAttribute("admin")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Absence absence = AbsenceDao.getAbsenceById(id);
                absence.setAgree("批准");
                AbsenceDao.updateAbsence(absence);
            }
            resp.sendRedirect("/admin");
            return;
        }
        // 管理员驳回
        if ("reject".equals(doSomething)) {
            if (session.getAttribute("id") != null && (boolean) session.getAttribute("admin")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Absence absence = AbsenceDao.getAbsenceById(id);
                absence.setAgree("驳回");
                AbsenceDao.updateAbsence(absence);
            }
            resp.sendRedirect("/admin");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Date date = Date.valueOf(req.getParameter("date"));
        String reason = req.getParameter("reason");
        HttpSession session = req.getSession();
        if (session.getAttribute("id") != null && !(boolean) session.getAttribute("admin")) {
            int uid = (int) session.getAttribute("id");
            Absence absence = new Absence();
            absence.setDate(date);
            absence.setReason(reason);
            absence.setUser_id(uid);
            absence.setAgree("待处理");
            AbsenceDao.insertAbsence(absence);
            resp.sendRedirect("/staff");
        }
        else {
            resp.sendRedirect("/");
        }
    }
}
