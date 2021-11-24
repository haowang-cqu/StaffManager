<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>
<h1>管理员登录</h1>

<form method="post" action="staff/login">
    ID: <input type="text" name="id"> <br />
    Name: <input type="text" name="name"> <br />
    Password: <input type="password" name="password"> <br />
    <input type="submit" value="登录">
</form>

</body>

<%@ include file="footer.jsp"%>
</html>

