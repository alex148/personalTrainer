$(document).ready(function(){
  $("#btnCommitId").click(function(){
	  	$.post("register",
    		  {
    		    cmd:"Register",
    		    email:$("#inputEmailId").val(),
    		    pwd:$("#inputPwdId").val(),
    		  },
    		  function(data,status){
    		    alert("Post Done new user added, id: " + data.userid + "\nStatus: " + status);
    		  });
  		});
});

function onSignIn(googleUser) {

	// Useful data for your client-side scripts:
	var profile = googleUser.getBasicProfile();
	console.log("ID: " + profile.getId()); // Don't send this directly to your server!
	console.log("Name: " + profile.getName());
	console.log("Image URL: " + profile.getImageUrl());
	console.log("Email: " + profile.getEmail());

	// The ID token you need to pass to your backend:
	var id_token = googleUser.getAuthResponse().id_token;
	console.log("ID Token: " + id_token);
};