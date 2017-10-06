var CreateRack = function () {

    var handleSelect2 = function () {
    	$("#rackEquip").select2({});
    	$("#rackPosition").select2({});
    };
    
    var handleInputMasks = function () {
        $.extend($.inputmask.defaults, {
            'autounmask': true
        });

        $("#rackPriority").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        $("#rackRows").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        
        $("#rackColumns").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {
        	handleSelect2();
        	handleInputMasks();
        	//var pageContent = $('.page-content');
            var form1 = $('#add-rack-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            jQuery.validator.addMethod("noSpace", function(value, element) { 
	      		  return value.indexOf(" ") < 0 && value != ""; 
	      	}, "Invalid");
            
            $('#rackEquip').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.posicionesUrl, {
            				equipCode : $('#rackEquip').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#rackPosition").select2('data',null);
            				$("#rackPosition").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i] + '">'
            							+ data[i] + '</option>';
            				}
            				html += '</option>';
            				$('#rackPosition').html(html);
            				App.unblockUI();
            			});
                    });
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	rackCode: {
                        required: false,
                        minlength: 0,
                        maxlength: 50,
                        noSpace:false
                    },
                    rackName: {
                        required: true,
                        minlength: 1,
                        maxlength: 150,
                        noSpace:true
                    },
                    rackObs: {
                        required: false,
                        maxlength: 500
                    },
                    rackRows: {
                        required: true,
                        min: 1,
                        max: 99,
                    },
                    rackColumns: {
                        required: true,
                        min: 1,
                        max: 99,
                    },
                    rackCapacity: {
                        required: true
                    },
                    rackPriority: {
                        required: true,
                        min: 1,
                        max: 999,
                    },
                    rackEquip: {
                        required: true
                    },
                    rackPosition: {
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
                    processRack();
                }
            });
            
            function processRack()
        	{
            	App.blockUI();
        	    $.post( parametros.saveRackUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			rack = JSON.parse(data);
        	    			if (rack.rackCode === undefined) {
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
        						$('#rackCode').val(rack.rackCode);
        						toastr.success(parametros.successmessage,rack.rackName);
        					}
        	            	$('#rackName').focus();
        	    			App.unblockUI();
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
        		    		alert( "error:" + errorThrown);
        		    		App.unblockUI();
        		  		});
        	}
            
            $("#rackRows").change(
               		function() {
               			$("#rackCapacity").val($("#rackRows").val()*$("#rackColumns").val());
            });
            
            $("#rackColumns").change(
               		function() {
               			$("#rackCapacity").val($("#rackRows").val()*$("#rackColumns").val());
            });
           
            
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