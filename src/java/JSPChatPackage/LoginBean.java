package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginBean {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean checkLogin(String uname, String pwd){
        
        //open a DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery("SELECT * FROM user");

            //check if the username exists
            while (users.next()){
                if (users.getString("username").equals(uname)){
                    //we found the user in the db
                    if (users.getString("password").equals(pwd)){
                        //the password matches as well
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        //we didnt find the username and password combo.
        return false;
    }
}
