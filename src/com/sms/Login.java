
	package com.sms;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	public class Login {

	    public boolean validate(String username, String password) {

	        try {

	            Connection con = DBConnection.getConnection();

	            String sql = "SELECT * FROM admin WHERE username=? AND password=?";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, username);
	            ps.setString(2, password);

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return false;
	    }
	}

