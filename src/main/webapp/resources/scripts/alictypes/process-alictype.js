var CreateAlicType = function () {
	
    var handleSelect2 = function () {
    	$("#alicTypeUse").select2({});
    	$("#alicTypeSample").select2({});
    };
	
	var handleMultiSelect = function () {

    };
    
    var handleBootstrapTouchSpin = function() {   
        $("#alicTypeTemp").TouchSpin({
            inputGroupClass: 'input-large',
            spinUpClass: 'btn-info',
            spinDownClass: 'btn-info',
            min: -120,
            max: 10,
            step: 10,
            decimals: 1,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '�C'
        }); 
        $("#alicTypeVol").TouchSpin({
            inputGroupClass: 'input-large',
            spinUpClass: 'btn-info',
            spinDownClass: 'btn-info',
            min: 0,
            max: 1500,
            step: 100,
            decimals: 0,
            boostat: 5,
            maxboostedstep: 10,
            postfix: 'uL'
        }); 
    };

    return {
        //main function to initiate the module
        init: function (parametros) {
        	//var pageContent = $('.page-content');
            handleMultiSelect();
            handleSelect2();
            handleBootstrapTouchSpin();
            var form1 = $('#add-alicType-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            jQuery.validator.addMethod("noSpace", function(value, element) { 
	      		  return value.indexOf(" ") < 0 && value != ""; 
	      	}, "Invalid");
            
            jQuery.validator.addMethod("noComma", function(value, element) { 
	      		  return value.indexOf(",") < 0 && value != ""; 
	      	}, "Invalid");
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	
                	alicTypeCode: {
                        required: false,
                        minlength: 0,
                        maxlength: 50,
                        noSpace:false
                    },
                    alicTypeName: {
                        required: true,
                        minlength: 2,
                        maxlength: 50,
                        noSpace:true,
                        noComma:true
                    },
                    alicTypeTemp: {
                        required: true,
                        min: -120,
                        max: 10,
                    },
                    alicTypeVol: {
                        required: true,
                        min: 0,
                        max: 5000,
                    },
                    alicTypeVolMin: {
                        required: true,
                        min: 0,
                        max: 5000,
                    },
                    alicTypeVolMax: {
                        required: true,
                        min: 0,
                        max: 5000,
                    },
                    alicTypeUse: {
                        required: true
                    },
                    alicTypeSample: {
                        required: true
                    },
                    alicTypeObs: {
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
                    processAlicType();
                }
            });
            
            function processAlicType()
        	{
            	App.blockUI();
        	    $.post( parametros.saveAlicTypeUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			tipoalic = JSON.parse(data);
        	    			if (tipoalic.alicTypeCode === undefined) {
        	    				data = data.replace(/u0027/g,"");
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
        						$('#alicTypeCode').val(tipoalic.alicTypeCode);
        						toastr.success(parametros.successmessage,tipoalic.alicTypeName);
        					}
        	            	$('#alicTypeName').focus();
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