package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        CurrentUserBean currentuserbean = new CurrentUserBean();
        currentuserbean.setUsername(username);
        currentuserbean.setPassword(password);
        
        if(currentuserbean.checkLogin(currentuserbean.getUsername(), currentuserbean.getPassword())){
            //login success
                        
            //set the session object to include the user id from the database
            HttpSession session = request.getSession();     
            session.setAttribute("sessionUserId", currentuserbean.getId());
            session.setAttribute("sessionCurrentUserBean", currentuserbean);

            //refresh all the bean attributes for the current user from DB
            currentuserbean.refresh_ALL();
            
           
            //send the user to the welcome page
            //----------------------------------------
            RequestDispatcher dispatch = request.getRequestDispatcher("/welcome.jsp");
            dispatch.forward(request, response);
        }
        else {
            //login fail
            RequestDispatcher dispatch = request.getRequestDispatcher("/loginError.jsp");
            dispatch.forward(request, response);
        }
        
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
