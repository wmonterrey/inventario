var CreateLocation = function () {

    var handleSelect2 = function () {
        $("#rackEquip").select2({});
        $("#rackCode").select2({});
        $("#boxName").select2({});
        $('#aliCond').select2({});
    };


    var patron = "";
    var alicPermStu = [];
    var alicPermBox = [];
    var formato = "";
    var volumen = 0;
    var volMin = 0;
    var volMax = 0;

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
                "hideDuration": "400",
                "timeOut": "6000",
                "extendedTimeOut": 0,
                "tapToDismiss": false
            };
            
            //Limpiar formulario de seleccion de caja
            function cleanForm1(){
            	$("#rackCode").select2('data',null);
                $("#rackCode").empty();
				$("#boxName").select2('data',null);
				$("#boxName").empty();
				$('#boxStudy').val("");
				$(".grid").empty();
        	}
            
            //Limpiar formulario de ingreso de alicuotas
            function cleanForm2(){
            	$('#alicTypeName').val("");
                $('#alicTypeUse').val("");
                $('#alicTypeTemp').val("");
                $('#aliObs').val("");
                $('#aliVol').val("");
        	}

            form1.validate({
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
                    },

                    boxName: {
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
            });

            form2.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    aliCode: {
                        required: true
                    },
                    alicTypeName: {
                        required: true
                    },
                    alicTypeUse: {
                        required: true
                    },
                    alicTypeTemp: {
                        required: true
                    },
                    alicCond: {
                        required: true
                    },
                    aliVol: {
                        required: true,
                        min: function ()  { return volMin},
                        max: function ()  { return volMax}
                    },
                    aliCond: {
                        required: true
                    },
                    aliObs: {
                        required: false,
                        maxlength: 500
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
                    cleanForm1();
                    $.getJSON(parametros.racksUrl, {
                        equipCode : $('#rackEquip').val(),
                        ajax : 'true'
                    }, function(data) {
                        var html='<option value=""></option>';
                        var len = data.length;
                        for ( var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].rackCode + '">'
                                + data[i].rackName + '</option>';
                        }
                        $('#rackCode').html(html);
                        $('#rackCode').focus();
            			$('#rackCode').select2('open');
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
                                + data[i].boxName + " - "  + data[i].boxStudy.studyName + " - "  + data[i].boxAlicUse + " - "  + data[i].boxAlicType + " - "  + data[i].boxResult +  '</option>';
                        }
                        $('#boxName').html(html);
                        $('#boxName').focus();
            			$('#boxName').select2('open');
                    });
                    App.unblockUI();
                });
            
            $('#boxName').change(
            		function() {
            			App.blockUI();
            			$('#aliCode').val("");
            			cleanForm2();
            			$.getJSON(parametros.getBoxUrl, {
            				boxCode : $('#boxName').val(),
            				ajax : 'true'
            			}, function(data) {
            				var item;
            				$('#boxResults').val(data.box.boxCode);
    						$('#boxStudy').val(data.box.boxStudy.studyCode).change();
    						alicPermBox = data.box.boxAlicType.split(",");
    						$(".grid").empty();
    	                    for (var i = 1; i <= data.box.boxCapacity; i++) {
    	                    	item = "<div class='grid-item vacio'><p class='number'></p> <button value='" + i + "' type='button' id='bttn" + i + "' onclick='$(\"#aliPosition\").val(" + i + ")' data-toggle='modal' data-target='#alicModal' class='btn btn-primary btn-lg butt'>" + i + "</button>";
    	                    	for(var j = 0; j < data.aliquots.length; j++){
    	                    		if(data.aliquots[j].aliPosition == i){
    	                    			item = "<div class='grid-item lleno'><p class='number'>"+i+"</p>";
    	                    			item += "<p class='symbol'>"+ data.aliquots[j].aliCode +"</p>";
    	                    			item += "<p class='name'>"+ data.aliquots[j].aliVol +"</p>";
    	                    			item += "<p class='weight'>"+ data.aliquots[j].aliCond +"</p>";
    	                    			item += "<p class='resultado'>"+ data.aliquots[j].aliRes +"</p>";
    	                    		}
    	                    	}
    	                    	item += "</div>";
    	                    	$(".grid").append(item);
    	                    }
    	                    var ancho = 100 / data.box.boxColumns + '%';
    	                    $('.grid-item').css({"width": ancho});
    	                    $('.butt').css({"width": "100%"});
    	                    $('.butt').css({"height": "100%"});
    	                    $('.grid-item').css({"position": "relative"});
    	                    $('.grid-item').css({"float": "left"});
    	                    $('.grid-item').css({"height": "100px"});
    	                    $('.grid-item').css({"border": "1px solid #333"});
    	                    $('.grid-item').css({"border-color": "hsla(0, 0%, 0%, 0.2)"});
    	                    $('.grid-item.vacio').css({"background": "#428bca"});
    	                    $('.grid-item.lleno').css({"background": "#d9534f"});
    	                    $('.grid').isotope({
    	                        // options
    	                        itemSelector: '.grid-item',
    	                        layoutMode: 'fitRows'
    	                    });
    	                    $("#legend").show();
    	                    App.unblockUI();
            			});
                    });
            
            $('#boxStudy').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.getAlicUrl, {
            				boxStudy : $('#boxStudy').val(),
            				ajax : 'true'
            			}, function(data) {
            				if(data.length<=0){
            					toastr["error"]( $('#boxStudy').text() + ' ' + parametros.noAlicStudy, "Error!!");
            					return;
            				}else{
            					alicPermStu = data;
            					patron = alicPermStu[0].estudio.studyPattern;
            					formato = alicPermStu[0].estudio.studyFormat;
            				}
            			});
            			App.unblockUI();
                    });
            
            $('#aliCode').change(
            		function() {
            			App.blockUI();
            			cleanForm2();
            			var res = false;
            			var resAlic = false;
            			var alicuotaIngresada = null;
            			try{
            				var patt = new RegExp(patron,"i");
            				res = patt.test($('#aliCode').val());
            			}
	            		catch(e){
	            			toastr["error"]( $('#aliCode').val() + ' ' + parametros.regExpInv, "Error!!");
	            			$('#aliCode').focus();
	            			return;
	            		}
            			if(!Boolean(res)){
      	    				toastr["error"]($('#aliCode').val() + ' ' +parametros.aliNotPattern, "Error!!");
      	    				$('#aliCode').focus();
      	    				return;
            			}
            			
            			try{
            				var pattAlic = new RegExp(formato,"i");
            				resAlic = pattAlic.test($('#aliCode').val());
            				if(!Boolean(resAlic)){
          	    				toastr["error"]($('#aliCode').val() + ' ' +parametros.aliNotPattern2, "Error!!");
          	    				$('#aliCode').focus();
          	    				return;
                			}
            				else{
            					var alicuotaIngresada = $('#aliCode').val().match(pattAlic);
            				}
            				
            			}
	            		catch(e){
	            			toastr["error"]( $('#aliCode').val() + ' ' + parametros.regExpInv2, "Error!!");
	            			$('#aliCode').focus();
	            			return;
	            		}
            				
            			
            			var len = alicPermStu.length;
            			var len2 = alicPermBox.length;
            			
            			var alicEncontrada = false; var alicName = ""; var alicUse = ""; var alicTemp = 0; var alicEncontradaBox = false;

        				for ( var i = 0; i < len; i++) {
        					if(alicuotaIngresada[0].localeCompare(alicPermStu[i].tipoAlicuota.alicTypeName)==0){
        						alicEncontrada = true;
        						for(var j = 0; j < len2; j++){
        							if(alicuotaIngresada[0].localeCompare(alicPermBox[j])==0){
        								alicEncontradaBox = true; 
		        						alicName = alicPermStu[i].tipoAlicuota.alicTypeName; 
		        						alicUse = alicPermStu[i].tipoAlicuota.alicTypeUse; 
		        						alicTemp=alicPermStu[i].tipoAlicuota.alicTypeTemp; 
		        						volumen=alicPermStu[i].tipoAlicuota.alicTypeVol;
		        						volMin=alicPermStu[i].tipoAlicuota.alicTypeVolMin;
		        						volMax=alicPermStu[i].tipoAlicuota.alicTypeVolMax;
		        						break;
        							}
        						}
        					}        					
        				}
            			
        				if(Boolean(alicEncontrada)&&Boolean(alicEncontradaBox)){
        					$('#alicTypeName').val(alicName);
        					$('#alicTypeUse').val(alicUse);
        					$('#alicTypeTemp').val(alicTemp);
        					$('#alicTypeVolMin').val(volMin);
        					$('#alicTypeVolMax').val(volMax);
        					$('#aliVol').val(volumen);
        					$('#aliVol').focus();
        				}
        				else if(!Boolean(alicEncontrada)){
        					cleanForm2();
    	    				toastr["error"](parametros.aliNotInList, "Error!!");
    	    				$('#aliCode').focus();
        				}
        				else if(!Boolean(alicEncontradaBox)){
        					cleanForm2();
    	    				toastr["error"](parametros.aliNotInListBox, "Error!!");
    	    				$('#aliCode').focus();
        				}
        				App.unblockUI();
                    });
            
            $('#aliVol').on('keydown',function(event){
            	event.stopImmediatePropagation();
    		    if( event.which == 13 ){
    		        event.preventDefault();
    		        if($('#aliCond').val()==""){
            			$('#aliCond').focus();
            			$('#aliCond').select2('open');
    		        }
    		        else{
    		        	$('#aliObs').focus();
    		        }
    		    }
            });
            
            $('#aliCond').change(
            		function() {
                  $('#aliObs').focus();
            });
            
            $('#aliObs').on('keydown',function(event){
            	event.stopImmediatePropagation();
    		    if( event.which == 13 ){
    		        event.preventDefault();
            			$('#btnSave').focus();
    		    }
            });

            function saveAlic(){
                App.blockUI();
                $.post( parametros.saveAlicUrl
                    , form2.serialize()
                    , function( data )
                    {
                        alic = JSON.parse(data);
                        if (alic.aliCode === undefined) {
                        	data = data.replace(/u0027/g,"");
                            toastr["error"](data, "Error!!");
                            App.unblockUI();
                        }
                        else{
                        	
                            toastr.success(parametros.successmessage,alic.aliCode);
                            App.unblockUI();
                            $('#btnClose').click();
                            $('#boxName').change();
                        }

                    }
                    , 'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        alert( "error:" + errorThrown);
                        App.unblockUI();
                    });
            }
            
            //Cuando abre el modal pone enfoque en codigo de alicuota
            $('#alicModal').on('shown.bs.modal', function () {
                $('#aliCode').focus();
            })  
            
          //Cuando cierra el modal limpia el form
            $('#alicModal').on('hide.bs.modal', function () {
            	$('#aliCode').val("");
                cleanForm2();
            })  

            

        }
    };
}();