import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
//            System.out.print("Enter name file: ");
//            Scanner sc = new Scanner(System.in);
//            String nameFile = sc.nextLine();
//            FileWriter myWriter = new FileWriter(nameFile);

            // Destination file
            String studentList = "StudentsList.txt";

            // Read file
            FileReader fr = new FileReader(studentList);
            BufferedReader br = new BufferedReader(fr);

            // Initial Buffered Writer
            FileWriter writer = new FileWriter("ahihi.txt");
            BufferedWriter bw = new BufferedWriter(writer);

            int i;
            StringBuilder strBulder = new StringBuilder();

            while ((i = br.read()) != -1) {
                    strBulder.append((char)i);
            }
            String[] strSplit = strBulder.toString().split("\n");
            for (String s : strSplit) {
                String fullname = s.split("\t")[1];
                bw.write(fullname);
            }
            bw.close();
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
