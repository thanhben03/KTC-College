package vn.edu.likelion.DemoOffice;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Main {
    private static File lists;
    public static final HandleFile handleFile = new HandleFile();
    private static String nameLists = "StudentsList.txt";

    public static void main(String[] args) {
        lists = handleFile.createFile(nameLists);

        try {
            BufferedReader brStudent = handleFile.createBufferedReader(nameLists);
            handleFile.handleFileToExcel(brStudent);
//            handleFile.readWorldFile();
            handleFile.readExcelFile();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}
