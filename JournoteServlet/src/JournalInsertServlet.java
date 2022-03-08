import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/journalinsertservlet.do")
public class JournalInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        journalInsert mJournalInsert = new journalInsert();
        boolean b;
        String result;
        String username =request.getParameter("username");
        String title = request.getParameter("title");
        String content = request.getParameter("content");


        b = mJournalInsert.isjournalInsert(username, title, content);
        //mJournalEdit.isjournalEdit(title, content, tag);
        if(b) {result = "success";}else {result ="fail" ;}
        PrintWriter out = response.getWriter();
        out.write(result);

        //out.write(result+username+password);
        //out.write(username);
        //out.write(password);
        out.flush();
        out.close();
        System.out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
