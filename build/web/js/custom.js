(function($) {
    "use strict";
    /* ======= Page preloader ======= */
    $(window).load(function() {
        $('.preloader').delay(1000).fadeOut(1000);
    });

    /* ======= Jquery Syntax Highlighter ======= */
    SyntaxHighlighter.all();

    /* ======= Bootstrap Tooltip ======= */
    $('[data-toggle="tooltip"]').tooltip();


    /* ======= Counter FunFacts ======= */
    function countUp() {
        var dataperc;
        $('.statistic-percent').each(function() {
            dataperc = $(this).attr('data-perc'),
                $(this).find('.percentfactor').delay(6000).countTo({
                    from: 0, // number to begin counting
                    to: dataperc,
                    speed: 1000, // ms
                    refreshInterval: 10
                });
        });
    }
    $('.statistic-percent').waypoint(function() {
        countUp();
    }, {
        offset: '95%',
        triggerOnce: true
    });


    /* ======= Page Scrolling Plugin ======= */
    $('a.page-scroll').on('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top - 60
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });


    /* ======= Jquery Parallax  ======= */
    $.stellar({
        responsive: true,
        horizontalScrolling: false,
        verticalOffset: 0,
    });



    /* ======= Testimonial Slider ======= */
    $("#owl-slider").owlCarousel({

        autoPlay: 6000, //Set AutoPlay to 3 seconds
        singleItem: true,
        items: 1,
        itemsDesktop: [1199, 3],
        itemsDesktopSmall: [979, 3],
        navigation: false
    });

    /* ======= Profile Image Upload ======= */

    $(document).on('change', '.btn-file :file', function() {
        var input = $(this),
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [label]);
    });

    $('.btn-file :file').on('fileselect', function(event, label) {

        var input = $(this).parents('.input-group').find(':text'),
            log = label;
        if (input.length) {
            input.val(log);
        }
    });

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                $('#img-upload').attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function() {
        readURL(this);
    });

})(jQuery);