package com.prominent.jobportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogInDao {
    public class LoginDao {
        public static boolean validate(String username,String password){
            boolean status=false;
            try{
                Class.forName("org.postgresql.Driver");
                Connection con= DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

                PreparedStatement ps=con.prepareStatement(
                        "select * from users where username=? and password=?");
                ps.setString(1,username);
                ps.setString(2,password);

                ResultSet rs=ps.executeQuery();
                status=rs.next();

            }catch(Exception e){System.out.println(e);}
            return status;
        }
    }
}
