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
import java.util.ArrayList;

@WebServlet(urlPatterns = "/staff/absence")
public class StaffAbsence extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 判断是否登录
        HttpSession session = req.getSession();
        if (session.getAttribute("id") != null && !(boolean) session.getAttribute("admin")) {
            String doSomething = req.getParameter("do");
            // 撤销
            if ("withdraw".equals(doSomething)) {
                int id = Integer.parseInt(req.getParameter("id"));
                AbsenceDao.deleteAbsence(id);
            }
            int uid = (int) session.getAttribute("id");
            ArrayList<Absence> absences = AbsenceDao.getAllAbusenceByuid(uid);
            req.setAttribute("absences", absences);
            req.getRequestDispatcher("/WEB-INF/absence.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect("/");
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
            ArrayList<Absence> absences = AbsenceDao.getAllAbusenceByuid(uid);
            req.setAttribute("absences", absences);
            req.getRequestDispatcher("/WEB-INF/absence.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect("/");
        }
    }
}
