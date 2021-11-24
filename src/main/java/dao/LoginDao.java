package dao;

import utils.Driver;
import utils.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    public static boolean doLogin(int id, String password, boolean admin){
        if (password ==null) return false;
        String sql=null;
        if(admin){
            sql="select pwd from administrator where id="+id;
        }else{
            sql="select pwd from staff where id="+id;
        }
        Statement stmt = Driver.getInstance().getStatement();
        String pwd = null;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                pwd = rs.getString("pwd");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        if (password.equals(pwd)){
            Log.write("密码正确");
            return true;
        }
        return false;
    }
}
