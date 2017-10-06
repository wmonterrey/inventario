var CreateBox = function () {

    var handleSelect2 = function () {
    	$("#rackEquip").select2({});
    	$("#boxRack").select2({});
    	$("#boxPosition").select2({});
    	$("#boxStudy").select2({});
    	$("#boxAlicUse").select2({});
    	$("#boxResult").select2({});
    	$('#boxAlicType').select2({
            allowClear: true
        });
    };
    
    var handleInputMasks = function () {
        $.extend($.inputmask.defaults, {
            'autounmask': true
        });

        $("#boxPriority").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        $("#boxRows").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        
        $("#boxColumns").inputmask({
            "mask": "9",
            "repeat": 2,
            "greedy": false
        });
        
    };
    
    var handleBootstrapTouchSpin = function() {   
        $("#boxTemp").TouchSpin({
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
        	handleInputMasks();
        	handleBootstrapTouchSpin();
        	//var pageContent = $('.page-content');
            var form1 = $('#add-box-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            jQuery.validator.addMethod("noSpace", function(value, element) { 
	      		  return value.indexOf(" ") < 0 && value != ""; 
	      	}, "Invalid");
            
            $('#boxStudy').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.alicsUrl, {
            				boxStudy : $('#boxStudy').val(),
            				boxAlicUse : $('#boxAlicUse').val(),
            				boxTemp : $('#boxTemp').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#boxAlicType").select2('data',null);
            				$("#boxAlicType").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].tipoAlicuota.alicTypeName + '">'
            							+ data[i].tipoAlicuota.alicTypeName + '</option>';
            				}
            				html += '</option>';
            				$('#boxAlicType').html(html);
            			});
            			App.unblockUI();
                    });
            
            $('#boxAlicUse').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.alicsUrl, {
            				boxStudy : $('#boxStudy').val(),
            				boxAlicUse : $('#boxAlicUse').val(),
            				boxTemp : $('#boxTemp').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#boxAlicType").select2('data',null);
            				$("#boxAlicType").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].tipoAlicuota.alicTypeName + '">'
            							+ data[i].tipoAlicuota.alicTypeName + '</option>';
            				}
            				html += '</option>';
            				$('#boxAlicType').html(html);
            			});
            			$.getJSON(parametros.equipsUrl, {
            				boxAlicUse : $('#boxAlicUse').val(),
            				boxTemp : $('#boxTemp').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#rackEquip").select2('data',null);
            				$("#rackEquip").empty();
            				$("#boxRack").select2('data',null);
            				$("#boxRack").empty();
            				var htmlPos='<option value="'+$("#boxPosition").val()+'">'+$("#boxPosition").val() +'</option>';
            				$("#boxPosition").select2('data',null);
            				$("#boxPosition").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].equipCode + '">'
            							+ data[i].equipName + '</option>';
            				}
            				html += '</option>';
            				$('#rackEquip').html(html);
            				$("#boxPosition").html(htmlPos);
            			});
            			App.unblockUI();
                    });
            
            $('#boxTemp').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.alicsUrl, {
            				boxStudy : $('#boxStudy').val(),
            				boxAlicUse : $('#boxAlicUse').val(),
            				boxTemp : $('#boxTemp').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#boxAlicType").select2('data',null);
            				$("#boxAlicType").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].tipoAlicuota.alicTypeName + '">'
            							+ data[i].tipoAlicuota.alicTypeName + '</option>';
            				}
            				html += '</option>';
            				$('#boxAlicType').html(html);
            			});
            			$.getJSON(parametros.equipsUrl, {
            				boxAlicUse : $('#boxAlicUse').val(),
            				boxTemp : $('#boxTemp').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#rackEquip").select2('data',null);
            				$("#rackEquip").empty();
            				$("#boxRack").select2('data',null);
            				$("#boxRack").empty();
            				var htmlPos='<option value="'+$("#boxPosition").val()+'">'+$("#boxPosition").val() +'</option>';
            				$("#boxPosition").select2('data',null);
            				$("#boxPosition").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].equipCode + '">'
            							+ data[i].equipName + '</option>';
            				}
            				html += '</option>';
            				$('#rackEquip').html(html);
            				$("#boxPosition").html(htmlPos);
            			});
        				App.unblockUI();
                    });
            		
            
            $('#rackEquip').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.racksUrl, {
            				equipCode : $('#rackEquip').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#boxRack").select2('data',null);
            				$("#boxRack").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].rackCode + '">'
            							+ data[i].rackName + '</option>';
            				}
            				html += '</option>';
            				$('#boxRack').html(html);
            			});
            			App.unblockUI();
                    });
            
            $('#boxRack').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.posicionesUrl, {
            				rackCode : $('#boxRack').val(),
            				ajax : 'true'
            			}, function(data) {
            				var html='<option value="'+$("#boxPosition").val()+'">'+$("#boxPosition").val() +'</option>';
            				$("#boxPosition").select2('data',null);
            				$("#boxPosition").empty();
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i] + '">'
            							+ data[i] + '</option>';
            				}
            				html += '</option>';
            				$('#boxPosition').html(html);
            			});
            			App.unblockUI();
                    });
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	boxCode: {
                        required: false,
                        minlength: 0,
                        maxlength: 50,
                        noSpace:false
                    },
                    boxName: {
                        required: true,
                        minlength: 1,
                        maxlength: 150,
                        noSpace:true
                    },
                    boxObs: {
                        required: false,
                        maxlength: 500
                    },
                    boxRows: {
                        required: true,
                        min: 1,
                        max: 99,
                    },
                    boxColumns: {
                        required: true,
                        min: 1,
                        max: 99,
                    },
                    boxCapacity: {
                        required: true
                    },
                    boxPriority: {
                        required: true,
                        min: 1,
                        max: 999,
                    },
                    rackEquip: {
                        required: true
                    },
                    boxRack: {
                        required: true
                    },
                    boxPosition: {
                        required: true
                    },
                    boxResult: {
                        required: true
                    },
                    boxStudy: {
                        required: true
                    },
                    boxTemp: {
                        required: true
                    },
                    boxAlicUse: {
                        required: true
                    },
                    boxAlicType: {
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
                    processBox();
                }
            });
            
            function processBox()
        	{
            	App.blockUI();
        	    $.post( parametros.saveBoxUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			box = JSON.parse(data);
        	    			if (box.boxCode === undefined) {
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
        						$('#boxCode').val(box.boxCode);
        						toastr.success(parametros.successmessage,box.boxName);
        					}
        	            	$('#boxName').focus();
        	    			App.unblockUI();
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
        		    		alert( "error:" + errorThrown);
        		    		App.unblockUI();
        		  		});
        	}
            
            $("#boxRows").change(
               		function() {
               			$("#boxCapacity").val($("#boxRows").val()*$("#boxColumns").val());
            });
            
            $("#boxColumns").change(
               		function() {
               			$("#boxCapacity").val($("#boxRows").val()*$("#boxColumns").val());
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