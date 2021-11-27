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

@WebServlet(urlPatterns = "/staff")
public class StaffHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 判断是否登录
        if (session.getAttribute("id") != null && !(boolean) session.getAttribute("admin")) {
            int id = (int) session.getAttribute("id");
            // 性别映射
            HashMap<String, String> genderMap = new HashMap<>();
            genderMap.put("M", "男");
            genderMap.put("W", "女");
            req.setAttribute("genderMap", genderMap);
            // 获得基本信息
            Staff staff = StaffDao.getStaffById(id);
            req.setAttribute("staff", staff);
            // 获取所有假条
            ArrayList<Absence> absences = AbsenceDao.getAllAbusenceByuid(id);
            req.setAttribute("absences", absences);
            // 获取所有部门
            ArrayList<Department> departments = DepartmentDao.getAllDepartment();
            HashMap<Integer, String> departmentMap = new HashMap<>();
            for (Department department : departments) {
                departmentMap.put(department.getDep_id(), department.getDep_name());
            }
            req.setAttribute("departmentMap", departmentMap);
            req.getRequestDispatcher("/WEB-INF/staff.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
