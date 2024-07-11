import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public File createFile(String nameFile) {
        return new File(nameFile);
    }

    public void backupFile(BufferedReader br) {

        try {
            String line = br.readLine();
            while (line != null) {
                String fullname = line.split("\t")[1];

                listStudents.add(fullname);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void copy(BufferedWriter bw) {
        try {
            for (int i = count; i < listStudents.size(); i++) {
                count++;
                bw.write(listStudents.get(i) + "\n");
                if (count == 4) {
                    bw.write("c\n");
                    count++;
                    break;
                }
            }
        }  catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
