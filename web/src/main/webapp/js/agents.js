/**
 * js file for post.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */
$(document).ready(function() {
	
	
	getRegistry2()


});



function getRegistry2() {
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:9999/web/api/v1/registry", 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
	$('#get_registry2').dataTable({
	    "aaData": data,
	        "aoColumns": [
	       { className: "dt-body-center",
	         "mDataProp": "id"
	    }, {
	        "mDataProp": "NAME"
	    }, {
	        "mDataProp": "SPECIES"
	    }, {
	        "mDataProp": "SRVSTART"
	    }, { 
	        "mDataProp": "SRVLAST"
	    }, {
	    	"mDataProp": "ADVNAME"
	    },{
	    	"mDataProp": "ADVTECH"
	    }]
	});
	
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
	

}


