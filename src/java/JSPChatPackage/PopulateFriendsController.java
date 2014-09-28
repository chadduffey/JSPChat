package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PopulateFriendsController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //retrieve the session object
        HttpSession session = request.getSession();
        //get the current user bean, assign it to a bean object to work with
        CurrentUserBean currentuserbean = (CurrentUserBean) session.getAttribute("sessionCurrentUserBean");
        
        //make sure the friends list is up to date
        currentuserbean.refresh_FriendIDs(currentuserbean.getId());
        
        //get the IDs of the friends stored in this users bean
        Integer[] FriendIds = currentuserbean.getFriendids();
        
        //create a friends bean to store everything we will need on the page
        FriendsBean friendsbean = new FriendsBean();
        friendsbean.setAllFriendIds(FriendIds);
        
        //store the friends bean on the session object        
        session.setAttribute("sessionFriendsBean", friendsbean);

        //display the page
        RequestDispatcher dispatch = request.getRequestDispatcher("/friends.jsp");
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
