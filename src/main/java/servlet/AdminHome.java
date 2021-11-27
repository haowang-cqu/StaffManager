package servlet;

import bean.Absence;
import bean.Department;
import bean.Staff;
import dao.AbsenceDao;
import dao.DepartmentDao;
import dao.StaffDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(urlPatterns = "/admin")
public class AdminHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("id") != null && (boolean) session.getAttribute("admin")) {
            // 性别映射
            HashMap<String, String> genderMap = new HashMap<>();
            genderMap.put("M", "男");
            genderMap.put("W", "女");
            // 获取所有员工
            ArrayList<Staff> staffs = StaffDao.getAllStaff();
            // 员工ID到Name的映射
            HashMap<Integer, String> staffMap = new HashMap<>();
            for (Staff staff : staffs) {
                staffMap.put(staff.getId(), staff.getName());
            }
            // 获取所有假条
            ArrayList<Absence> absences = AbsenceDao.getallAbsence();
            // 获取所有部门
            ArrayList<Department> departments = DepartmentDao.getAllDepartment();
            HashMap<Integer, String> departmentMap = new HashMap<>();
            for (Department department : departments) {
                departmentMap.put(department.getDep_id(), department.getDep_name());
            }
            req.setAttribute("absences", absences);
            req.setAttribute("staffMap", staffMap);
            req.setAttribute("staffs", staffs);
            req.setAttribute("genderMap", genderMap);
            req.setAttribute("departmentMap", departmentMap);
            req.getRequestDispatcher("/WEB-INF/admin.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
