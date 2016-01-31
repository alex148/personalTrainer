/**
 * Created by Robin on 21/01/2016.
 */
$(function () {
    var key = queryParameters.key;
    var type = queryParameters.type;
    var parent="";
    if(type=="EXERCISE"){
        parent=queryParameters.parent;
    }
    $.post(
        "Detail",
        {
            id: key,
            kind: type,
            parent:parent
        }
        )
        .success(function (data) {
            var json = $.parseJSON(data)
            $.each(json.exercisesList, function (key, data) {
                var i = 0;
                var exerciseDetail = '<tr><td class=" col-md-12 col-sm-12 col-xs-12"> ' +
                    '<div class="row"> ' +
                    '<div class=" col-md-3 col-sm-12 col-xs-12 "> ' +
                    '<h3>' + data.title + '</h3> ' +
                    '</div> ' +
                    '<div class=" col-md-1 col-sm-2 col-xs-2 "> ' +
                    '<p style="margin-top:25px"><span class="glyphicon glyphicon-time">' +
                    '</span>' + data.hour + ':' + data.minute + ':' + data.seconde + '</p> ' +
                    '</div> ' +
                    '</div> ' +
                    '<div class="row"> ' +
                    '<div class=" col-md-1 col-sm-0 col-xs-0 " ></div> ' +
                    '<div class=" col-md-6 col-sm-12 col-xs-12 "> ' +
                    '<p>'+data.description+'</p> ' +
                    '</div> ' +
                    '<div class=" col-md-3 col-sm-12 col-xs-12 "> ' +
                    '<div class=" col-md-12 col-sm-12 col-xs-12 "> ' +
                    '<div id="flipcountdownbox' + i + '"> ' +
                    '</div> ' +
                    '</div> ' +
                    '<div class=" col-md-12 col-sm-12 col-xs-12 centered"> ' +
                    '<div class="btn-group"> ' +
                    '<button type="button" class="btn btn-default " ><span class="glyphicon glyphicon-play"></span> </button> ' +
                    '<button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-pause"></span> </button> ' +
                    '<button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-stop"></span> </button> ' +
                    '</div> ' +
                    '<button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-repeat"></span> </button> ' +
                    '</div> ' +
                    '</div> ' +
                    '</div> ' +
                    '<div class=" col-md-2 ol-sm-5 col-xs-12 text-center" > ' +
                    '<button type="submit" class="btn btn-success btn-lg"> <span class="glyphicon glyphicon-ok"></span> </button> ' +
                    '<button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-fast-forward"></span> </button> ' +
                    '</div> ' +
                    '</td> ' +
                    '</tr>';
                i++;
                $('#exercisesDetails').append(exerciseDetail);
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