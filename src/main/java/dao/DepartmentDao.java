package dao;

import bean.Department;
import bean.Staff;
import utils.Driver;
import utils.Log;

import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartmentDao {
    public static ArrayList<Department> getAllDepartmentDao(){
        ArrayList<Department> departments=new ArrayList<>();
        Statement stmt = Driver.getInstance().getStatement();
        try {
            ResultSet rs = stmt.executeQuery("select * from department");
            while (rs.next()){
                Department department = new Department();
                department.setDep_id(rs.getInt("dep_id"));
                department.setDep_name(rs.getString("dep_name"));
                departments.add(department);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return departments;
    }

    public static Department getDepartmentById(int id){
        Statement stmt = Driver.getInstance().getStatement();
        Department department = new Department();
        try {
            ResultSet rs = stmt.executeQuery("select * from department where dep_id="+id);
            if(rs==null){
                Log.write("没有该部门");
            }
            while(rs.next()){
                department.setDep_id(id);
                department.setDep_name(rs.getString("dep_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }

    public static boolean updateDepartment(Department department){
        Statement stmt = Driver.getInstance().getStatement();
        String dep_name = department.getDep_name();
        int dep_id = department.getDep_id();
        try {
            int flag = stmt.executeUpdate(String.format("update department set dep_id=%d,dep_name=%s",dep_id,dep_name));
            if(flag==0){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean insertDepartment(Department department){
        Statement stmt = Driver.getInstance().getStatement();
        String dep_name = department.getDep_name();
        int dep_id = department.getDep_id();
        try {
            int flag = stmt.executeUpdate(String.format("insert into department (dep_id,dept_name) values (%d,%s)",dep_id,dep_name));
            if(flag==0){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean deleteDepartment(int id){
        Statement stmt = Driver.getInstance().getStatement();
        String sql="delete from department where id="+id;
        try {
            int flag = stmt.executeUpdate(sql);
            if(flag==0){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
