package com.demo.pipeline.registry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.demo.dao.SchemaRegistry;

@Path("/v3/registry")
public class V3_registry {
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAgent(String incomingData) throws Exception {
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray(); 
		JSONObject jsonObject = new JSONObject();		
		SchemaRegistry dao = new SchemaRegistry();
		
		try {
			
			JSONObject agentData = new JSONObject(incomingData);
			System.out.println("jsonData: " + agentData.toString());
			
			int http_code = dao.insertIntoAgents(agentData.optString("AGENT_NAME"), 
												 agentData.optString("AGENT_SPECIES"), 
												 agentData.optString("SERVICE_START"), 
												 agentData.optString("SERVICE_LAST"), 
												 agentData.optString("ADVERSARY_NAME"),
												 agentData.optString("ADVERSARY_TECH"));
			
			if( http_code == 200 ) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully, Version 3");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
			System.out.println("returnString: " + returnString);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	@Path("/{species}/{name}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAgent(@PathParam("species") String species,
								@PathParam("name") String name,
								String incomingData) throws Exception {
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray(); 
		JSONObject jsonObject = new JSONObject();		
		SchemaRegistry dao = new SchemaRegistry();
		String aName;
		String srvLast;
		
		try {
			
			JSONObject agentData = new JSONObject(incomingData);
			aName = agentData.optString("AGENT_NAME");
			srvLast = agentData.optString("SERVICE_LAST");
			
			System.out.println("jsonData: " + agentData.toString());
			
			int http_code = dao.updateAgentLastServiceDate(aName, srvLast);
			
			if( http_code == 200 ) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully, Version 3");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
			System.out.println("returnString: " + returnString);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}

	

	
	@Path("/{species}/{name}")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAgent(@PathParam("species") String species,
								@PathParam("name") String name,
								String incomingData) throws Exception {
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray(); 
		JSONObject jsonObject = new JSONObject();		
		SchemaRegistry dao = new SchemaRegistry();
			
		try {
			
			JSONObject agentData = new JSONObject(incomingData);
						
			System.out.println("jsonData: " + agentData.toString());
			
			int http_code = dao.deleteFromAgents(name, species);
			
			if( http_code == 200 ) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully");
			} else {
				return Response.status(500).entity("The server was not able to process the delete request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
			System.out.println("returnString: " + returnString);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process the delete request").build();
		}
		
		return Response.ok(returnString).build();
	}


}
