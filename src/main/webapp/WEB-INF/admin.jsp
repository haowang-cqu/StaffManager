<%@ page import="bean.Staff" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.StaffDao" %>
<%@ page import="bean.Department" %>
<%@ page import="dao.DepartmentDao" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bean.Absence" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Staff> staffs = (ArrayList<Staff>) request.getAttribute("staffs");
    HashMap<Integer, String> departmentMap = (HashMap<Integer, String>) request.getAttribute("departmentMap");
    HashMap<String, String> genderMap = (HashMap<String, String>) request.getAttribute("genderMap");
    ArrayList<Absence> absences = (ArrayList<Absence>) request.getAttribute("absences");
    HashMap<Integer, String> staffMap = (HashMap<Integer, String>) request.getAttribute("staffMap");
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <title>管理员主页</title>
</head>

<body class="container">
<h1 style="text-align: center; margin-bottom: 20px">欢迎您，<%= session.getAttribute("name")%>（管理员）！<small><a href="/logout">退出</a></small></h1>
<div class="panel panel-primary">
    <div class="panel-heading">
        员工管理
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
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Staff staff : staffs) {
            %>
            <tr>
                <td><%= staff.getId() %></td>
                <td><%= staff.getName() %></td>
                <td><%= genderMap.get(staff.getGender()) %></td>
                <td><%= staff.getAge() %></td>
                <td><%= staff.getDate() %></td>
                <td><%= departmentMap.get(staff.getDep_id()) %></td>
                <td><%= staff.getPos() %></td>
                <td><%= staff.getSalary() %>元</td>
                <td>
                    <button type="button" onclick="window.updateStaffInfo(this)" class="btn btn-primary btn-xs">修改</button>
                    <a href="/sm?do=delete&id=<%= staff.getId() %>" class="btn btn-danger btn-xs">删除</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <div style="text-align:center;">
            <button type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#newStaffModal">添加新员工</button>
        </div>
    </div>
</div>

<div class="panel panel-primary">
    <div class="panel-heading">
        假条管理
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>编号</th>
                <th>员工</th>
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
                <td><%= staffMap.get(absence.getUser_id()) %></td>
                <td><%= absence.getDate() %></td>
                <td><%= absence.getReason() %></td>
                <td><%= absence.getAgree() %></td>
                <td>
                    <a href="/am?do=approve&id=<%= absence.getAbs_id() %>" class="btn btn-success btn-xs">批准</a>
                    <a href="/am?do=reject&id=<%= absence.getAbs_id() %>" class="btn btn-danger btn-xs">驳回</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
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

<div class="modal fade" id="newStaffModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="newStaffModalLabel">添加员工</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="/sm" id="newStaffForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="staffName" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input name="name" type="text" class="form-control" id="staffName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="staffGender" class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <select name="gender" id="staffGender" class="form-control">
                                <option value="M">男</option>
                                <option value="W">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="staffAge" class="col-sm-2 control-label">年龄</label>
                        <div class="col-sm-10">
                            <input name="age" type="number" class="form-control" id="staffAge">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="staffDate" class="col-sm-2 control-label">入职时间</label>
                        <div class="col-sm-10">
                            <input name="date" type="date" class="form-control" id="staffDate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="staffDept" class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-10">
                            <select name="dep_id" id="staffDept" class="form-control">
                                <%
                                    for (Map.Entry<Integer, String> entry : departmentMap.entrySet()) {
                                %>
                                <option value="<%= entry.getKey() %>"><%= entry.getValue() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="staffPos" class="col-sm-2 control-label">职位</label>
                        <div class="col-sm-10">
                            <input name="pos" type="text" class="form-control" id="staffPos">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="staffSalary" class="col-sm-2 control-label">工资</label>
                        <div class="col-sm-10">
                            <input name="salary" type="number" class="form-control" id="staffSalary">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="window.newStaff()">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="updateStaffModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="updateStaffModalLabel">修改员工信息</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="/sm" id="updateStaffForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="updateStaffName" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input name="name" type="text" class="form-control" id="updateStaffName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateStaffGender" class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <select name="gender" id="updateStaffGender" class="form-control">
                                <option value="M">男</option>
                                <option value="W">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateStaffAge" class="col-sm-2 control-label">年龄</label>
                        <div class="col-sm-10">
                            <input name="age" type="number" class="form-control" id="updateStaffAge">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateStaffDate" class="col-sm-2 control-label">入职时间</label>
                        <div class="col-sm-10">
                            <input name="date" type="date" class="form-control" id="updateStaffDate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateStaffDept" class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-10">
                            <select name="dep_id" id="updateStaffDept" class="form-control">
                                <%
                                    for (Map.Entry<Integer, String> entry : departmentMap.entrySet()) {
                                %>
                                <option class="deptOptions" value="<%= entry.getKey() %>"><%= entry.getValue() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateStaffPos" class="col-sm-2 control-label">职位</label>
                        <div class="col-sm-10">
                            <input name="pos" type="text" class="form-control" id="updateStaffPos">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateStaffSalary" class="col-sm-2 control-label">工资</label>
                        <div class="col-sm-10">
                            <input name="salary" type="number" class="form-control" id="updateStaffSalary">
                            <input type="hidden" name="id" id="updateStaffId">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="window.updateStaff()">提交</button>
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
    $(function () {
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

        window.newStaff = function () {
            $("#newStaffForm").submit();
        }

        window.updateStaff = function () {
            $("#updateStaffForm").submit();
        }

        window.updateStaffInfo = function (btn) {
            // 获取待修改员工的所有属性
            const staff = $(btn).parents("tr").children("td");
            const id = parseInt($(staff[0]).html());
            const name = $(staff[1]).html();
            const gender = $(staff[2]).html();
            const age = parseInt($(staff[3]).html());
            const date = $(staff[4]).html();
            const deptName = $(staff[5]).html();
            const pos = $(staff[6]).html();
            const salary = parseInt($(staff[7]).html());
            // 修改表单
            $("#updateStaffId").val(id);
            $("#updateStaffName").val(name);
            if (gender === "男") {
                $("#updateStaffGender").val("M");
            }
            else {
                $("#updateStaffGender").val("W");
            }
            $("#updateStaffAge").val(age);
            $("#updateStaffDate").val(date);
            const deptOptions = $(".deptOptions");
            for (let i = 0; i < deptOptions.length; i++) {
                if ($(deptOptions[i]).text() === deptName) {
                    $(deptOptions[i]).attr("selected", true);
                    break;
                }
            }
            $("#updateStaffPos").val(pos);
            $("#updateStaffSalary").val(salary);
            $("#updateStaffModal").modal('show');
        };
    });
</script>
</body>

</html>