/**
 * js file for delete.html
 * Please use modern web browser as this file will not attempt to be
 * compatible with older browsers. Use Chrome and open javascript console
 * or Firefox with developer console.
 * 
 * jquery is required
 */

var domainName = location.protocol + '//' + location.host + '/' + location.pathname.split('/')[1];

$(document).ready(function() {
	
	getRegistry();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, AGENT_PK = $this.val()
			, obj = {AGENT_PK : AGENT_PK}
			, $tr = $this.closest('tr')
			, AGENT_NAME = $tr.find('.CL_AGENT_NAME').text()			
			, AGENT_SPECIES = $tr.find('.CL_AGENT_SPECIES').text();
	
	
		deleteAgent(obj, AGENT_NAME, AGENT_SPECIES);
	});
});

function deleteAgent(obj, agent, species) {
	
	ajaxObj = {  
			type: "DELETE",
			url: domainName + "/api/v3/registry/" + species + "/" + agent,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				console.log(data);
				$('#delete_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				console.log( XMLHttpRequest.getAllResponseHeaders() );
				getRegistry();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}




function getRegistry() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:9999/web/api/v1/registry", 
			data: "ts="+n, 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					console.log(val1);
					html_string = html_string + templateGetRegistry(val1);
					console.log(html_string);
				});
				
				$('#get_registry').html("<table id=\"agentData\" border='1'><tr><thead><th>Name</th><th>Species</th></thead></tr>" + html_string + "</table>");
			
		
				
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function templateGetRegistry(param) {
	return '<tr>' +
				'<td class="CL_AGENT_NAME">' + param.NAME + '</td>' +
				'<td class="CL_AGENT_SPECIES">' + param.SPECIES + '</td>' +
				'<td class="CL_SERVICE_START">' + param.SRVSTART + '</td>' +
				'<td class="CL_SERVICE_LAST">' + param.SRVLAST + '</td>' +
				'<td class="CL_ADVERSARY_NAME">' + param.ADVNAME + '</td>' +
				'<td class="CL_ADVERSARY_TECH">' + param.ADVTECH + '</td>' +
				'<td class="CL_AGENT_UPDATE_BTN"> <button class="DELETE_BTN" value=" ' + param.AGENT_PK + ' " type="button">Delete</button> </td>' +
			'</tr>';
}

