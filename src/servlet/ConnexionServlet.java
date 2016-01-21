package servlet;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import model.User;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 20/01/2016.
 */
public class ConnexionServlet extends HttpServlet {

    public static final String USER = "USER";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String id_token = req.getParameter("id_token");
        if(name != null && name != "" && mail != null && mail != "" && id_token != null && id_token!=""){
            User user = new User(name,mail,id_token);
            addUserInCache(user);
        }
    }


    public static boolean addUserInCache(User u){
        Cache cache = null;
        Map props = new HashMap();

        props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
        props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);

        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(props);

        } catch (net.sf.jsr107cache.CacheException e) {
            e.printStackTrace();
        }

        if (cache.get(USER) == null) {
            try{
                cache.put(USER, u);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        return false;
    }

}
