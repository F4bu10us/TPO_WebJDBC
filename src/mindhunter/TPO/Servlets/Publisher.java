package mindhunter.TPO.Servlets;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;

@WebServlet(name = "Publisher", urlPatterns = {"/publisher"}
, initParams = {@WebInitParam(name = "loginPattern"
        ,value = "loginPattern.html")})
public class Publisher extends HttpServlet {
    private String prolog =
            "<html><title>Logged</title>" +
                    "<body>";
    private String epilog = "</body></html>";

    private void inputHTML(PrintWriter out) throws IOException {
        String formFile = getInitParameter("loginPattern");
        ServletContext context = getServletContext();
        InputStream in = context.getResourceAsStream("/WEB-INF/" + formFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) out.println(line);
    }

    private void serviceRequest(HttpServletRequest req,
                                HttpServletResponse resp)
            throws IOException, SQLException {
        String charset = "ISO8859-2";
        resp.setContentType("text/html; charset=" + charset);
        PrintWriter out = resp.getWriter();
        HttpSession ses = req.getSession();
        Model model = (Model) ses.getAttribute("Res");
        if (model.getNames().size()==0) {
            inputHTML(out);
        }else{
            out.println(prolog);
            out.println("<ol>");
            for(int i = 0; i<model.getNames().size(); ++i) {
            out.println("<li>" + model.getNames().get(i)
                            +"</li>" +
                    "<form method=\"get\" action=\"http://localhost:8080/WebJDBC/details\">" +
                    "<input type=\"hidden\" value=\""+i+"\" name=\"num\">" +
                    "<input type=\"submit\" value=\"Details\">\n" +
                    "</form>");
            }
            out.println("<form method=\"get\" action=\"http://localhost:8080/WebJDBC/index.jsp\">" +
                    "<input type=\"submit\" value=\"Log out\">\n" +
                    "</form>");
            out.println(epilog);
        }
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
