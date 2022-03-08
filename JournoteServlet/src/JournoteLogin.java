import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;



/**
 * Servlet implementation class JournoteLogin
 */
@WebServlet("/journotelogin.do")
public class JournoteLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JournoteLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        boolean b = false;
        userLogin mUserLogin = new userLogin();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result = "";
        //b =mUserLogin.isuserLogin(username, password);
        b = mUserLogin.isuserLogin(username, password);
        PrintWriter out = response.getWriter();
        if(b) {
            result = "success";
        }else {
            result = "fail";
        }
        out.write(result);
        out.flush();
        out.close();
        System.out.println(result);

    }

}
