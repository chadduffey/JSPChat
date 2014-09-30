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
            <h2>Your Messages</h2>
            <table class="table table-striped">
                <tr>
                    <th>Opened?</th>
                    <th>Subject</th>
                    <th>Sender</th>
                    <th>Delete?</th>
                </tr>
                <c:forEach var="item" items="${sessionMessagesBean.getAllMsgIds()}">
                    <tr>                  
                        
                        <c:if test="${sessionMessagesBean.isRead(item, sessionCurrentUserBean.getId()) == false}">
                            <td><span class="glyphicon glyphicon-envelope"></span><input type="hidden" name="id" value=${item}></td>
                        </c:if>

                        <c:if test="${sessionMessagesBean.isRead(item, sessionCurrentUserBean.getId()) == true}">
                            <td><span class="glyphicon glyphicon-ok"></span><input type="hidden" name="id" value=${item}></td>
                        </c:if>
                        
                        <td><a href="ReadMessageController?id=${item}">${sessionMessagesBean.getMessageSubject(item)}</a></td> 
                        <td>${sessionMessagesBean.getMessageSenderName(item)}</td>
                        <td><a href="DeleteMessageController?id=${item}"><span class="glyphicon glyphicon-trash"></span> </a></td>
                    </tr>                   
                </c:forEach>
            </table>
            <h5><a href="messagesDeletedItems.jsp"><span class="glyphicon glyphicon-zoom-in"></span>Recycle Bin</a></h5>
          </div>  
            
        </div>
        
        
    </body>
</html>
