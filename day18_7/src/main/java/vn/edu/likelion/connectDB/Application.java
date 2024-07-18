package vn.edu.likelion.connectDB;


import vn.edu.likelion.connectDB.utils.HandleFile;
import vn.edu.likelion.connectDB.utils.MyConnect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Application {
	private static final HandleFile handleFile = new HandleFile();
	private static MyConnect conn = null;

	public static void main(String[] args) {
		// Destination file
		String studentList = "StudentsList.txt";

		// Read file
//		try {
//			BufferedReader br = handleFile.createBufferedReader(studentList);
//			conn = new MyConnect();
//			writeFileToDb(br);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}

//		viewListStudent();
		diemDanh();
//		String query = "SELECT * FROM USERS";
//
//		PreparedStatement stat = null;
//		ResultSet resultSet = null;
//		try {
//			conn = new MyConnect();
//			stat = conn.openConnect().prepareStatement(query);
//			resultSet = stat.executeQuery();
//
//			while (resultSet.next()) {
//				System.out.println(resultSet.getString(2));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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

	public static void viewListStudent() {
		String query = "SELECT * FROM STUDENTS";
		PreparedStatement stat = null;
		ResultSet resultSet = null;
		try {
			conn = new MyConnect();
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
		String query = "SELECT * FROM STUDENTS";
		PreparedStatement stat = null;
		PreparedStatement stat2 = null;
		ResultSet resultSet = null;
		try {
			conn = new MyConnect();
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
