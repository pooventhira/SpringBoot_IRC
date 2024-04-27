package poovie.cc1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import poovie.cc1.model.CartModel;
import poovie.cc1.repository.CartRepository;

@Service
public class CartService 
{
    @Autowired
    CartRepository repo;

    // Creating Product
    public CartModel createNewproduct (@NonNull CartModel product) {
        return repo.save(product);
    }
    // Getting All Products
    public List<CartModel> getAllProducts () {
        return repo.findAll();
    }
    // Getting Product By Product Name
    public Optional<CartModel> getProductByName (String productName) {
        return repo.findByProductName(productName);
    }
    // Updating Product
    public CartModel updatProduct (@NonNull String productName, @RequestBody CartModel product) {
        return repo.findByProductName(productName)
        .map(existingproduct -> {
            existingproduct.setProductId(product.getProductId());
            existingproduct.setProductName(product.getProductName());
            return repo.save(existingproduct);
        }).orElseThrow(
            () -> new RuntimeException("product not found in this productName: "+productName)
        );
    }
    // Deleting User
    public void removeUser(@NonNull Integer productId) {
        repo.deleteById(productId);
    }
}