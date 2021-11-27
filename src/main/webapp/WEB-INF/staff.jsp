<%@ page import="bean.Absence" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bean.Staff" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Staff staff = (Staff) request.getAttribute("staff");
    HashMap<Integer, String> departmentMap = (HashMap<Integer, String>) request.getAttribute("departmentMap");
    HashMap<String, String> genderMap = (HashMap<String, String>) request.getAttribute("genderMap");
    ArrayList<Absence> absences = (ArrayList<Absence>) request.getAttribute("absences");
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <title>员工主页</title>
</head>

<body class="container">
<h1 style="text-align: center; margin-bottom: 20px">欢迎您，<%= staff.getName() %>！<small><a href="/logout">退出</a></small></h1>
<div class="panel panel-primary">
    <div class="panel-heading">
        个人信息
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>工号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>入职日期</th>
                <th>部门</th>
                <th>职位</th>
                <th>工资</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%= staff.getId() %></td>
                <td><%= staff.getName() %></td>
                <td><%= genderMap.get(staff.getGender()) %></td>
                <td><%= staff.getAge() %></td>
                <td><%= staff.getDate() %></td>
                <td><%= departmentMap.get(staff.getDep_id()) %></td>
                <td><%= staff.getPos() %></td>
                <td><%= staff.getSalary() %>元</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="panel panel-primary">
    <div class="panel-heading">
        我的假条
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>编号</th>
                <th>日期</th>
                <th>缘由</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Absence absence : absences) {
            %>
            <tr>
                <td><%= absence.getAbs_id() %></td>
                <td><%= absence.getDate() %></td>
                <td><%= absence.getReason() %></td>
                <td><%= absence.getAgree() %></td>
                <td><a href="/am?do=withdraw&id=<%= absence.getAbs_id() %>" class="btn btn-danger btn-xs">撤销</a></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <div style="text-align:center;">
            <button type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#newAbsenceModal">请假</button>
        </div>
    </div>
</div>

<div class="panel panel-primary">
    <div class="panel-heading">
        修改密码
    </div>
    <div class="panel-body">
        <form method="post" action="/password" class="form-horizontal">
            <div class="form-group">
                <label for="oldPassword" class="col-sm-1 control-label">旧密码</label>
                <div class="col-sm-11">
                    <input name="old" type="password" class="input form-control" id="oldPassword">
                </div>
            </div>
            <div class="form-group">
                <label for="newPassword" class="col-sm-1 control-label">新密码</label>
                <div class="col-sm-11">
                    <input name="new" type="password" class="input form-control" id="newPassword">
                </div>
            </div>
            <div class="form-group">
                <label for="repeatPassword" class="col-sm-1 control-label">确认</label>
                <div class="col-sm-11">
                    <input type="password" class="input form-control" id="repeatPassword">
                </div>
            </div>
            <div style="text-align: center;">
                <button id="changePasswordSubmit" type="submit" class="btn btn-primary" disabled>提交</button>
            </div>
        </form>
    </div>
</div>

<div class="modal fade" id="newAbsenceModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">请假</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="/am" id="newAbsenceForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="reason" class="col-sm-2 control-label">缘由</label>
                        <div class="col-sm-10">
                            <input name="reason" type="text" class="form-control" id="reason">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="date" class="col-sm-2 control-label">时间</label>
                        <div class="col-sm-10">
                            <input name="date" type="date" class="form-control" id="date">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="window.newAbsence()">提交</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"
        integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
        integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
        crossorigin="anonymous"></script>
<script>
    $(".input").bind("input oninput", function () {
        let oldPassword = $("#oldPassword").val();
        let newPassword = $("#newPassword").val();
        let repeat = $("#repeatPassword").val();
        if (oldPassword.length > 0 && newPassword.length > 0 && newPassword === repeat) {
            $("#changePasswordSubmit").prop("disabled", false);
        } else {
            $("#changePasswordSubmit").prop("disabled", true);
        }
    });

    window.newAbsence = function () {
        $("#newAbsenceForm").submit();
    };
</script>
</body>

</html>