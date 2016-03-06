package com.demo.pipeline.registry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;	


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;

import com.demo.dao.MyDataSource;
import com.demo.util.ToJSON;

@Path("v1/registry")
public class V1_registry {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllResponders() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		Response resp = null;
		
		try {
			
			conn = MyDataSource.getMySQLDataSource().getConnection();
			query = conn.prepareStatement("select * from agents");
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
						
			query.close();
			
			returnString = json.toString();
			resp = Response.ok(returnString).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return resp;
	}
	

	
	
	
}
