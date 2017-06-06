package org.nau.beangrinderUI.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.nau.beangrinderUI.model.Admin;
import org.nau.beangrinderUI.model.Instructor;


public class AdminDao {
	Database database;
	 Connection connection;
	
	 public AdminDao()  {
	 database= new Database();
	 try {
		connection = database.Get_Connection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public Admin getAdminDetails(String id, String pass) throws Exception
	{
		Admin admin = new Admin();
		try
		{
			PreparedStatement ps = 
					connection.prepareStatement("SELECT * FROM admin where adminId = ? and admin_password = ?");
			ps.setString(1,id);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				admin.setAdminId(rs.getString("adminId"));
				admin.setPassword(rs.getString("admin_password"));
				admin.setEmail(rs.getString("admin_email"));
				admin.setAdminName(rs.getString("admin_name"));
			}
			return admin;
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
}
