var SearchAlic = function () {
    return {
        //main function to initiate the module
        init: function (parametros) {
            //var pageContent = $('.page-content');
            var form1 = $('#add-alic-use');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);


                $('#example').DataTable( {
                    "scrollX": true
                } );


            var table1 = $('#list').DataTable({
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

                    boxStudy:{
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
                    showInfo();

                }
            });



            function showInfo() {
                App.blockUI();

                var aliCode = $('#aliCode').val();

                $.getJSON(parametros.getAlicUrl, {
                    aliCode: aliCode,
                    ajax: 'true'
                }, function (data) {
                        var length = data.length;

                        if (length > 0){
                            table1.fnClearTable();
                            $('#dList').show();

                            for (var i = 0; i < length; i++) {
                                //add Data in empty table
                                table1.fnAddData([data[i].codAlic, data[i].posBox, data[i].codFreezer, data[i].codRack, data[i].codBox, data[i].posNeg, data[i].regDate, data[i].codUser, data[i].aliVol, data[i].weight, data[i].type, data[i].condition, data[i].separated, data[i].numDesc, data[i].destination, data[i].user, data[i].outputDate, data[i].usedVol, data[i].use, data[i].userUse, data[i].useDate, data[i].table]);
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



        }
    };

}();