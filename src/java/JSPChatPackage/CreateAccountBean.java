package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccountBean {
    private String username;
    private String password;
    private String name;
    private String DOB;
    private String gender;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean userExists(String username){
        
    //open a DB connection
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            Statement statement = connection.createStatement();
            ResultSet users = statement.executeQuery("SELECT * FROM user");

            //check if the username exists
            while (users.next()){
                if (users.getString("username").equals(username)){
                    //we found the username in the db
                    users.close();
                    return true;
                }
            }
            users.close();
        } catch (SQLException e) {
                e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        //user name does not already exist
        return false;
    }
    
    public boolean addToDB(){
        
        //open a DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            //commit all values to the DB
            String query = "INSERT INTO user (username, password, name, DOB, gender) " +
                            "VALUES ('" + username + "', " +
                                    "'" + password + "', " +
                                    "'" + name + "', " +
                                    "'" + DOB + "', " +
                                    "'" + gender + "')";
            
            Statement statement = connection.createStatement();
            int rowCount = statement.executeUpdate(query);
            
            //success
            return true;
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        //something went wrong
        return false;
        
    }
    
    
}
