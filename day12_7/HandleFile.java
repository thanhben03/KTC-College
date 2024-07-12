package vn.edu.likelion.DemoOffice;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;

public class HandleFile {

    private LocalDate dt = LocalDate.now();
    private String nameExcelFile;
    private String nameWorldFile;

    // Function call
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    public void handleFileToExcel(BufferedReader br) {
        try {
            String line = br.readLine();
            ArrayList<String[]> listA = new ArrayList<>();
            ArrayList<String[]> listB = new ArrayList<>();

            while (line != null) {
                String[] strSplit = line.split("\t");
                String status = strSplit[2];
                if (status.equals("1")) {
                    listA.add(strSplit);
                } else {
                    listB.add(strSplit);
                }
                line = br.readLine();
            }
            br.close();
            copyToExcelFile(listA);
            copyToWord(listB);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyToExcelFile(ArrayList<String[]> data) {
        XSSFWorkbook workbook = new XSSFWorkbook(); // Tạo một workbook mới
        Sheet sheet = workbook.createSheet("Sheet1"); // Tạo một sheet mới
        for (int i = 0; i < data.size(); i++) {
            int cellNumber = 0;
            Row row = sheet.createRow(i); // Tạo một hàng mới

            Cell cell1 = row.createCell(cellNumber); // Tạo các ô và đặt giá trị
            cell1.setCellValue(data.get(i)[0]);
            cellNumber++;

            Cell cell2 = row.createCell(cellNumber);
            cell2.setCellValue(
                    Base64.getEncoder()
                            .encodeToString(data.get(i)[1].getBytes())
            );
        }

        try {
            nameExcelFile = "diemdanh_"+convertTimeToString()+".xlsx";
            FileOutputStream out =
                    new FileOutputStream(nameExcelFile); // Ghi ra file
            workbook.write(out);
            workbook.close();
            System.out.println("Đã tạo file Xlsx thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyToWord(ArrayList<String[]> data) {
        try {
            XWPFDocument document = new XWPFDocument(); // Tạo 1 tài liệu mới
            for (int i = 0; i < data.size(); i++) {
                // Tạo 1 đoạn văn bản và thêm nội dung
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(
                        data.get(i)[0] + "\t" +
                        Base64.getEncoder()
                        .encodeToString(data.get(i)[1].getBytes())
                );
            }
            // Ghi ra file output.docx
            nameWorldFile = "diemdanh_"+convertTimeToString()+".docx";
            FileOutputStream out = new FileOutputStream(nameWorldFile);
            document.write(out);
            document.close(); // Đóng tài liệu
            System.out.println("Đã tạo file docx thành công!");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String convertTimeToString() {
        int day = dt.getDayOfMonth();
        int month = dt.getMonthValue();
        int year = dt.getYear();
        return ""+day+month+year;
    }

    public void readExcelFile() {
        try {
            // lấy file excel vật lý
            FileInputStream fis = new FileInputStream(new File("output.xlsx"));

            // tạo ra 1 cái workbook từ file vật lý
            Workbook workbook = new XSSFWorkbook(fis);

            // lấy cái sheet ở trong workbook trên
            Sheet sheet = workbook.getSheetAt(0);

            // duyệt từng row trong sheet
            for (Row row : sheet) {
                // duyệt từng cell trong row
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.println(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            System.out.println(cell.getDateCellValue() + "\t");
                            break;
                    }
                }
            }
            workbook.close();
            fis.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void readWorldFile() {
        // Mở tài liệu output.docx đã tạo từ bước trước
        File file = new File(nameWorldFile);
        try {
            InputStream is = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(is);

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String[] strSplit = paragraph.getText().split("\t");

                System.out.println(strSplit[0] + "\t" + giaiMa(strSplit[1]));
            }

            document.close();
            is.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String giaiMa(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    }
}
