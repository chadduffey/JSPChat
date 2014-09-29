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
                <li><a href="PopulateMessagesController">Inbox</a></li>
                <li class="active"><a href="NewMsgController">New Message</a></li>
                <li><a href="profileAuth.jsp">Profile</a></li>
                <li><a href="LogoutController">Logout</a></li>
            </ul>
          </div>
        
            <form action="NewMsgController" method="post">
                <div class="col-md-1">  
                    <h3>Recipients</h3>
                    <c:forEach var="item" items="${sessionCurrentUserBean.getFriendids()}">
                        
                        <c:if test="${rcptname == item}">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="recipients" checked value=${item}> ${sessionFriendsBean.getFriendName(item)}
                                </label>
                            </div>
                        </c:if>

                        <c:if test="${rcptname != item}">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="recipients" value=${item}> ${sessionFriendsBean.getFriendName(item)}
                                </label>
                            </div>
                        </c:if>
                        
                    </c:forEach>
                </div>
                <div class="col-md-6">
                    <h3>Subject</h3>
                    <input type="text" name="subject" class="form-control"><br>
                    <h3>Message</h3>
                    <textarea name="message" rows="10" class="form-control"></textarea>
                    <button type="submit">Send Message</button>
                </div>
            </form>                  
        </div>

    </body>
</html>
