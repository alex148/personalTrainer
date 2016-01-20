package servlet;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Robin on 19/01/2016.
 */
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Filter heightMinFilter =
                new FilterPredicate("height",
                        FilterOperator.IN,
                        minHeight);

        Filter heightMaxFilter =
                new FilterPredicate("height",
                        FilterOperator.LESS_THAN_OR_EQUAL,
                        maxHeight);

        //Use CompositeFilter to combine multiple filters
        Filter heightRangeFilter =
                CompositeFilterOperator.and(heightMinFilter, heightMaxFilter);


        Query q=new Query("USER");
        PreparedQuery pq=datastore.prepare(q);
        for(Entity e:pq.asIterable()){

        }
    }
}
