package dao;

import bean.Absence;
import bean.Department;
import utils.Driver;
import utils.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class AbsenceDao {
    public static ArrayList<Absence> getallAbsence(){
        ArrayList<Absence> absences=new ArrayList<>();
        Statement stmt = Driver.getInstance().getStatement();
        try {
            ResultSet rs = stmt.executeQuery("select * from absence");
            while(rs.next()){
                Absence absence = new Absence();
                absence.setAbs_id(rs.getInt("abs_id"));
                absence.setDate(rs.getDate("date"));
                absence.setReason(rs.getString("reason"));
                absence.setUser_id(rs.getInt("user_id"));
                absence.setAgree(rs.getString("agree"));
                absences.add(absence);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return absences;
    }

    public static Absence getAbsenceById(int id){
        Statement stmt = Driver.getInstance().getStatement();
        Absence absence = new Absence();
        try {
            ResultSet rs = stmt.executeQuery("select * from absence where abs_id="+id);
            if(rs==null){
                Log.write("没有该假条");
            }
            while(rs.next()){
                absence.setAbs_id(rs.getInt("abs_id"));
                absence.setUser_id(rs.getInt("user_id"));
                absence.setReason(rs.getString("reason"));
                absence.setDate(rs.getDate("date"));
                absence.setAgree(rs.getString("agree"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return absence;
    }

    public static boolean insertAbsence(Absence absence){
        Statement stmt = Driver.getInstance().getStatement();
        int abs_id = absence.getAbs_id();
        int user_id = absence.getUser_id();
        String reason = absence.getReason();
        Date date = absence.getDate();
        String agree= absence.getAgree();
        try {
            int flag = stmt.executeUpdate(String.format("insert into absence (abs_id,date,reason,user_id,agree) values (%d,%tx,%s,%d,%s)"
                    ,abs_id,date,reason,user_id,agree));
            if(flag==0){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean updateAbsence(Absence absence){
        Statement stmt = Driver.getInstance().getStatement();
        int abs_id = absence.getAbs_id();
        int user_id = absence.getUser_id();
        String reason = absence.getReason();
        Date date = absence.getDate();
        String agree= absence.getAgree();
        try {
            int flag = stmt.executeUpdate(String.format("update absence set abs_id=%d,date=%tx,reason=%s,user_id=%d,agree=%s"
                    ,abs_id,date,reason,user_id,agree));
            if(flag==0){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean deleteAbsence(int id){
        Statement stmt = Driver.getInstance().getStatement();
        try {
            int flag = stmt.executeUpdate("delete from absence where abs_id="+id);
            if(flag==0){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Absence> getAllAbusenceByuid(int user_id){
        ArrayList<Absence> absences=new ArrayList<>();
        Statement stmt = Driver.getInstance().getStatement();
        try {
            ResultSet rs = stmt.executeQuery("select * from absence where user_id="+user_id);
            while (rs.next()){
                Absence absence = new Absence();
                absence.setAbs_id(rs.getInt("abs_id"));
                absence.setDate(rs.getDate("date"));
                absence.setReason(rs.getString("reason"));
                absence.setUser_id(rs.getInt("user_id"));
                absence.setAgree(rs.getString("agree"));
                absences.add(absence);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return absences;
    }
}
