$.get("/splashservlet", function (msg) {
    document.getElementById("description").innerHTML = msg;
});

setTimeout(function () {
    window.location = '/Search';
}, 5000);
