var firstEvent = function(){
    $("#fEvent").bind("click", function(){
        clickEvent(this, $(this).index());
    });
            $("#fEvent").trigger("click");
}
        
var listClick = function(li){
    $(li).off().on("click", function(){
        clickEvent(this, $(this).index());
    });
}
        
var clickEvent = function(li, i) {
    var target;
    if(i==0){
        target = "#my_account";
    }else if(i==1) {
        target = "#my_profile";
    }else if(i==2) {
        target = "#my_delete";
    }
    cssMain();
    $(target).show();
    $(li).css({
        "background-color":"#dbdbdb",
        "color":"#2e3136",
        "border-radius":"5px"
    });
}
        
var cssMain = function() {
    $(".div_mypage_data").hide();
    $(".div_mypage_menubar ul li").css({
        "background-color":"#2e3136",
        "color":"#dbdbdb"
    });
}