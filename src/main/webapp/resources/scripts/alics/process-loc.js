var CreateLocation = function () {

    var handleSelect2 = function () {
        $("#boxStudy").select2({});
        $("#rackEquip").select2({});
        $("#rackCode").select2({});
        $("#boxName").select2({});
    };

    var patron = "";
    var activeAliquots = [];

    return {
        //main function to initiate the module
        init: function (parametros) {
            handleSelect2();
            //var pageContent = $('.page-content');
            var form1 = $('#add-alic-form');
            var form2 = $('#save-alic-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            var error2 = $('.alert-danger', form2);
            var success2 = $('.alert-success', form2);

            toastr.options = {
                "closeButton": true,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": 0,
                "extendedTimeOut": 0,
                "tapToDismiss": false
            };

         /*   form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    rackCode: {
                        required: true
                    },
                    rackEquip: {
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

                }
            });*/

            form2.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    boxStudy: {
                        required: true
                    },
                    aliCode: {
                        required: true
                    },
                    alicTypeName: {
                        required: true
                    },
                    vol: {
                        required: true
                    },
                    condition: {
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
                    saveAlic();

                }
            });

            $('#rackEquip').change(
                function() {
                    App.blockUI();
                    $.getJSON(parametros.racksUrl, {
                        equipCode : $('#rackEquip').val(),
                        ajax : 'true'
                    }, function(data) {
                        $("#rackCode").select2('data',null);
                        $("#rackCode").empty();
                        var html='<option value=""></option>';
                        var len = data.length;
                        for ( var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].rackCode + '">'
                                + data[i].rackName + '</option>';
                        }
                        html += '</option>';
                        $('#rackCode').html(html);
                    });
                    App.unblockUI();
                });

            $('#rackCode').change(
                function() {
                    App.blockUI();
                    $.getJSON(parametros.boxesUrl, {
                        rackCode : $('#rackCode').val(),
                        ajax : 'true'
                    }, function(data) {
                        $("#boxName").select2('data',null);
                        $("#boxName").empty();
                        var html='<option value=""></option>';
                        var len = data.length;
                        for ( var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].boxCode + '">'
                                + data[i].boxName + '</option>';
                        }
                        html += '</option>';
                        $('#boxName').html(html);
                    });
                    App.unblockUI();
                });


            $('#boxName').change(
                function() {
                    App.blockUI();
                    $('#boxId').val('');
                    activeAliquots = [];

                    var boxId = $('#boxName').val();
                    $('#boxId').val(boxId);

                    $.getJSON(parametros.getActiveAliquotsUrl, {
                        boxCode : boxId,
                        ajax : 'true'
                    }, function(data) {

                        for (var i = 0; i < data.length; i++) {
                                activeAliquots.push(data[i].aliPosition) ;
                        }
                    });


                    $.getJSON(parametros.boxLocUrl, {
                        boxCode : boxId,
                        ajax : 'true'
                    }, function(data) {
                        var capac = data.boxCapacity;
                        $(".grid").empty();
                        for (var i = 1; i <= capac; i++) {

                            if(activeAliquots.indexOf(i) >= 0 ){
                                $(".grid").append("<div class='grid-item'><p class='number'></p> <button value='"+i+"' type='button' id='bttn" +i+"' class='btn btn-danger btn-lg butt'>" +i+"</button>   </div>");
                            }else{
                                $(".grid").append("<div class='grid-item'><p class='number'></p> <button value='"+i+"' type='button' id='bttn" +i+"' onclick='$(\"#pos\").val("+i+")' data-toggle=\"modal\" data-target=\"#alicModal\" class='btn btn-primary btn-lg butt'>" +i+"</button>   </div>");

                            }


                        }

                        var ancho = 100 /data.boxColumns + '%';
                        $('.grid-item').css({"width": ancho});
                        $('.butt').css({"width":"100%"});
                        $('.butt').css({"height": "100px"});
                        $('.grid-item').css({"position": "relative"});
                        $('.grid-item').css({"float": "left"});
                        $('.grid-item').css({"height": "100px"});
                        $('.grid-item').css({"border": "1px solid #333"});
                        $('.grid-item').css({"border-color": "hsla(0, 0%, 0%, 0.2)"});
                        $('.grid').isotope({
                            // options
                            itemSelector: '.grid-item',
                            layoutMode: 'fitRows'
                        });
                    });
                    App.unblockUI();
                });

            $('#boxStudy').change(
                function() {
                    App.blockUI();
                    $.getJSON(parametros.getAlicUrl, {
                        boxStudy : $('#boxStudy').val(),
                        ajax : 'true'
                    }, function(data) {
                        alicPerm = data;
                        patron = alicPerm[0].estudio.studyPattern;
                    });
                    App.unblockUI();
                });

            $('#aliCode').change(

                function() {
                    App.blockUI();
                    $('#alicTypeName').val('');
                    $('#alicTypeUse').val('');
                    $('#alicTypeTemp').val('');
                    $('#type').val('');

                    var res = false;
                    try{
                        var patt = new RegExp(patron);
                        res = patt.test($('#aliCode').val());
                    }
                    catch(e){
                        toastr["error"]( $('#aliCode').val() + ' ' + parametros.regExpInv, "Error!!");
                        $('#aliCode').focus();
                        return;
                    }
                    if(!Boolean(res)){
                        toastr["error"](parametros.aliNotPattern, "Error!!");
                        $('#aliCode').focus();
                        return;
                    }
                    var alicuota = $('#aliCode').val().substring($('#aliCode').val().lastIndexOf(".")+1,$('#aliCode').val().length);
                    var len = alicPerm.length;
                    var alicEncontrada = false; var alicName = ""; var alicUse = ""; var alicTemp = 0; var code = "";
                    for ( var i = 0; i < len; i++) {
                        if(alicuota.localeCompare(alicPerm[i].tipoAlicuota.alicTypeName)==0){
                            alicEncontrada = true; alicName = alicPerm[i].tipoAlicuota.alicTypeName; alicUse = alicPerm[i].tipoAlicuota.alicTypeUse; alicTemp=alicPerm[i].tipoAlicuota.alicTypeTemp; code=alicPerm[i].tipoAlicuota.alicTypeCode;
                            break;
                        }
                    }
                    if(Boolean(alicEncontrada)){
                        $('#alicTypeName').val(alicName);
                        $('#alicTypeUse').val(alicUse);
                        $('#alicTypeTemp').val(alicTemp);
                        $('#type').val(code);
                    }else{
                        toastr.options = {
                            "closeButton": true,
                            "onclick": null,
                            "showDuration": "300",
                            "hideDuration": "1000",
                            "timeOut": 0,
                            "extendedTimeOut": 0,
                            "tapToDismiss": false
                        };
                        toastr["error"](parametros.aliNotInList, "Error!!");
                        $('#aliCode').focus();
                    }

                    App.unblockUI();
                });


            function saveAlic()
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
                                "timeOut": 0,
                                "extendedTimeOut": 0,
                                "tapToDismiss": false
                            };
                            toastr["error"](data, "Error!!");
                        }
                        else{
                            $('#alicTypeName').val('');
                            $('#alicTypeUse').val('');
                            $('#alicTypeTemp').val('');
                            $('#type').val('');
                            $('#obs').val('');
                            $('#condition').val('');
                            $('#vol').val('');
                            $('#aliCode').val('');
                            $('#pos').val('');
                            $('#boxId').val('');
                            $("#boxStudy").val('').change();

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