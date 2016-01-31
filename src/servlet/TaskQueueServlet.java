package servlet;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import utils.DatabaseInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 21/01/2016.
 */
public class TaskQueueServlet extends HttpServlet{

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String EXERCICES = "exercises";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String titre = req.getParameter(TITLE);
        String desc = req.getParameter(DESCRIPTION);
        String domaine = req.getParameter(DatabaseInfo.TRAINING_DOMAIN);
        String exercises = req.getParameter(EXERCICES);
        String durationTotal = req.getParameter(DatabaseInfo.TRAINING_TOTAL_DURATION);

        Queue queue = QueueFactory.getDefaultQueue();
        TaskOptions task=TaskOptions.Builder.withUrl("/addTraining");

        task.param(TITLE, titre);
        task.param(DESCRIPTION, desc);
        task.param(DatabaseInfo.TRAINING_DOMAIN, domaine);
        task.param(EXERCICES, exercises);
        task.param(DatabaseInfo.TRAINING_TOTAL_DURATION, durationTotal);

        task.method(TaskOptions.Method.POST);
        queue.add(task);
    }
}
