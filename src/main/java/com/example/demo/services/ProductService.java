package com.example.demo.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
@Service
public class ProductService {
    @Autowired
    ProductRepository productrepository;

    public List<Product> getallproduct(){
        return productrepository.getallproduct();
    }

    public void saveproduct(MultipartFile pimage,Product product) {

        if(pimage == null){
            product.setImage(null);
        }else{
            if(uploadImage(pimage)){
                System.out.println("Upload successfully");
            }
            product.setImage( pimage.getOriginalFilename());
        }
        productrepository.save(product);
    }
    private final String UPLOAD_FOLDER = "C:\\Users\\KARTHIK AV\\Downloads\\web-application-for-admin\\src\\main\\resources\\static\\assets\\img";
    public boolean uploadImage(MultipartFile pimage){
        
        boolean isUpload = false;
        try {
            Files.copy(pimage.getInputStream(),
                    Paths.get(UPLOAD_FOLDER + File.separator, pimage.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return isUpload;
    }

    public Optional<Product> findproductbyid(int id) {
      return  productrepository.findById(id);
    }

    public void deleteproduct(int id) {
        productrepository.deletebycond(id);
    }

    public void deleteproductbycat(int id) {
       productrepository.deleteproductbycat(id);
    }

    public void saveproductbyitems(MultipartFile pimage,Product product){
        Product p = productrepository.findById(product.getId());
        if (pimage == null || pimage.isEmpty()) {
            product.setImage(p.getImage());
        }
        else{
            uploadImage(pimage);
            product.setImage(pimage.getOriginalFilename());
        }
        productrepository.save(product);
    }

	public Product getbyid(int pid) {
		return productrepository.getbyid(pid);
	}
}
