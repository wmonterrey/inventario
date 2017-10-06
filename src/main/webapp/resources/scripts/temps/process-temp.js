var CreateTemp = function () {

    var handleSelect2 = function () {
    	$("#equipCode").select2({});
    };
    
    var handleDatetimePicker = function (lenguaje) {
    	
    	$(".form_datetime").datetimepicker({
            autoclose: true,
            isRTL: App.isRTL(),
            format: "yyyy-mm-dd hh:ii",
            pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
            autoclose: true,
            todayBtn: true,
            language: lenguaje
        });

        $(".form_meridian_datetime").datetimepicker({
            isRTL: App.isRTL(),
            format: "dd MM yyyy - HH:ii P",
            showMeridian: true,
            autoclose: true,
            pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
            todayBtn: true,
            language: lenguaje
        });

        $('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
    };
    
    var handleBootstrapTouchSpin = function() {   
        $("#tempRecord").TouchSpin({
            inputGroupClass: 'input-large',
            spinUpClass: 'btn-info',
            spinDownClass: 'btn-info',
            min: -120,
            max: 10,
            step: 10,
            decimals: 1,
            boostat: 5,
            maxboostedstep: 10,
            postfix: 'C'
        }); 
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {
        	handleSelect2();
        	handleDatetimePicker(parametros.lenguaje);
        	handleBootstrapTouchSpin();
        	//var pageContent = $('.page-content');
            var form1 = $('#add-temp-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            var minimo = parametros.minimo;
            var maximo = parametros.maximo;
            
           
            $('#equipCode').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.getEquipUrl, {
            				equipCode : $('#equipCode').val(),
            				ajax : 'true'
            			}, function(data) {
            				minimo = data.equipTempMin;
            				maximo = data.equipTempMax;
            				$( "#tempRecord" ).rules( "add", {
            					  min: minimo,
            					  max: maximo
            					});
            				App.unblockUI();
            			});
                    });
            
            $('#fueraRango').change(
            		function() {
            			if($('#fueraRango').prop('checked')){
            				$( "#tempRecord" ).rules( "remove", "min max" );
            			}
            			else{
            				$( "#tempRecord" ).rules( "add", {
          					  min: minimo,
          					  max: maximo
          					});
            			}
                    });
            
            $('#malEstado').change(
            		function() {
            			if($('#malEstado').prop('checked')){
            				$( "#tempRecord" ).rules( "remove", "min max" );
            			}
            			else{
            				$( "#tempRecord" ).rules( "add", {
          					  min: minimo,
          					  max: maximo
          					});
            			}
                    });
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	tempCode: {
                        required: false,
                        minlength: 0,
                        maxlength: 50
                    },
                    equipCode: {
                        required: true
                    },
                    tempDate: {
                        required: true
                    },
                    tempRecord: {
                        required: true
                    },
                    tempObs: {
                        required: false,
                        maxlength: 500
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
                    console.log( form1.serialize() );
                    processTemp();
                }
            });
            
            if(!($('#malEstado').prop('checked')||$('#fueraRango').prop('checked'))){
				$( "#tempRecord" ).rules( "add", {
				  min: minimo,
				  max: maximo
				});
			}
            
            function processTemp()
        	{
            	App.blockUI();
        	    $.post( parametros.saveTempUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			temp = JSON.parse(data);
        	    			if (temp.tempCode === undefined) {
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
        						$('#tempCode').val(temp.tempCode);
        						toastr.success(parametros.successmessage,temp.tempCode);
        					}
        	    			App.unblockUI();
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
        		    		alert( "error:" + errorThrown);
        		    		App.unblockUI();
        		  		});
        	}
            
            
            $(document).on('keypress','form input',function(event)
    		{                
    		    event.stopImmediatePropagation();
    		    if( event.which == 13 )
    		    {
    		        event.preventDefault();
    		        var $input = $('form input');
    		        if( $(this).is( $input.last() ) )
    		        {
    		            //Time to submit the form!!!!
    		            //alert( 'Hooray .....' );
    		        }
    		        else
    		        {
    		            $input.eq( $input.index( this ) + 1 ).focus();
    		        }
    		    }
    		});
        }
    };

}();