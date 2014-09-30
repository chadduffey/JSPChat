
package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReadMessageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Integer message = Integer.parseInt(request.getParameter("id"));
        
        //setIndividualMessageContent
        MessagesBean messagebean = new MessagesBean();
        messagebean.setIndividualMessageContent(message);
        
        //set the session object for the individual message
        HttpSession session = request.getSession();     
        session.setAttribute("sessionMsgContent", messagebean);
       
        
        //get the current user bean, assign it to a bean object to work with
        CurrentUserBean currentuserbean = (CurrentUserBean) session.getAttribute("sessionCurrentUserBean");
        
        //mark the message as read
        messagebean.markMessageRead(message, currentuserbean.getId());
        
        //display the page
        RequestDispatcher dispatch = request.getRequestDispatcher("/readMessage.jsp");
        dispatch.forward(request, response);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
