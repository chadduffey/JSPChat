<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
        <link href="css/profile.css" rel="stylesheet" />
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
                <li><a href="messages.jsp">Inbox</a></li>
                <li><a href="newmessage.jsp">New Message</a></li>
                <li class="active"><a href="profileAuth.jsp">Profile</a></li>
                <li><a href="LogoutController">Logout</a></li>
            </ul>
          </div>
            
            <form action="ModifyProfileController" method="post">
                <div class="col-md-6">
                    <h2>Editing ${sessionCurrentUserBean.getName()}</h2>
                    <form action="EditUserController" method="post">
                            <p>Login Name:       <input type="text" name="username" class="form-control" value="${username}"/></p>
                            <p>Password:         <input type="password" name="password" class="form-control" value="${password}"/></p>
                            <p>Your Full Name:   <input type="text" name="name" class="form-control" value="${fullname}"/></p>
                    <div class="radio">
                        <label>
                            <input type="radio" name="gender" id="female" value="female" checked>
                            Female
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="gender" id="male" value="male">
                            Male
                        </label>
                    </div>
                        <p><label>Date of Birth:</label></p>
                        <input class="bday" type="text" name="bDay" value="Date"/>
                        <select class="bday" id="bMonth" name="bMonth">
                            <option value="01" checked>January</option>
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
                        
                    <button class="form-control" type="submit">Update</button>
                </div>
            </form>
        </div>
    </body>
</html>
