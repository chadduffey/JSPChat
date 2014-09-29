package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessagesBean {
    
    private Integer[] AllMsgIds;
    private String[] AllMsgSubjects;

    public Integer[] getAllMsgIds() {
        return AllMsgIds;
    }

    public String[] getAllMsgSubjects() {
        return AllMsgSubjects;
    }

    public Integer[] getMessageIds(Integer userId){

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

            Integer newmsg_id;

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

        AllMsgIds = idsArray;
        
        return idsArray;
    }
    
    public String getMessageSubject(Integer msgId){

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

        AllMsgSubjects = messagesArray;
        
        return messagesArray;  
    }
    
    public Integer getMessageSenderId(Integer msgId){

        Integer senderId = null;
        
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
            senderId = mymessages.getInt("sender_id"); 
            mymessages.close();
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return senderId;   
    }
    
    public String getMessageSenderName(Integer msgId){

        Integer senderId = null;
        
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
            senderId = mymessages.getInt("sender_id"); 
                        
            //get the name
            String preparedSQL2 = "SELECT * FROM user WHERE user_id = ?";
            PreparedStatement ps2 = connection.prepareStatement(preparedSQL2);
            ps2.setInt(1, senderId);
            ResultSet mysender = ps2.executeQuery();
            mysender.next();
            String senderName = mysender.getString("name"); 
            
            mymessages.close();
            mysender.close();
            
            return senderName;
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "Not Found";   
    }
}
