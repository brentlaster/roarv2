package com.demo.dao;

import java.sql.*;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import com.demo.util.ToJSON;

public class SchemaRegistry extends MyDataSource {
	
	public int insertIntoAgents(String aName,
								String aSpecies,
								String aSrvStart,
								String aSrvLast,
								String advName,
								String advTech)
			throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		Response resp = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			
			conn = MysqlConnection()	;
			query = conn.prepareStatement("insert into agents (NAME, SPECIES, SRVSTART, SRVLAST, ADVNAME, ADVTECH) " +
					"values(?, ?, ?, ?, ?, ?)");
		
		   
			query.setString(1, aName);
			query.setString(2, aSpecies);	
			
			query.setDate(3, Date.valueOf(aSrvStart));			
			query.setDate(4, Date.valueOf(aSrvLast));
			query.setString(5, advName);
			query.setString(6, advTech);

			query.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		return 200;
		
	}
	
	public int deleteFromAgents(String aName, String aSpecies) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			
			/* TO-DO: Implement Data Validation */

			conn = MysqlConnection();
			query = conn.prepareStatement("delete from agents where NAME = ? and SPECIES = ?");
			query.setString(1, aName);
			query.setString(2, aSpecies);

			
			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close();
		}
		return 200;

	}
		
	
	
	public JSONArray queryReturnSpeciesAgents(String species)throws Exception {
		
	
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		Response resp = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			
			conn = MysqlConnection()	;
			query = conn.prepareStatement("select NAME, SPECIES, SRVSTART, SRVLAST, ADVNAME, ADVTECH " +
			"from agents " +
			"where UPPER(SPECIES) = ? ");
		   
			query.setString(1, species.toUpperCase()); 
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
						
			query.close();
			
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		return json;
		
	}
	
	public JSONArray queryReturnSpeciesNameAgents(String species, String name) throws Exception {
		
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		Response resp = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			
			conn = MysqlConnection()	;
			query = conn.prepareStatement("select NAME, SPECIES, SRVSTART, SRVLAST, ADVNAME, ADVTECH " +
			"from agents " +
			"where UPPER(SPECIES) = ? " +
			"and UPPER(NAME) = ? ");
		   
			query.setString(1, species.toUpperCase()); 
			query.setString(2, name.toUpperCase()); 
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
						
			query.close();
			
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		return json;
		
	}
	
	public int updateAgentLastServiceDate(String aName, String srvLast) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			
			// to-do data validation
			
			conn = MysqlConnection();
			query = conn.prepareStatement("update Agents " +
										  "set SRVLAST = ? " +
										  "where NAME = ? ");

			query.setString(1, srvLast); 
			query.setString(2, aName.toUpperCase()); 
			query.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		return 200;
	
}

}
