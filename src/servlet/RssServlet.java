package servlet;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Thomas on 20/01/2016.
 */
public class RssServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            try {
                JSONArray news = new JSONArray();
                URL url = new URL(
                        "http://www.elysee.fr/home/rss/presse");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder dBuilder;
                dBuilder = dbFactory.newDocumentBuilder();
                InputStream in = new BufferedInputStream(url.openStream());
                Document doc;
                doc = dBuilder.parse(in);
                NodeList nList = doc.getElementsByTagName("item");
                for (int i = 0; i < nList.getLength(); i++) {
                    Node currentNode = nList.item(i);

                    if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                        JSONObject currentNews = new JSONObject();
                        Element e = (Element) currentNode;
                        currentNews.put("link", e.getElementsByTagName("link")
                                .item(0).getTextContent());
                        currentNews.put("title", e.getElementsByTagName("title")
                                .item(0).getTextContent());
                        currentNews.put("description",
                                e.getElementsByTagName("description").item(0)
                                        .getTextContent());
                        news.put(currentNews);
                    }
                }
                resp.getWriter().println(news);
            } catch (SAXException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (DOMException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (ParserConfigurationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // ...
            }
        }
    }