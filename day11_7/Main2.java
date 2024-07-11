import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    private static Scanner sc = new Scanner(System.in);
    private static final HandleFile handleFile = new HandleFile();
    private static int count = 0;

    public static void main(String[] args) {
        try {
            File lists = handleFile.createFile("StudentsList.txt");
            BufferedReader brStudent = handleFile.createBufferedReader("StudentsList.txt");

            BufferedWriter bwAhihi = handleFile.createBufferedWriter("ahihi.txt");

            handleFile.backupFile(brStudent);
            handleFile.copy(bwAhihi);
            handleFile.copy(bwAhihi);
            lists.delete();
            bwAhihi.close();

//            lists.delete();
//            copy();
//            copy();
//            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }






}
