package dao;

import bean.Staff;
import utils.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class StaffDao {
    public static ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> staffArrayList=new ArrayList<>();
        Statement stmt = Driver.getInstance().getStatement();
        try {
            ResultSet rs = stmt.executeQuery("select * from staff");
            while (rs.next()){
                Staff staff=new Staff();
                staff.setName(rs.getString("name"));
                staff.setAge(rs.getInt("age"));
                staff.setGender(rs.getString("gender"));
                staff.setDate(rs.getDate("date"));
                staff.setSalary(rs.getInt("salary"));
                staff.setPos(rs.getString("pos"));
                staff.setPwd(rs.getString("pwd"));
                staff.setDep_id(rs.getInt("dep_id"));
                staffArrayList.add(staff);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffArrayList;
    }

    public static Staff getStaffById(int id) {
        Statement stmt = Driver.getInstance().getStatement();
        String sql = "select * from staff where id="+id;
        Staff staff=new Staff();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                staff.setName(rs.getString("name"));
                staff.setAge(rs.getInt("age"));
                staff.setGender(rs.getString("gender"));
                staff.setDate(rs.getDate("date"));
                staff.setSalary(rs.getInt("salary"));
                staff.setPos(rs.getString("pos"));
                staff.setPwd(rs.getString("pwd"));
                staff.setDep_id(rs.getInt("dep_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staff;
    }
    public static boolean updateStaff(Staff staff){
        Statement stmt = Driver.getInstance().getStatement();
        String sql = "";
        int id = staff.getId();
        String name = staff.getName();
        int age = staff.getAge();
        String gender = staff.getGender();
        Date date = staff.getDate();
        int salary = staff.getSalary();
        String pos = staff.getPos();
        String pwd = staff.getPwd();
        int dep_id = staff.getDep_id();
        sql = String.format("update staff set name=%s,age=%d,gender=%s,data=%tx,salary=%d,pos=%s,pwd=%s,dep_id=%d where id=%d",
                name,age,gender,date,salary,pos,pwd,dep_id,id);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public static boolean insertStaff(Staff staff){
        Statement stmt = Driver.getInstance().getStatement();
        String sql = "";
        int id = staff.getId();
        String name = staff.getName();
        int age = staff.getAge();
        String gender = staff.getGender();
        Date date = staff.getDate();
        int salary = staff.getSalary();
        String pos = staff.getPos();
        String pwd = staff.getPwd();
        int dep_id = staff.getDep_id();
        sql = String.format("insert into staff (id,gender,name,age,date,salary,pos,pwd,dep_id) values (%d,%s,%s,%d,%tx,%d,%s,%s,%d)",
                id,gender,name,age,date,salary,pos,pwd,dep_id);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public static boolean deleteStaff(int id){
        Statement stmt = Driver.getInstance().getStatement();
        String sql="delete from staff where id="+id;
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
