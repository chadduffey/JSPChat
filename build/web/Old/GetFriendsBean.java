package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetFriendsBean {

    private Integer[] myFriendIds;
    private Integer[] nonFriendIds;
    private String[] allMyFriends;
    private String[] allMyNonFriends;

    public Integer[] getMyFriendIds() {
        return myFriendIds;
    }

    public Integer[] getNonFriendIds() {
        return nonFriendIds;
    }

    public String[] getAllMyFriends() {
        return allMyFriends;
    }

    public String[] getAllMyNonFriends() {
        return allMyNonFriends;
    }
    
    public Integer[] getFriendIds(Integer userId){

            ArrayList<Integer> ids = new ArrayList<Integer>(1);


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
                ps.setInt(1, userId);

                ResultSet myfriends = ps.executeQuery();

                Integer newfriend_id;

                //scroll through the friends for the user id
                while (myfriends.next()){
                    //get the message id
                    newfriend_id = myfriends.getInt("friendee");
                    ids.add(newfriend_id); 
                }
                myfriends.close();

            } catch (SQLException e) {
                    e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //convert the array list to an array to return it
            Integer [] idsArray = ids.toArray(new Integer[ids.size()]);

            return idsArray;
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

                String preparedSQL = "SELECT * FROM friend_links WHERE friender != ?";
                PreparedStatement ps = connection.prepareStatement(preparedSQL);
                ps.setInt(1, userId);

                ResultSet myfriends = ps.executeQuery();

                Integer newfriend_id;

                //scroll through the friends for the user id
                while (myfriends.next()){
                    //get the message id
                    newfriend_id = myfriends.getInt("friendee");
                    ids.add(newfriend_id); 
                }
                myfriends.close();

            } catch (SQLException e) {
                    e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //convert the array list to an array to return it
            Integer [] idsArray = ids.toArray(new Integer[ids.size()]);

            return idsArray;
    }
    
    public String getFriendName(Integer friendId){

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
        //return "not found " + msgId + " *";    
    }
    
    public String[] getAllFriends(Integer[] ids){
        ArrayList<String> allFriends = new ArrayList<String>(1);
        
        for (Integer id : ids) {
            allFriends.add(getFriendName(id));   
        }
        
        //convert the array list to an array to return it
        String [] friendArray = allFriends.toArray(new String[allFriends.size()]);

        return friendArray;  
    }
    
    public String[] getAllNonFriends(Integer[] ids){
        ArrayList<String> allNonFriends = new ArrayList<String>(1);
        
        for (Integer id : ids) {
            allNonFriends.add(getFriendName(id));   
        }
        
        //convert the array list to an array to return it
        String [] nonFriendArray = allNonFriends.toArray(new String[allNonFriends.size()]);

        return nonFriendArray;  
    } 
}
