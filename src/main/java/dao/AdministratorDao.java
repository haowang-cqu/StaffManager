package dao;

import bean.Administrator;
import utils.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
