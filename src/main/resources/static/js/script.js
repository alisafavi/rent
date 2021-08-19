$(document).ready(function() {

    $(".add-more").click(function(){
    var html = $(".copy").html();
    $(".first").before(html);
    });

    $("body").on("click",".remove",function(){
    $(this).parents(".form-group").remove();
    });
    
});
