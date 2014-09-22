package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetMessagesBean {
    
    public Integer[] getMessageIds(int userId){

        ArrayList<Integer> ids = new ArrayList<Integer>(1);
        
        
        //open a DB connection
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String preparedSQL = "SELECT * FROM messages WHERE recipient_id = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, userId);

            ResultSet mymessages = ps.executeQuery();

            int newmsg_id;

            //scroll through the messages for the user id
            while (mymessages.next()){
                //get the message id
                newmsg_id = mymessages.getInt("msg_id");
                ids.add(newmsg_id); 
            }
            mymessages.close();
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //convert the array list to an array to return it
        Integer [] idsArray = ids.toArray(new Integer[ids.size()]);

        return idsArray;
    }
    
    public String getMessageSubject(int msgId){

        String theSubject = null;
        
        //open a DB connection
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String preparedSQL = "SELECT * FROM msg_content WHERE msg_id = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, msgId);
            ResultSet mymessages = ps.executeQuery();
            mymessages.next();
            theSubject = mymessages.getString("subject"); 
            mymessages.close();
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (theSubject == null){
            theSubject = "not found " + msgId + " *";
        }
        
        return theSubject;
        //return "not found " + msgId + " *";    
    }
    
    public String[] getAllMessageSubjects(Integer[] ids){
        ArrayList<String> allSubjects = new ArrayList<String>(1);
        
        for (Integer id : ids) {
            allSubjects.add(getMessageSubject(id));   
        }
        
        //convert the array list to an array to return it
        String [] messagesArray = allSubjects.toArray(new String[allSubjects.size()]);

        return messagesArray;  
    }
    
}
