var loginEvent = function(){
	$("#h_login").on("click", function(){
		location.href = "/login-main";
	});
}

var logoutEvent = function(){
	$("#h_logout").off().on("click", function(){
		if (confirm("本当にログアウトしますか？") == true) {
			location.href = "/logout";
		}
	});
}