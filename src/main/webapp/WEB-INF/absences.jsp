<%@ page import="bean.Staff" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Department" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bean.Absence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>
<h1>假条管理</h1>
<a href="/home">回到首页</a>
<a href="/logout">退出登录</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>员工</th>
        <th>时间</th>
        <th>缘由</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Staff> staffs = (ArrayList<Staff>) request.getAttribute("staffs");
        ArrayList<Absence> absences = (ArrayList<Absence>) request.getAttribute("absences");
        HashMap<Integer, String> staffMap = new HashMap<>();
        for (Staff staff : staffs) {
            staffMap.put(staff.getId(), staff.getName());
        }

        for (Absence absence : absences) {
            String staffName = staffMap.get(absence.getUser_id());
    %>
    <tr>
        <td><%= absence.getAbs_id() %></td>
        <td><%= staffName %></td>
        <td><%= absence.getDate() %></td>
        <td><%= absence.getReason() %></td>
        <td><%= absence.getAgree() %></td>
        <td>
            <a href="/absences?do=approve&id=<%= absence.getAbs_id() %>">批准</a>
            <a href="/absences?do=reject&id=<%= absence.getAbs_id() %>">驳回</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>

<%@ include file="footer.jsp"%>
<script>
    $(function () {
        document.title = "假条管理";
    });
</script>
</html>