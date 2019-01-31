var CreateUse = function () {

    var handleSelect2 = function () {
        $("#boxStudy").select2({});
    };

    var alicPerm = [];
    var patron = "";

    return {
        //main function to initiate the module
        init: function (parametros) {
            handleSelect2();
            //var pageContent = $('.page-content');
            var form1 = $('#add-alic-use');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);

            var form2 = $('#add-alic-use1');
            var error2 = $('.alert-danger', form2);
            var success2 = $('.alert-success', form2);

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
                    use: {
                        required: true
                    },

                    usedVolume: {
                        required: true
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit
                    success2.hide();
                    error2.show();
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
                    success2.show();
                    error2.hide();
                    saveUse();

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


            $('#boxStudy').change(
                function() {
                    var boxStudy = $('#boxStudy').val();
                    if (boxStudy != '' ){
                        App.blockUI();

                        $.getJSON(parametros.getAlicStudyUrl, {
                            boxStudy : boxStudy,
                            ajax : 'true'
                        }, function(data) {
                            alicPerm = data;
                            patron = alicPerm[0].estudio.studyPattern;


                        });
                        App.unblockUI();
                    }

                });



            function showInfo() {
                $('#add-alic-use1').hide();

                
                    App.blockUI();

                    var aliCode = $('#aliCode').val();
                    var study = $('#boxStudy').val();
                    $('#aliPosition').val('');
                    $('#aliVol').val('');
                    $('#rackEquip').val('');
                    $('#boxRack').val('');
                    $('#aliBox').val('');
                    $('#dData').hide();
                    $('#add-alic-use1').hide();


                    $.getJSON(parametros.getAlicUrl, {
                        aliCode: aliCode,
                        study: study,
                        ajax: 'true'
                    }, function (data) {
                        if (data != null){
                            $('#aliPosition').val(data.aliPosition);
                            $('#aliVol').val(data.aliVol);
                            $('#rackEquip').val(data.aliBox.boxRack.rackEquip.equipName);
                            $('#boxRack').val(data.aliBox.boxRack.rackName);
                            $('#aliBox').val(data.aliBox.boxName);
                            $('#aliCode1').val(aliCode);
                            $('#study1').val(study);
                            $('#add-alic-use1').show();

                        }else{
                            toastr.options = {
                                "closeButton": true,
                                "onclick": null,
                                "showDuration": "300",
                                "hideDuration": "1000",
                                "extendedTimeOut": 0,
                                "tapToDismiss": false
                            };
                            toastr["info"](parametros.msgNotFound, aliCode);
                        }


                        App.unblockUI();
                    });







            }

            $('#allVol').change(function() {
                if(this.checked) {
                    $('#usedVol').val($('#aliVol').val());
                }else{
                    $('#usedVol').val('');
                }

            });

            $('#usedVol').change(function() {
               var total =  $('#aliVol').val();
               var use = $('#usedVol').val();;
               var res = total - use;

               if (res < 0 ){
                   toastr.options = {
                       "closeButton": true,
                       "onclick": null,
                       "showDuration": "300",
                       "hideDuration": "1000",
                       "extendedTimeOut": 0,
                       "tapToDismiss": false
                   };
                   toastr["error"](parametros.infoMsg, "Error!!");

                   $('#btnSave').attr("disabled", true);

               }else{
                   $('#btnSave').attr("disabled", false);
               }


            });


            function saveUse()
            {
                App.blockUI();
                $.post( parametros.saveAlicUrl
                    , form2.serialize()
                    , function( data )
                    {

                        alic = JSON.parse(data);
                        if (alic.aliCode === undefined) {
                            toastr.options = {
                                "closeButton": true,
                                "onclick": null,
                                "showDuration": "300",
                                "hideDuration": "1000",
                                "extendedTimeOut": 0,
                                "tapToDismiss": false
                            };
                            toastr["error"](data, "Error!!");
                        }
                        else{
                            $('#aliPosition').val('');
                            $('#aliVol').val('');
                            $('#rackEquip').val('');
                            $('#boxRack').val('');
                            $('#aliBox').val('');
                            $('#aliCode1').val('');
                            $('#add-alic-use1').hide();
                            $('#aliCode').val('');
                            $('#boxStudy').val('').change();
                            $('#aliCode1').val('');
                            $('#use').val('');
                            $('#usedVol').val('');
                            $("#allVol :checkbox").attr('checked', false);

                            toastr.success(parametros.successmessage,alic.aliCode);

                        }
                        App.unblockUI();

                    }
                    , 'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        alert( "error:" + errorThrown);
                        App.unblockUI();
                    });
            }



        }
    };

}();