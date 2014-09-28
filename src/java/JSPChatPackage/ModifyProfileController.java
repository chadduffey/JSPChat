package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifyProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModifyProfileController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModifyProfileController at " + request.getContextPath() + "</h1>");
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
        
        //retrieve the session object
        HttpSession session = request.getSession();
        //get the current user bean, assign it to a bean object to work with
        CurrentUserBean currentuserbean = (CurrentUserBean) session.getAttribute("sessionCurrentUserBean");
        
        //grab a create account bean to add things to the user database
        CreateAccountBean createaccountbean = new CreateAccountBean();
        
        //get the updates from the user
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("name");  
        String day      = request.getParameter("bDay");
        String month    = request.getParameter("bMonth");
        String year     = request.getParameter("bYear");
        String DOB      = day + month + year;  
        String gender   = request.getParameter("gender");
        
        //make the change in the database
        createaccountbean.modifyUser(currentuserbean.getId(), username, password, fullname, DOB, gender);

        //display the page
        RequestDispatcher dispatch = request.getRequestDispatcher("/profileUpdated.jsp");
        dispatch.forward(request, response);
        
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
