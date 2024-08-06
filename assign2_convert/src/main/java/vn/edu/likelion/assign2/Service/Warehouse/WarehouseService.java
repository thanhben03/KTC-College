package vn.edu.likelion.assign2.Service.Warehouse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.assign2.Entity.ProductEntity;
import vn.edu.likelion.assign2.Entity.WarehouseEntity;
import vn.edu.likelion.assign2.Repository.Product.ProductRepository;
import vn.edu.likelion.assign2.Repository.Warehouse.WarehouseRepository;
import vn.edu.likelion.assign2.Service.BaseServiceInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService implements BaseServiceInterface<WarehouseEntity> {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public WarehouseEntity create(WarehouseEntity warehouseEntity) {
        return warehouseRepository.save(warehouseEntity);
    }

    @Override
    public WarehouseEntity update(int id, WarehouseEntity request) {
        Optional<WarehouseEntity> findWarehouseData = warehouseRepository.findById(id);
        WarehouseEntity _warehouseData = null;
        if (findWarehouseData.isPresent()) {
            _warehouseData = findWarehouseData.get();
            _warehouseData.setWarehouseName(request.getWarehouseName());
            _warehouseData.setUserId(request.getUserId());

        }
        return warehouseRepository.save(_warehouseData);
    }

    @Override
    public void delete(WarehouseEntity productEntity) {
        warehouseRepository.delete(productEntity);
    }

    @Override
    public List<WarehouseEntity> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<WarehouseEntity> getById(int id) {
        return warehouseRepository.findById(id);
    }

    public Boolean exportExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("Total");

        //init first row for label
        Row row = sheet1.createRow(0);
        // init first cell value for label
        Cell cell = row.createCell(1);
        cell.setCellValue("Warehouse Name");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Total Product");

        List<WarehouseEntity> warehouses;
        List<ProductEntity> listProducts = null;

        warehouses = warehouseRepository.findAll();
        int rowCount = 1;
        int cellCount = 1;
        int totalProduct = 0;
        for (WarehouseEntity w : warehouses) {
            listProducts = productRepository.findAllByWarehouseId(w.getId());
            int totalProductInWarehouse = listProducts.size();

            // create row inside sheet
            row = sheet1.createRow(rowCount);

            // create cell and insert value
            cell = row.createCell(cellCount);
            cell.setCellValue(w.getWarehouseName());
            cell2 = row.createCell(++cellCount);
            cell2.setCellValue(totalProductInWarehouse);
            rowCount++;
            cellCount = 1;
            totalProduct += totalProductInWarehouse;
        }
        row = sheet1.createRow(rowCount);
        cell = row.createCell(0);
        cell.setCellValue("Total");
        cell2 = row.createCell(1);
        cell2.setCellValue(warehouses.size());
        Cell cell3 = row.createCell(2);
        cell3.setCellValue(totalProduct);


        // create sheet 2
        Sheet sheet2 = workbook.createSheet("ChiTiet");
        sheet2 = writeToSheet2(sheet2, warehouses);

        // tạo ra 1 file excel vật lý
        exportToExcel(workbook, "admin.xlsx");

        return true;
    }

    private Sheet writeToSheet2(Sheet sheet, List<WarehouseEntity> listWarehouses)
    {

        //init first row for label
        Row rowSheet2 = sheet.createRow(0);
        // init first cell value for label
        Cell cell = rowSheet2.createCell(1);
        cell.setCellValue("Product Name");
        Cell cell2 = rowSheet2.createCell(2);
        cell2.setCellValue("Product Description");
        Cell cell3 = rowSheet2.createCell(3);
        cell3.setCellValue("Product Quantity");
        Cell cell4 = rowSheet2.createCell(4);
        cell4.setCellValue("Product Price");

        int rowStart = 1;

        Row rowContinue = null;
        int totalPrice = 0; // count total price all product
        int totalQuantity = 0; // count total quantity all product

        for (WarehouseEntity w : listWarehouses) {
            int cellStart = 1;
            rowContinue = sheet.createRow(rowStart);

            Cell cellLabel = rowContinue.createCell(0);
            cellLabel.setCellValue(w.getWarehouseName()); // set warehouse name

            // get product of warehouse
            List<ProductEntity> listProducts = productRepository.findAllByWarehouseId(w.getId());

            for (ProductEntity p : listProducts) {

                // create cell product name and set value
                Cell cellName = rowContinue.createCell(1);
                cellName.setCellValue(p.getProductName());

                // create cell product desc and set value
                Cell cellDesc =  rowContinue.createCell(2);
                cellDesc.setCellValue(p.getProductDesc());

                // create cell product quantity and set value
                Cell cellQuantity =  rowContinue.createCell(3);
                cellQuantity.setCellValue(p.getProductQuantity());

                // create cell product price and set value
                Cell cellPrice =  rowContinue.createCell(4);
                cellPrice.setCellValue(p.getProductPrice());
                rowStart++; // create new row when insert 1 product
                rowContinue = sheet.createRow(rowStart);

                totalPrice += p.getProductPrice(); // calculate price
                totalQuantity += p.getProductQuantity(); // calculate quantity
            }
            rowStart++;

        }

        // create row footer
        rowContinue = sheet.createRow(rowStart);
        Cell totalCell = rowContinue.createCell(2);
        totalCell.setCellValue("Total");

        Cell cellTotalQuantity = rowContinue.createCell(3);
        cellTotalQuantity.setCellValue(totalQuantity);

        Cell cellTotalPrice = rowContinue.createCell(4);
        cellTotalPrice.setCellValue(totalPrice);

        return sheet;
    }

    private void exportToExcel(Workbook workbook, String nameFile) {
        try {
            FileOutputStream fos = new FileOutputStream(nameFile);
            workbook.write(fos);
            System.out.println("Exported excel file successfully !");

            workbook.close();
            fos.close();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

}
