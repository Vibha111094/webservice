package com.betsol.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class Database {
	
	private static Connection connection;
	private static Statement statement;
	
	public static void setUp() { 	   
		String DB_URL = "jdbc:mysql://localhost:3306/user";
        try {
			connection = DriverManager.getConnection(DB_URL, "root", "");
			statement = connection.createStatement();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	public void create_user(String data) { 
		setUp();	    
	    JSONObject jsonObject;	   
	      try {  
	    	 jsonObject = new JSONObject(data);
			statement.executeUpdate("INSERT INTO user_info VALUES "
					+ "('" + jsonObject.getString("user_name") + "', '"
							+ jsonObject.getString("passsword") + "', '"
							+ jsonObject.getString("phone_number") + "', '"
							+ jsonObject.getString("address") + "', '"
							+ jsonObject.getString("first_name") + "', '"
							+ jsonObject.getString("last_name") + "');");
	      } catch (SQLException e) {
			e.printStackTrace();
	      }
	}
	 
	
	 public static  Response retrieve_user(String name) throws SQLException {
	    	name = name.replaceAll("(\\n)", "");
	    	String one="'", two="'";
	    	JSONObject jobj = new JSONObject();
	    	Statement stmt =null;
	    	String  user_name1= null;
		   	String 	password= null ;
		    String 	phone_number= null ;
		    String  address= null ;
		    String  first_name= null ;
		    String  last_name= null ;
	    	String USER = "root";
			String PASS = "";
			setUp();
			String sql = "SELECT * FROM user_info WHERE user_name ='"+name+"'";
			
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
			    user_name1= rs.getString("user_name");
			    password= rs.getString("phone_number") ;
			    phone_number= rs.getString("password") ;
			    address= rs.getString("address") ;
			    first_name= rs.getString("first_name") ;
			    last_name= rs.getString("last_name") ;
			 }
			 rs.close();
			 jobj.put("user_name", user_name1);
			 jobj.put("phone_number",phone_number);
			 jobj.put("password", password);
			 jobj.put("address", address);
			 jobj.put("first_name", first_name);
			 jobj.put("last_name",last_name );
			 
		     return Response.ok(jobj.toString()).build();
	    }
	 
	 public static Response update_user(String name) {
	    	name = name.replaceAll("(\\n)", "");
	    	System.out.println(name);
	    	setUp();
			String sql1 = "UPDATE user_info SET `password` = 'surabhi'  WHERE  user_name='"+name+"'";
	        System.out.println(sql1);
			 try {
				 statement.executeUpdate(sql1); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			Response res = null;
			try {
				res = Database.retrieve_user(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
	    }
}





