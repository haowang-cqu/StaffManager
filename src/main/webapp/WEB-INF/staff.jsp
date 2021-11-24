<%@ page import="bean.Staff" %>
<%@ page import="bean.Department" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>
<%
    boolean isNew = (boolean) request.getAttribute("new");
    Staff staff = null;
    if (!isNew) staff = (Staff) request.getAttribute("staff");
    ArrayList<Department> departments = (ArrayList<Department>) request.getAttribute("departments");
%>

<% if (isNew) { %>
<h1>添加员工</h1>
<% } else { %>
<h1>更新员工信息</h1>
<% } %>

<% if ( isNew ) {%>
<form method="post" action="/staffs">
    名字：<input type="text" name="name"><br />
    年龄：<input type="number" name="age"><br />
    性别：
        <input type="radio" name="gender" value="M">男
        <input type="radio" name="gender" value="W">女 <br />
    入职日期：<input type="date" name="date"><br />
    部门：<select name="dep_id">
            <%
                for (Department department : departments) {
            %>
                <option value="<%= department.getDep_id()%>"><%= department.getDep_name() %></option>
            <% } %>
        </select><br />
    职位：<input type="text" name="pos"><br>
    工资：<input type="number" name="salary"><br />
    <input type="submit" value="提交">
</form>
<% } else { %>
<form method="post" action="/staffs">
    名字：<input type="text" name="name" value="<%= staff.getName() %>"><br />
    年龄：<input type="number" name="age" value="<%= staff.getAge() %>"><br />
    性别：
    <% if ("M".equals(staff.getGender())) {%>
        <input type="radio" name="gender" value="M" checked>男
        <input type="radio" name="gender" value="W">女 <br />
    <% } else { %>
        <input type="radio" name="gender" value="M">男
        <input type="radio" name="gender" value="W" checked>女 <br />
    <% } %>
    入职日期：<input type="date" name="date" value="<%= staff.getDate() %>"><br />
    部门：<select name="dep_id">
    <%
        for (Department department : departments) {
            if (department.getDep_id() == staff.getDep_id()) {
    %>
        <option value="<%= department.getDep_id()%>" selected><%= department.getDep_name() %></option>
        <%  } else { %>
        <option value="<%= department.getDep_id()%>"><%= department.getDep_name() %></option>
        <%  } %>
    <% } %>
</select><br />
    职位：<input type="text" name="pos" value="<%= staff.getPos() %>"><br>
    工资：<input type="number" name="salary" value="<%= staff.getSalary() %>"><br />
    <input type="hidden" name="id" value="<%= staff.getId() %>">
    <input type="submit" value="提交">
</form>
<% } %>

</body>

<%@ include file="footer.jsp"%>
<script>
    $(function () {
        document.title = "员工信息";
    });
</script>
</html>