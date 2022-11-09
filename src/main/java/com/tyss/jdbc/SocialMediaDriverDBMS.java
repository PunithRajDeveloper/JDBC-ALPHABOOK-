package com.tyss.jdbc;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SocialMediaDriverDBMS {

	public static void alphabook() {
		System.out.println("************ALPHABOOK***********");
		System.out.println("1.LOGIN");
		System.out.println("2.SIGNUP");
		System.out.println("3.EXIT");
		System.out.println("ENTER YOUR CHOICE");
	}
	public static void main(String[] args) throws Exception {

		Scanner scanner= new Scanner(System.in);
		//		System.out.println("enter  your first_name");
		//		String firstName=scanner.next();
		//		System.out.println("enter last name");
		//		String lastName=scanner.next();
		//		System.out.println("enter your gender");
		//		String gender=scanner.next();
		//		System.out.println("enter contact number");
		//		long number=scanner.nextLong();
		//		System.out.println("enter date of birth");
		//		String dateofbirth=scanner.next();
		//		System.out.println("enter email id");
		//		String email=scanner.next();
		//		System.out.println("enter password");
		//		String password=scanner.next();



		Status status=new Status();


		User user=new User();
		//		user.setContact_number(number);
		//		user.setDate_of_birth(dateofbirth);
		//        user.setEmail_id(email);
		//        user.setGender(gender);
		//        user.setLast_name(lastName);
		//        user.setFirst_name(firstName);
		//        user.setPassword(password);
		Connection connection=new UserConnectionObject().getConnectiponObject();

		LocalDateTime date;

		SocialMedia media=new SocialMedia(user);

		SocialMediaDriverDBMS.alphabook();
		int choice=scanner.nextInt();

		switch(choice) {
		case 1:{
			System.out.println("ENTER EMAIL ID");
			String email=scanner.next();
			System.out.println("ENTER PASSWORD");
			String password=scanner.next();
			boolean b;
			try {
				b = media.login(email, password, user);
				if(b!=false) {
					date=LocalDateTime.now();
					System.out.println("********LOGIN SUCCESSFULL WELLCOME TO APLHA BOOK*******");
					System.out.println("LOGIN DATE :"+date);
					media.retriveUserDataCompletly(email);
					System.out.println(" 1 : POST STATUS");
					System.out.println(" 2 : PROFILE ");
					int choice2=scanner.nextInt();
					if(choice2==1) {
//						System.out.println("enter status hear");
//						String nstatus=scanner.nextLine();
//						System.out.println("enter emailid");
//						String email2=scanner.nextLine();
//
//						status.setEmailid(email2);
//						status.setNewStatus(nstatus);
						user.postStatus();
					}
					if(choice2==2) {
						user.Profile(email,user);
					}
					System.out.println("***********");

				}

				else 
			    System.out.println("***LOGIN UNSUCCESSFULL DUE TO INVALID PASSWOR OR EMAIL ID PLEASE GO THROUGH THE BELOW OPTIONS***");
				SocialMediaDriverDBMS.alphabook();
				System.out.println("**********************");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		break;

		case 2:{
			try {
				System.out.println("ENTER YOUR FIRST NAME");
				String firstName=scanner.next();
				System.out.println("ENTERER YOUR LAST NAME");
				String lastName=scanner.next();
				System.out.println("ENTER YOUR GENDER");
				String gender=scanner.next();
				System.out.println("ENTER CONTACTNUMBER");
				long number=scanner.nextLong();
				System.out.println("ENTER DATAE OF BIRTH");
				String dateofbirth=scanner.next();
				System.out.println("ENTER MAIL ID");
				String email=scanner.next();
				System.out.println("ENTER PASSWORD");
				String password=scanner.next();
				user.setContact_number(number);
				user.setDate_of_birth(dateofbirth);
				user.setEmail_id(email);
				user.setGender(gender);
				user.setLast_name(lastName);
				user.setFirst_name(firstName);
				user.setPassword(password);
				System.out.println("1.SUBMIT");
				System.out.println("2.CANCLE");
				System.out.println("ENTER YOUR CHOICE");
				int choice3=scanner.nextInt();
				if(choice3==1) {
					media.Signup();
				}
				else {
					System.out.println("*****REINNITIATE THE SIGNHUP PROSSES**********");
					SocialMediaDriverDBMS.alphabook();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		break;
		case 3:{
			try {
				System.out.println("******LOGOUT SUCCESSFULL*******");
				media.Logout();
				connection.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		}





	}
}

