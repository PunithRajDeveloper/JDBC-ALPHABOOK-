package com.tyss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class User {

	private String first_name;
	private String last_name;
	private String gender;
	private long contact_number;
	private String date_of_birth;
	private String email_id;
	private String password;

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getContact_number() {
		return contact_number;
	}
	public void setContact_number(long contact_number) {
		this.contact_number = contact_number;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	Scanner scanner=new  Scanner(System.in);

	LocalDate date=null;
	String oldStatus="";
	String newStatus="";


	Status status=new Status();

	String o="";
	String n="";

	public void saveStatus(Status status)throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphabook", "root","root");
		PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO STATUS VALUES(?,?)");
		preparedStatement.setString(1,status.getNewStatus());
		preparedStatement.setString(2,status.getEmailid());

		preparedStatement.executeUpdate();

		connection.close();

	}

	public void updateStatus(String st,String uid,Status status)throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphabook", "root","root");
		PreparedStatement preparedStatement=connection.prepareStatement("UPDATE STATUS SET STATUS = ? WHERE emailid=?");
		preparedStatement.setString(1,status.getNewStatus());

		preparedStatement.execute();

		connection.close();
	}
	public String getStatus(String uid,Status status) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphabook", "root","root");
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM STATUS WHERE EMAILID=?");
		preparedStatement.setString(2,status.getEmailid());
		ResultSet resultSet=preparedStatement.executeQuery();

		while(resultSet.next()) {
			o = resultSet.getString(2);
		}
		preparedStatement.executeUpdate();
		connection.close();
		return o;
	}

	public void deleteStatus(String email,User user) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphabook", "root","root");
		PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM STATUS WHERE id = ?");
		preparedStatement.setString(1,status.getEmailid());

		preparedStatement.executeUpdate();

		connection.close();
	}
	public void postStatus()  {
		date=LocalDate.now();
		System.out.println("enter your status hear");
		newStatus=scanner.nextLine();
//		System.out.println(getStatus("status : "+email_id, status)+"\n "+date);
		System.out.println(" your Status : "+newStatus+"\n date "+date );

	}

	public void Profile(String email,User user){
		try {
			SocialMedia media =new SocialMedia(user);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/alphabook", "root", "root");
			PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM USER WHERE EMAILID=?");
			preparedStatement.setString(1,email);
			ResultSet resultSet=preparedStatement.executeQuery();

			while(resultSet.next()) {
				System.out.println("FIRST NAME      : "+resultSet.getString(1));
				System.out.println("LAST NAME       : "+resultSet.getString(2));
				System.out.println("GENDER          : "+resultSet.getString(3));
				System.out.println("CONTACT  NUMBER : "+resultSet.getLong(4));
				System.out.println("DATE OF BIRTH   : "+resultSet.getString(5));
				System.out.println("EMAIL ID        : "+resultSet.getString(6));
				System.out.println("PASSWORD        : "+resultSet.getString(7));
				System.out.println("------------------------");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
