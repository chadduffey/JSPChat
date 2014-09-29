package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommitNewMessageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //retrieve the session object
        HttpSession session = request.getSession();
        //get the current user bean, assign it to a bean object to work with
        CurrentUserBean currentuserbean = (CurrentUserBean) session.getAttribute("sessionCurrentUserBean");
        
        //get the parameters
        String[] msgrecipients = request.getParameterValues("recipients");
        
        //convert to integers
        Integer[] intMsgRecipients;
        intMsgRecipients = new Integer[msgrecipients.length]; 
        for (Integer i = 0; i < msgrecipients.length; i++){
            intMsgRecipients[i] =  Integer.parseInt(msgrecipients[i]);   
        }
        
        String msgsubject = request.getParameter("subject");
        String msgcontent = request.getParameter("message");
        
        //bean for writing message
        MessagesBean messagesbean = new MessagesBean();
        
        //write the parameters to the DB
        messagesbean.writeMessageBody(currentuserbean.getId(), msgsubject, msgcontent);
        
        //get message id
        Integer msgId = messagesbean.returnMessageId();
        
        //for each recipient we create one link of the message (once we know the id
        for (Integer i = 0; i < msgrecipients.length; i++){
            messagesbean.writeMessageLinks(msgId, intMsgRecipients[i]);
            i++;
        }
        
        //display the page
        RequestDispatcher dispatch = request.getRequestDispatcher("/messageSent.jsp");
        dispatch.forward(request, response);
        
        
        processRequest(request, response);
    
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
