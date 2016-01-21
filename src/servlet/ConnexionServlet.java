package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 20/01/2016.
 */
public class ConnexionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String id_token = req.getParameter("id_token");
        if(name != null && name != "" && mail != null && mail != "" && id_token != null && id_token!=""){
            User user = new User(name,mail,id_token);
            req.getSession().setAttribute("User",user);
            User u = (User)req.getSession().getAttribute("User");
        }
    }
}
