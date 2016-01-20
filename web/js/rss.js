
    $.get("/rss", function(news) {

        //displayContent(news);

        var response = JSON.parse(news);
        var rsslist = document.getElementById("rssList");
        rsslist.innerHTML = "";
        for ( var i in response) {
            rsslist.innerHTML += "<p>" + "From " + "<a href=\""
            + response[i].link + "\" target=\"_blank\">"
            + response[i].link + "</a>" + "</br >"
            + response[i].title	+ "</br >"
            + response[i].description + "</br >" + "</p>"
        }
    });
