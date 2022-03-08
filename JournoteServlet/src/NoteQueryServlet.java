import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/notequeryservlet.do")
public class NoteQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        List<JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        //journalQuery mjournalQuery = new journalQuery();
        noteQuery mNoteQuery = new noteQuery();

        String username = request.getParameter("username");
        String result = "";
        mJournoteBeanList = mNoteQuery.getNote(username);

        JSONArray jsonArray = new JSONArray();
        for (JournoteBean bean : mJournoteBeanList) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("date", bean.getDate());
                jsonObject.put("title", bean.getTitle());
                jsonObject.put("content", bean.getContent());
                jsonObject.put("isjournal", bean.getIsjournal());
                jsonObject.put("hasnoti", bean.getHasnoti());
                jsonObject.put("label", bean.getLabel());
                jsonObject.put("tag", bean.getTag());
                jsonObject.put("username", bean.getUsername());
            } catch (Exception e) {
            }
            jsonArray.add(jsonObject);
        }

        PrintWriter out = response.getWriter();
//        if(b) {
//            result = "success";
//        }else {
//            result = "fail";
//        }
//        out.write(result);
        out.write(jsonArray.toString());
        out.flush();
        out.close();
        System.out.println(result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
