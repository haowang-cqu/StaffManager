package dao;

import bean.Administrator;
import bean.Staff;
import utils.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class AdministratorDao {
    public static Administrator getAdministartorById(int id){
        Statement stmt = Driver.getInstance().getStatement();
        Administrator administrator = new Administrator();
        try {
            ResultSet rs = stmt.executeQuery("select * from administrator where id="+id);
            while(rs.next()){
                administrator.setId(rs.getInt("id"));
                administrator.setName(rs.getString("name"));
                administrator.setPwd(rs.getString("pwd"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return administrator;
    }

    public static boolean updateAdministrator(Administrator administrator){
        Statement stmt = Driver.getInstance().getStatement();
        String sql = "";
        int id = administrator.getId();
        String name = administrator.getName();
        String pwd = administrator.getPwd();
        sql = String.format("update administrator set name=\"%s\",pwd=\"%s\" where id=%d",
                name,pwd,id);
        try {
            int flag = stmt.executeUpdate(sql);
            if(flag==0){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
