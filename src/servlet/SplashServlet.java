package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import javax.cache.CacheException;

import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.memcache.MemcacheService;



/**
 * Created by Thomas on 19/01/2016.
 */
public class SplashServlet extends HttpServlet {

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity DescriptionSplash=null;

        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            Map props = new HashMap();
            props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
            Cache cache=null;

            try {
                // Récupération du Cache
                CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
                cache = cacheFactory.createCache(props);

            } catch (net.sf.jsr107cache.CacheException e) {
                e.printStackTrace();
            }

            Key cleDescription = KeyFactory.createKey("Description", "idDescription");

            DescriptionSplash = new Entity("Description","idDescription");
            DescriptionSplash.setProperty("description", " Ce site est dédié à votre entrainement sportif et au suivi des exercices que vous effectuez");
            datastore.put(DescriptionSplash);
            String key ="message";
            String value=null;
            value=(String) cache.get(key);
            if(value!=null){
                resp.getWriter().write( value);
            }
            else{
                try {
                    DescriptionSplash = datastore.get(cleDescription);
                    String message = (String) DescriptionSplash.getProperty("description");
                    cache.put(key, message);
                    resp.getWriter().write(message);
                } catch (EntityNotFoundException e) {
                   e.printStackTrace();
                }
            }
        }
        public void toDataStore(String description){


        }
    }



