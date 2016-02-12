package com.demo.pipeline.status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.sql.*;

import com.demo.dao.*;

@Path("/v1/status")
public class V1_status {
	
	private static final String api_version = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version</p>" + api_version;
	}

	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			
			conn = MyDataSource.getMySQLDataSource().getConnection();
			query = conn.prepareStatement("select * from comments");
			ResultSet rs = query.executeQuery();
						
			while (rs.next()){
				myString = rs.getString("DATUM");
			}
			
			query.close();
			
			returnString = "<p>Database Status</p> " +
			     "<p>Database Date/Time return: " + myString + "</p>";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return returnString;
	}
	
}
