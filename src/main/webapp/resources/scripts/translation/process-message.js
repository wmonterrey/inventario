var CreateMessage = function () {

    return {
        //main function to initiate the module
        init: function (parametros) {

        	//var pageContent = $('.page-content');
            var form1 = $('#add-message-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
           
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	
                	messageKey: {
                        required: true
                    },
                    spanish: {
                        required: true,
                        minlength: 1,
                        maxlength: 255
                    },
                    english: {
                        required: true,
                        maxlength: 255
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
                    processMessage();
                }
            });
            
            function processMessage()
        	{
            	App.blockUI();
        	    $.post( parametros.saveTransUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			message = JSON.parse(data);
        	    			if (message.messageKey === undefined) {
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
        						$('#messageKey').val(message.messageKey);
        						toastr.success(parametros.successmessage,message.messageKey);
        					}
        	            	$('#spanish').focus();
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