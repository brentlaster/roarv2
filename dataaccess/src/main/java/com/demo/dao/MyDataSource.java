package com.demo.dao;

import java.sql.Connection;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


import javax.sql.*;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDataSource {
	
	private static MysqlDataSource mysqlDS = null;

		
    public static DataSource getMySQLDataSource() throws Exception {
    	Properties props = new Properties();
        InputStream is = null;
        
        if (mysqlDS != null) {
			return mysqlDS;	
		}
    
             
        try {
        	is = new URL("http://localhost:8080/static/db.properties").openStream();
            props.load(is);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
         } catch (Exception e) {
           e.printStackTrace();
            
            
        }
        return mysqlDS;
    }
    
    protected static Connection MysqlConnection() {
    	Connection conn = null;
    	try {
    		conn = MyDataSource.getMySQLDataSource().getConnection();
    		return conn;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return conn;
    }
    	
    

}
