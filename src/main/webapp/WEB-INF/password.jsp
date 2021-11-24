<%@ page import="bean.Administrator" %>
<%@ page import="bean.Staff" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp"%>

<body>
<h1>修改密码</h1>

<form method="post" action="/password">
    原密码：<input id="old" class="input" type="password" name="old"><br />
    新密码：<input id="new" class="input" type="password" name="new"><br />
    重复密码：<input id="repeat" class="input" type="password"><br />
    <input id="submit" type="submit" value="提交" disabled>
</form>

</body>
<%@ include file="footer.jsp"%>
<script>
    $(function () {
        document.title = "修改密码";

        if (window.location.href.search("error") !== -1) {
            alert("密码错误");
        }

        $(".input").bind("input oninput", function () {
            console.log(111)
           let oldPassword = $("#old").val();
           let newPassword = $("#new").val();
           let repeat = $("#repeat").val();
           if (oldPassword.length > 0 && newPassword.length > 0 && newPassword === repeat) {
               $("#submit").prop("disabled", false);
           } else {
               $("#submit").prop("disabled", true);
           }
        });
    });
</script>
</html>