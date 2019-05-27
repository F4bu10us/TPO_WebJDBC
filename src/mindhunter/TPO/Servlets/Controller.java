package mindhunter.TPO.Servlets;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.sql.*;

@WebServlet(name = "Controller", urlPatterns = {"/logged"})
@Resource(name = "jdbc/userdb")
public class Controller extends HttpServlet {
    private ServletContext context;
    private String publisher;
    private Connection con;
    private DataSource dataSource;
    private Logic l;
    public Controller(){ l = new Logic();}

    public void init() {
        context = getServletContext();
        publisher = context.getInitParameter("publisherServ");
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/userdb");
        } catch (NamingException exc) {
            exc.printStackTrace();
        }
    }


    private void serviceRequest(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException, IOException {
        String charset = "ISO8859-2";
        response.setContentType("text/html; charset=" + charset);
        PrintWriter out = response.getWriter();
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        PreparedStatement stmt;
        con = null;
        Model model = null;
        try {
            synchronized (dataSource) {
                con = dataSource.getConnection();
            }
            stmt = con.prepareStatement(l.query(login, pass));
            ResultSet rs = stmt.executeQuery();
            int count = -1;
            model = new Model();
            while (rs.next()) {
                ++count;
                out.println(count);
                model.addContent(rs.getString("resName"), rs.getString("resContent"), count);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        } finally {
            try { con.close(); } catch (Exception ignored) {}
        }
        HttpSession ses = request.getSession();
        ses.setAttribute("Res", model);
        RequestDispatcher disp = context.getRequestDispatcher(publisher);
        disp.forward(request,response);
    }

    public void doPost(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException, IOException {
        serviceRequest(request, response);
    }

    public void doGet(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException, IOException {
        serviceRequest(request, response);
    }
}
