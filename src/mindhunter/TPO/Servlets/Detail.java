package mindhunter.TPO.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Detail", urlPatterns = {"/details"})
public class Detail extends HttpServlet {

    private String prolog =
            "<html><title>Logged</title>" +
                    "<body>";
    private String epilog = "</body></html>";

    private void serviceRequest(HttpServletRequest req,
                                HttpServletResponse resp)
            throws IOException, SQLException {
        PrintWriter out = resp.getWriter();
        HttpSession ses = req.getSession();
        Model model = (Model) ses.getAttribute("Res");
        int n = Integer.parseInt(req.getParameter("num"));
        out.println(prolog);
        out.println("<h2>"+model.getNames().get(n)+"</h2>");
        out.println("<hr>");
        out.println("<h3 style =\"color: green\">"+model.getContexts().get(n)+"</h3>");
        out.println("<form method=\"get\" action=\"http://localhost:8080/WebJDBC/publisher\">" +
                "<input type=\"submit\" value=\"Go Back\">\n" +
                "</form>");
        out.println("<form method=\"get\" action=\"http://localhost:8080/WebJDBC/index.jsp\">" +
                "<input type=\"submit\" value=\"Log out\">\n" +
                "</form>");
        out.println(epilog);
        out.close();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        try {
            serviceRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException {
        try {
            serviceRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
