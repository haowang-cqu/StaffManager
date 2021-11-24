<%@ page import="bean.Staff" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Department" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bean.Absence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>
<h1>我的假条</h1>
<a href="/home">回到首页</a>
<a href="/logout">退出登录</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>时间</th>
        <th>缘由</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Absence> absences = (ArrayList<Absence>) request.getAttribute("absences");
        for (Absence absence : absences) {
    %>
    <tr>
        <td><%= absence.getAbs_id() %></td>
        <td><%= absence.getDate() %></td>
        <td><%= absence.getReason() %></td>
        <td><%= absence.getAgree() %></td>
        <td>
            <a href="/staff/absence?do=withdraw&id=<%= absence.getAbs_id() %>">撤销</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<h1>请假</h1>
<form method="post" action="/staff/absence">
    时间：<input type="date" name="date"><br \>
    缘由：<input type="text" name="reason"><br \>
    <input type="submit" value="提交">
</form>

</body>

<%@ include file="footer.jsp"%>
<script>
    $(function () {
        document.title = "我的假条";
    });
</script>
</html>