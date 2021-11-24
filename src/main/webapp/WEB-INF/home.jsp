<%@ page import="bean.Administrator" %>
<%@ page import="bean.Staff" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>

<%
    boolean isAdmin = (boolean) session.getAttribute("admin");
    Administrator admin = null;
    Staff staff = null;
    String name = null;
    if (isAdmin) {
        admin = (Administrator) request.getAttribute("admin");
        name = admin.getName();
    } else {
        staff = (Staff) request.getAttribute("staff");
        name = staff.getName();
    }
%>

<h1>欢迎您，<%= name %>！</h1>

<% if(isAdmin)  { %>
    <a href="#">假条管理</a>
    <a href="/staffs">员工管理</a>
    <a href="/logout">退出登录</a>
<% } else { %>
    <a href="#">我的假条</a>
    <a href="/logout">退出登录</a>
<% } %>

<%@ include file="footer.jsp"%>
<script>
    $(function () {
        document.title = "主页";
    });
</script>
</html>