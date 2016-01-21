package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alex on 21/01/2016.
 */
public class DeconnexionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            User u = (User)req.getSession().getAttribute("User");
            if(u != null){
                req.getSession().setAttribute("User",null);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
