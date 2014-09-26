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
                <li><a href="findfriends.jsp">Find Friends</a></li>
                <li><a href="messages.jsp">Inbox</a></li>
                <li><a href="newmessage.jsp">New Message</a></li>
                <li class="active"><a href="profile.jsp">Profile</a></li>
                <li><a href="LogoutController">Logout</a></li>
            </ul>
          </div>

            <div class="col-md-6">
                <h1>Editing ${sessionFullName}</h1>
                <form action="EditUserController" method="post">
                        <p>Login Name:       <input type="text" name="username"class="form-control"/></p>
                        <p>Password:         <input type="password" name="password"class="form-control"/></p>
                        <p>Your Full Name:   <input type="text" name="name"class="form-control"/></p>

                        <p><label>Your Gender</label></p>
                        <input type="radio" id="Male" value="Male" name="gender" checked>
                        <label for="Male" class="light">Male</label><br/>
                        <input type="radio" id="Female" value="Female" name="gender">
                        <label for="Female" class="light">Female</label>

                        <p><label>Date of Birth:</label></p>
                        <input class="bday" type="text" name="bDay" value="Date"/>
                        <select class="bday" id="bMonth" name="bMonth">
                            <option value="01">January</option>
                            <option value="02">February</option>
                            <option value="03">March</option>
                            <option value="04">April</option>
                            <option value="05">May</option>
                            <option value="06">June</option>
                            <option value="07">July</option>
                            <option value="08">August</option>
                            <option value="09">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                        <input class="bday" type="text" name="bYear" value="Year"/>

                        <button type="submit">Update</button>
                </form>
            </div>
        </div>
    </body>
</html>
