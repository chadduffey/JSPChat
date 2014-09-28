package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateController at " + request.getContextPath() + "</h1>");
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
        String fullname = request.getParameter("name");  
        String day      = request.getParameter("bDay");
        String month    = request.getParameter("bMonth");
        String year     = request.getParameter("bYear");
        String DOB      = day + month + year;  
        String gender   = request.getParameter("gender");

        //bean to add the new user to the DB if the username does not exist
        CreateAccountBean createaccount = new CreateAccountBean();
        createaccount.setUsername(username);
        createaccount.setPassword(password);
        createaccount.setName(fullname);
        createaccount.setDOB(DOB);
        createaccount.setGender(gender);
        
        //check if the user already exists in the db
        if (createaccount.userExists(username)) {
            RequestDispatcher dispatch = request.getRequestDispatcher("/createUserError.jsp");
            dispatch.forward(request, response);            
        }
        
        //add the user to the DB
        if(createaccount.addToDB()){
            //add success
            
            //log them in - this will update the session object
            CurrentUserBean currentuserbean = new CurrentUserBean();
            
            if (currentuserbean.checkLogin(username, password)){
                
                //set the session object to include the user id from the database
                HttpSession session = request.getSession();
                session.setAttribute("sessionUserId", currentuserbean.getId());
                session.setAttribute("sessionCurrentUserBean", currentuserbean);
                
                RequestDispatcher dispatch = request.getRequestDispatcher("/welcome.jsp");
                dispatch.forward(request, response);               
            }
            
            RequestDispatcher dispatch = request.getRequestDispatcher("/loginError.jsp");
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
