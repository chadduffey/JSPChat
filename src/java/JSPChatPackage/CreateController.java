package JSPChatPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String DOB = request.getParameter("DOB");
        String gender = request.getParameter("gender");
        
        
        CreateAccountBean createaccount = new CreateAccountBean();
        createaccount.setUsername(username);
        createaccount.setPassword(password);
        createaccount.setName(fullname);
        createaccount.setDOB(DOB);
        createaccount.setGender(gender);
        
        if (createaccount.userExists(username)) {
            RequestDispatcher dispatch = request.getRequestDispatcher("/createUserError.jsp");
            dispatch.forward(request, response);            
        }
        
//        if(loginBean.checkLogin(loginBean.getUsername(), loginBean.getPassword())){
//            //login success
//            RequestDispatcher dispatch = request.getRequestDispatcher("/loginSuccess.jsp");
//            dispatch.forward(request, response);
//        }
//        else {
//            //login fail
//            RequestDispatcher dispatch = request.getRequestDispatcher("/loginError.jsp");
//            dispatch.forward(request, response);
//        }
        
        processRequest(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
