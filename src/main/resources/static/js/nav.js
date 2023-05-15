$(document).ready(function () {
    let allmenu = $('#allmenu');
    let m_menu = allmenu.find('.m_menu');

    // 마우스 over
    m_menu.mouseenter(function () {
        $('.s_menu_group').show();

        let menuHeight = $('#navbar').outerHeight();
        let groupHeight = $('.s_menu_group').outerHeight();
        $('.close').css({
            'top': menuHeight + 'px',
            height: groupHeight + 'px'
        });
    });

    allmenu.mouseleave(function () {
        $('.s_menu_group').hide();
        $('.close').css('height', '0');
    });

    $('.m_menu').mouseenter(function () {
        $(this).children().addClass('active');
        $(this).closest('.s_menu_group').find('.m_menu').not(this).children().removeClass('active');
    });

    $('.m_menu').mouseleave(function () {
        $(this).children().removeClass('active');
    });

    // 목록버튼
    $('.menu_btn').click(function () {
        $(this).toggleClass('active');
        $('.fulldown').slideToggle();
        if ($('.m_menu').css('display') === 'none') {
            $('.m_menu').css('display', 'block');
        } else {
            $('.m_menu').css('display', 'none');
        }
    })
});