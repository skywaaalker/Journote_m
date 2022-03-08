import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/isjournotehasnotiservlet.do")
public class IsJournoteHasnotiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        IsJournoteHasnoti mIsJournoteHasnoti = new IsJournoteHasnoti();
        String journoteTag = request.getParameter("journoteTag");

        boolean hasnoti = false;
        hasnoti = mIsJournoteHasnoti.isJournoteHasnoti(journoteTag);
        String result = "";
        if(hasnoti) result="hasnoti"; else result="nothasnoti";
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
        System.out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
