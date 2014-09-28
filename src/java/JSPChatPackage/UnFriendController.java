/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UnFriendController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher dispatch = request.getRequestDispatcher("LogoutController");
        dispatch.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("sessionUserId");
        Integer linkToRemove = Integer.parseInt(request.getParameter("rmId")); 
        
        //create an instance of the User Bean that can access the delete method
        FriendsBean deletefriendbean = new FriendsBean();
        deletefriendbean.removeFriendLink(userid, linkToRemove);
                
        //re-display the page, with the friends list updated
        RequestDispatcher dispatch = request.getRequestDispatcher("PopulateFriendsController");
        dispatch.forward(request, response);
        
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
