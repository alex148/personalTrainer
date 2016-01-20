package dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;

import java.util.List;

/**
 * Created by Alex on 20/01/2016.
 */
public interface InterfaceDao<T> {

    public DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();

    public T getByKey(Key key);
    public boolean deleteByKey(Key key);
    public boolean update(T object);
    public Key add(T object);
    public List<T> getAll(String entityName);
}
