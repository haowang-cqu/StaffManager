<%@ page import="bean.Staff" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Department" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>
<h1>员工管理</h1>
<a href="/staffs?do=add">添加员工</a>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>名字</th>
            <th>年龄</th>
            <th>性别</th>
            <th>入职日期</th>
            <th>部门</th>
            <th>职位</th>
            <th>工资</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <%
            ArrayList<Staff> staffs = (ArrayList<Staff>) request.getAttribute("staffs");
            ArrayList<Department> departments = (ArrayList<Department>) request.getAttribute("departments");
            HashMap<Integer, String> departmentMap = new HashMap<>();
            for (Department department : departments) {
                departmentMap.put(department.getDep_id(), department.getDep_name());
            }
            for (Staff staff : staffs) {
                String gender = staff.getGender();
                if ("M".equals(gender)) {
                    gender = "男";
                } else {
                    gender = "女";
                }
                String departmentName = departmentMap.get(staff.getDep_id());
        %>
        <tr>
            <td><%= staff.getId() %></td>
            <td><%= staff.getName() %></td>
            <td><%= staff.getAge() %></td>
            <td><%= gender %></td>
            <td><%= staff.getDate() %></td>
            <td><%= departmentName %></td>
            <td><%= staff.getPos() %></td>
            <td><%= staff.getSalary() %></td>
            <td>
                <a href="/staffs?do=delete&id=<%= staff.getId() %>">删除</a>
                <a href="/staffs?do=update&id=<%= staff.getId() %>">修改</a>
            </td>
        </tr>
        <% } %>
    </tbody>
</table>

</body>

<%@ include file="footer.jsp"%>
<script>
    $(function () {
        document.title = "员工管理";
    });
</script>
</html>