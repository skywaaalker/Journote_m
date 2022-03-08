import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JournoteRegister
 */
@WebServlet("/journoteregister.do")
public class JournoteRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JournoteRegister() {
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
        userRegister mUserRegister = new userRegister();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result = "";
        b = mUserRegister.isuserRegister(username, password);

        PrintWriter out = response.getWriter();
        if(b) {
            result = "success";
        }else {
            result = "fail";
        }
        out.write(result);

        //out.write(result+username+password);
        //out.write(username);
        //out.write(password);
        out.flush();
        out.close();
        System.out.println(result);
        System.out.println(username);
        System.out.println(password);

    }

}