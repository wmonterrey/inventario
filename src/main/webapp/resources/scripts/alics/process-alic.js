var CreateAlic = function () {
	
	var handleSelect2 = function () {
    	$("#boxStudy").select2({});
    	$("#boxResultType").select2({});
    	$("#aliCond").select2({});
    };
    
    var alicPerm = [];
    var patron = "";
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
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);

            $("#boxResults").select2({
        		formatResult: function(caja) { 
        			var originalOption = caja.element;
                	var markup = "<table'><tr>";
                    markup += "<td valign='top'><h5><b>" + caja.text + "</b>, "+parametros.posAvailable+ ": <b>" + $(originalOption).data('disponibles')+"</b></h5>";
                    markup += "<div>" + parametros.boxRack+ ": " + $(originalOption).data('rack') + " , " + parametros.boxPosition+ ": " +  $(originalOption).data('posicion') +"</div>";
                    markup += "<div>" + parametros.rackEquip+ ": " + $(originalOption).data('equipo') + " , " + parametros.rackPosition+ ": " +  $(originalOption).data('rackpos') +"</div>";
                    markup += "</td></tr></table>";
                    return markup; 
                }
        	});
            
            toastr.options = {
					  "closeButton": true,
					  "onclick": null,
					  "showDuration": "300",
					  "hideDuration": "1000",
					  "timeOut": "6000",
					  "extendedTimeOut": "0",
					  "tapToDismiss": false
					};
            
            var table  = $('#lista_alicuotas').DataTable({
                // set the initial value
            	"bPaginate": true,
                "iDisplayLength": 10,
                "oLanguage": {
        			"sUrl": parametros.dataTablesLang
                }
            });
            
            $('#boxStudy').change(
            		function() {
            			cleanForm(true);
            			$('#aliCode').val("");
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
            					$('#boxResultType').focus();
                    			$('#boxResultType').select2('open');
            				}
            			});
            			App.unblockUI();
                    });
            
            function cleanForm(borrarTodo)
        	{
            	if(borrarTodo) $('#alicTypeName').val("");
            	if(borrarTodo) $('#alicTypeUse').val("");
            	if(borrarTodo) $('#alicTypeTemp').val("");
				$('#rackEquip').val("");
				$('#boxRack').val("");
				$('#aliBox').val("");
				$('#aliPosition').val("");
				$('#aliVol').val("");
				$('#aliObs').val("");
				$("#boxResults").select2('data',null);
				$("#boxResults").empty();
				if(borrarTodo) $(".grid" ).empty();
        	}
            
            $('#aliCode').change(
            		function() {
            			App.blockUI();
            			cleanForm(true);
            			var res = false;
            			var resAlic = false;
            			var alicuotaIngresada = null;
            			if($('#boxStudy').val()==""){
            				toastr["error"]( parametros.studyName + ' ' + parametros.requiredmessage, "Error!!");
            				$('#aliCode').focus();
            				return;
            			}
            			if($('#boxResultType').val()==""){
            				toastr["error"]( parametros.boxResultType + ' ' + parametros.requiredmessage, "Error!!");
            				$('#aliCode').focus();
            				return;
            			}
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
            				
            			
            			var len = alicPerm.length;
            			
            			var alicEncontrada = false; var alicName = ""; var alicUse = ""; var alicTemp = 0;

        				for ( var i = 0; i < len; i++) {
        					if(alicuotaIngresada[0].localeCompare(alicPerm[i].tipoAlicuota.alicTypeName)==0){
        						alicEncontrada = true; 
        						alicName = alicPerm[i].tipoAlicuota.alicTypeName; 
        						alicUse = alicPerm[i].tipoAlicuota.alicTypeUse; 
        						alicTemp=alicPerm[i].tipoAlicuota.alicTypeTemp; 
        						volumen=alicPerm[i].tipoAlicuota.alicTypeVol;
        						volMin=alicPerm[i].tipoAlicuota.alicTypeVolMin;
        						volMax=alicPerm[i].tipoAlicuota.alicTypeVolMax;
        						break;
        					}        					
        				}
            			
        				if(Boolean(alicEncontrada)){
        					$('#alicTypeName').val(alicName);
        					$('#alicTypeUse').val(alicUse);
        					$('#alicTypeTemp').val(alicTemp);
        					$('#alicTypeVolMin').val(volMin);
        					$('#alicTypeVolMax').val(volMax);
        					$('#aliVol').val(volumen);
        					App.blockUI();
                			$.getJSON(parametros.getPosUrl, {
                				boxStudy : $('#boxStudy').val(),
                				alicTypeName: $('#alicTypeName').val(),
                				alicTypeUse: $('#alicTypeUse').val(),
                				alicTypeTemp: $('#alicTypeTemp').val(),
                				boxResultType : $('#boxResultType').val(),
                				ajax : 'true'
                			}, function(data) {
                				
                				if (data.length === 0) {
              	    				toastr["error"](parametros.posNotAvailable, "Error!!");   
              	    				cleanForm(false);
              	    				$('#aliCode').focus();
            					}
            					else{
            						var html="<option value=''></option>";
                    				var len = data.length;
                    				for ( var i = 0; i < len; i++) {
                    					html += "<option value='" + data[i].box.boxCode 
                    					+ "' data-posicion='" + data[i].box.boxPosition + "'"
                    					+ "' data-rack='" + data[i].box.boxRack.rackName + "'"
                    					+ "' data-rackpos='" + data[i].box.boxRack.rackPosition + "'"
                    					+ "' data-equipo='" + data[i].box.boxRack.rackEquip.equipName + "'"
                    					+ "' data-disponibles='" + data[i].disponibles + "'>" 
                    					+ data[i].box.boxName + "</option>";
                    				}
                    				$('#boxResults').html(html);
            						toastr.success(data.length +" "+parametros.boxAvailable,parametros.successmessage);
            						$('#boxResults').select2('open');
            					}
                			});
        				}
        				else{
        					cleanForm(true);
    	    				toastr["error"](parametros.aliNotInList, "Error!!");
    	    				$('#aliCode').focus();
        				}
        				App.unblockUI();
                    });
            
            $('#boxResults').change(
            		function(){
            			llenarCaja();
            			$('#aliVol').focus();
            			$('#aliVol').select();
            		});
            
            $('#aliCond').change(
            		function() {
                  $('#aliObs').focus();
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
            
            $('#aliObs').on('keydown',function(event){
            	event.stopImmediatePropagation();
    		    if( event.which == 13 ){
    		        event.preventDefault();
            			$('#guardar').focus();
    		    }
            });
 
            $('#boxResultType').change(
            		function() {
            			cleanForm(true);
            			$('#aliCode').val("");
            			$('#aliCode').focus();
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
                    },
                    boxResultType: {
                        required: true
                    },
                    aliBox: {
                        required: true
                    },
                    boxResults: {
                        required: true
                    },
                    rackEquip: {
                        required: true
                    },
                    boxRack: {
                        required: true
                    },
                    aliPosition: {
                        required: true
                    },
                    aliVol: {
                        required: true,
                        min: function ()  { return volMin},
                        max: function ()  { return volMax}
                    },
                    aliCond:{
                    	required:true
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
                    $('#aliCode').val("");
	            	$('#aliCode').focus();
                }
            });
            
            function processAlic(){
            	App.blockUI();
        	    $.post( parametros.saveAlicUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			alicuota = JSON.parse(data);
        	    			if (alicuota.aliId === undefined) {
        	    				data = data.replace(/u0027/g,"");
          	    				toastr["error"](data, "Error!!");        						
        					}
        					else{
        						toastr.success(parametros.successmessage,alicuota.aliId.aliCode);
        						table.fnAddData([alicuota.aliId.aliCode,alicuota.aliBox.boxStudy.studyName,alicuota.aliBox.boxName,alicuota.aliPosition,alicuota.aliVol,alicuota.aliCond,alicuota.aliRes,alicuota.aliObs,null]);
        					}
        	    			llenarCaja();
        	    			App.unblockUI();
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
        		    		alert( "error:" + errorThrown);
        		    		App.unblockUI();
        		  		});
        	}
            
            function llenarCaja() {
    			App.blockUI();
    			$.getJSON(parametros.getBoxUrl, {
    				boxCode : $('#boxResults').val(),
    				ajax : 'true'
    			}, function(data) {
    				var item;
    				$('#aliBox').val(data.box.boxName);
					$('#boxRack').val(data.box.boxRack.rackName);
					$('#rackEquip').val(data.box.boxRack.rackEquip.equipName);
					$('#aliPosition').val(data.primeraDisponible);
					$(".grid").empty();
                    for (var i = 1; i <= data.box.boxCapacity; i++) {
                    	item = "<div class='grid-item vacio' onclick='actualizarPosicion("+i+")'><p class='number'><a href='#'>"+i+"</a></p>";
                    	for(var j = 0; j < data.aliquots.length; j++){
                    		if(data.aliquots[j].aliPosition == i){
                    			item = "<div class='grid-item lleno'><p class='number'>"+i+"</p>";
                    			item += "<p class='symbol'>"+ data.aliquots[j].aliId.aliCode +"</p>";
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
                    $('.grid-item').css({"position": "relative"});
                    $('.grid-item').css({"float": "left"});
                    $('.grid-item').css({"height": "100px"});
                    $('.grid-item').css({"border": "1px solid #333"});
                    $('.grid-item').css({"border-color": "hsla(0, 0%, 0%, 0.2)"});
                    $('.grid-item.vacio').css({"background": "#EEEEEE"});
                    $('.grid-item.lleno').css({"background": "#99CCFF"});
                    $('.grid').isotope({
                        // options
                        itemSelector: '.grid-item',
                        layoutMode: 'fitRows'
                    });
    			});
    			App.unblockUI();
            }
        }
    };

}();