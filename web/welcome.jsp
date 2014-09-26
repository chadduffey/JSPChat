<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
        <link href="css/welcome.css" rel="stylesheet" />
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
                <li class="active"><a href="welcome.jsp">Welcome</a></li>
                <li><a href="friends.jsp">Friends</a></li>
                <li><a href="findfriends.jsp">Find Friends</a></li>
                <li><a href="messages.jsp">Inbox</a></li>
                <li><a href="newmessage.jsp">New Message</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="LogoutController">Logout</a></li>
            </ul>
          </div>
          <div class="col-md-9">
              <h2>Welcome to JSP Chat</h2>
          </div>
        </div>
        
        
    </body>
</html>
