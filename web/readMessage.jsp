<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
        <title>Welcome</title>
    </head>
    
    <body>
        <div class="row">
            <div class="jumbotron">
                <h1>JSP Chat</h1>
                <p>A fun place to chat with virtual friends</p>
            </div>
        </div>
        
        <div class="row">
          <div class="col-md-1">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="welcome.jsp">Welcome</a></li>
                <li><a href="PopulateFriendsController">Friends</a></li>
                <li><a href="FindFriendsController">Find Friends</a></li>
                <li class="active"><a href="PopulateMessagesController">Inbox</a></li>
                <li><a href="NewMsgController">New Message</a></li>
                <li><a href="profileAuth.jsp">Profile</a></li>
                <li><a href="LogoutController">Logout</a></li>
            </ul>
          </div>
          
          <div class="col-md-8">  
                    <h3>Sender</h3>
                    <input type="text" name="sender" class="form-control" disabled="" value="${sessionMsgContent.getMsgSender()}"><br>
                    <h3>Subject</h3>
                    <input type="text" name="subject" class="form-control" disabled="" value="${sessionMsgContent.getMsgSubject()}"><br>
                    <h3>Message</h3>
                    <textarea name="message" rows="10" class="form-control" disabled="">${sessionMsgContent.getMsgContent()}</textarea>
              <a href="PopulateMessagesController">Back To Inbox</a>
          </div>  
            
        </div>
        
        
    </body>
</html>
