package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import javax.cache.CacheException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.memcache.MemcacheService;



/**
 * Created by Thomas on 19/01/2016.
 */
public class SplashServlet extends HttpServlet {

    private static final String MSG_LABEL = "msg";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        String msg = getDescription(datastore);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(msg);

    }

    private String getDescription(DatastoreService datastore) {

        //récupération du service Cache
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

        if (cache.get(MSG_LABEL) != null) {
            return (String) cache.get(MSG_LABEL);

        } else {
            String msgDatastore = null;
            Query q = new Query("SPLASHMSG");
            PreparedQuery pq = datastore.prepare(q);

            for (Entity result : pq.asIterable()) {
                msgDatastore = (String) result.getProperty(MSG_LABEL);
            }

            //udpate cache
            cache.put(MSG_LABEL, msgDatastore);
            return msgDatastore;
        }
    }
}


