/**
 * Created by Robin on 20/01/2016.
 */
$(function () {

    var search = queryParameters.search;
    $.post(
        "Result",
        {
            search: search
        }
        )
        .success(function (data) {
            var json = $.parseJSON(data)
            $.each(json.trainingsList, function (key, data) {
                var newTraining = '<div class=" col-md-6 col-sm-6 col-xs-6">' +
                    '<button type="submit" class="btn btn-link">' + data.title + '</button>' +
                    '</div>' +
                    '<div class=" col-md-6 col-sm-6 col-xs-6"> ' +
                    '<label class="btn"> <span class="glyphicon glyphicon-time"></span> 50 min. </label> ' +
                    '</div>'
                $('#trainings').append(newTraining);
            });
            $.each(json.exercisesList, function (key, data) {
                var newExercise = '<div class=" col-md-6 col-sm-6 col-xs-6"> ' +
                    '<button type="submit" class="btn btn-link">'+data.title+'</button> ' +
                    '</div> ' +
                    '<div class=" col-md-6 col-sm-6 col-xs-6"> ' +
                    '<label class="btn"> <span class="glyphicon glyphicon-time"></span> ' + data.hour + ':' + data.minute + ':' + data.seconde +
                    '</label> ' +
                    '</div>';
                $('#exercises').append(newExercise);
            });
        });
});

var queryParameters = function () {
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [query_string[pair[0]], decodeURIComponent(pair[1])];
            query_string[pair[0]] = arr;
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}();