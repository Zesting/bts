$(() => {
    $(".item1").first().click(() => {
        $("#bts").slideDown(250);
    });
    
    $(".item2").first().click(() => {
        $("#bts").slideDown(250);
    });
    
    $(".item3").first().click(() => {
        $("#bts").slideDown(250);
    });
    
    $("#btsClose").click(() => {
        $("#bts").slideUp(250);
    });

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').addClass('active');
        $('.overlay').fadeIn();
    });
    
    
    $('.overlay').on('click', function () {
        $('#sidebar').removeClass('active');
        $('.overlay').fadeOut();
    });

});