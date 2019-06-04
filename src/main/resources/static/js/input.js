var codeBtnEvent = function(){
    var flag = true;
    $("#codeBtn").on("click",function(){
        if(flag){
            $("#inputCode").show();
            $(this).text("取り消し");
        }else{
            $("#inputCode").hide();
            $("#inputCode").val("");
            $(this).text("ソースコードを添付");
        }
        flag = !flag;
    });
}