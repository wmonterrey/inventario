var SearchMessage = function () {

   
    
    return {
        //main function to initiate the module
        init: function (parametros) {
        	//var pageContent = $('.page-content');
            var form1 = $('#search-message-form');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);  
            
            $('#messageParameter').change(
            		function() {
            			form1.submit();
                    });
            
            var table  = $('#lista_mesajes').DataTable({
                // set the initial value
            	"bPaginate": false,
            	"aaSorting": [[ 0, "asc" ]] ,
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
                	messageParameter: {
                		minlength:2,
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
                    searchMessages();
                }
            });
            
            function searchMessages()
        	{
            	App.blockUI();
            	$.getJSON(parametros.messagesUrl, {
            		messageParameter : $('#messageParameter').val(),
    				ajax : 'true'
    			}, function(data) {
    				var len = data.length;
    				for ( var i = 0; i < len; i++) {
    					var editUrl = parametros.editUrl + '/'+data[i].messageKey+ '/';
    					table.fnAddData(
    							['<a href='+ editUrl + '>'+data[i].messageKey+'</a>',data[i].spanish,data[i].english,'<a href='+ editUrl + ' class="btn btn-default btn-xs"><i class="fa fa-edit"></i></a>']);
    				}
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