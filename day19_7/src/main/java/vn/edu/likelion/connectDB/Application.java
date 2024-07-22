package vn.edu.likelion.connectDB;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.likelion.connectDB.utils.HandleFile;
import vn.edu.likelion.connectDB.utils.MyConnect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Base64;
import java.util.Scanner;

public class Application {
	enum PERMISSION{
		THONG_KE_HOC_VIEN(1),DIEMDANH(2);
		private int value;

		private PERMISSION(int value) {
			this.value = value;
		}
	}
	private static final HandleFile handleFile = new HandleFile();
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private static MyConnect conn = null;
	private static boolean isLogin = false;
	private static int roleId;

	public static void main(String[] args) {
		// Destination file
		String studentList = "StudentsList.txt";
		Scanner sc = new Scanner(System.in);
//		diemDanh();

		login();
		boolean run = true;
		while (run) {
			System.out.println("Menu: ");
			System.out.println("1. Xem danh sach hoc vien");
			System.out.println("2. Xem hoc vien vang mat");
			System.out.println("3. Xem hoc vien co mat");
			System.out.println("4. Diem danh");
			System.out.println("5. Dang xuat");
			int choose = sc.nextInt();
			switch (choose) {
				case 1:
					viewListStudent("all");
					break;
				case 2:
					viewListStudent("0");
					break;
				case 3:
					viewListStudent("1");
					break;
				case 4:
					diemDanh();
					break;
				case 5:
					run = false;
					break;
			}
		}
	}

	public static void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Username: ");
		String username = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.next();

		String query = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
		ResultSet resultSet = null;
		PreparedStatement stat;
		try {
			conn = new MyConnect();
			stat = conn.openConnect().prepareStatement(query);
			String passwordEncode = Base64.getEncoder().encodeToString(password.getBytes());

			stat.setString(1, username);
			stat.setString(2, passwordEncode);
			resultSet = stat.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getString(3));
				roleId = Integer.parseInt(resultSet.getString(4));
			} else {
			 	login();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void writeFileToDb(BufferedReader br) {
		try {
			String line = br.readLine();
			while (line != null) {
				String ID = line.split("\t")[0];
				String fullname = line.split("\t")[1];
				String query = "INSERT INTO STUDENTS (id,fullname) VALUES (?,?)";
				PreparedStatement preparedStatement =
						conn.openConnect().prepareStatement(query);

				preparedStatement.setString(1, ID);
				preparedStatement.setString(2, fullname);
				preparedStatement.executeUpdate();

				line = br.readLine();
			}
			br.close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static void viewListStudent(String type) {
		String queryCheck = "SELECT * FROM ROLE_HAS_PERMISSIONS WHERE ROLE_ID=? AND PERMISSION_id = ?";
		PreparedStatement stat = null;
		ResultSet resultSet = null;
		conn = new MyConnect();

		try {
			stat = conn.openConnect().prepareStatement(queryCheck);
			stat.setString(1, String.valueOf(roleId));
			stat.setString(2, String.valueOf(PERMISSION.THONG_KE_HOC_VIEN.value));
			resultSet = stat.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Ban khong co quyen truy cap !");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = null;
		switch (type) {

			case "all":

				query = "SELECT * FROM STUDENTS";
				break;
			case "0":
				query = "SELECT * FROM STUDENTS WHERE STATUS = 0";
				break;
			case "1":
				query = "SELECT * FROM STUDENTS WHERE STATUS = 1";

				break;
		}

		try {
			stat = conn.openConnect().prepareStatement(query);
			resultSet = stat.executeQuery();
			while (resultSet.next()) {
				System.out.print("ID: " + resultSet.getString(1) + "\t");
				System.out.print("Name: " + resultSet.getString(2));
				System.out.println("\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void diemDanh() {

		String queryCheck = "SELECT * FROM ROLE_HAS_PERMISSIONS WHERE ROLE_ID=? AND PERMISSION_id = ?";
		String query = "SELECT * FROM STUDENTS";
		PreparedStatement stat = null;
		ResultSet resultSet = null;
		try {
			conn = new MyConnect();
			stat = conn.openConnect().prepareStatement(queryCheck);
			stat.setString(1, String.valueOf(roleId));
			stat.setString(2, String.valueOf(PERMISSION.DIEMDANH.value));
			resultSet = stat.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Ban khong co quyen !");
				return;
			}
			stat = conn.openConnect().prepareStatement(query);
			resultSet = stat.executeQuery();
			System.out.println("Diem danh sinh vien:");

			while (resultSet.next()) {
				Scanner sc = new Scanner(System.in);
				System.out.print(resultSet.getString(2) + ": ");
				String val = sc.nextLine();

                stat = conn.openConnect().prepareStatement("UPDATE STUDENTS SET status =? WHERE ID=?");
				stat.setString(1, val);
				stat.setString(2, resultSet.getString(1));
				stat.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
