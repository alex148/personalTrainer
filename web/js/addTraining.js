/**
 * Created by Alex on 19/01/2016.
 */


function addExercice(){
    //get values from html
    var title = $("#titleDescription").val();
    var description = $("#exerciceDescription").val();
    var hours = $("#hours").val();
    var minutes = $("#minutes").val();
    var secondes = $("#secondes").val();
    var lastId = $('#exercices tr:last td:first-child').html();

    //check exercise table size
    if(lastId == null || lastId == undefined){
        lastId = 0;
    }

    //check required data
    if(title == null || title == undefined || title == ""){
        toastr.error("Please enter an exercise title !");
        return;
    }
    if(description == null || description == undefined || description == ""){
        toastr.error("Please enter an exercise description !");
        return;
    }
    if(!isInteger(hours) || !isInteger(minutes) || !isInteger(secondes)){
        toastr.error("Please enter a valid duration !");
        return;
    }

    if(hours<0 || hours > 99){
        toastr.error("Please enter a valid hour duration !");
        return;
    }
    if(minutes<0 || minutes > 59){
        toastr.error("Please enter a valid minute duration !");
        return;
    }
    if(secondes<0 || secondes > 59){
        toastr.error("Please enter a valid secondes duration !");
        return;
    }
    //get next exercise id
    lastId++;


    //check duration format
    if(hours < 10){
        hours = "0"+hours;
    }
    if(minutes < 10){
        minutes = "0"+minutes;
    }
    if(secondes < 10){
        secondes = "0"+secondes;
    }

    //create new exercise
    var newExercice = "<tr>" +
        "<td name='exId'>"+lastId+"</td>"+
        "<td name='exTitle'>"+title+"</td>"+
        "<td class='hidden-xs' name='exDesc'>"+description+"</td>"+
        "<td name='exDuration'>"+hours+":"+minutes+":"+secondes+"</td>"+
        "<td> <button type='button' class='deleteLink btn btn-danger btn-sm'> <span class='glyphicon glyphicon-remove'></span> </button></td>"+
        "</tr>";

    //add exercise in table
    if(lastId == 1){
        $('#exercices').append(newExercice);
    }else{
        $('#exercices tr:last').after(newExercice);
    }

    //calculing and setting of total duration
    setDurationTotal();

    //add an event on delete button click
    $("#exercices").on('click','.deleteLink',function(){
        var tr = $(this).closest('tr');
        tr.css("background-color","#FF3700");
        tr.fadeOut(400, function(){
            tr.remove();
        });
        return false;
    });

    $("#titleDescription").val("");
    $("#exerciceDescription").val("");
    $("#hours").val("");
    $("#minutes").val("");
    $("#secondes").val("");
}

function setDurationTotal() {
    var duration = "";
    var hours = 0;
    var min = 0;
    var sec = 0;
    var tmp = "";

    $("td[name='exDuration']").each(function () {
        duration = $(this).html();
        hours = hours + parseInt(duration[0]+duration[1]);
        min = min + parseInt(duration[3]+duration[4]);
        sec = sec + parseInt(duration[6]+duration[7]);
    });
    min += Math.floor(sec / 60);
    sec = sec % 60;
    hours+= Math.floor(min/60);
    min = min % 60;

    //check duration format
    if(hours < 10){
        hours = "0"+hours;
    }
    if(min < 10){
        min = "0"+min;
    }
    if(sec < 10){
        sec = "0"+sec;
    }

    var DurationTotal = hours+":"+min+":"+sec;
    $("#durationTotal").text(DurationTotal);
}


function isInteger(str) {
    var n = ~~Number(str);
    return String(n) === str && n >= 0;
}

function sendTraining(){
    var tbl = $('#exercices tr:has(td)').map(function(i, v) {
        var $td =  $('td', this);
        return {
            numero: $td.eq(0).text(),
            title: $td.eq(1).text(),
            description: $td.eq(2).text(),
            duration: $td.eq(3).text()
        }
    }).get();
    $.post(
        "addTraining",
        {
            exercises : JSON.stringify(tbl),
            title : $("#inputTitle").val(),
            description : $("#inputDescription").val(),
            domain : $("#e1").val()
        }
        )
        .success(function(data){
            toastr.success("Training has been added successfully!");
        });

}




