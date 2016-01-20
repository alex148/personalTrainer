/**
 * Created by Thomas on 20/01/2016.
 */
$.get("/personalData", function (resp) {

    var i, completed, data = JSON.parse(resp);
    for (i = 0; i < data.length; i++) {
        completed = data[i].Status == "Success" ? "Yes" : "No";

        var Html = "<tr><td>" + data[i].date + "</td><td>" + data[i].exerciceTitle + "</td><td> " + data[i].timeExpected + "</td><td>" + data[i].duration + "</td><td>" + completed + "</td></tr>";
        $("#personalDataTable").append(Html);
    }
});