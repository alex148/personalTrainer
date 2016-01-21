/**
 * Created by Robin on 20/01/2016.
 */

function goToResult(){
    var search=$("#searchBar").val();
    alert(search);
    $.get("/Search?search="+search);
}