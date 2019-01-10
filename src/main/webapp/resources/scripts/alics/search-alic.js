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
            
            var table2 = $('#list2').DataTable({
                "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
                "t" +
                "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_dt_basic) {
                        responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#list2'), breakpointDefinition);
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
            
            
            var table3 = $('#list3').DataTable({
                "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>" +
                "t" +
                "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                "autoWidth": true,
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_dt_basic) {
                        responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#list3'), breakpointDefinition);
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
                        var length = data.alics.length;
                        table1.fnClearTable();
                        if (length > 0){
                            
                            $('#dList').show();

                            for (var i = 0; i < length; i++) {
                                //add Data in empty table
                                table1.fnAddData([data.alics[i].aliId.aliCode, data.alics[i].aliBox.boxStudy.studyName, data.alics[i].aliBox.boxRack.rackEquip.equipRoom.roomCenter.centerName, data.alics[i].aliBox.boxName,
                                	data.alics[i].aliPosition,data.alics[i].aliBox.boxRack.rackName,
                                	data.alics[i].aliBox.boxPosition,data.alics[i].aliBox.boxRack.rackEquip.equipName,data.alics[i].aliBox.boxRack.rackPosition,data.alics[i].aliVol]);
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
                        
                        var length2 = data.alicsused.length;
                        table2.fnClearTable();
                        if (length2 > 0){
                            
                            $('#eList').show();

                            for (var i = 0; i < length2; i++) {
                                //add Data in empty table
                                table2.fnAddData([data.alicsused[i].aliCode, data.alicsused[i].aliBox.boxStudy.studyName, data.alicsused[i].aliBox.boxRack.rackEquip.equipRoom.roomCenter.centerName, data.alicsused[i].aliBox.boxName,
                                	data.alicsused[i].aliPosition,data.alicsused[i].aliBox.boxRack.rackName,
                                	data.alicsused[i].aliBox.boxPosition,data.alicsused[i].aliBox.boxRack.rackEquip.equipName,data.alicsused[i].aliBox.boxRack.rackPosition,data.alicsused[i].aliVol]);
                            }

                        }
                        
                        
                        var length3 = data.alicssent.length;
                        table3.fnClearTable();
                        if (length3 > 0){
                            
                            $('#fList').show();

                            for (var i = 0; i < length3; i++) {
                                //add Data in empty table
                                table3.fnAddData([data.alicssent[i].aliCode, data.alicssent[i].eNic, data.alicssent[i].aliDestination, data.alicssent[i].aliBox,
                                	data.alicssent[i].aliPosition,data.alicssent[i].aliRack,
                                	data.alicssent[i].aliRack,data.alicssent[i].aliEquip,data.alicssent[i].aliEquip,data.alicssent[i].aliVol]);
                            }

                        }


                    App.unblockUI();
                });


            }



        }
    };

}();