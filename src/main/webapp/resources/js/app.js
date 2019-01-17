if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}

var $app = {
    init: function() {
        //$app.datePicker.init();
        $app.datatable.init();
        $app.validation.init();
        $app.navigation.init();
        $app.file.init();
        $app.focus.init();
        $app.events.init();
    },
    datePicker: {
        init: function() {
            $app.datePicker.setup();
            $app.datePicker.turnOffAutoComplete();
        },
        setup: function() {
            $('.date-picker').datepicker({
                format: 'dd/mm/yyyy'
            });
        },
        turnOffAutoComplete: function() {
            $('input.date-picker').attr('autocomplete', 'off');
        }
    },
    datatable: {
        init: function() {
            $app.datatable.setup();
            $app.datatable.setupReportTable();
        },
        setup: function() {
            $("#data-table").DataTable({
                dom: 
                    "<'row'<'col-sm-12 col-md-6 m-b-0-i'l><'col-sm-12 col-md-6 m-b-0-i'f>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-12 col-md-5 m-b-0-i'i><'col-sm-12 col-md-7 m-b-0-i'p>>",
                ordering: false,
                searching: true,
                lengthChange: true,
                pageLength: 5,
                bInfo : true,
                language: {
                    paginate: {
                        previous: "&laquo",
                        next: "&raquo",
                    }
                },
                drawCallback: function(settings) {
                    var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
                    pagination.toggle(this.api().page.info().pages > 1);
                    $('div.dataTables_filter input').focus();
                }
            });
        },
        setupReportTable: function() {
            $("#report-data-table").DataTable({
                dom: 
                    "<'row'<'col-sm-12 col-md-6 m-b-0-i'l><'col-sm-12 col-md-6 m-b-0-i'f>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-12 col-md-5 m-b-0-i'i><'col-sm-12 col-md-7 m-b-0-i'p>>",
                ordering: false,
                searching: false,
                lengthChange: false,
                pageLength: 5,
                bInfo : true,
                scrollY: '250px',
                language: {
                    paginate: {
                        previous: "&laquo",
                        next: "&raquo",
                    }
                },
                drawCallback: function(settings) {
                    var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
                    pagination.toggle(this.api().page.info().pages > 1);
                }
            });
        }
    },
    validation: {
        init: function() {
            $app.validation.setup();
        },
        setup: function() {
            $("#validation-form").validate({
                onfocusout: false,
                invalidHandler: function(form, validator) {
                    var errors = validator.numberOfInvalids();
                    if (errors) {
                        validator.errorList[0].element.focus();
                    }
                },
                highlight: function (input) {
                    $(input).parents('.form-line').addClass('error');
                },
                unhighlight: function (input) {
                    $(input).parents('.form-line').removeClass('error');
                },
                errorPlacement: function (error, element) {
                    $(element).parents('.form-group').append(error);
                }
            });
        }
    },
    navigation: {
        init: function() {
            $app.navigation.setNavingation();
            $app.navigation.scrollActiveItemWhenPageLoad();
        },
        setNavingation: function() {
            var path = window.location.pathname; // Returns path only
            path = path.replace(/\/$/, "");
            path = decodeURIComponent(path);

            $('ul#nav a').filter(function () {
               return $(this).attr('href') === path;
            }).parents('li').addClass('active').children('a').addClass('toggled').click();
        },
        scrollActiveItemWhenPageLoad: function() {
            if ($.AdminBSB.options.leftSideBar.scrollActiveItemWhenPageLoad) {
                var $el = $('.list');
                var item = $('.menu .list li.active')[0];
                if (item) {
                    var activeItemOffsetTop = item.offsetTop;
                    if (activeItemOffsetTop > 150) $el.slimscroll({ scrollTo: activeItemOffsetTop + 'px' });
                }
            }
        }
    },
    file: {
        init: function() {
            $app.file.onChangeAvatar();
        },
        onChangeAvatar: function() {
            $("#file").change(function() {
                $app.file.readURL(this);
            });
        },
        readURL: function(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('.avatar').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    },
    focus: {
        init: function() {
            $app.focus.onTabLastItem();
        },
        onTabLastItem: function() {
            $("#btn-cancel").on('keydown', function(e) {
                if (e.keyCode == 9) {
                    $("[autofocus='autofocus']").focus();
                    e.preventDefault();
                }
            });
        }
    },
    events: {
        init: function() {
            $app.events.onChangeTypeReport();
            $app.events.onResetForm();
            $app.events.hideFilterChart();
        },
        hideFilterChart: function () {
            $(".filter-chart").hide();
        },
        onChangeTypeReport: function() {
            $("input[name='typeReport']").change(function () {
                var displayType = $(this).val();
                if (displayType =="report") {
                    $("#report").show();
                    $("#chart").hide();
                    $(".filter-report").show();
                    $(".filter-chart").hide();
                }
                else if(displayType = "chart") {
                    $("#report").hide();
                    $("#chart").show();
                    $(".filter-report").hide();
                    $(".filter-chart").show();
                }
                else {
                    $("#report").show();
                    $("#chart").hide();
                    $(".filter-report").show();
                    $(".filter-chart").hide();
                }
            });
        },
        onResetForm: function() {
            $(".reset").click(function() {
                $(':input','.filter-form')
                .not(':button, :submit, :reset, :hidden')
                .val('')
                .prop('selected', false);
            });
        }
    }
}

$(function () {
    $app.init();
});