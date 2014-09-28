package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifyProfileAuthController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet modifyProfileAuthController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet modifyProfileAuthController at " + request.getContextPath() + "</h1>");
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

        String password = request.getParameter("password");
        
        //retrieve the session object
        HttpSession session = request.getSession();
        //get the current user bean, assign it to a bean object to work with
        CurrentUserBean currentuserbean = (CurrentUserBean) session.getAttribute("sessionCurrentUserBean");

        if(currentuserbean.checkLogin(currentuserbean.getUsername(), password)){
            //login success
            
            //pass the details to pre-poulate the form
            String username = currentuserbean.getUsername();
            String fullname = currentuserbean.getName();
            
            request.setAttribute("username", username);
            request.setAttribute("fullname", fullname);
            request.setAttribute("password", password);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("/profile.jsp");
            dispatch.forward(request, response);
        }
        else {
            //login fail
            RequestDispatcher dispatch = request.getRequestDispatcher("/profileAuth.jsp");
            dispatch.forward(request, response);
        }
        
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
