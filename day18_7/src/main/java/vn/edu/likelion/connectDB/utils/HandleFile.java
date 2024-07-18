package vn.edu.likelion.connectDB.utils;

import java.io.*;
import java.util.ArrayList;

public class HandleFile {
    private static int count = 0;
    public ArrayList<String> listStudents = new ArrayList<>();

    public BufferedReader createBufferedReader(String nameFile) throws FileNotFoundException {
        FileReader fr =  new FileReader(nameFile);

        return new BufferedReader(fr);
    }

    public BufferedWriter createBufferedWriter(String nameFile) throws IOException {
        FileWriter fw =  new FileWriter(nameFile);

        return new BufferedWriter(fw);
    }

}
