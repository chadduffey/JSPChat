package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MessagesBean {
    
    private Integer[] AllMsgIds;
    private String[] AllMsgSubjects;
    
    private String msgSubject;
    private String msgContent;
    private String msgSender;

    public String getMsgSubject() {
        return msgSubject;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public String getMsgSender() {
        return msgSender;
    }

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
    
    public String getMessageContent(Integer msgId){

        String theContent = null;
        
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
            theContent = mymessages.getString("body"); 
            mymessages.close();
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (theContent == null){
            theContent = "not found " + msgId + " *";
        }
        
        return theContent;
        //return "not found " + msgId + " *";    
    }
    
    public void setIndividualMessageContent(Integer id){
        msgSubject = getMessageSubject(id);
        msgSender = getMessageSenderName(id);
        msgContent = getMessageContent(id);
        
    }
    
    public void writeMessageBody(Integer userId, String messageSubject, String messageBody){
        //open a DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            //commit all values to the DB
            String query = "INSERT INTO msg_content (subject, body, sender_id, time) " +
                            "VALUES ('" + messageSubject + "', " +
                                    "'" + messageBody + "', " +
                                    "'" + userId + "', " +
                                    "'" + "11:30" + "')";
            
            Statement statement = connection.createStatement();
            Integer rowCount = statement.executeUpdate(query);
            statement.close();
            
   
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public int returnMessageId(){
        
        Integer msgId = 0;
        //open a DB connection    
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            Statement statement = connection.createStatement();
            ResultSet messages = statement.executeQuery("SELECT * FROM msg_content");

            //check for last message
            while (messages.next()){
                msgId = messages.getInt("msg_id");
            }
            messages.close();
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        //we return zero if this fails, which will show up in the DB
        return msgId;
    }
    
    public void writeMessageLinks(Integer msgId, Integer rcptId){
        //open a DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            //commit all values to the DB
            String query = "INSERT INTO messages (msg_id, recipient_id) " +
                            "VALUES ('" + msgId + "', " +
                                    "'" + rcptId + "')";
            
            Statement statement = connection.createStatement();
            Integer rowCount = statement.executeUpdate(query);
            statement.close();
            
   
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public boolean isRead(Integer id, Integer User){
        
        //open a DB connection
        try {
            //load driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String preparedSQL = "SELECT * FROM messages WHERE msg_id = ? AND recipient_id = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, id);
            ps.setInt(2, User);
            ResultSet mymessages = ps.executeQuery();
            mymessages.next();
            Integer value = mymessages.getInt("is_read"); 
            mymessages.close();
            
            if (value == 1){
                return true;
            }
            
            
        } catch (SQLException e) {
                e.printStackTrace();
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        //if we cant prove its read, assume it is not
        return false;
    }
    
    public void markMessageRead(Integer msgId, Integer user){
        //open a DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            String preparedSQL = "SELECT * FROM messages WHERE msg_id = ? AND recipient_id = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setInt(1, msgId);
            ps.setInt(2, user);
            ResultSet mymessages = ps.executeQuery();
            mymessages.next();
            Integer value = mymessages.getInt("id"); 
            mymessages.close();
            
            

            //commit all values to the DB
            String query = "UPDATE messages SET " +
                    "is_read = '" + 1 + 
                    "' WHERE id = '" + value + "'";

            Statement statement = connection.createStatement();
            Integer rowCount = statement.executeUpdate(query);
            
            statement.close();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }       
    }
}
