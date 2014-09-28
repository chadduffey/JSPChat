/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPChatPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewMessageBean {

    private String to;
    private String from;
    private String cc;
    private String bcc;
    private String subject;
    private String content;
    
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean updateMessageTable(){
        
        //open a DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            //commit all values to the DB
            String query = "INSERT INTO messages (senderid, to, cc, bcc, subject, message, time) " +
                            "VALUES ('" + "1" + "', " +
                                    "'" + to + "', " +
                                    "'" + cc + "', " +
                                    "'" + bcc + "', " +
                                    "'" + subject + "', " +
                                    "'" + content + "', " +
                                    "'" + "11:30" + "')";
            
            Statement statement = connection.createStatement();
            Integer rowCount = statement.executeUpdate(query);
            
            //success
            return true;
            
        } catch (SQLException e) {
                e.printStackTrace();
        }

        //something went wrong
        return false;
    }
    
    public boolean updateAllMessagesTable(){
        
        //open a DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/jspchat";
            String dbUsername = "root";
            String dbPassword = "Password123";
            Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

            //commit all values to the DB
            //***Dummy values for now ****
            String query = "INSERT INTO allmessages (messageid, recipient_id, type) " +
                            "VALUES ('" + "1" + "', " +
                                    "'" + "2" + "', " +
                                    "'" + "to" + "')";
            
            Statement statement = connection.createStatement();
            Integer rowCount = statement.executeUpdate(query);
            
            //success
            return true;
            
        } catch (SQLException e) {
                e.printStackTrace();
        }

        //something went wrong
        return false;
    }
    
    
}
