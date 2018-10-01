var CreateAlic = function () {
	
	var handleSelect2 = function () {
    	$("#boxStudy").select2({});
    	$("#boxResultType").select2({});
    	$("#boxResults").select2({});
    };
    
    var alicPerm = [];
    var patron = "";
    var formato = "";
 
    return {
        //main function to initiate the module
        init: function (parametros) {
        	handleSelect2();
        	//var pageContent = $('.page-content');
            var form1 = $('#add-alic-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);
            
            toastr.options = {
					  "closeButton": true,
					  "onclick": null,
					  "showDuration": "300",
					  "hideDuration": "1000",
					  "timeOut": "6000",
					  "extendedTimeOut": "0",
					  "tapToDismiss": false
					};
            
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
            					alicPerm = data;
            					patron = alicPerm[0].estudio.studyPattern;
            					formato = alicPerm[0].estudio.studyFormat;
            				}
            			});
            			App.unblockUI();
                    });
            
            function cleanForm()
        	{
            	$('#alicTypeName').val("");
				$('#alicTypeUse').val("");
				$('#alicTypeTemp').val("");
				$('#rackEquip').val("");
				$('#boxRack').val("");
				$('#aliBox').val("");
				$('#aliPosition').val("");
				$('#aliVol').val("");
        	}
            
            $('#aliCode').change(
            		function() {
            			App.blockUI();
            			var res = false;
            			var resAlic = false;
            			var alicuotaIngresada = null;
            			try{
            				var patt = new RegExp(patron,"i");
            				res = patt.test($('#aliCode').val());
            			}
	            		catch(e){
	            			toastr["error"]( $('#aliCode').val() + ' ' + parametros.regExpInv, "Error!!");
	            			cleanForm();
	            			$('#aliCode').focus();
	            			return;
	            		}
            			if(!Boolean(res)){
      	    				toastr["error"]($('#aliCode').val() + ' ' +parametros.aliNotPattern, "Error!!");
      	    				cleanForm();
      	    				$('#aliCode').focus();
      	    				return;
            			}
            			
            			try{
            				var pattAlic = new RegExp(formato,"i");
            				resAlic = pattAlic.test($('#aliCode').val());
            				if(!Boolean(resAlic)){
          	    				toastr["error"]($('#aliCode').val() + ' ' +parametros.aliNotPattern2, "Error!!");
          	    				cleanForm();
          	    				$('#aliCode').focus();
          	    				return;
                			}
            				else{
            					var alicuotaIngresada = $('#aliCode').val().match(pattAlic);
            				}
            				
            			}
	            		catch(e){
	            			toastr["error"]( $('#aliCode').val() + ' ' + parametros.regExpInv2, "Error!!");
	            			cleanForm();
	            			$('#aliCode').focus();
	            			return;
	            		}
            				
            			
            			var len = alicPerm.length;
            			
            			var alicEncontrada = false; var alicName = ""; var alicUse = ""; var alicTemp = 0;

        				for ( var i = 0; i < len; i++) {
        					if(alicuotaIngresada[0].localeCompare(alicPerm[i].tipoAlicuota.alicTypeName)==0){
        						alicEncontrada = true; alicName = alicPerm[i].tipoAlicuota.alicTypeName; alicUse = alicPerm[i].tipoAlicuota.alicTypeUse; alicTemp=alicPerm[i].tipoAlicuota.alicTypeTemp;
        						break;
        					}        					
        				}
            			
        				if(Boolean(alicEncontrada)){
        					$('#alicTypeName').val(alicName);
        					$('#alicTypeUse').val(alicUse);
        					$('#alicTypeTemp').val(alicTemp);
        					App.blockUI();
                			$.getJSON(parametros.getPosUrl, {
                				boxStudy : $('#boxStudy').val(),
                				alicTypeName: $('#alicTypeName').val(),
                				alicTypeUse: $('#alicTypeUse').val(),
                				alicTypeTemp: $('#alicTypeTemp').val(),
                				boxResultType : $('#boxResultType').val(),
                				ajax : 'true'
                			}, function(data) {
                				
                				if (data.box === null) {
            	    				toastr.options = {
            	    						  "closeButton": true,
            	    						  "onclick": null,
            	    						  "showDuration": "300",
            	    						  "hideDuration": "1000",
            	    						  "timeOut": "6000",
            	    						  "extendedTimeOut": "0",
            	    						  "tapToDismiss": false
            	    						};
              	    				toastr["error"](data.mensaje, "Error!!");   
              	    				cleanForm();
            					}
            					else{
            						$('#aliBox').val(data.box.boxName);
            						$('#boxRack').val(data.box.boxRack.rackName);
            						$('#rackEquip').val(data.box.boxRack.rackEquip.equipName);
            						$(".grid" ).empty();
                    				$( ".grid" ).append( "<div class='grid-item'><p class='number'>1</p></div>" );
                    				$( ".grid" ).append( "<div class='grid-item'><p class='number'>2</p></div>" );
                    				$( ".grid" ).append( "<div class='grid-item'><p class='number'>3</p></div>" );
                    				$( ".grid" ).append( "<div class='grid-item'><p class='number'>4</p></div>" );
                    				$( ".grid" ).append( "<div class='grid-item'><p class='number'>5</p></div>" );
                    				var ancho = "50%";
                    		        $('.grid-item').css({"width":ancho});
                    		        $('.grid-item').css({"position":"relative"});
                    		        $('.grid-item').css({"float":"left"});
                    		        $('.grid-item').css({"height":"100px"});
                    		        $('.grid-item').css({"background":"#FFFFFF"});
                    		        $('.grid-item').css({"border":"1px solid #333"});
                    		        $('.grid-item').css({"border-color":"hsla(0, 0%, 0%, 0.2)"});
                    		        $('.grid').isotope({
                    		        	  // options
                    		        	  itemSelector: '.grid-item',
                    		        	  layoutMode: 'fitRows'
                    		        	});
            						toastr.success(parametros.successmessage,data.box.boxName);
            						$('#aliVol').focus();
            					}
                			});
        				}
        				else{
        					cleanForm();
        					toastr.options = {
  	    						  "closeButton": true,
  	    						  "onclick": null,
  	    						  "showDuration": "300",
  	    						  "hideDuration": "1000",
  	    						"timeOut": "6000",
  	    					  "extendedTimeOut": "0",
  	    						  "tapToDismiss": false
  	    						};
    	    				toastr["error"](parametros.aliNotInList, "Error!!");
    	    				$('#aliCode').focus();
        				}
        				App.unblockUI();
                    });
            
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    aliObs: {
                        required: false,
                        maxlength: 500
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
                    aliCode: {
                        required: true
                    },
                    boxStudy: {
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
                    processAlic();
                }
            });
            
            function processAlic()
        	{
            	App.blockUI();
        	    $.post( parametros.saveAlicUrl
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
        	    						  "timeOut": "6000",
        	    						  "extendedTimeOut": "0",
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