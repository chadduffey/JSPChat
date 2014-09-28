
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
    private Integer[] AllNonFriendIds;
    private String[] AllNonFriendNames;

    public Integer[] getAllNonFriendIds() {
        return AllNonFriendIds;
    }

    public String[] getAllNonFriendNames() {
        return AllNonFriendNames;
    }
    
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
    
    public void addFriendLink(Integer newFriend, Integer userId){
        //open a DB connection
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String query = "INSERT INTO friend_links (friender, friendee) " +
                            "VALUES ('" + userId + "', " + 
                            "'" + newFriend + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
  
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }      
    }
    
     public Integer[] getNonFriendIds(Integer userId){

            ArrayList<Integer> ids = new ArrayList<Integer>(1);

            //open a DB connection
            try {
                //load driver
                Class.forName("com.mysql.jdbc.Driver");

                String dbURL = "jdbc:mysql://localhost:3306/jspchat";
                String dbUsername = "root";
                String dbPassword = "Password123";
                Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                
                String preparedSQL = "SELECT user_id FROM user WHERE user_id != ?";
                PreparedStatement ps = connection.prepareStatement(preparedSQL);
                ps.setInt(1, userId);
                
                ResultSet everyoneButMe = ps.executeQuery();
                
                while (everyoneButMe.next()){
                    //add the ID to the list
                    ids.add(everyoneButMe.getInt("user_id"));
                }
                //this should leave us with a list of id's that arnt ours.
                
            } catch (SQLException e) {
                    e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
                
            //next - exclude those on our friends list.
            //AllFriendIds has the friends in it
                
            //convert the array list to an array to return it
            Integer [] idsArray = ids.toArray(new Integer[ids.size()]);

            //set up a structure to hold people that arnt friends yet
            ArrayList<Integer> notFriendsYet = new ArrayList<Integer>(1);
            
            Integer idsvalue;
            Integer friendsvalue;
            Boolean found = false;
            
            //compare the two arrays - friends, and everyone
            for (int i = 0; i < idsArray.length; i++){
                idsvalue = idsArray[i];
                for (int j = 0; j < AllFriendIds.length; j++){
                    friendsvalue = AllFriendIds[j];
                    if (idsvalue == friendsvalue){
                        found = true;
                    }
                }
                if (!found){
                    notFriendsYet.add(idsvalue);
                }
                found = false;
            }

            //we should now have a list of just the people we arnt friends
            //with yet
            Integer [] notYetFriendsArray = notFriendsYet.toArray(new Integer[notFriendsYet.size()]);
            
            AllNonFriendIds = notYetFriendsArray;
            return notYetFriendsArray;
    }
}
