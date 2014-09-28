package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MakeFriendController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Integer friendToAdd = Integer.parseInt(request.getParameter("addId"));

        //retrieve the session object
        HttpSession session = request.getSession();
        //get the current user bean, assign it to a bean object to work with
        CurrentUserBean currentuserbean = (CurrentUserBean) session.getAttribute("sessionCurrentUserBean");
        
        FriendsBean addfriendsbean = new FriendsBean();
        addfriendsbean.addFriendLink(friendToAdd, currentuserbean.getId());
        
        //display the page
        RequestDispatcher dispatch = request.getRequestDispatcher("PopulateFriendsController");
        dispatch.forward(request, response);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
