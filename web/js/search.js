/**
 * Created by Robin on 20/01/2016.
 */

function goToResult(){
    var search=$("#searchBar").val();
    $.get("/Search?search="+search);
}

function sendDomain(domain){
    document.location.href="/Search?domain="+domain;
}