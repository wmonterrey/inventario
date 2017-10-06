var SearchBox = function () {

    var handleSelect2 = function () {
    	$("#equipCode").select2({});
    	$("#rackCode").select2({});
    };
   
    
    return {
        //main function to initiate the module
        init: function (parametros) {
        	handleSelect2();
        	//var pageContent = $('.page-content');
            var form1 = $('#select-equip-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);  
            
            $('#equipCode').change(
            		function() {
            			App.blockUI();
            			$.getJSON(parametros.racksUrl, {
            				equipCode : $('#equipCode').val(),
            				ajax : 'true'
            			}, function(data) {
            				$("#rackCode").select2('data',null);
            				$("#rackCode").empty();
            				var html='<option value=""></option>';
            				var len = data.length;
            				for ( var i = 0; i < len; i++) {
            					html += '<option value="' + data[i].rackCode + '">'
            							+ data[i].rackName + '</option>';
            				}
            				html += '</option>';
            				$('#rackCode').html(html);
            			});
            			App.unblockUI();
                    });
            
            $('#rackCode').change(
            		function() {
            			 success1.show();
                         error1.hide();
                         table.fnClearTable();
                         searchBoxes();
                    });
            
            $('#boxName').change(
            		function() {            			 
                         table.fnClearTable();
                         searchBoxesName();
                    });
            
            var table  = $('#lista_boxes').DataTable({
                // set the initial value
            	"bPaginate": false,
            	"aaSorting": [[ 6, "asc" ]] ,
                "iDisplayLength": 10,
                "oLanguage": {
        			"sUrl": parametros.dataTablesLang
                },
                "aoColumnDefs": [
                                 {"aTargets": [0],
                                     "bVisible": false,
                                     "bSearchable": true}
                               ]
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
                    searchBoxes();
                }
            });
            
            function searchBoxes()
        	{
            	App.blockUI();
            	$.getJSON(parametros.boxesUrl, {
            		rackCode : $('#rackCode').val(),
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
	    					var boxUrl = parametros.boxUrl + '/'+data[i].boxCode+ '/';
	    					var editUrl = parametros.editUrl + '/'+data[i].boxCode+ '/';
	    					var activo="";
	    					if (data[i].pasive=='0'.charAt(0)) activo = '<span class="label label-success">'+parametros.simon;
	    					if (data[i].pasive!='0'.charAt(0)) activo = '<span class="label label-danger">'+parametros.nelson;
	    					table.fnAddData(
	    							['<a href='+ boxUrl + '>'+data[i].boxCode+'</a>','<a href='+ boxUrl + '>'+data[i].boxName+'</a>'
	    							 ,data[i].boxStudy.studyName,data[i].boxAlicType,data[i].boxAlicUse,data[i].boxTemp,data[i].boxPosition,data[i].boxCapacity
	    							 ,data[i].boxPriority,activo,data[i].recordUser,d.yyyymmdd(),'<a href='+ boxUrl + ' class="btn btn-default btn-xs"><i class="fa fa-search"></i></a>'
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
            
            function searchBoxesName()
        	{
            	App.blockUI();
            	$.getJSON(parametros.boxesNameUrl, {
            		boxName : $('#boxName').val(),
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
	    					var boxUrl = parametros.boxUrl + '/'+data[i].boxCode+ '/';
	    					var editUrl = parametros.editUrl + '/'+data[i].boxCode+ '/';
	    					var activo="";
	    					if (data[i].pasive=='0'.charAt(0)) activo = '<span class="label label-success">'+parametros.simon;
	    					if (data[i].pasive!='0'.charAt(0)) activo = '<span class="label label-danger">'+parametros.nelson;
	    					table.fnAddData(
	    							['<a href='+ boxUrl + '>'+data[i].boxCode+'</a>','<a href='+ boxUrl + '>'+data[i].boxName+'</a>'
	    							 ,data[i].boxStudy.studyName,data[i].boxAlicType,data[i].boxAlicUse,data[i].boxTemp,data[i].boxPosition,data[i].boxCapacity
	    							 ,data[i].boxPriority,activo,data[i].recordUser,d.yyyymmdd(),'<a href='+ boxUrl + ' class="btn btn-default btn-xs"><i class="fa fa-search"></i></a>'
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
        }
    };

}();