/**
 * js file for post.html
 * 
 * jquery is required
 */

var domainName = location.protocol + '//' + location.host + '/' + location.pathname.split('/')[1];

$(document).ready(function() {
	
	var $put_agent_update = $('#put_agent_update')
		, $SET_AGENT_NAME = $('#SET_AGENT_NAME')
		, $SET_AGENT_SPECIES = $('#SET_AGENT_SPECIES');
	
	
	getRegistry()


	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		console.log(this);
		var $this = $(this)
			, AGENT_PK = $this.val()
			, $tr = $this.closest('tr')
			, AGENT_NAME = $tr.find('.CL_AGENT_NAME').text()			
			, AGENT_SPECIES = $tr.find('.CL_AGENT_SPECIES').text()
			, SERVICE_START = $tr.find('.CL_SERVICE_START').text()
			, SERVICE_LAST = $tr.find('.CL_SERVICE_LAST').text()
			, ADVERSARY_NAME = $tr.find('.CL_ADVERSARY_NAME').text()
			, ADVERSARY_TECH = $tr.find('.CL_ADVERSARY_TECH').text();
		
		$('#SET_AGENT_PK').val(AGENT_PK);
		$SET_AGENT_NAME.text(AGENT_NAME);
		$SET_AGENT_SPECIES.text(AGENT_SPECIES);
		$('#SET_SERVICE_START').text(SERVICE_START);
		$('#SET_SERVICE_LAST').text(SERVICE_LAST);
		$('#SET_ADVERSARY_NAME').text(ADVERSARY_NAME);
		$('#SET_ADVERSARY_TECH').text(ADVERSARY_TECH);
		document.getElementById('SET_AGENT_NAME').value= AGENT_NAME ; 
		document.getElementById('SET_SERVICE_LAST').value= SERVICE_LAST ; 
		
		$('#update_response').text("");
	});
	
	$put_agent_update.submit(function(e) {
		e.preventDefault(); //cancel form submit
		
		var obj = $put_agent_update.serializeObject()
			, AGENT_NAME = $SET_AGENT_NAME.text()
			, AGENT_SPECIES = $SET_AGENT_SPECIES.text();
		
		updateRegistry(obj, AGENT_NAME, AGENT_SPECIES);
	});
});

function updateRegistry(obj, name, species) {
	
	ajaxObj = {  
			type: "PUT",
			url: domainName + "/api/v3/registry/" + species + "/" + name,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				console.log(data);
				$('#update_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
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
				'<td class="CL_AGENT_UPDATE_BTN"> <button class="UPDATE_BTN" value=" ' + param.AGENT_PK + ' " type="button">Update</button> </td>' +
			'</tr>';
}

