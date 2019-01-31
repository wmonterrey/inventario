var CreateExit = function () {

    var handleSelect2 = function () {
        $("#boxStudy").select2({});
        $("#transportation").select2({});
    };

    var alicPerm = [];
    var patron = "";
    var tod1 = new Date();

    return {
        //main function to initiate the module
        init: function (parametros) {
            handleSelect2();
            //var pageContent = $('.page-content');
            var form1 = $('#search-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);

            var form2 = $('#exit_form1');
            var error2 = $('.alert-danger', form2);
            var success2 = $('.alert-success', form2);

            var form3 = $('#upload-form');
            var error3 = $('.alert-danger', form3);
            var success3 = $('.alert-success', form3);

            var table1 = $('#lista_alic').DataTable({
                "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
                "t" +
                "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_dt_basic) {
                        responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#lista_alic'), breakpointDefinition);
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
                }


            });

            var table2 = $('#lista_alic2').DataTable({
                "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
                "t" +
                "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_dt_basic) {
                        responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#lista_alic2'), breakpointDefinition);
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
                }

            });

            toastr.options = {
                "closeButton": true,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "400",
                "extendedTimeOut": 0,
                "tapToDismiss": false
            };

            form2.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    enic: {
                        required: true
                    },

                    transportation: {
                        required: true
                    },

                    containerNum: {
                        required: true
                    },

                    carrier: {
                        required: true
                    },

                    sendDate: {
                        required: true
                    },

                    destination: {
                        required: true
                    },

                    searchManager: {
                        required: true
                    },

                    packingManager: {
                        required: true
                    },

                    boxNum: {
                        required: true
                    },

                    requestBy: {
                        required: true
                    },

                    approveBy: {
                        required: true
                    },

                    purpose: {
                        required: true
                    }


                },

                invalidHandler: function (event, validator) { //display error alert on form submit
                    success2.hide();
                    error2.show();
                    App.scrollTo(error2, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success2.show();
                    error2.hide();
                    save1();

                }
            });


            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    aliCode: {
                        required: true
                    },

                    boxStudy: {
                        required: true
                    }

                },

                invalidHandler: function (event, validator) { //display error alert on form submit
                    success1.hide();
                    error1.show();
                    App.scrollTo(error1, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success1.show();
                    error1.hide();
                    searchAlic();

                }
            });


            $('#boxStudy').change(
                function () {
                    var boxStudy = $('#boxStudy').val();
                    if (boxStudy != '') {
                        App.blockUI();

                        $.getJSON(parametros.getAlicStudyUrl, {
                            boxStudy: boxStudy,
                            ajax: 'true'
                        }, function (data) {
                            alicPerm = data;
                            patron = alicPerm[0].estudio.studyPattern;


                        });
                        App.unblockUI();
                    }

                    var rows = $('#rows').val();
                    var columns = $('#columns').val();

                    if (rows != '' && columns != '' && boxStudy != ''){
                        $('#dImportFile').show();
                    }else{
                        $('#dImportFile').hide();
                    }

                });

            $('#aliCode').change(
                function () {


                    $('#btnSearch').removeAttr("disabled");


                });


            function searchAlic() {
                $("#sendDate").val(tod1.toISOString().substr(0, 10));
                var aliCode = $('#aliCode').val();
                var study = $('#boxStudy').val();
                $.getJSON(parametros.getAlicUrl, {
                    aliCode: aliCode,
                    study: study,
                    ajax: 'true'
                }, function (data) {
                    if (data != null) {
                        App.blockUI();
                        var date1 = null;
                        var res = null;
                        var len;

                        //check repeated records
                        len = table1.fnGetData().length;

                        if (len > 0) {
                            var firstCellArray = [];
                            var secondCellArray = [];
                            $.each(table1.fnGetData(), function (i, row) {
                                firstCellArray.push(row[0]);
                                secondCellArray.push(row[1]);
                            });

                            if (firstCellArray.indexOf(aliCode) >= 0 && secondCellArray.indexOf(study) >= 0) {
                                toastr.options = {
                                    "closeButton": true,
                                    "onclick": null,
                                    "showDuration": "300",
                                    "hideDuration": "1000",
                                    "extendedTimeOut": 0,
                                    "tapToDismiss": false
                                };
                                toastr["info"](parametros.msgRepeated, "Info!");

                            } else {

                                if (data.recordDate != null) {
                                    var date = new Date(data.recordDate);
                                    date1 = date.toLocaleString();
                                }

                                if (data.aliRes != null) {
                                    res = data.aliRes;
                                }

                                table1.fnAddData([data.aliId.aliCode, data.aliId.aliStudy, data.aliBox.boxRack.rackEquip.equipName, data.aliBox.boxRack.rackName, data.aliBox.boxName, data.aliPosition, res, data.aliVol, date1, data.recordUser, "<button type='button' value='" + data.aliCode + "' class='btn btn-danger btn-xs butt fa fa-times'></button>"]);
                                toastr.success(parametros.successmessage);

                            }

                        } else {
                            if (data.recordDate != null) {
                                var date = new Date(data.recordDate);
                                date1 = date.toLocaleString();
                            }

                            if (data.aliRes != null) {
                                res = data.aliRes;
                            }

                            table1.fnAddData([data.aliId.aliCode, data.aliId.aliStudy, data.aliBox.boxRack.rackEquip.equipName, data.aliBox.boxRack.rackName, data.aliBox.boxName, data.aliPosition, res, data.aliVol, date1, data.recordUser, "<button type='button' value='" + data.aliCode + "' class='btn btn-danger btn-xs butt fa fa-times'></button>"]);
                            toastr.success(parametros.successmessage);

                        }

                        $('#dTable').show();

                    } else {
                        $('#dTable').show();
                        var length;
                        //check repeated records
                        length = table2.fnGetData().length;

                        if (length > 0) {

                            var dataTable2 = [];
                            $.each(table2.fnGetData(), function (i, row) {
                                dataTable2.push(row[0]);
                            });

                            if (dataTable2.indexOf(aliCode) === -1) {
                                $("#mi-modal").modal('show');
                            }else{
                                toastr.options = {
                                    "closeButton": true,
                                    "onclick": null,
                                    "showDuration": "300",
                                    "hideDuration": "1000",
                                    "extendedTimeOut": 0,
                                    "tapToDismiss": false
                                };
                                toastr["info"](parametros.msgRepeated, "Info!");
                            }

                        }else{
                                $("#mi-modal").modal('show');

                        }
                    }
                    App.unblockUI();

                });
            }

            $("#modal-btn-si").on("click", function(){
                var aliCode = $('#aliCode').val();
                $("#mi-modal").modal('hide');
                table2.fnAddData([aliCode, "<button type='button' value='" + aliCode + "' class='btn btn-danger btn-xs butt fa fa-times'></button>"]);
                toastr.success(parametros.successmessage);

            });



            $("#modal-btn-no").on("click", function(){
                $("#mi-modal").modal('hide');
            });


            function save1() {
                App.blockUI();

                var codes = [];
                $.each(table1.fnGetData(), function (i, row) {
                    codes.push(row[0]);
                });

                var codes2 = [];
                $.each(table2.fnGetData(), function (i, row) {
                    codes2.push(row[0]);
                });

                var studies = [];
                $.each(table1.fnGetData(), function (i, row) {
                    studies.push(row[1]);
                });

                var studies2 = [];
                $.each(table2.fnGetData(), function (i, row) {
                    studies2.push(row[1]);
                });

                var obj = {};
                var dt = $("#sendDate").val();
                var dtF = new Date(dt);

                obj['codes'] = codes.toString();
                obj['codes2'] = codes2.toString();
                obj['studies'] = studies.toString();
                obj['studies2'] = studies2.toString();
                obj['mensaje'] = '';
                obj['enic'] = $("#enic").val();
                obj['transportation'] = $('#transportation').find('option:selected').val();
                obj['containerNum'] = $("#containerNum").val();
                obj['carrier'] = $("#carrier").val();
                obj['sendDate'] = dtF.toLocaleString();
                obj['destination'] = $("#destination").val();
                obj['searchManager'] = $("#searchManager").val();
                obj['packingManager'] = $("#packingManager").val();
                obj['boxNum'] = $("#boxNum").val();
                obj['requestBy'] = $("#requestBy").val();
                obj['approveBy'] = $("#approveBy").val();
                obj['purpose'] = $("#purpose").val();
                obj['study'] = $("#boxStudy").val();

                $.ajax(
                    {
                        url: parametros.saveAlicUrl,
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
                                table2.fnClearTable();

                                $("#enic").val('');
                                $('#transportation').val('').change();
                                $("#containerNum").val('');
                                $("#carrier").val('');
                                $("#destination").val('');
                                $("#searchManager").val('');
                                $("#packingManager").val('');
                                $("#boxNum").val('');
                                $("#requestBy").val('');
                                $("#approveBy").val('');
                                $("#purpose").val('');
                                $('#boxStudy').val('').change();
                                $("#aliCode").val('');

                                $('#dTable').hide();

                            }
                            App.unblockUI();
                        },
                        error: function (jqXHR) {
                            App.unblockUI();
                        }
                    });

            }


            $('#lista_alic').on("click", "button", function () {
               // $(this).closest('tr').remove();
                var nRow = $(this).parents('tr')[0];
                table1.fnDeleteRow( nRow );
                return false;

            });

            $('#lista_alic2').on("click", "button", function () {
                var nRow = $(this).parents('tr')[0];
                table2.fnDeleteRow( nRow );
                return false;

            });

            $('#rows').change(
                function () {
                    var rows = $('#rows').val();
                    var columns = $('#columns').val();
                    var boxStudy = $('#boxStudy').val();

                    if (rows != '' && columns != '' && boxStudy != '' ){
                        $('#dImportFile').show();
                    }else{
                        $('#dImportFile').hide();
                    }

                });

            $('#columns').change(
                function () {
                    var rows = $('#rows').val();
                    var columns = $('#columns').val();
                    var boxStudy = $('#boxStudy').val();

                    if (rows != '' && columns != '' && boxStudy != ''){
                        $('#dImportFile').show();
                    }else{
                        $('#dImportFile').hide();
                    }


                });

             $('#file1').fileupload({
                 dataType: 'json',
                 autoUpload: true,
                 maxFileSize: 999000,
                 // Enable image resizing, except for Android and Opera,
                 // which actually support image resizing, but fail to
                 // send Blob objects via XHR requests:
                 disableImageResize: /Android(?!.*Chrome)|Opera/
                     .test(window.navigator.userAgent),
                 previewMaxWidth: 100,
                 previewMaxHeight: 100,
                 previewCrop: true,


                   add: function (e, data) {
                       form3.serialize(),
                        data.submit();
                   },
                 done: function (e, data) {
                     var length = data.result.length;
                     var study = $('#boxStudy').val();
                     $("#sendDate").val(tod1.toISOString().substr(0, 10));
                     if (length > 0){
                         //check repeated records
                         var len = table2.fnGetData().length;
                         var dt1 = null;
                         var cont = 0;
                         $('#dTable').show();

                         //clean inputs
                         $('#rows').val('');
                         $('#columns').val('');
                         $('#boxStudy').val('').change();

                         for (var i = 0; i < length; i++) {

                             if (data.result[i].recordDate != null) {
                                 var dt = new Date(data.result[i].recordDate);
                                 dt1 = dt.toLocaleString();
                             }


                             if (len > 0) {
                                 var firstCellArray = [];
                                 $.each(table2.fnGetData(), function (i, row) {
                                     firstCellArray.push(row[0]);
                                 });

                                 if (firstCellArray.indexOf(data.result[i].aliCode) === -1) {
                                     table2.fnAddData([data.result[i].aliCode, study, "<button type='button' value='" + data.result.aliCode + "' class='btn btn-danger btn-xs butt fa fa-times'></button>"]);
                                     cont++;
                                 }
                             } else{
                                 //add Data in empty table
                                 table2.fnAddData([data.result[i].aliCode, study, "<button type='button' value='" + data.result.aliCode + "' class='btn btn-danger btn-xs butt fa fa-times'></button>"]);
                                 cont++;
                             }
                         }

                         if (length === cont){
                             toastr.success(cont + " " + parametros.addedAlic);
                         }else{

                             if (cont === 0){
                                 toastr.error(cont + " " + parametros.addedAlic + " " + parametros.msgRepeated2);
                             }else{

                                 var res = length - cont;
                                 toastr.info(cont + " " + parametros.addedAlic + " "+ res + " " + parametros.msgRepeated2);
                             }
                         }



                     }

                 },

             }).prop('disabled', !$.support.fileInput)
                 .parent().addClass($.support.fileInput ? undefined : 'disabled');

        }
    };

}();