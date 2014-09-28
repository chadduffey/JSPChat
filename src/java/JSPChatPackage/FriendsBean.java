
package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendsBean {

    private Integer[] AllFriendIds;
    private String[] AllFriendNames;
    
    public Integer[] getAllFriendIds() {
        return AllFriendIds;
    }

    public void setAllFriendIds(Integer[] AllFriendIds) {
        this.AllFriendIds = AllFriendIds;
    }

    public String[] getAllFriendNames() {
        
        ArrayList<String> allFriends = new ArrayList<String>(1);
        
        for (Integer id : AllFriendIds) {
            allFriends.add(getFriendName(id));   
        }
        
        //convert the array list to an array to return it
        String [] friendArray = allFriends.toArray(new String[allFriends.size()]);

        AllFriendNames = friendArray; 
        
        return AllFriendNames;
    }

    public String getFriendName(Integer friendId){
        
        //get a single friends name
        
        String name = null;
        
        //open a DB connection
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String preparedSQL = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, friendId);
            ResultSet myFriends = ps.executeQuery();
            myFriends.next();
            name = myFriends.getString("name"); 
            myFriends.close();
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (name == null){
            name = "not found " + friendId + " *";
        }
        
        return name; 
    }
    
    public void removeFriendLink(Integer userid, Integer friendToUnlink){
        //remove the linke for this friend in the database
        
    //open a DB connection
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String preparedSQL = "SELECT * FROM friend_links WHERE friender = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, userid);
            ResultSet myFriends = ps.executeQuery();
           
            Integer rowToDelete = 0;
            
            while (myFriends.next()){
                if (myFriends.getInt("friendee") == friendToUnlink){
                    //this is the row we need to delete.
                    rowToDelete = myFriends.getInt("id");
                } 
            }
            
            //close the dataset
            myFriends.close();
            
            //delete the row
            String deleteQuery = "DELETE FROM friend_links WHERE id='" + rowToDelete + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteQuery);
            
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    
        
    }
}
