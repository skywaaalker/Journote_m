import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/labelupdateservlet.do")
public class LabelUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        LabelUpdate mLabelUpdate = new LabelUpdate();
        String journoteTag = request.getParameter("journoteTag");
        String label_str = request.getParameter("label") ;

        int label = Integer.parseInt(label_str);
        String result = "success";
        mLabelUpdate.isLabelUpdate(journoteTag, label);

        PrintWriter out =  response.getWriter();
        out.write(result);
        out.flush();
        out.close();
        System.out.println(result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
