var CreateEquip = function () {

    var handleSelect2 = function () {
    	$("#equipType").select2({});
    	$("#equipRoom").select2({});
    };
    
    var handleMultiSelect = function () {
    	$('#equipUse').multiSelect();
    };
    
    var handleInputMasks = function () {
        $.extend($.inputmask.defaults, {
            'autounmask': true
        });

        $("#equipRows").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        
        $("#equipColumns").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        
        $("#equipPriority").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
    };
    
    var handleBootstrapTouchSpin = function() {   
        $("#equipTempMin").TouchSpin({
            inputGroupClass: 'input-large',
            spinUpClass: 'btn-info',
            spinDownClass: 'btn-info',
            min: -120,
            max: 10,
            step: 10,
            decimals: 1,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '°C'
        });  
        
        $("#equipTempMax").TouchSpin({
            inputGroupClass: 'input-large',
            spinUpClass: 'btn-info',
            spinDownClass: 'btn-info',
            min: -120,
            max: 10,
            step: 10,
            decimals: 1,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '°C'
        }); 
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {
        	handleSelect2();
        	handleMultiSelect();
        	handleInputMasks();
        	handleBootstrapTouchSpin();
        	//var pageContent = $('.page-content');
            var form1 = $('#add-equip-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            jQuery.validator.addMethod("noSpace", function(value, element) { 
	      		  return value.indexOf(" ") < 0 && value != ""; 
	      	}, "Invalid");
            jQuery.validator.addMethod("greaterThan", function(value, element, param) { 
            	var $otherElement = $(param);
  	          	return parseFloat(value) >= parseFloat($otherElement.val());
	      	}, "Invalid");
            
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	
                	equipCode: {
                        required: false,
                        minlength: 0,
                        maxlength: 50,
                        noSpace:false
                    },
                    equipName: {
                        required: true,
                        minlength: 1,
                        maxlength: 150
                    },
                    equipObs: {
                        required: false,
                        maxlength: 500
                    },
                    equipUse: {
                        required: true
                    },
                    equipType: {
                        required: true
                    }
                    ,
                    equipRoom: {
                        required: true
                    },
                    equipRows: {
                        required: true,
                        min: 1,
                        max: 99,
                    },
                    equipColumns: {
                        required: true,
                        min: 1,
                        max: 99,
                    },
                    equipCapacity: {
                        required: true
                    },
                    equipTempMin: {
                        required: true,
                        min: -120,
                        max: 10,
                    },
                    equipTempMax: {
                        required: true,
                        min: -120,
                        max: 10,
                        greaterThan: "#equipTempMin"
                    },
                    equipBrand: {
                        required: false,
                        maxlength: 255
                    },
                    equipModel: {
                        required: false,
                        maxlength: 255
                    },
                    equipSerie: {
                        required: false,
                        maxlength: 255
                    },
                    equipPriority: {
                        required: true,
                        min: 1,
                        max: 999,
                    },
                    equipResp: {
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
                    processEquipment();
                }
            });
            
            function processEquipment()
        	{
            	App.blockUI();
        	    $.post( parametros.saveEquipUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			equipo = JSON.parse(data);
        	    			if (equipo.equipCode === undefined) {
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
        						$('#equipCode').val(equipo.equipCode);
        						toastr.success(parametros.successmessage,equipo.equipName);
        					}
        	            	$('#equipName').focus();
        	    			App.unblockUI();
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
        		    		alert( "error:" + errorThrown);
        		    		App.unblockUI();
        		  		});
        	}
            
            $("#equipRows").change(
               		function() {
               			$("#equipCapacity").val($("#equipRows").val()*$("#equipColumns").val());
            });
            
            $("#equipColumns").change(
               		function() {
               			$("#equipCapacity").val($("#equipRows").val()*$("#equipColumns").val());
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