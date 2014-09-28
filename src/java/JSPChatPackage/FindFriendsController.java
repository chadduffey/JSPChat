package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FindFriendsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //retrieve the session object
        HttpSession session = request.getSession();
        //get the current user bean, assign it to a bean object to work with
        CurrentUserBean currentuserbean = (CurrentUserBean) session.getAttribute("sessionCurrentUserBean");
        
        //make sure the friends list is up to date
        currentuserbean.refresh_FriendIDs(currentuserbean.getId());
        
        //get the ids of the non friends
        FriendsBean friendsbean = new FriendsBean();
        //this wil make sure existing friends are populated in the bean object
        friendsbean.setAllFriendIds(currentuserbean.getFriendids());
        //now we call the get non friends which relies on knowing existing friends
        friendsbean.getNonFriendIds(currentuserbean.getId());        
        
        //store the friends bean object in the session object        
        session.setAttribute("sessionNonFriendsBean", friendsbean);
        
        //display the page
        RequestDispatcher dispatch = request.getRequestDispatcher("/findfriends.jsp");
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
