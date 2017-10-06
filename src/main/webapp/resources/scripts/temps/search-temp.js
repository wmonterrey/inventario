var SearchTemp = function () {

    var handleSelect2 = function () {
    	$("#equipCode").select2({});
    };
   
    
    var handleDatetimePicker = function (lenguaje) {

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
    
    var handleDatePickers = function (lenguaje) {

        if (jQuery().datepicker) {
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                autoclose: true,
                language: lenguaje
            });
            $('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
        }
    };
    
    return {
        //main function to initiate the module
        init: function (parametros) {
        	handleSelect2();
        	handleDatetimePicker(parametros.lenguaje);
        	handleDatePickers(parametros.lenguaje);
        	//var pageContent = $('.page-content');
            var form1 = $('#select-equip-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);  
            
            $('#equipCode').change(
            		function() {
            			 success1.show();
                         error1.hide();
                         table.fnClearTable();
                    });
            
            var table  = $('#lista_temps').DataTable({
                // set the initial value
            	"bPaginate": false,
            	"aaSorting": [[ 1, "desc" ]] ,
                "iDisplayLength": 10,
                "oLanguage": {
        			"sUrl": parametros.dataTablesLang
                }
            });
    		
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                	equipCode: {
                        required: true,
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
                    table.fnClearTable();
                    searchTemps();
                }
            });
            
            function searchTemps()
        	{
            	App.blockUI();
            	$.getJSON(parametros.tempsUrl, {
    				equipCode : $('#equipCode').val(),
    				fecDesde : $('#fecDesde').val(),
    				fecHasta : $('#fecHasta').val(),
    				ajax : 'true'
    			}, function(data) {
    				var len = data.length;
    				if(len==0){
    					toastr.options = {
    						  "closeButton": true,
    						  "onclick": null,
    						  "showDuration": "300",
    						  "hideDuration": "1000",
    						  "timeOut": 3000,
    						  "extendedTimeOut": 0,
    						  "tapToDismiss": true
    						};
	    				toastr["warning"](parametros.notFound);
    				}
    				else{
	    				for ( var i = 0; i < len; i++) {
	    					var d = new Date(data[i].recordDate);
	    					var tDate = new Date(data[i].tempDate);
	    					var tempUrl = parametros.tempUrl + '/'+data[i].tempCode+ '/';
	    					var editUrl = parametros.editUrl + '/'+data[i].tempCode+ '/';
	    					var activo="";
	    					if (data[i].pasive=='0'.charAt(0)) activo = '<span class="label label-success">'+parametros.simon;
	    					if (data[i].pasive!='0'.charAt(0)) activo = '<span class="label label-danger">'+parametros.nelson;
	    					var fueraRango="";
	    					if (data[i].fueraRango=='0'.charAt(0)) fueraRango = '<span class="label label-success">'+parametros.nelson;
	    					if (data[i].fueraRango!='0'.charAt(0)) fueraRango = '<span class="label label-danger">'+parametros.simon;
	    					var malEstado="";
	    					if (data[i].malEstado=='0'.charAt(0)) malEstado = '<span class="label label-success">'+parametros.nelson;
	    					if (data[i].malEstado!='0'.charAt(0)) malEstado = '<span class="label label-danger">'+parametros.simon;
	    					table.fnAddData(
	    							['<a href='+ tempUrl + '>'+data[i].tempCode+'</a>',tDate.yyyymmddhhMM(),data[i].tempRecord,malEstado,fueraRango,data[i].tempObs,activo,data[i].recordUser,d.yyyymmdd(),'<a href='+ tempUrl + ' class="btn btn-default btn-xs"><i class="fa fa-search"></i></a>'
	    							 +'<a href='+ editUrl + ' class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a>']);
	    				}
    				}
    				App.unblockUI();
    			})
    			.fail(function() {
				    alert( "error" );
				    App.unblockUI();
				});
        	}
            
            Date.prototype.yyyymmdd = function() {         
		        
		        var yyyy = this.getFullYear().toString();                                    
		        var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
		        var dd  = this.getDate().toString();             
		                            
		        return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
		   };
		   
		   Date.prototype.yyyymmddhhMM = function() {         
		        
		        var yyyy = this.getFullYear().toString();                                    
		        var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
		        var dd  = this.getDate().toString(); 
		        var hh  = this.getHours().toString(); 
		        var mins  = this.getMinutes().toString(); 
		                            
		        return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0])+ ' ' + (hh[1]?hh:"0"+hh[0])+ ':' + (mins[1]?mins:"0"+mins[0]);
		   };
		   
           
        }
    
    
    };

}();