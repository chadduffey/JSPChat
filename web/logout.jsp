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
                <li class="active"><a href="logout.jsp">Logout</a></li>
            </ul>
          </div>
            <div class="col-md-1">
                <h1>Goodbye.</h1>
                <form action="loginForm.html" method="post">
                    <button type="submit">Log Back In</button>
                </form>
            </div>  
        </div>
        
        
    </body>
</html>
