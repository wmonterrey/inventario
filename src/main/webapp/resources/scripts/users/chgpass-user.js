var ChgPass = function () {

    var handlePasswordStrengthChecker = function () {
        var initialized = false;
        var input = $("#password");

        input.keydown(function () {
            if (initialized === false) {
                // set base options
                input.pwstrength({
                    raisePower: 1.4,
                    minChar: 8,
                    verdicts: ["Weak", "Normal", "Medium", "Strong", "Very Strong"],
                    showVerdicts:false,
                    scores: [17, 26, 40, 50, 60]
                });

                // add your own rule to calculate the password strength
                input.pwstrength("addRule", "demoRule", function (options, word, score) {
                    return word.match(/[a-z].[0-9]/) && score;
                }, 10, true);

                // set as initialized 
                initialized = true;
            }
        });
    };

    return {
        //main function to initiate the module
        init: function (parameters) {
        	
            
            handlePasswordStrengthChecker();
            var form1 = $('#chg-pass-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            jQuery.validator.addMethod("noSpace", function(value, element) { 
	      		  return value.indexOf(" ") < 0 && value != ""; 
	      	}, "No space");
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    password: {
                        minlength: 8,
                        maxlength: 150,
                        noSpace:true,
                        required: true,
                        pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[!@#$%^&*()?])).+$/
                    },
                    confirm_password: {
                        minlength: 8,
                        maxlength: 150,
                        required: true,
                        noSpace:true,
                        equalTo: "#password"
                    },
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
                    processUser();
                }
            });
            
            function processUser()
        	{
            	App.blockUI();
        	    $.post( parameters.chgPassUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			usuario = JSON.parse(data);
        	    			if (usuario.username === undefined) {
        						toastr.error(data);        						
        					}
        					else{
        						$('#username').val(usuario.username);
        						toastr.success(parameters.successmessage,usuario.username);
        					}
        	    			App.unblockUI();
        	    			$('#username').val('');
    	            		$('#password').val('');
    	            		$('#confirm_password').val('');
    	    				window.setTimeout(function(){
    	    			        window.location.href = parameters.usuarioUrl;
    	    			    }, 1500);
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