$.get("/splashservlet", function (msg) {
    document.getElementById("description").innerHTML = msg;
});

setTimeout(function () {
    window.location = 'ha-search-screen.html';
}, 5000);
