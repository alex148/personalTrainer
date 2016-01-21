/**
 * Created by Alex on 20/01/2016.
 */


function onSignIn(googleUser) {

    var profile = googleUser.getBasicProfile();

    var name = profile.getName();
    var mail = profile.getEmail();
    var id_token = googleUser.getAuthResponse().id_token;
    window.localStorage.name = name;
    window.localStorage.mail = mail;
    window.localStorage.id_token = id_token;

    $.post(
        "connexion",
        {
            name : name,
            mail : mail,
            id_token : id_token
        }
        )
        .success(function(data){
            checkIfUserConnected();
        });
};

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    deconnexion();
    window.localStorage.name = null;
    window.localStorage.mail = null;
    window.localStorage.id_token = null;
    checkIfUserConnected();
}

function checkIfUserConnected(){
    if(window.localStorage.id_token != "null"){
        $("#signInButton").hide();
        $("#disconnectButton").show();
        $("#userInfos").text(window.localStorage.name);
        $("#personalButton").show();
        $("#statButton").show();

    }else{
        $("#signInButton").show();
        $("#disconnectButton").hide();
        $("#userInfos").text("");
        $("#personalButton").hide();
        $("#statButton").hide();


    }
}


$(function(){
    checkIfUserConnected();
});

function deconnexion(){
    $.post(
        "deconnexion",
        {
            name : window.localStorage.name,
            mail : window.localStorage.mail,
            id_token : window.localStorage.id_token
        }
        )
        .success(function(data){
            console.log("User signed out");
        });
}