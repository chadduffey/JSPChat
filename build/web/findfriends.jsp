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
                <li><a href="friends.jsp">Friends</a></li>
                <li class="active"><a href="findfriends.jsp">Find Friends</a></li>
                <li><a href="messages.jsp">Inbox</a></li>
                <li><a href="newmessage.jsp">New Message</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="LogoutController">Logout</a></li>
            </ul>
          </div>
          <div class="col-md-4">  
            <table class="table table-striped">
                <tr>
                    <th>Name</th>
                    <th>New Friend?</th>
                </tr>
                <c:forEach var="item" items="${sessionNonFriends}">
                    <tr>                  
                        <td><span class="glyphicon glyphicon-globe"></span> ${item}</td>
                        <td><span class="glyphicon glyphicon-thumbs-up"></span></td>
                    </tr>                   
                </c:forEach>
            </table>
          </div>
            
        </div>
        
        
    </body>
</html>