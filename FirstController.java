package com.application.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class FirstController {

	 @Autowired  
	    JdbcTemplate jdbc;   
	



	@PostMapping("/api/doctors/register")
	public String doc_registration(String name,String specialization,String contact_number)
	{
	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_Manag", "root", "Avantika@255");
		PreparedStatement stmt = con.prepareStatement("insert into Doctor_info(name,specialization,contact_number) values (?,?,?)");
		stmt.setString(1, name);
		stmt.setString(2, specialization);
		stmt.setString(3, contact_number);
		int i =stmt.executeUpdate();
		if(i>0)
		{
			return "You have Registered Successfuliy" +  "'"+name+"'";
		}
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "";
	}

	
	
	@PostMapping("/api/doctors/login")
	public String login(int doctor_id,String name)
	{
	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_Manag", "root", "Avantika@255");
		PreparedStatement stmt = con.prepareStatement("select ?,? from Doctor_info where doctor_id='"+doctor_id+"'");
		stmt.setInt(1,doctor_id);
		stmt.setString(2, name);
		ResultSet rs =stmt.executeQuery();
		if(rs.next())
		{
			int id1 = rs.getInt(doctor_id);
			String Doc_name = rs.getString("name");
			if(doctor_id==id1 && Doc_name.equals(name))
			{
				return "You have Loggin Successfuliy";
			}
		}
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "";
	}
	
	
	
	@PostMapping("api/patients/register")
	public String patients_register(String name,String age,String gender,String contact_number)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_Manag", "root", "Avantika@255");
			PreparedStatement stmt=con.prepareStatement("insert into patient_info values(?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, age);
			stmt.setString(3, gender);
			stmt.setString(4, contact_number);
			int i = stmt.executeUpdate();
			if(i==0)
			{
				return "You have Registered Successfully";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	@PostMapping("/api/Book_appoinment")
	public String appointments(int patient_id, int doctor_id,Timestamp appo_datetime ,String status)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_Manag", "root", "Avantika@255");
			PreparedStatement stmt = con.prepareStatement("insert into appointments (patient_id, doctor_id, appo_datetime, status) VALUES (?, ?, ?, ?)");
			stmt.setInt(1, patient_id);
			stmt.setInt(2, doctor_id);
			stmt.setTimestamp(3, appo_datetime);
			stmt.setString(4, status);
			int i = stmt.executeUpdate();
			if(i>0)
			{
				return "Appointment is done";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	
	@GetMapping("/api/create_report")
	public String create_report(int patient_id,int doctor_id,String description)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_Manag", "root", "Avantika@255");
			PreparedStatement stmt =con.prepareStatement("insert into reports (patient_id, doctor_id, description) VALUES (?, ?, ?)");
			stmt.setInt(1, patient_id);
			stmt.setInt(2, doctor_id);
			stmt.setString(3, description);
			int i = stmt.executeUpdate();
			if(i>0)
			{
				return "Report subitted";
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
	@GetMapping("/api/report/{patient_id}")
	public String repor(@PathVariable("patient_id")int patient_id)
	{
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_Manag", "root", "Avantika@255");
			PreparedStatement stmt = con.prepareStatement("select patient_id,doctor_id,description from reports where patient_id = '"+patient_id+"'");
		   ResultSet rs = stmt.executeQuery();
		   while(rs.next())
		   {
			   int patient =rs.getInt("patient_id");
			   int doctor = rs.getInt("doctor_id");
			   String descri = rs.getString("description");
			   return  "Patient_id: " +patient + " " + "Doctor_id: " + doctor + " " + "'"+descri+"'";
		   }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	}	







