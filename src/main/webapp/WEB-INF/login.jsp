<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>

<%
    boolean admin = (boolean) request.getAttribute("admin");
    if (admin) {
        out.write("<h1>管理员登录</h1>");
    } else {
        out.write("<h1>员工登录</h1>");
    }
%>

<form method="post" action="/login">
    ID: <input type="text" name="id"> <br />
    Name: <input type="text" name="name"> <br />
    Password: <input type="password" name="password"> <br />
    <%
        if (admin) {
            out.write("<input type='hidden' name='admin' value='1'>");
        }
    %>
    <input type="submit" value="登录">
</form>

</body>

<%@ include file="footer.jsp"%>
<script>
    $(function () {
        document.title = "登录";
    });
</script>
</html>