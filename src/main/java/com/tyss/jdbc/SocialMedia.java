package com.tyss.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialMedia {
	Connection connection=new UserConnectionObject().getConnectiponObject();
	User user;

	public SocialMedia(User user) {
		this.user = user;
	}

	public  void saveUserData(User user) throws Exception {
		PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO USER VALUE(?,?,?,?,?,?,?)");
		preparedStatement.setString(1, user.getFirst_name());
		preparedStatement.setString(2, user.getLast_name());
		preparedStatement.setString(3,user.getGender());
		preparedStatement.setLong(4,user.getContact_number());
		preparedStatement.setString(5, user.getDate_of_birth());
		preparedStatement.setString(6, user.getEmail_id());
		preparedStatement.setString(7,user.getPassword());


		preparedStatement.executeUpdate();

		connection.close();

		System.out.println("data saved successfully");
	}

	public void deleteUserData() {

	}
	public void upDateUserData(long phonenum,User user) throws Exception {
		PreparedStatement preparedStatement=connection.prepareStatement("UPDATE USER SET PHONENUM = ? WHERE emailid=? ");
		preparedStatement.setString(1, user.getEmail_id());
		preparedStatement.setLong(2, user.getContact_number());

		preparedStatement.executeUpdate();
	}
	public void retriveUserDataById(String email,User user) throws Exception {
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM USER WHERE EMAILID=?");
		preparedStatement.setString(1, user.getEmail_id());
		ResultSet resultSet=preparedStatement.executeQuery();

		while(resultSet.next()) {
			System.out.println("first name"+resultSet.getString(1));
			System.out.println("last name"+resultSet.getString(2));
			System.out.println("gender"+resultSet.getString(3));
			System.out.println("contact number"+resultSet.getLong(4));
			System.out.println("date of birth"+resultSet.getString(5));
			System.out.println("email id"+resultSet.getString(6));
			System.out.println("password is"+resultSet.getString(7));
			System.out.println("------------------------");
		}


	}
	public  void retriveUserDataCompletly(String email) throws Exception {
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM USER WHERE EMAILID=?");
		preparedStatement.setString(1, email);
		ResultSet resultSet=preparedStatement.executeQuery();

		while(resultSet.next()) {
			System.out.println("FIRST NAME      : "+resultSet.getString(1));
			System.out.println("LAST NAME       : "+resultSet.getString(2));
			//			System.out.println("GENDER          : "+resultSet.getString(3));
			//			System.out.println("CONTACT  NUMBER : "+resultSet.getLong(4));
			//			System.out.println("DATE OF BIRTH   : "+resultSet.getString(5));
			//			System.out.println("EMAIL ID        : "+resultSet.getString(6));
			//			System.out.println("PASSWORD        : "+resultSet.getString(7));
			System.out.println("------------------------");
		}


	}

	public boolean login(String uid,String pw,User user) throws Exception {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE EMAILID=? ");
		preparedStatement.setString(1, uid);
		ResultSet resultset= preparedStatement.executeQuery();
		String passd="";
		String emaild="";
		while(resultset.next()) {
			emaild=resultset.getString(6);
			passd=resultset.getString(7);
		}
		
		if(uid.equalsIgnoreCase(emaild)&&pw.equalsIgnoreCase(passd)) {
			return true;
		}
		
		return false;
		
 
	}

	public void Signup() throws Exception {
		saveUserData(user);
	}

	public void Logout() {
		System.exit(0);
	}

}
