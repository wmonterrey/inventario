var RequestSheet = function () {

    var handleSelect2 = function () {
        $("#boxStudy").select2({});
    };

    var details = [];


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
                        initDate: true
                    },

                    endDate: {
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
                    showInfo();

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
                        responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#reqList'), breakpointDefinition);
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

                "scrollX": true,

                "aoColumns": [
                    { "sClass": "center", "bSortable": false },
                    null,
                    null,
                    null,
                    null,
                    null


                ],
                "bProcessing": true,

                "aaSorting": [[1, 'asc']]

            });




            function showInfo() {
                App.blockUI();

                var initDate = $('#initDate').val();
                var endDate = $('#endDate').val();

                $.getJSON(parametros.getReqUrl, {
                    initDate: initDate,
                    endDate: endDate,
                    ajax: 'true'
                }, function (data) {
                    var length = data.length;

                    if (length > 0){
                        table1.fnClearTable();
                        $('#dTable').show();

                        for (var i = 0; i < length; i++) {
                            var code = data[i].idRequest;
                            var action = parametros.getDownloadExcelUrl + "?code="+code ;
                            var date = new Date(data[i].requestDate);

                            //add Data in empty table
                            table1.fnAddData(["<img src='../resources/img/details_open.png' />", data[i].idRequest, date.toLocaleString(), data[i].respRequest, data[i].authorizedBy,'<a title="Descargar Excel" href=' + action + ' class="btn btn-success btn-xs"><i class="fa fa-file-excel-o"></i></a>' ]);
                        }


                    }else{
                        toastr.options = {
                            "closeButton": true,
                            "onclick": null,
                            "showDuration": "300",
                            "hideDuration": "1000",
                            "extendedTimeOut": 0,
                            "tapToDismiss": false
                        };
                        toastr["info"](parametros.msgNotFound, "Info!");
                    }


                    App.unblockUI();
                });


            }

            /*Show request detail */
            /* Formating function for row details */
            function fnFormatDetails ()
            {

                var len =  details.length;
                console.log(details);
                console.log(len);


                var child = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
                    '<tr><td style="font-weight: bold">' + parametros.code + '</td><td style="font-weight: bold">' + parametros.aliCode + '</td><td style="font-weight: bold">' + parametros.aliVol2 + '</td><td style="font-weight: bold">' + parametros.subAliVol + '</td><td style="font-weight: bold">' + parametros.boxStudy + '</td><td style="font-weight: bold">' + parametros.alicTypeSample + '</td><td style="font-weight: bold">' + parametros.samplingDate + '</td><td style="font-weight: bold">' + parametros.purposeRequest + '</td><td style="font-weight: bold">' + parametros.username + '</td><td style="font-weight: bold">' + parametros.destination + '</td><td style="font-weight: bold">' + parametros.centerObs + '</td></tr>';
                for (var i = 0; i < len; i++) {
                    var dt;
                    var obs = '';
                    var com = details[i].comments;

                    if (details[i].samplingDate != null){
                        dt = new Date(details[i].samplingDate);
                    }

                    if (!(com === null)){
                        obs = details[i].comments;
                    }


                    child = child +
                        '<tr></tr><tr><td>' + details[i].code + '</td><td>' + details[i].aliCode + '</td><td>' + details[i].aliVol + '</td><td>' + details[i].subAliVol + '</td><td>' + details[i].study + '</td><td>' + details[i].alicTypeName + '</td><td>' + dt.toLocaleDateString() + '</td><td>' + details[i].purposeRequest + '</td><td>' + details[i].recordUser + '</td><td>' + details[i].destination + '</td><td>' + obs + '</td></tr>';
                }
                child = child + '</table>';
                return child;
            }





            function fnLoadDetail ( nTr )
            {
                var aData = table1.fnGetData( nTr );
                details = [];

                //load samples
                $.getJSON(parametros.getRequestUrl, {
                    code: aData[1],
                    ajax: 'true'
                }, function (data) {
                    var length = data.length;
                 //   console.log(data);
                    if (length > 0){

                    details = data;
                    table1.fnOpen( nTr, fnFormatDetails, 'details' );
                    }

                });



            }

         $('#reqList tbody td img').live('click', function () {
                var nTr = $(this).parents('tr')[0];
                if (table1.fnIsOpen(nTr)) {
                    /!* This row is already open - close it *!/
                    this.src = "../resources/img/details_open.png";
                    table1.fnClose(nTr);
                }
                else {
                    /!* Open this row *!/
                    this.src = "../resources/img/details_close.png";
                    table1.fnOpen(nTr, fnLoadDetail(nTr), 'details');
                }
            });



        }
    };
}();