var CreateCenter = function () {
	
	var handleMultiSelect = function () {
        $('#users').multiSelect();
        $('#studies').multiSelect();
    };

    return {
        //main function to initiate the module
        init: function (parametros) {
        	//var pageContent = $('.page-content');
            handleMultiSelect();
            var form1 = $('#add-center-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            jQuery.validator.addMethod("noSpace", function(value, element) { 
	      		  return value.indexOf(" ") < 0 && value != ""; 
	      	}, "Invalid");
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	
                	centerCode: {
                        required: false,
                        minlength: 0,
                        maxlength: 50,
                        noSpace:false
                    },
                    centerName: {
                        required: true,
                        minlength: 5,
                        maxlength: 150
                    },
                    centerContact: {
                        required: true,
                        maxlength: 150
                    },
                    centerAddress: {
                        required: true,
                        maxlength: 255
                    },
                    centerPhoneNumber: {
                        required: true,
                        maxlength: 25,
                        pattern: /^\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4,5})$/
                    },
                    centerEmail: {
                        required: true,
                        minlength: 3,
                        maxlength: 50,
                        noSpace:true,
                        email: true
                    },
                    centerObs: {
                        required: false,
                        maxlength: 500
                    },
                    users: {
                        required: true
                    },
                    studies: {
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
                    processCenter();
                }
            });
            
            function processCenter()
        	{
            	App.blockUI();
        	    $.post( parametros.saveCenterUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			centro = JSON.parse(data);
        	    			if (centro.centerCode === undefined) {
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
        						$('#centerCode').val(centro.centerCode);
        						toastr.success(parametros.successmessage,centro.centerName);
        					}
        	            	$('#centerName').focus();
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