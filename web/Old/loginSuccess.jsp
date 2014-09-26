<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <link href="css/normalize.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Amatic+SC|Shadows+Into+Light|PT+Sans+Narrow' rel='stylesheet' type='text/css'>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
        <link href="css/messages.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#"> ... jsp chat</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <li class="active"><a href="#">Your Stuff</a></li>
                <li><a href="#">All Users</a></li>
                <li><a href="editProfile.jsp">Your Profile</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="LogoutController">Log Out</a></li>
              </ul>
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav>
        
        <h1>${sessionFullName} - Your Stuff</h1>
        
        <div class="friendbox">
            <h3>Friends</h3>
            <ul class="list-group">
                <c:forEach var="item" items="${sessionFriends}">
                    <li class="list-group-item"><a href="#"><span class="glyphicon glyphicon-user"></span> ${item}</a></li>   
                </c:forEach>
            </ul>
            <h3>New Friends?</h3>
            <ul class="list-group">
                <c:forEach var="item" items="${sessionNonFriends}">
                    <li class="list-group-item"><a href="#"><span class="glyphicon glyphicon-user"></span> ${item}</a></li>   
                </c:forEach>
            </ul>
        </div>

        <div class="messagebox">
            <h3>Messages</h3>
            <ul class="list-group">
                <c:forEach var="item" items="${sessionSubjects}">
                    <li class="list-group-item"><a href="#"><span class="glyphicon glyphicon-envelope"></span> ${item}</a></li>   
                </c:forEach>
            </ul>
        </div>
        
        <div class="input-group">
            <h3>Send a Message</h3>
            <form action="NewMessageController" method="post">
                        <p>To:          <input type="text" name="to" class="form-control"/></p>
                        <p>Cc:          <input type="text" name="cc" class="form-control"/></p>
                        <p>Bcc:         <input type="text" name="bcc" class="form-control"/></p>
                        <p>Subject:     <input type="text" name="subject" class="form-control"/>
                        <p>Message:     <textarea name="content" class="form-control" rows="5"></textarea>	
                        <p><button type="submit" class="btn btn-default">Send Message</button></p>
            </form>
        </div>
    </body>
</html>
