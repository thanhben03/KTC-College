package vn.edu.likelion.assign2.Service.Product;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.assign2.Entity.ProductEntity;
import vn.edu.likelion.assign2.Entity.UserEntity;
import vn.edu.likelion.assign2.Entity.WarehouseEntity;
import vn.edu.likelion.assign2.Model.ErrorHandler;
import vn.edu.likelion.assign2.Repository.Product.ProductRepository;
import vn.edu.likelion.assign2.Repository.User.UserRepository;
import vn.edu.likelion.assign2.Repository.Warehouse.WarehouseRepository;
import vn.edu.likelion.assign2.Service.BaseServiceInterface;
import vn.edu.likelion.assign2.Utils.Helper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService implements BaseServiceInterface<ProductEntity> {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public ProductEntity create(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity update(int id, ProductEntity request) {
        Optional<ProductEntity> findUserData = productRepository.findById(id);
        ProductEntity _productData = null;
        if (findUserData.isPresent()) {
            _productData = findUserData.get();
            _productData.setProductName(request.getProductName());
            _productData.setProductDesc(request.getProductDesc());
            _productData.setProductPrice(request.getProductPrice());
            _productData.setProductQuantity(request.getProductQuantity());
            _productData.setWarehouseId(request.getWarehouseId());
        }
        return productRepository.save(_productData);
    }

    @Override
    public void delete(ProductEntity productEntity) {
        productRepository.delete(productEntity);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> getById(int id) {
        return productRepository.findById(id);
    }

    public List<ProductEntity> findAllByWarehouseId(int warehouseId) {
        return productRepository.findAllByWarehouseId(warehouseId);
    }

    public Boolean importExcel(Map<String, Object> payload) {
        String filename = payload.get("filename").toString();
        int warehouseId = (int) payload.get("warehouseId");
        int userId = (int) payload.get("userId");

        try {
            Optional<UserEntity> userExist = userRepository.findById(userId);
            Optional<WarehouseEntity> warehouse = warehouseRepository.findById(warehouseId);

            if (warehouse.isEmpty()) {
                throw new ErrorHandler("Warehouse not found !");

            }
            if (userExist.isEmpty()) {
                throw new ErrorHandler("User not found !");
            }
            Sheet sheet = Helper.getSheet(filename);
            for (int i = 5; ;i++) {
                // Get first cell
                Cell firstCellOfRow = sheet.getRow(i).getCell(0);

                // check if row empty
                if (firstCellOfRow.toString().isEmpty()) {
                    break;
                }

                String productName = sheet.getRow(i).getCell(1).toString(); // get product name
                String desc = sheet.getRow(i).getCell(2).toString(); // get desc
                String quantity = sheet.getRow(i).getCell(3).toString(); // get quantity product
                String price = sheet.getRow(i).getCell(4).toString(); // get price product

                ProductEntity product = new ProductEntity();
                product.setProductName(productName);
                product.setProductDesc(desc);
                product.setProductQuantity(Integer.parseInt(quantity.split("\\.")[0]));
                product.setProductPrice(Integer.parseInt(price.split("\\.")[0]));
                product.setWarehouseId(warehouse.get().getId());

                productRepository.save(product);

            }

            return true;


        } catch (Exception e) {
            throw new ErrorHandler(e.getMessage());
        }

    }
}
