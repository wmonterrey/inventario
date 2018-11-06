var SampleRequest = function () {

    var handleSelect2 = function () {
        $("#boxStudy").select2({});
    };


    var handleDatePickers = function (lenguaje) {

        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                autoclose: true,
                language: lenguaje

            });
        }
    };



    return {
        //main function to initiate the module
        init: function (parametros) {
            handleSelect2();
            //var pageContent = $('.page-content');
            var form1 = $('#add-alic-use');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            handleDatePickers(parametros.lenguaje);
            var today = new Date();
            $('#requestDate').val(today.toLocaleString().substr(0, 10));

            toastr.options = {
                "closeButton": true,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "400",
                "extendedTimeOut": 0,
                "tapToDismiss": false
            };


            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    aliCode: {
                        required: true
                    },

                    code: {
                        required: true
                    },

                    boxStudy:
                        {
                            required: true
                        },

                    aliType:
                        {
                            required: true
                        },

                    samplingDate:
                        {
                            required: true
                        },

                    purpose:
                        {
                            required: true
                        },

                    destination:
                        {
                            required: true
                        },

                    requestDate:
                        {
                            required: true
                        },

                    respRequest:
                        {
                            required: true
                        },
                    approvedBy:
                        {
                            required: true
                        }

                },

                invalidHandler: function (event, validator) { //display error alert on form submit
                    success1.hide();
                    error1.show();
                    App.scrollTo(error1, -200);
                }
                ,

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                }
                ,

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                }
                ,

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                }
                ,

                submitHandler: function (form) {
                    success1.show();
                    error1.hide();
                    addInfo();

                }
            })
            ;

            var table1 = $('#reqList').DataTable({
                "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
                "t" +
                "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_dt_basic) {
                        responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#list'), breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    responsiveHelper_dt_basic.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    responsiveHelper_dt_basic.respond();
                },

                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                },

                "scrollX": true


            });


            function addInfo() {
                App.blockUI();
                var code = $('#code').val();
                var aliCode = $('#aliCode').val();
                var aliVol = $('#aliVol').val();
                var suAliVol = $('#suAliVol').val();
                var boxStudy = $('#boxStudy').val();
                var aliType = $('#aliType').val();
                var samplingDate = $('#samplingDate').val();
                var purpose = $('#purpose').val();
                var destination = $('#destination').val();
                var obs = $('#obs').val();

                table1.fnAddData([code, aliCode, aliVol, suAliVol, boxStudy, aliType, samplingDate, purpose, destination, obs, "<button type='button' class='btn btn-danger btn-xs butt fa fa-times'></button>"]);
                $('#dTable').show();
                $('#bTable').show();
                $('#code').val('');
                $('#aliCode').val('');
                $('#aliVol').val('');
                $('#suAliVol').val('');
                $('#boxStudy').val('').change();
                $('#aliType').val('');
                $('#samplingDate').val('');
                $('#purpose').val('');
                $('#destination').val('');
                $('#obs').val('');
                App.unblockUI();

            }

            $('#reqList').on("click", "button", function () {
                // $(this).closest('tr').remove();
                var nRow = $(this).parents('tr')[0];
                table1.fnDeleteRow( nRow );
                return false;

            });

            $("#btnSave").click(function () {
                    App.blockUI();

                    var codes = [];
                    $.each(table1.fnGetData(), function (i, row) {
                        codes.push(row);
                    });

                    var obj = {};
                    var dt = $("#requestDate").val();

                    obj['codes'] = codes;
                    obj['mensaje'] = '';
                    obj['requestDate'] = dt;
                    obj['approvedBy'] = $('#approvedBy').val();
                    obj['respRequest'] = $('#respRequest').val();
                    $.ajax(
                        {
                            url: parametros.saveRequestUrl,
                            type: 'POST',
                            dataType: 'json',
                            data: JSON.stringify(obj),
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            success: function (data) {
                                if (data.mensaje.length > 0) {
                                    toastr["error"](data.mensaje, "Error!!");
                                } else {
                                    toastr.success(parametros.successmessage);
                                    table1.fnClearTable();

                                    $('#requestDate').val('').change();
                                    $('#approvedBy').val('');
                                    $('#respRequest').val('');
                                    $('#code').val('');
                                    $('#aliCode').val('');
                                    $('#aliVol').val('');
                                    $('#suAliVol').val('');
                                    $('#boxStudy').val('').change();
                                    $('#aliType').val('');
                                    $('#samplingDate').val('');
                                    $('#purpose').val('');
                                    $('#destination').val('');
                                    $('#obs').val('');
                                    $('#dTable').hide();
                                    $('#bTable').hide();

                                }
                                App.unblockUI();
                            },
                            error: function (jqXHR) {
                                App.unblockUI();
                            }
                        });

            });


        }
    };

}();